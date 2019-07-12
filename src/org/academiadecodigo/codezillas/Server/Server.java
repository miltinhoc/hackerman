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
    }

    public void start(){

        try {
            serverSocket = new ServerSocket(Defaults.SERVER_PORT);

        } catch (IOException e) {
            System.err.println(Defaults.CONNECTION_ERROR);
            e.printStackTrace();
        }
    }

    public void waitForConnections(){

        while (true){

            try {

                System.out.println(Defaults.CONNECTION_OK + serverSocket.getInetAddress() + serverSocket.getLocalPort());

                servicePool.submit(new ClientHandler(serverSocket.accept()));

            } catch (IOException e) {
                System.err.println(Defaults.CONNECTION_ERROR);
                e.printStackTrace();
            }
        }
    }

    public class ClientHandler implements Runnable{

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

        }

        public void setup(){
            //prompt getnickname;
            nickname = "Anon" + Defaults.rng();
            clientList.put(nickname, this);
        }

        public void setupStream(){
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println();
                e.printStackTrace();
            }
        }

        public void handle() throws IOException{
            synchronized (this){
                while(true){
                    //TODO: Client-server API goes in here.

                    String receivedCommand;

                    receivedCommand = reader.readLine();

                    if(false){
                        return;
                    }
                    
                }
            }
        }

        public void close(){
            try {
                clientList.remove(this.nickname);
                client.close();
                System.err.println("Client exited");
                System.exit(0);

            } catch (IOException e) {
                System.out.println("System exiting error");
                e.printStackTrace();
            }
        }
    }

}
