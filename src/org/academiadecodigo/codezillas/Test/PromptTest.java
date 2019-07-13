package org.academiadecodigo.codezillas.Test;

import org.academiadecodigo.codezillas.Client.Client;
import org.academiadecodigo.codezillas.Client.ClientPrompt;
import org.academiadecodigo.codezillas.Server.Server;

public abstract class PromptTest {

    //Create tests at will or more test classes inside this package
    public static void main(String[] args) {
        Server server = new Server();

        Client client = new Client("boas");

        ClientPrompt clientPrompt = new ClientPrompt();
        clientPrompt.setPeer(client);

        clientPrompt.runClientPrompt();

    }
}
