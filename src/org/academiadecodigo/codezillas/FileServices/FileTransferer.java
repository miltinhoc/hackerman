package org.academiadecodigo.codezillas.FileServices;

import org.academiadecodigo.codezillas.Utils.Defaults;

import java.io.*;

public abstract class FileTransferer {

    /**
     *
     * @param outputStream
     * @param container
     */
    public static void upload(ObjectOutputStream outputStream, FileContainer container){

        if (container.getFile() != null){

            try {

                File file = container.getFile();

                FileInputStream reader = new FileInputStream(file);

                String name = file.getName();
                byte[] data = reader.readAllBytes();

                System.out.println("uploading");
                outputStream.writeObject(new FileData(data, name));
                outputStream.flush();
                System.out.println("uploaded");

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     *
     * @param inputStream
     * @param path
     * @return
     */
    public static void download(ObjectInputStream inputStream, String path){

        try {

            System.out.println("Starting download");


            FileData fileData = (FileData) inputStream.readObject();

            FileOutputStream fileOutputStream = new FileOutputStream(Defaults.CLIENT_ROOT + fileData.getName());

            fileOutputStream.write(fileData.getData());
            fileOutputStream.flush();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e){
            System.err.println("Class not found");
        }
    }
}
