package org.academiadecodigo.codezillas.Client;

import java.io.File;
import java.net.Socket;

public class Client implements Peer {

    private Socket socket;
    private String nickname;

    public void connecoToServer(){};
    public void connectToPeer(){};
    public void getIP(){};

    @Override
    public void write() {

    }

    @Override
    public void read() {

    }

    @Override
    public void saveFile(File file) {

    }

    @Override
    public void shutdown() {

    }
}
