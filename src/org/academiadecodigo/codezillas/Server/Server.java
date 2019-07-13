package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.codezillas.Utils.Defaults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            }
        }
    }

    //package-private
    String[] getActiveClientsNames(){
        return clientList.keySet().toArray(new String[0]);
    }

    private class ClientHandler implements Runnable{

        private Socket client;
        private BufferedReader reader;
        private PrintWriter writer;
        private String nickname;

        public ClientHandler(Socket client){
            this.client = client;
        }

        @Override
        public void run() {

            setup();
            setupStream();

            try {
                handle();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void setup(){
            //prompt getnickname;
            nickname = "Anon" + Defaults.rng();
            clientList.put(nickname, this);
            System.out.println("CLIENT NICKNAME SETUP: OK");
        }

        public void setupStream(){
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println();
                e.printStackTrace();
            }
            System.out.println("CLIENT STREAMS SETUP: OK");
        }

        public void handle() throws IOException{

                while(true){
                    System.out.println("HANDLING CLIENT: OK");
                    //TODO: Client-server API goes in here.

                    String receivedCommand;

                    System.out.println(receivedCommand = reader.readLine());
                    writer.println("ok bro");
                    if(receivedCommand.equals("/exit")){
                        close();
                    }
                }
        }


        public void close(){
            try {
                clientList.remove(this.nickname);
                client.close();
                System.out.println("CLIENT " + nickname + " DISCONNECT: OK");
                System.exit(0);

            } catch (IOException e) {
                System.err.println("System exiting error");
                e.printStackTrace();
            }
        }
    }

}
