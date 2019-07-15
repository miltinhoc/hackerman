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

    private Server.NavigationPossibilitiesType navigationPossibilitiesType = Server.NavigationPossibilitiesType.INITIAL_MENU;
    private Map<Server.NavigationPossibilitiesType, NavigationPossibilities> possibilitiesMap;
    private int downloadChoice = 1;
    private Server server;

    public RequestHandler(Server server) {
        NavigationUtils.initMap(Navigation.onlineClientsMenu(server.getActiveClientsNames()));
        possibilitiesMap = NavigationUtils.menuMap;
        this.server = server;
    }

    public ServerRequest handleRequest(ClientRequest clientRequest, ObjectInputStream inputStream, ObjectOutputStream outputStream) {

        String command = clientRequest.getCommand();
        ServerRequest serverRequest = null;

        switch (command) {


            case Commands.INT:
                Server.NavigationPossibilitiesType.ONLINE_CLIENTS_MENU.setInputScanner(Navigation.onlineClientsMenu(server.getActiveClientsNames()));

                if (navigationPossibilitiesType == Server.NavigationPossibilitiesType.DOWNLOAD_MENU) {

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

                return initMenu();

            case Commands.UPLOAD:

                analyzeDownloadOption(downloadChoice, outputStream);
                break;
        }

        return serverRequest;

    }

    private Server.NavigationPossibilitiesType[] options() {

        return possibilitiesMap.get(navigationPossibilitiesType).getOptionsType();

    }

    private String[] nextCommands() {
        return possibilitiesMap.get(navigationPossibilitiesType).getNextCommand();
    }

    private ServerRequest analyzeIntAnswer(int answer) {

        Server.NavigationPossibilitiesType[] options = options();
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

            if (answer - 1 == i) {
                command = nextCommands[i];
                System.out.println(command);
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

        System.out.println("starting upload");

        FileTransferer.upload(outputStream, new FileContainer(file));

        System.out.println("upload done");

    }


    private ServerRequest analyzeStringAnswer(String answer, ObjectInputStream inputStream) {


        switch (navigationPossibilitiesType) {

            case UPLOAD_MESSAGE:
                if (answer.equals("yes")) {

                    FileTransferer.download(inputStream, "server");
                }
        }
        return initMenu();
    }

    public ServerRequest initMenu(String nickname) {
        navigationPossibilitiesType = Server.NavigationPossibilitiesType.INITIAL_MENU;
        return new ServerRequest(Commands.MENU, Navigation.clientMenu(nickname));
    }

    public ServerRequest initMenu() {
        navigationPossibilitiesType = Server.NavigationPossibilitiesType.INITIAL_MENU;
        return new ServerRequest(Commands.MENU, Navigation.clientMenu());
    }


    public ServerRequest getNickname() {
        return new ServerRequest(Commands.QUESTION, Navigation.getLogin());
    }

    public ServerRequest invalidNickname() {
        return new ServerRequest(Commands.QUESTION, Navigation.getValidLogin());
    }
}


