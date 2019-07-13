package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.codezillas.Connectable;
import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.FileServices.FileTransferer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Peer implements Connectable {

    public void write(File file, Socket socket){

        try {

            OutputStream outputStream = socket.getOutputStream();

            FileTransferer.upload(outputStream, file);

            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File download(String path, Socket socket){


                File downloadedFile = null;

                try {

                    InputStream input = socket.getInputStream();

                    downloadedFile = FileTransferer.download(input, path);

                    input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex){
            ex.getMessage();
        }

        return downloadedFile;
    }

    public void saveFile(File file){
        FileManager.saveFile(file);
    }

    public abstract void shutdown();


}
