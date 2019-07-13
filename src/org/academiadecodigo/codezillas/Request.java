package org.academiadecodigo.codezillas;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;

import java.io.Serializable;

public class Request implements Serializable {

    private String command;
    private transient InputScanner scanner;

    public Request(String command, InputScanner scanner) {
        this.command = command;
        this.scanner = scanner;
    }

    public String getCommand() {
        return command;
    }

    public InputScanner getScanner() {
        return scanner;
    }
}
