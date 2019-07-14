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
        System.out.println(ASCII.SERVERINTRO);
        System.out.println("SERVER BOOT: OK");
        waitForConnections();
    }

    private void waitForConnections(){

        System.out.println("Now waiting for connections...");
        while (true){

            try {

                servicePool.submit(new ClientHandler(serverSocket.accept()));
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
            System.out.println("Shutting down server.");
            serverSocket.close();
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Failed to Shutdown Server.");
            e.printStackTrace();
        }
    }

    //package-private
    String[] getActiveClientsNames(){
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
            requestHandler = new RequestHandler();
        }

        @Override
        public void run() {

            setupStream();

            try {
                handle();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void setup(){
            //prompt getnickname;

            clientList.put(nickname, this);
            System.out.println(Defaults.userDetails(nickname, client.getInetAddress().toString()));
        }

        public void setupStream(){
            try {
                inputStream = new ObjectInputStream(client.getInputStream());
                outputStream = new ObjectOutputStream(client.getOutputStream());
            } catch (IOException e) {
                System.out.println();
                e.printStackTrace();
            }
            System.out.println("CLIENT STREAMS SETUP: OK");
        }

        public void login(){

            respondRequest(requestHandler.getNickname());
            logged = true;
            ClientRequest clientRequest = null;

            try {
                clientRequest = (ClientRequest) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            nickname = clientRequest.getAnswerString().trim().isEmpty() ?
                    "Bro" + Defaults.rng() : clientRequest.getAnswerString();

            clientList.put(nickname, this);

            System.out.println("Client nickname is now " + nickname);
            System.out.println(Defaults.userDetails(nickname, client.getInetAddress().toString()));
        }

        public void handle() throws IOException{

            System.out.println("HANDLING CLIENT: OK");
            while(true){
                try {

                    if(!logged){
                        login();
                        respondRequest(requestHandler.initMenu(nickname));
                    }

                    ClientRequest clientRequest = (ClientRequest) inputStream.readObject();
                    respondRequest(requestHandler.handleStart(clientRequest, nickname));
                    System.out.println("nickname");

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
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
                System.err.println("System exiting error");
                e.printStackTrace();
            }
        }
    }

}
