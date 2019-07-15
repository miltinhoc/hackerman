package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.codezillas.Client.ClientRequest;
import org.academiadecodigo.codezillas.Utils.ASCII;
import org.academiadecodigo.codezillas.Utils.Defaults;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService servicePool;
    private Map<String, ClientHandler> clientList;

    public Server(){
        servicePool = Executors.newCachedThreadPool();
        clientList = Collections.synchronizedMap(new HashMap<>());

        try {
            serverSocket = new ServerSocket(Defaults.SERVER_PORT);
        } catch (IOException e) {
            System.err.println(Defaults.CONNECTION_ERROR);
            e.printStackTrace();
            System.exit(1); //TODO: replace with initial menu instead of forced quit
        }
    }

    public void start(){
        System.out.println(ASCII.SERVERINTRO + "\n" + Defaults.SERVER_BOOT);
        waitForConnections();
    }

    private void waitForConnections(){

        System.out.println(Defaults.WAITING);

        while (true){

            try {
                servicePool.submit(new ClientHandler(serverSocket.accept()));
                System.out.println(Defaults.NEW_CONNECTION);

            } catch (IOException e) {
                System.err.println(Defaults.CONNECTION_ERROR);
                e.printStackTrace();
                shutdown();
            }
        }

    }

    public void shutdown(){
        try {
            System.err.println(Defaults.SHUTDOWN);
            serverSocket.close();
            System.exit(1);
        } catch (IOException e) {
            System.err.println(Defaults.SHUTDOWN_FAIL);
            e.printStackTrace();
        }
    }

    private String[] getActiveClientsNames(){
        return clientList.keySet().toArray(new String[0]);
    }

    private class ClientHandler implements Runnable{

        private Socket client;
        private ObjectInputStream inputStream;
        private ObjectOutputStream outputStream;
        private String nickname;
        private RequestHandler requestHandler;
        private boolean logged;

        public ClientHandler(Socket client){
            this.client = client;
            setupStream();
            requestHandler = new RequestHandler();
        }

        @Override
        public void run() {

            try {

                handle();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void setupStream(){
            try {
                inputStream = new ObjectInputStream(client.getInputStream());
                outputStream = new ObjectOutputStream(client.getOutputStream());
            } catch (IOException e) {
                System.out.println();
                e.printStackTrace();
            }
            System.out.println(Defaults.STREAMS);
        }

        public void login(){

            respondRequest(requestHandler.getNickname());

            try {

                ClientRequest clientRequest = (ClientRequest) inputStream.readObject();

                nickname = clientRequest.getAnswerString().trim().isEmpty() ?
                        Defaults.NAME : clientRequest.getAnswerString();

                validateLogin(nickname);

            } catch (IOException | ClassNotFoundException e) {
                System.err.println(Defaults.disconnected(nickname));
                close();
            }

            logged = true;

            System.out.println(Defaults.userDetails(nickname, client.getInetAddress().getHostAddress()));
        }

        public void validateLogin(String nickname) throws IOException, ClassNotFoundException{

            while(clientList.containsKey(nickname)){
                respondRequest(requestHandler.invalidNickname());
                ClientRequest clientRequest = (ClientRequest) inputStream.readObject();
                nickname = clientRequest.getAnswerString();
            }

            this.nickname = nickname;
            clientList.put(nickname, this);

        }

        public void handle() throws IOException{

            System.out.println(Defaults.HANDLING_CLIENT);
            while(true){
                try {

                    if(!logged){
                        login();
                        respondRequest(requestHandler.initMenu(nickname));
                        Defaults.printLoggedUsers(getActiveClientsNames());
                    }

                    ClientRequest clientRequest = (ClientRequest) inputStream.readObject();
                    System.out.println(clientRequest.getAnswerString());
                    respondRequest(requestHandler.handleRequest(clientRequest, inputStream, outputStream));

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (EOFException EOFE){
                    System.err.println(Defaults.disconnected(nickname));
                    close();
                    break;
                }
            }

        }

        public void respondRequest(ServerRequest request){

            try {
                outputStream.writeObject(request);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void close(){
            try {
                clientList.remove(this.nickname);
                client.close();
                System.out.println("CLIENT " + nickname + " DISCONNECT: OK");

            } catch (IOException e) {
                System.err.println(Defaults.DISCONNECT_FAIL);
                e.printStackTrace();
            }
        }
    }

}
