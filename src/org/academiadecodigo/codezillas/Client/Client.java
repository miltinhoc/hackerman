package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.codezillas.FileServices.FileTransferer;
import org.academiadecodigo.codezillas.Utils.Defaults;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Peer {

    private Socket socket;
    private String nickname;
    private InputStream inputStream;
    private OutputStream outputStream;


    public Client(String nickname){
        this.nickname = nickname;
    }


    public void connectToServer(){

        try {
            socket = new Socket("localhost",Defaults.SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void connectToPeer(){}
    public void getIP(){}

    @Override
    public void write(File file) {

        try {

            outputStream = socket.getOutputStream();
            FileTransferer.upload(outputStream, file);

        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public String getNickname() {
        return nickname;
    }
}
