package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.codezillas.FileServices.FileContainer;
import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.Utils.Commands;
import org.academiadecodigo.codezillas.Utils.Defaults;
import java.io.*;
import java.net.Socket;

public class Client extends Peer {

    private Socket serverSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private PromptHandler promptHandler;

    public Client() {
        promptHandler = new PromptHandler();
    }

    public void start() {

        init();
        serverCommunication();
        shutdown();

    }

    private void init() {
        connectToServer();
    }

    private void connectToServer() {

        try {
            serverSocket = new Socket("192.168.1.125", Defaults.SERVER_PORT);
            setUpStreams();
        } catch (IOException e) {
            System.err.println("Something went wrong while trying to connect to the server");
        }

    }

    private void setUpStreams() {

        try {

            outputStream = new ObjectOutputStream(serverSocket.getOutputStream());
            inputStream = new ObjectInputStream(serverSocket.getInputStream());

        } catch (IOException ex){
            System.err.println("Something went Wrong while opening Client Streams");
        }
    }

    private void serverCommunication() {

        while (serverSocket.isBound()) {

            String[] command = promptHandler.handleRequests(inputStream, outputStream);
            System.out.println(command[0]);
            switch (command[0]) {

                case Commands.DOWNLOAD:

                    super.download(inputStream);

                    try {
                        outputStream.writeObject(new ClientRequest(Commands.MENU, "yes"));
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case Commands.UPLOAD:
                    uploadToServer(command[1]);
                    break;
            }
        }
    }

    private void uploadToServer(String path) {
        System.out.println("Upload starting... \n");
        File file = fileToUpload(path);
        super.write(new FileContainer(file), outputStream);
    }

    private File fileToUpload(String path) {

        return FileManager.loadFile(path);
    }

    public void shutdown() {

        try {
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
