package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;

public class ServerRequest {

    private String command;
    private String ip;
    private InputScanner inputScanner;

    public ServerRequest(String command, InputScanner inputScanner) {
        this.command = command;
        this.inputScanner = inputScanner;
    }

    public ServerRequest(String command, InputScanner inputScanner, String ip) {
        this.command = command;
        this.inputScanner = inputScanner;
        this.ip = ip;
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
