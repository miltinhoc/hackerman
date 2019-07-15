package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.codezillas.FileServices.FileTransferer;
import org.academiadecodigo.codezillas.FileServices.FileContainer;

import java.io.*;

public abstract class Peer {

    public void write(FileContainer container, ObjectOutputStream outputStream){

        FileTransferer.upload(outputStream, container);
    }

    public void download(ObjectInputStream inputStream ){

        FileTransferer.download(inputStream);

    }

}
