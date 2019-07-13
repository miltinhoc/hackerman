package org.academiadecodigo.codezillas.Test;

import org.academiadecodigo.codezillas.Client.Client;
import org.academiadecodigo.codezillas.Client.ClientPrompt;

public abstract class Test {

    //Create tests at will or more test classes inside this package
    public static void main(String[] args) {

        Client client = new Client();

        ClientPrompt clientPrompt = new ClientPrompt();
        clientPrompt.setPeer(client);

        clientPrompt.runClientPrompt();

    }
}
