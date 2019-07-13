package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.codezillas.Client.ClientRequest;

public class RequestHandler {

    private InputScanner lastInput;

    public Object handleStart(ClientRequest clientRequest){

        InputScanner scanner = null;

        String command = clientRequest.getCommand();
        String answerString = clientRequest.getAnswerString();
        int answerInt = clientRequest.getAnswerInt();

        if (lastInput == null) {
            scanner = Navigation.loginRegisterMenu();
            lastInput = scanner;
            return scanner;
        }

        if (lastInput.equals(Navigation.loginRegisterMenu())) {
            scanner = Navigation.clientMenu();
            lastInput = scanner;
            return scanner;
        }

        return Navigation.loginRegisterMenu();
    }
}
