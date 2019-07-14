package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.codezillas.Client.ClientRequest;
import org.academiadecodigo.codezillas.FileServices.FileContainer;
import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.FileServices.FileTransferer;
import org.academiadecodigo.codezillas.Utils.Commands;
import org.academiadecodigo.codezillas.Utils.NavigationPossibilities;
import org.academiadecodigo.codezillas.Utils.NavigationPossibilitiesType;
import org.academiadecodigo.codezillas.Utils.NavigationUtils;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class RequestHandler {

    private NavigationPossibilitiesType navigationPossibilitiesType;
    private Map<NavigationPossibilitiesType, NavigationPossibilities> possibilitesMap;

    public RequestHandler() {
        NavigationUtils.initMap();
        possibilitesMap = NavigationUtils.menuMap;
    }

    public ServerRequest handleRequest(ClientRequest clientRequest, ObjectInputStream inputStream, ObjectOutputStream outputStream){

        String command = clientRequest.getCommand();
        System.out.println(command);
        ServerRequest serverRequest = null;

            System.out.println(navigationPossibilitiesType);

        switch (command){


            case Commands.INT:

                if(navigationPossibilitiesType == NavigationPossibilitiesType.DOWNLOAD_MENU){

                    System.out.println("entrar no tipo do download");
                    return new ServerRequest(Commands.DOWNLOAD);

                }

                serverRequest = analyzeIntAnswer(clientRequest.getAnswerInt());
                break;

            case Commands.STRING:
                serverRequest = analyzeStringAnswer(clientRequest.getAnswerString(), inputStream);
                break;

            case Commands.MENU:
                serverRequest = initMenu();
                break;

            case Commands.UPLOAD:
                System.out.println("A entrar no upload");
                analyzeDownloadOption(clientRequest.getAnswerInt(), outputStream);
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
                System.out.println(navigationPossibilitiesType + " 2");
                //initMenu();
            }
        }

        for (int i = 0; i < nextComands.length; i++) {

            if(answer - 1 == i){
                command = nextComands[i];
                System.out.println(navigationPossibilitiesType);
                System.out.println(command);
            }
        }

        return new ServerRequest(command, inputScanner);

    }

    private void analyzeDownloadOption(int answer, ObjectOutputStream outputStream){

        System.out.println("entrei no analiasador da opcao do download");

        String[] files = FileManager.listAllFiles();

        File file = new File(files[0]);

        System.out.println("fui buscar a file, starting upload");

        FileTransferer.upload(outputStream, new FileContainer(file));

        System.out.println("upload done");

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


