package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.codezillas.Client.ClientRequest;
import org.academiadecodigo.codezillas.Utils.Commands;

public class RequestHandler {

    private int menuPosition;

    public RequestHandler() { }

    public ServerRequest handleStart(ClientRequest clientRequest){

        InputScanner scanner = null;

        String command = clientRequest.getCommand();
        String answerString = clientRequest.getAnswerString();
        int answerInt = clientRequest.getAnswerInt();

        ServerRequest serverRequest = handleRequest(command, answerString, answerInt);

        return serverRequest;
    }

    private ServerRequest handleRequest(String command, String answerString, int answerInt) {
        if (command.equals(Commands.MENU) && answerString == null && answerInt == 0) {
            menuPosition = 0;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.loginRegisterMenu());
        }

        if (command.equals(Commands.MENU) && answerString == null && answerInt == 1 && menuPosition == 0) {
            menuPosition = 1;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.clientMenu());
        }

        if (command.equals(Commands.MENU) && answerString == null && answerInt == 1 && menuPosition == 1) {
            menuPosition = 0;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.uploadMenu());
        }

        if (command.equals(Commands.MENU) && answerString == null && answerInt == 2 && menuPosition == 1) {
            menuPosition = 2;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.downloadMenu(new String[]{"1", "2"}));
        }

        if (command.equals(Commands.MENU) && answerString == null && answerInt == 3 && menuPosition == 1) {
            menuPosition = 2;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.transferFileMenu(new String[]{"3","4"}));
        }

        if (command.equals(Commands.MENU) && answerString == null && answerInt == 1 && menuPosition == 2) {
            menuPosition = 0;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.acceptConnectionMenu("Yo BRO"));
        }
        return null;
    }
}
