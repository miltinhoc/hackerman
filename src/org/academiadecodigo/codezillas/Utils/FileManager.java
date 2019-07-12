package org.academiadecodigo.codezillas.Utils;

import java.io.*;

public abstract class FileManager {

    public static void saveFile(File file){

        try {

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileOutputStream fileOutputStream = new FileOutputStream(Defaults.ROOT + "test.txt");

            byte[] buffer = new byte[1024];
            int numOfBytes = inputStream.read(buffer);

            while (numOfBytes != -1){
                fileOutputStream.write(buffer,0, numOfBytes);
                numOfBytes = inputStream.read(buffer);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static File loadFile(String filepath){

        File file = new File(filepath);

        if (file.exists()){
            return file;
        }
        return null;
    }

    public boolean pathExists(String receivedPath){

        File path = new File(receivedPath);
        return path.isDirectory();
    }

    public static void choosePath(String path){

    }

    public static void createDirectory(String path){

        File directory = new File(path);
        if (!directory.exists()){
            boolean result = directory.mkdir();
        }
    }

}
