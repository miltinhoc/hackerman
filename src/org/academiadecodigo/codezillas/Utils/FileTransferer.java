package org.academiadecodigo.codezillas.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public abstract class FileTransferer {
    //can be made non-abstract if necessary

    public static void upload(OutputStream outputStream, File file){

        if (file.exists()){

            try {
                outputStream.write(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static File download(InputStream inputStream, String path){
        return null;
    }

}
