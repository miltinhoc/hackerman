package org.academiadecodigo.codezillas.Test;

import org.academiadecodigo.codezillas.Client.Client;
import org.academiadecodigo.codezillas.Client.ClientPrompt;
import org.academiadecodigo.codezillas.Server.Server;

public abstract class Test {

    //Create tests at will or more test classes inside this package
    public static void main(String[] args) {
        Server server = new Server();

        Client client = new Client();

        ClientPrompt clientPrompt = new ClientPrompt();
        clientPrompt.setPeer(client);
        clientPrompt.setServer(server);

        clientPrompt.runClientPrompt();

    }
}
