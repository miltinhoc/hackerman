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

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(outputStream));

        int count;
        byte[] buffer = new byte[16*1024];

        try {

            InputStream inputStream = new FileInputStream(file);

            while ( (count = inputStream.read(buffer)) > 0 ){
                out.write(buffer, 0, count);
            }

            out.close();
            inputStream.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

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

            DataInputStream in = new DataInputStream(new BufferedInputStream(inputStream));
            OutputStream outputStream = new FileOutputStream(path);

            byte[] buffer = new byte[16*1024];
            int count;

            while ( (count = in.read(buffer)) > 0 ){
                int a = 1;
                outputStream.write(buffer, 0, count);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new File(path);
    }
}
