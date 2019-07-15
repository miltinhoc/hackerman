package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.Server.Navigation;
import org.academiadecodigo.codezillas.Server.ServerRequest;
import org.academiadecodigo.codezillas.Utils.Commands;

import java.io.*;
import java.util.Scanner;

class PromptHandler {

    private Prompt prompt;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    PromptHandler() {

            prompt = new Prompt(System.in, System.out);

    }

    String[] handleRequests(ObjectInputStream inputStream, ObjectOutputStream outputStream) {

        this.inputStream = inputStream;
        this.outputStream = outputStream;

        ServerRequest serverRequest = receiveRequest();
        if(serverRequest == null){
            serverRequest = new ServerRequest(Commands.MENU, Navigation.clientMenu());
        }
        String command = serverRequest.getCommand();
        String[] defaultAnswer = {""};

        try {

            switch (command) {

                case Commands.MENU:

                    int answerMenu = menuRequestHandler((MenuInputScanner) serverRequest.getInputScanner());
                    write(new ClientRequest(Commands.INT, answerMenu));
                    break;

                case Commands.QUESTION:

                    String answerQuestion = questionRequestHandler((StringInputScanner) serverRequest.getInputScanner());
                    write(new ClientRequest(Commands.STRING, answerQuestion));
                    break;

                case Commands.RECEIVE_FILE:

                    return ((Commands.RECEIVE_FILE + receiveFileRequestHandler((StringInputScanner) serverRequest.getInputScanner())).split("/"));

                case Commands.DOWNLOAD:

                    String[] pathDownload = (new String[]{Commands.DOWNLOAD});
                    write(new ClientRequest(Commands.UPLOAD, "yes"));
                    return pathDownload;

                case Commands.UPLOAD:

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter path of the desired file: ");
                    String path[] = new String[]{Commands.UPLOAD,scanner.nextLine()};

                    File file = new File(path[1]);

                    if(file.exists()){
                        write(new ClientRequest(Commands.STRING, "yes"));
                        System.out.println("File was sent!");
                        return path;
                    }

                    System.err.println("File doesn't exist");
                    write(new ClientRequest(Commands.STRING, "no"));
                    break;

            }

        } catch (IOException ex){
            System.err.println("Something went wrong while handling The Request");
        }
        return defaultAnswer; //TODO: check if better alternative to return the ip
    }

     private ServerRequest receiveRequest(){

        ServerRequest serverRequest = null;

        try {

            serverRequest = (ServerRequest) inputStream.readObject(); //TODO: check if better alternative of read method

        } catch (IOException e) {
            System.err.println("Something went wrong while receiving the request");
        } catch (ClassNotFoundException e) {
            System.err.println("Class received not found");
        }

        return serverRequest;
    }


    private int menuRequestHandler(MenuInputScanner menuInputScanner) {

        return prompt.getUserInput(menuInputScanner);

    }

    private String questionRequestHandler(StringInputScanner stringInputScanner) {

        return prompt.getUserInput(stringInputScanner);

    }

    private String receiveFileRequestHandler(StringInputScanner stringInputScanner){
        return prompt.getUserInput(stringInputScanner);
    }

    private void write(ClientRequest clientRequest) throws IOException {
        outputStream.writeObject(clientRequest);
        outputStream.flush();
    }

}
