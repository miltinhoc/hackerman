package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.codezillas.Client.ClientRequest;
import org.academiadecodigo.codezillas.Utils.Commands;
import org.academiadecodigo.codezillas.Utils.NavigationPossibilities;
import org.academiadecodigo.codezillas.Utils.NavigationPossibilitiesType;
import org.academiadecodigo.codezillas.Utils.NavigationUtils;

import java.util.Map;

public class RequestHandler {

    private NavigationPossibilitiesType navigationPossibilitiesType;
    private Map<NavigationPossibilitiesType, NavigationPossibilities> possibilitesMap;

    public RequestHandler() {
        NavigationUtils.initMap();
        possibilitesMap = NavigationUtils.menuMap;
    }

    public ServerRequest handleRequest(ClientRequest clientRequest){

        String command = clientRequest.getCommand();
        ServerRequest serverRequest = null;

        switch (command){

            case Commands.INT:
                serverRequest = analyzeIntAnswer(clientRequest.getAnswerInt());
                break;

            case Commands.STRING:

                //TODO: add logic
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
        navigationPossibilitiesType = NavigationPossibilitiesType.INITIAL_MENU;
        return new ServerRequest(Commands.MENU, Navigation.clientMenu());
    }

}


