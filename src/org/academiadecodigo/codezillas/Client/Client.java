package org.academiadecodigo.codezillas.Client;

import java.io.File;
import java.net.Socket;

public class Client extends Peer {

    private Socket socket;
    private String nickname;

    public void connecoToServer(){};
    public void connectToPeer(){};
    public void getIP(){};

    public Client() {

    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /*
    @Override
    public void write(File file) {

    }

    @Override
    public void read(File file, String path) {

    }

    @Override
    public void saveFile(File file) {

    }

    @Override
    public void shutdown() {

    }
    */
}
