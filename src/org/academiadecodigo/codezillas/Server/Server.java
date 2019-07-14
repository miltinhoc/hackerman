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
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService servicePool;
    private Map<String, ClientHandler> clientList;
    private Socket connectionSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

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
                connectionSocket = serverSocket.accept();
                System.out.println("Connection established");
                inputStream = new ObjectInputStream(connectionSocket.getInputStream());
                outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
                System.out.println("Streams oppened");

                servicePool.submit(new ClientHandler(connectionSocket, inputStream, outputStream));
                System.out.println(Defaults.CONNECTION_OK); //TODO: Include client IP and Port

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

        public ClientHandler(Socket client, ObjectInputStream inputStream, ObjectOutputStream outputStream){
            this.client = client;
            requestHandler = new RequestHandler();
            this.inputStream = inputStream;
            this.outputStream = outputStream;
        }

        @Override
        public void run() {

            //setupStream();

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
                        "Bro" + Defaults.rng() : clientRequest.getAnswerString();

                validateLogin(nickname);

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("User " + nickname + " disconnected abruptly");
                close();
            }

            logged = true;

            System.out.println(Defaults.userDetails(nickname, client.getInetAddress().toString()));
        }

        public void validateLogin(String nickname) throws IOException, ClassNotFoundException{

            while(clientList.containsKey(nickname)){
                respondRequest(requestHandler.invalidNickname());
                ClientRequest clientRequest = (ClientRequest) inputStream.readObject();
                nickname = clientRequest.getAnswerString();
            }

            clientList.put(nickname, this);

        }

        public void handle() throws IOException{

            System.out.println(Defaults.HANDLING_CLIENT);
            while(true){
                try {

                    if(!logged){
                        login();
                        respondRequest(requestHandler.initMenu(nickname));
                    }

                    ClientRequest clientRequest = (ClientRequest) inputStream.readObject();
                    System.out.println(clientRequest.getAnswerString());
                    respondRequest(requestHandler.handleRequest(clientRequest, inputStream));


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (EOFException EOFE){
                    System.err.println("User " + nickname + " disconnected abruptly");
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

        public String[] loggedClients(){

            Set<String> nicknames = clientList.keySet();
            String[] clientsLoggedIn = new String[nicknames.size()];

            int counter = 0;

            for (String n: nicknames) {                     //TODO: change to Array method
                clientsLoggedIn[counter] = n;
                counter++;
            }

            return clientsLoggedIn;
        }

    }

}
