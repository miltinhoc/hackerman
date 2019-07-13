package org.academiadecodigo.codezillas.Test;

import org.academiadecodigo.codezillas.Client.Client;

public class ClientTester {
    public static void main(String[] args) {
        Client client = new Client("BRO");

        client.start();
        //ClientPrompt clientPrompt = new ClientPrompt();
        //clientPrompt.setPeer(client);
        //clientPrompt.runClientPrompt();

    }

}
