package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.codezillas.Utils.Defaults;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

public class Host implements Peer {

    private ServerSocket serverSocket;
    private static final int PORT = Defaults.CLIENT_PORT;

    public Socket acceptConnection() {
        return null;
    }

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
