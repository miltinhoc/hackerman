package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.codezillas.Connectable;
import org.academiadecodigo.codezillas.FileServices.FileContainer;
import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.Utils.Commands;
import org.academiadecodigo.codezillas.Utils.Defaults;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client extends Peer implements Connectable {

    private Socket serverSocket;
    private Socket peerSocket;
    private String nickname;
    private Host host;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private PromptHandler promptHandler;


    public Client(String nickname) {
        this.nickname = nickname;
        host = new Host();
        promptHandler = new PromptHandler();
    }

    public void start() {

        init();
        serverCommunication();

    }

    private void init() {
        connectToServer();
    }

    private void connectToServer() {

        try {

            serverSocket = new Socket("localhost", Defaults.SERVER_PORT);
            setUpStreams();
        } catch (IOException e) {
            System.err.println("Something went wrong while trying to connect to the server");
        }

    }

    private void setUpStreams() {

        try {

            outputStream = new ObjectOutputStream(serverSocket.getOutputStream());
            inputStream = new ObjectInputStream(serverSocket.getInputStream());
            System.out.println("Streams opened"); //TODO: erase when debugging is done

        } catch (IOException ex){
            System.err.println("Something went Wrong while opening Client Streams");
        }
    }




    private void serverCommunication() {

        while (serverSocket.isBound()) {

            String[] command = promptHandler.handleRequests(inputStream, outputStream);
            System.out.println("entrei no client");
            System.out.println(command[0]);
            switch (command[0]) {

                case Commands.IP:
                    peerToPeerTransfer(command[1]);
                    break;

                case Commands.RECEIVE_FILE:
                    host.start(command[1]);

                case Commands.DOWNLOAD:
                    //download(command[1]);
                    break;

                case "upload":
                    System.out.println("entrei no switch upload");
                    uploadToServer(command[1]);
                    break;
            }
        }
    }

    private void peerToPeerTransfer(String ip) {

            connectToPeer(ip);
            //sendToPeer();

    }

    private void connectToPeer(String ip) {

        try {
            peerSocket = new Socket(ip, Defaults.CLIENT_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void uploadToServer(String path) {
        System.out.println("starting to upload");
        File file = fileToUpload(path);
        super.write(new FileContainer(file), outputStream);
    }

    private File fileToUpload(String path) {

        return FileManager.loadFile(path);
    }

   /* private void sendToPeer() {

        File file = fileToUpload();
        super.write(file, peerSocket);

    } */

    //private void download(String path) {
    //    super.download(path, serverSocket);
    //}

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

    private class Host extends Peer implements Connectable {

        private ServerSocket serverSocket;
        private Socket connectionSocket;

        public void start(String savePath) {
            initSocket();
            awaitConnection();
            //File file = receiveFile(savePath);
            //saveFile(file);
            shutdown();
        }

        private void initSocket() {
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

        //public File receiveFile(String path) {
        //    return super.download(path, connectionSocket);
        //}

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
