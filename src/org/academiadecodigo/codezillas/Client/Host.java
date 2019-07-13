package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.codezillas.Utils.Defaults;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

public class Host extends Peer {

    private ServerSocket serverSocket;
    private static final int PORT = Defaults.CLIENT_PORT;

    public Host() {

    }

    @Override
    public void shutdown() {

    }

    /*
    public Socket acceptConnection() {
        return null;
    }

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
