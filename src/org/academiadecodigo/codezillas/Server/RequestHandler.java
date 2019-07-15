package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.codezillas.Client.ClientRequest;
import org.academiadecodigo.codezillas.FileServices.FileContainer;
import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.FileServices.FileTransferer;
import org.academiadecodigo.codezillas.Utils.*;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class RequestHandler {

    private NavigationPossibilitiesType navigationPossibilitiesType;
    private Map<NavigationPossibilitiesType, NavigationPossibilities> possibilitesMap;
    private int downloadChoice = 1;

    public RequestHandler() {
        NavigationUtils.initMap();
        possibilitesMap = NavigationUtils.menuMap;
    }

    public ServerRequest handleRequest(ClientRequest clientRequest, ObjectInputStream inputStream, ObjectOutputStream outputStream){

        String command = clientRequest.getCommand();
        ServerRequest serverRequest = null;

            System.out.println(navigationPossibilitiesType);

        switch (command){

            case Commands.INT:

                if(navigationPossibilitiesType == NavigationPossibilitiesType.DOWNLOAD_MENU){

                    System.out.println("entrar no tipo do download");
                    downloadChoice = clientRequest.getAnswerInt();
                    if(downloadChoice == 1){
                        return initMenu();
                    }
                    return new ServerRequest(Commands.DOWNLOAD);

                }

                serverRequest = analyzeIntAnswer(clientRequest.getAnswerInt());
                break;

            case Commands.STRING:
                serverRequest = analyzeStringAnswer(clientRequest.getAnswerString(), inputStream);
                break;

            case Commands.MENU:
                System.out.println("entrei no menu");
                serverRequest = new ServerRequest(Commands.MENU, Navigation.clientMenu());
                System.out.println(serverRequest.getCommand());
                return serverRequest;

            case Commands.UPLOAD:

                System.out.println("A entrar no upload");
                analyzeDownloadOption(downloadChoice, outputStream);
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

        String[] files = FileManager.listAllFiles();

        for (int i = 0; i < files.length ; i++) {
            files[i] = Defaults.SERVER_ROOT + files[i];
        }

        File file = new File(files[answer - 2]);

        System.out.println("starting upload");

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
                    FileTransferer.download(inputStream, Defaults.SERVER_ROOT);
                    System.out.println("e aqui");
                }
        }
        return initMenu();
    }

    public ServerRequest initMenu(String nickname){
        navigationPossibilitiesType = NavigationPossibilitiesType.INITIAL_MENU;
        return new ServerRequest(Commands.MENU, Navigation.clientMenu(nickname));
    }

    public ServerRequest initMenu(){
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


