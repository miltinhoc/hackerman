package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.codezillas.Connectable;
import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.FileServices.FileTransferer;
import org.academiadecodigo.codezillas.Request;
import org.academiadecodigo.codezillas.Server.ServerRequest;
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
    private PromptHandler promptHandler;


    public Client(String nickname) {
        this.nickname = nickname;
        host = new Host();
    }

     public void start(){

        init();
        serverCommunication();

    }

    private void init(){
        connectToServer();
        initPromptHandler(serverSocket);
    }

    public void connectToServer(){

        try {
            serverSocket = new Socket("localhost",Defaults.SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initPromptHandler(Socket socket){
        promptHandler = new PromptHandler(socket);
    }

    /*public void initP2PTransfer(String nickname, File file){

        Thread thread = new Thread(() -> peerToPeerTransfer(nickname, file));

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    /*private void initNotificationHandler(){

        Thread thread = new Thread(() -> notificationHandler());

        thread.start();

    }*/

    private void peerToPeerTransfer(String nickname, File file){

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


    private void serverCommunication() {

        while (serverSocket.isBound()) {

           // String ip = promptHandler.handleRequests();

          //  if(!ip.equals("")){
            //    host.start(Defaults.ROOT); //TODO: check path name


            try {
                //DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(serverSocket.getInputStream()));

                //OutputStream outputStream = new FileOutputStream("gg.txt");
                //outputStream.write(dataInputStream.readAllBytes());

                //outputStream = new FileOutputStream("gg.txt");
                /*byte[] buffer = new byte[16*1024];

                int count;

                while ( (count = dataInputStream.read(buffer)) > 0 ){
                    outputStream.write(buffer, 0, count);
                }*/

                FileTransferer.download(serverSocket.getInputStream(),"home/ola.txt");

                //FileManager.saveFile();
                //FileManager.saveFile(FileTransferer.download(serverSocket.getInputStream(), "gg.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        break;

        }

    }

    private void requestPeerConnection(String nickname){
        writer.println("/ " + nickname); //TODO: Implement /CODE, decide with Alex The Lion
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

    //................................................................................................................//
    //--------------------------------------------> Host class <------------------------------------------------------//
    //................................................................................................................//

    private class Host extends Peer implements Connectable{

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
