package org.academiadecodigo.codezillas.Client;

import java.io.Serializable;

public class ClientRequest implements Serializable {

    private String command;
    private int answerInt;
    private String answerString;

    public ClientRequest(String command, int answerInt){
        this.command = command;
        this.answerInt = answerInt;
    }

    public ClientRequest(String command, String answerString){
        this.command = command;
        this.answerString = answerString;
    }

    public String getCommand() {
        return command;
    }

    public int getAnswerInt() {
        return answerInt;
    }

    public String getAnswerString() {
        return answerString;
    }
}
