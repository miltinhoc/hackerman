package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.codezillas.Client.ClientRequest;
import org.academiadecodigo.codezillas.Utils.Commands;
import org.academiadecodigo.codezillas.Utils.NavigationPossibilites;
import org.academiadecodigo.codezillas.Utils.NavigationPossibilitesType;
import org.academiadecodigo.codezillas.Utils.NavigationUtils;

import java.util.Map;

public class RequestHandler {

    private NavigationPossibilitesType navigationPossibilitesType;
    private Map<NavigationPossibilitesType, NavigationPossibilites> possibilitesMap;

    public RequestHandler() {
        NavigationUtils.initMap();
        possibilitesMap = NavigationUtils.menuMap;
    }

    public ServerRequest handleRequest(ClientRequest clientRequest){

        String command = clientRequest.getCommand();
        ServerRequest serverRequest = null;

        switch (command){

            case Commands.INT:
                analyzeIntAnswer(clientRequest.getAnswerInt());
                break;

            case Commands.STRING:

                //TODO: add logic
                break;

        }
        return serverRequest;
    }

    private NavigationPossibilitesType[] options(){

        return possibilitesMap.get(navigationPossibilitesType).getOptionsType();

    }

    private String[] nextCommands(){
        return possibilitesMap.get(navigationPossibilitesType).getNextCommand();
    }

    private ServerRequest analyzeIntAnswer(int answer){

         NavigationPossibilitesType[] options = options();
         String[] nextComands = nextCommands();

         InputScanner inputScanner = null;
         String command = "";

        for (int i = 0; i < options.length ; i++) {

            if(answer - 1 == i){
                inputScanner = options[i].getInputScanner();
            }
        }

        for (int i = 0; i < nextComands.length; i++) {

            if(answer - 1 == i){
                command = nextComands[i];
            }
        }

        return new ServerRequest(command, inputScanner);

    }

    public ServerRequest initMenu(){
        navigationPossibilitesType = NavigationPossibilitesType.INITIAL_MENU;
        return new ServerRequest(Commands.MENU, Navigation.clientMenu());
    }

}


