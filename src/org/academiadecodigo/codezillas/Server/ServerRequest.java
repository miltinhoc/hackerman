package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.codezillas.FileServices.FileContainer;

import java.io.Serializable;

public class ServerRequest implements Serializable {

    private String command;
    private String ip;
    private InputScanner inputScanner;

    ServerRequest(String command, InputScanner inputScanner) {
        this.command = command;
        this.inputScanner = inputScanner;
    }


    ServerRequest(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public InputScanner getInputScanner() {
        return inputScanner;
    }

    public String getIp() {
        return ip;
    }
}
