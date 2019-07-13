package org.academiadecodigo.codezillas.Test;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.Client.Client;
import org.academiadecodigo.codezillas.Client.ClientPrompt;
import org.academiadecodigo.codezillas.Server.Server;

public abstract class Test {

    //Create tests at will or more test classes inside this package
    public static void main(String[] args) {

        Server server = new Server();

        Client client = new Client("Goncalo");

        ClientPrompt clientPrompt = new ClientPrompt();
        clientPrompt.setPrompt();

        //clientPrompt.setStreams(server, server);

        clientPrompt.runClientPrompt();

    }
}
