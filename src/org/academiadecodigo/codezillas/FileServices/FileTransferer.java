package org.academiadecodigo.codezillas.FileServices;

import java.io.*;
import java.nio.file.Files;

public abstract class FileTransferer {
    //can be made non-abstract if necessary

    /**
     *
     * @param outputStream
     * @param container
     */
    public static void upload(ObjectOutputStream outputStream, FileContainer container){

        if (container.getFile() != null){

            try {
                System.out.println("uploading");
                outputStream.writeObject(container);
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

            FileContainer container = (FileContainer) inputStream.readObject();

            File file = container.getFile();

            System.out.println("File downloaded");
            System.out.println(file.getAbsolutePath());


            byte[] data = Files.readAllBytes(file.toPath());

            FileOutputStream fileOutputStream = new FileOutputStream(path);

            fileOutputStream.write(data);
            fileOutputStream.flush();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e){
            System.err.println("Class not found");
        }
    }
}
