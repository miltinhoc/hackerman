package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.Server.ServerRequest;
import org.academiadecodigo.codezillas.Utils.Commands;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PromptHandler {

    private Prompt prompt;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;


    public PromptHandler(Socket socket){

        try {

            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            prompt = new Prompt(System.in, System.out);

        } catch (IOException e) {
            System.err.println("Something went wrong while opening the Client Streams");
        }
    }

    public String handleRequests(ServerRequest serverRequest) {

        String command = serverRequest.getCommand();
        String ip;

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

                    int connectionAnswer = connectionRequestHandler((MenuInputScanner) serverRequest.getInputScanner());
                    write(new ClientRequest(Commands.INT, connectionAnswer));
                    break;

                case Commands.IP:
                    ip = serverRequest.getIp();

                   if(ip.equals("")){
                       System.out.println("Destination User refused to connect"); //TODO: check message
                   }
                    return ip;
            }

        } catch (IOException ex){
            System.err.println("Something went wrong while handlingTheRequest");
        }
        return ""; //TODO: check if better alternative to return the ip
    }

    private ServerRequest eceiveRequest(){

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

    private void write(ClientRequest clientRequest) throws IOException {
        outputStream.writeObject(clientRequest);
        outputStream.flush();
    }

}
