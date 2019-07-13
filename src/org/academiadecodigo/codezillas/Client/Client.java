package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.codezillas.Connectable;
import org.academiadecodigo.codezillas.Utils.Defaults;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Peer implements Connectable {

    private Socket serverSocket;
    private Socket peerSocket;
    private String nickname;
    private BufferedReader reader;
    private PrintWriter writer;
    private Host host;


    public Client(String nickname) {
        this.nickname = nickname;
        host = new Host();
    }

     public void start(){

        connectToServer();
        notificationHandler();

    }

    public void connectToServer(){

        try {
            serverSocket = new Socket("localhost",Defaults.SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initP2PTransfer(String nickname, File file){ //TODO: Check join Logic with Milton

        Thread thread = new Thread(() -> peerTooPeerTransfer(nickname, file));

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initNotificationHandler(){

        Thread thread = new Thread(() -> notificationHandler());

        thread.start();

        //TODO: implement joins logic

    }

    private void peerTooPeerTransfer(String nickname, File file){

        requestPeerConnection(nickname);

        try {

            System.out.println("Waiting for a confirmation..."); //TODO: Check message; defaults?
            String answer = reader.readLine();

            if (answer.toLowerCase().equals("no")){
                System.out.println("The other client refused the connection."); //TODO: Check message; defaults?
                return;
            }

            connectToPeer(answer);
            writeToPeer(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void notificationHandler() {

        setUpReaderStreams();
        Scanner scanner = new Scanner(System.in);
        String notification = "";
        String answer = "";

        while (serverSocket.isBound()) {

            try {

                notification = reader.readLine();
                System.out.println(notification);

                answer = scanner.nextLine();
                writer.println(answer);

                System.out.println("Where do you want to Save the file?"); //TODO: change the message; defaults?
                String savePath = reader.readLine();

                if (answer.toLowerCase().equals("yes")) {
                    host.start(savePath);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void requestPeerConnection(String nickname){
        writer.println("/CODE " + nickname); //TODO: Implement /CODE, decide with Alex The Lion
    }

    public void connectToPeer(String ip){

        try {
            peerSocket = new Socket(ip, Defaults.CLIENT_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setUpReaderStreams(){

        try {

            reader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            writer = new PrintWriter(serverSocket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToServer(File file) {
        super.write(file, serverSocket);
    }

    public void writeToPeer(File file){
        super.write(file, peerSocket);
    }

    public void download(String path){
        super.download(path, serverSocket);
    }

    @Override
    public void shutdown() {

        try {

            peerSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }


    private class Host extends Peer  implements Connectable{

        private ServerSocket serverSocket;
        private Socket connectionSocket;
        public static final int PORT = Defaults.CLIENT_PORT;

        public void start(String savePath){
            initSocket();
            awaitConnection();
            File file = receiveFile(savePath);
            saveFile(file);
            shutdown();
        }

        private void initSocket(){
            try {
                serverSocket = new ServerSocket(Defaults.CLIENT_PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void awaitConnection() {
            try {
                connectionSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        public File receiveFile(String path) {
            return super.download(path, connectionSocket);
        }

        @Override
        public void saveFile(File file) {
            super.saveFile(file);
        }

        @Override
        public void shutdown() {

            try {
                serverSocket.close();
                connectionSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
