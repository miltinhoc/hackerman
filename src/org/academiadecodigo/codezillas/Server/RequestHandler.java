package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.codezillas.Client.ClientRequest;
import org.academiadecodigo.codezillas.FileServices.FileTransferer;
import org.academiadecodigo.codezillas.Utils.Commands;
import org.academiadecodigo.codezillas.Utils.NavigationPossibilities;
import org.academiadecodigo.codezillas.Utils.NavigationPossibilitiesType;
import org.academiadecodigo.codezillas.Utils.NavigationUtils;

import java.io.ObjectInputStream;
import java.util.Map;

public class RequestHandler {

    private NavigationPossibilitiesType navigationPossibilitiesType;
    private Map<NavigationPossibilitiesType, NavigationPossibilities> possibilitesMap;

    public RequestHandler() {
        NavigationUtils.initMap();
        possibilitesMap = NavigationUtils.menuMap;
    }

    public ServerRequest handleRequest(ClientRequest clientRequest, ObjectInputStream inputStream){

        String command = clientRequest.getCommand();
        System.out.println(command);
        ServerRequest serverRequest = null;

        switch (command){

            case Commands.INT:
                serverRequest = analyzeIntAnswer(clientRequest.getAnswerInt());
                break;

            case Commands.STRING:
                serverRequest = analyzeStringAnswer(clientRequest.getAnswerString(), inputStream);
                break;

        }
        return serverRequest;
    }

    private NavigationPossibilitiesType[] options(){

        return possibilitesMap.get(navigationPossibilitiesType).getOptionsType();

    }

    private String[] nextCommands(){
        return possibilitesMap.get(navigationPossibilitiesType).getNextCommand();
    }

    private ServerRequest analyzeIntAnswer(int answer){

         NavigationPossibilitiesType[] options = options();
         String[] nextComands = nextCommands();

         InputScanner inputScanner = null;
         String command = "";

        for (int i = 0; i < options.length ; i++) {

            if(answer - 1 == i){
                inputScanner = options[i].getInputScanner();
                navigationPossibilitiesType = options[i];
                System.out.println(navigationPossibilitiesType);
            }
        }

        for (int i = 0; i < nextComands.length; i++) {

            if(answer - 1 == i){
                command = nextComands[i];
                System.out.println(command);
            }
        }

        return new ServerRequest(command, inputScanner);

    }

    private ServerRequest analyzeStringAnswer(String answer, ObjectInputStream inputStream){

        System.out.println("entrei no analyzeString");

        switch (navigationPossibilitiesType){

            case UPLOAD_MESSAGE:
                System.out.println("upload");
                if(answer.equals("yes")){

                    System.out.println("cheguei aqui");
                    FileTransferer.download(inputStream, "goncalo.txt");
                    System.out.println("e aqui");
                }
        }
        return initMenu();
    }

    public ServerRequest initMenu(String nickname){
        navigationPossibilitiesType = NavigationPossibilitiesType.INITIAL_MENU;
        return new ServerRequest(Commands.MENU, Navigation.clientMenu(nickname));
    }
    public ServerRequest initMenu( ){
        navigationPossibilitiesType = NavigationPossibilitiesType.INITIAL_MENU;
        return new ServerRequest(Commands.MENU, Navigation.clientMenu());
    }


    public ServerRequest getNickname(){
        return new ServerRequest(Commands.QUESTION, Navigation.getLogin());
    }

    public ServerRequest invalidNickname(){
        return new ServerRequest(Commands.QUESTION, Navigation.getValidLogin());
    }
}


