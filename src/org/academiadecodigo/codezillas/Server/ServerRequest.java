package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;

import java.io.Serializable;

public class ServerRequest implements Serializable {

    private String command;
    private InputScanner inputScanner;

    public ServerRequest(String command, InputScanner inputScanner) {
        this.command = command;
        this.inputScanner = inputScanner;
    }

    public ServerRequest(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public InputScanner getInputScanner() {
        return inputScanner;
    }

}
