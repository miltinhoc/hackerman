package org.academiadecodigo.codezillas.FileServices;

import java.io.*;
import java.nio.file.Files;

public abstract class FileTransferer {
    //can be made non-abstract if necessary

    /**
     *
     * @param outputStream
     * @param file
     */
    public static void upload(OutputStream outputStream, File file){

        if (file.exists()){

            try {
                outputStream.write(Files.readAllBytes(file.toPath()));
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
    public static File download(InputStream inputStream, String path){

        try {

            byte[] file = inputStream.readAllBytes();
            FileOutputStream fileOutputStream = new FileOutputStream(path);

            fileOutputStream.write(file);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new File(path);
    }
}
