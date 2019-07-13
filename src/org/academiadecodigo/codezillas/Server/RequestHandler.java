package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.Client.ClientRequest;

public class RequestHandler {

    private InputScanner lastInput;

    public InputScanner stringScanner(String message){
        StringInputScanner stringScanner = new StringInputScanner();
        stringScanner.setMessage(message);

        return stringScanner;
    }

    public void intScanner(String message){

    }

    private InputScanner showMenu(String[] options, String message){
        MenuInputScanner menuInput = new MenuInputScanner(options);
        menuInput.setMessage(message);

        return menuInput;
    }

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
        /*
        switch (lastInput) {
            case Navigation.loginRegisterMenu():
                InputScanner i = Navigation.loginRegisterMenu();
        }
        */
        return Navigation.loginRegisterMenu();
    }
}
