package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.codezillas.Connectable;
import org.academiadecodigo.codezillas.FileServices.FileContainer;
import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.FileServices.FileTransferer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Peer implements Connectable {

    public void write(FileContainer container, ObjectOutputStream outputStream){

        //try {

            //OutputStream outputStream = socket.getOutputStream();

            FileTransferer.upload(outputStream, container);

            //outputStream.close();

        //} catch (IOException e) {
         //   e.printStackTrace();
        //}
    }

    public void download(String path, ObjectInputStream inputStream){


                    FileTransferer.download(inputStream, path);

      /*          try {

                    //InputStream input = socket.getInputStream();


                    //input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex){
            ex.getMessage();
        }

        return downloadedFile;*/
    }

    public void saveFile(File file){
        FileManager.saveFile(file);
    }

    public abstract void shutdown();


}
