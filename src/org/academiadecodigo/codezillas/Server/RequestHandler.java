package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.codezillas.Client.ClientRequest;
import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.Utils.Commands;

public class RequestHandler {

    private int menuPosition;

    public RequestHandler() { }

    public ServerRequest handleStart(ClientRequest clientRequest){

        String command = clientRequest.getCommand();
        String answerString = clientRequest.getAnswerString();
        int answerInt = clientRequest.getAnswerInt();

        ServerRequest serverRequest = handleRequest(command, answerString, answerInt);

        return serverRequest;
    }

    private ServerRequest handleRequest(String command, String answerString, int answerInt) {

        if (command.equals(Commands.INT) && answerString == null && answerInt == 1 && menuPosition == 1) {
            menuPosition = 0;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.QUESTION, Navigation.uploadMenu());
        }

        if (command.equals(Commands.INT) && answerString == null && answerInt == 2 && menuPosition == 1) {
            menuPosition = 2;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.downloadMenu(FileManager.listAllPathContent(""))); //TODO:check listAllPaths content method implementation
        }

        if (command.equals(Commands.INT) && answerString == null && answerInt == 3 && menuPosition == 1) {
            menuPosition = 2;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.transferFileMenu(new String[]{"3","4"}));
        }

        if (command.equals(Commands.INT) && answerString == null && answerInt == 1 && menuPosition == 2) {
            menuPosition = 0;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.acceptConnectionMenu("Yo BRO"));
        }
        return null;
    }

    public ServerRequest initMenu() {
        menuPosition = 1;
        System.out.println("RequestHandler: menu position " + menuPosition);
        return new ServerRequest(Commands.MENU, Navigation.clientMenu());
    }

    /*
    public ServerRequest initLoginMenu() {
        menuPosition = 0;
        System.out.println("RequestHandler: menu position " + menuPosition);
        return new ServerRequest(Commands.MENU, Navigation.loginRegisterMenu());
    }
    */
}
