package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.Server.ServerRequest;
import org.academiadecodigo.codezillas.Utils.Commands;

import java.io.*;
import java.net.Socket;

public class PromptHandler {

    private Prompt prompt;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;


    public PromptHandler(Socket socket) {

        try {

            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            prompt = new Prompt(System.in, System.out);

        } catch (IOException e) {
            System.err.println("Something went wrong while opening the Client Streams");
        }
    }

    public String[] handleRequests() {

        ServerRequest serverRequest = receiveRequest();

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

                case Commands.CONNECTION:

                    int answerConnection = connectionRequestHandler((MenuInputScanner) serverRequest.getInputScanner());
                    write(new ClientRequest(Commands.INT, answerConnection));
                    break;

                case Commands.RECEIVE_FILE:

                    //TODO: Servidor tem de perguntar onde vou guardar o ficheiro

                    String[] pathReceive = ((Commands.RECEIVE_FILE + receiveFileRequestHandler((StringInputScanner) serverRequest.getInputScanner())).split("/"));
                    return pathReceive;

                case Commands.DOWNLOAD:

                    //TODO: Servidor tem de perguntar onde vou guardar o ficheiro

                    String[] pathDownload = (Commands.DOWNLOAD + downloadRequestHandler((StringInputScanner) serverRequest.getInputScanner())).split("/");
                    return pathDownload;

                case Commands.UPLOAD:

                    //TODO: Servidor tem de perguntar onde quero guardar o ficheiro na cloud

                    String[] pathUpload = (Commands.UPLOAD + uploadRequestHandler((StringInputScanner) serverRequest.getInputScanner())).split("/");
                    write(new ClientRequest(Commands.STRING, pathUpload[2]));
                    return pathUpload;

                case Commands.IP:

                    String[] ip = (Commands.IP + serverRequest.getIp()).split("/");

                   if(ip[1].equals("no")){

                       System.out.println("Destination User refused to connect"); //TODO: check message
                       return defaultAnswer;
                   }
                    return ip;
            }

        } catch (IOException ex){
            System.err.println("Something went wrong while handlingTheRequest");
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

    private int connectionRequestHandler(MenuInputScanner menuInputScanner) {

        return prompt.getUserInput(menuInputScanner);

    }

    private String uploadRequestHandler(StringInputScanner stringInputScanner){
        return prompt.getUserInput(stringInputScanner);
    }

    private String downloadRequestHandler(StringInputScanner stringInputScanner){
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
