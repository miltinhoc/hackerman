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

    private NavigationPossibilitiesType navigationPossibilitiesType = NavigationPossibilitiesType.INITIAL_MENU;
    private Map<NavigationPossibilitiesType, NavigationPossibilities> possibilitesMap;
    private int downloadChoice = 1;

    public RequestHandler() {
        NavigationUtils.initMap();
        possibilitesMap = NavigationUtils.menuMap;
    }

    public ServerRequest handleRequest(ClientRequest clientRequest, ObjectInputStream inputStream, ObjectOutputStream outputStream) {

        String command = clientRequest.getCommand();
        ServerRequest serverRequest = null;

<<<<<<< HEAD
        switch (command) {
=======
        switch (command){
>>>>>>> bbe951bf6e496468b9d5d8c7c0fa59c54dc3baaa

            case Commands.INT:

                if (navigationPossibilitiesType == NavigationPossibilitiesType.DOWNLOAD_MENU) {

                    downloadChoice = clientRequest.getAnswerInt();

                    if (downloadChoice == 1) {
                        return initMenu();
                    }

                    return new ServerRequest(Commands.DOWNLOAD);
                }

                if (clientRequest.getAnswerInt() == 1) {

                    return new ServerRequest(Commands.QUIT);

                }

                serverRequest = analyzeIntAnswer(clientRequest.getAnswerInt());
                break;

            case Commands.STRING:
                serverRequest = analyzeStringAnswer(clientRequest.getAnswerString(), inputStream);
                break;

            case Commands.MENU:
<<<<<<< HEAD

                System.out.println("eu mandei este");
                return initMenu();

            case Commands.UPLOAD:

=======
                serverRequest = new ServerRequest(Commands.MENU, Navigation.clientMenu());
                System.out.println(serverRequest.getCommand());
                return serverRequest;

            case Commands.UPLOAD:
>>>>>>> bbe951bf6e496468b9d5d8c7c0fa59c54dc3baaa
                analyzeDownloadOption(downloadChoice, outputStream);
                break;
        }

        return serverRequest;

    }

    private NavigationPossibilitiesType[] options() {

        return possibilitesMap.get(navigationPossibilitiesType).getOptionsType();

    }

    private String[] nextCommands() {
        return possibilitesMap.get(navigationPossibilitiesType).getNextCommand();
    }

    private ServerRequest analyzeIntAnswer(int answer) {

        NavigationPossibilitiesType[] options = options();
        String[] nextCommands = nextCommands();

        InputScanner inputScanner = null;
        String command = "";

        for (int i = 0; i < options.length; i++) {

            if (answer - 1 == i) {
                inputScanner = options[i].getInputScanner();
                navigationPossibilitiesType = options[i];
            }
        }

        for (int i = 0; i < nextCommands.length; i++) {

<<<<<<< HEAD
            if (answer - 1 == i) {
                command = nextCommands[i];
                System.out.println(command);
=======
            if(answer - 1 == i){
                command = nextComands[i];
>>>>>>> bbe951bf6e496468b9d5d8c7c0fa59c54dc3baaa
            }
        }

        return new ServerRequest(command, inputScanner);

    }

    private void analyzeDownloadOption(int answer, ObjectOutputStream outputStream) {

        String[] files = FileManager.listAllFiles();

        for (int i = 0; i < files.length; i++) {
            files[i] = Defaults.SERVER_ROOT + files[i];
        }

        File file = new File(files[answer - 2]);

        System.out.println("Starting upload");

        FileTransferer.upload(outputStream, new FileContainer(file));

        System.out.println("Upload finished");

    }

<<<<<<< HEAD

    private ServerRequest analyzeStringAnswer(String answer, ObjectInputStream inputStream) {


        switch (navigationPossibilitiesType) {

            case UPLOAD_MESSAGE:
                if (answer.equals("yes")) {

                    FileTransferer.download(inputStream, Defaults.SERVER_ROOT);
=======
    private ServerRequest analyzeStringAnswer(String answer, ObjectInputStream inputStream){

        switch (navigationPossibilitiesType){

            case UPLOAD_MESSAGE:
                System.out.println("Received download request...\n");
                if(answer.equals("yes")){

                    FileTransferer.download(inputStream);
                    System.out.println("Download success.");
>>>>>>> bbe951bf6e496468b9d5d8c7c0fa59c54dc3baaa
                }
        }
        return initMenu();
    }

    public ServerRequest initMenu(String nickname) {
        navigationPossibilitiesType = NavigationPossibilitiesType.INITIAL_MENU;
        return new ServerRequest(Commands.MENU, Navigation.clientMenu(nickname));
    }

<<<<<<< HEAD
    public ServerRequest initMenu() {
=======
    public ServerRequest initMenu(){
>>>>>>> bbe951bf6e496468b9d5d8c7c0fa59c54dc3baaa
        navigationPossibilitiesType = NavigationPossibilitiesType.INITIAL_MENU;
        return new ServerRequest(Commands.MENU, Navigation.clientMenu());
    }

<<<<<<< HEAD

    public ServerRequest getNickname() {
=======
    public ServerRequest getNickname(){
>>>>>>> bbe951bf6e496468b9d5d8c7c0fa59c54dc3baaa
        return new ServerRequest(Commands.QUESTION, Navigation.getLogin());
    }

    public ServerRequest invalidNickname() {
        return new ServerRequest(Commands.QUESTION, Navigation.getValidLogin());
    }
}


