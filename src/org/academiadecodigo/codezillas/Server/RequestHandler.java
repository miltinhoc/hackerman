package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

public class RequestHandler {

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

}
