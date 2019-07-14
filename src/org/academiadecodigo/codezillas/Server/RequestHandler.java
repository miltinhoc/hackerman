package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.codezillas.Client.ClientRequest;
import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.Utils.Commands;

public class RequestHandler {

    private int menuPosition;
    private String nickname;

    public RequestHandler() { }

    public ServerRequest handleStart(ClientRequest clientRequest, String nickname){

        this.nickname = nickname;
        String command = clientRequest.getCommand();
        String answerString = clientRequest.getAnswerString();
        int answerInt = clientRequest.getAnswerInt();

        return handleRequest(command, answerString, answerInt);
    }

    private ServerRequest handleRequest(String command, String answerString, int answerInt) {

        if (command.equals(Commands.INT) && answerInt == 1 && menuPosition == 1) {
            menuPosition = 0;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.QUESTION, Navigation.uploadMenu());
        }

        if (command.equals(Commands.INT) && answerInt == 2 && menuPosition == 1) {
            menuPosition = 2;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.downloadMenu(FileManager.listAllPathContent(""))); //TODO:check listAllPaths content method implementation
        }

        if (command.equals(Commands.INT) && answerInt == 3 && menuPosition == 1) {
            menuPosition = 2;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.transferFileMenu(FileManager.listAllPathContent(""))); //TODO: get all files available to download
        }

        if (command.equals(Commands.INT) && answerInt == 1 && menuPosition == 2) {
            menuPosition = 0;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.acceptConnectionMenu(nickname)); //TODO: list of all files on the server
        }

        if (command.equals(Commands.STRING) && answerString != null && menuPosition == 2){
            menuPosition = 1;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.uploadMenu());
        }

        if (command.equals(Commands.IP) && answerString != null){
            menuPosition = 1;
            System.out.println("RequestHandler: menu position " + menuPosition);
            return new ServerRequest(Commands.MENU, Navigation.acceptConnectionMenu(nickname));
        }
        return null;
    }

    public ServerRequest initMenu() {
        menuPosition = 1;
        System.out.println("RequestHandler: menu position " + menuPosition);
        return new ServerRequest(Commands.MENU, Navigation.clientMenu());
    }

}
