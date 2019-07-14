package org.academiadecodigo.codezillas.FileServices;

import org.academiadecodigo.codezillas.Utils.Defaults;

import java.io.*;
import java.nio.file.Path;

public abstract class FileManager {

    /**
     *
     * @param file String of the wanted path to save the file
     */
    public static void saveFile(File file){

        try {

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileOutputStream fileOutputStream = new FileOutputStream(Defaults.ROOT + "gg.txt");

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

    /**
     *
     * @param filepath String of the path to the file
     * @return a File if exists, null if doesn't exist
     */
    public static File loadFile(String filepath){

        File file = new File(filepath);

        if (file.exists()){
            return file;
        }
        return null;
    }

    /**
     *
     * @param receivedPath String
     * @return true if Directory exists, false if doesn't exist
     */
    public boolean pathExists(String receivedPath){

        File path = new File(receivedPath);
        return path.isDirectory();
    }

    public static void choosePath(String path){

    }

    /**
     *
     * @param path String name of the directory to create
     */
    public static void createDirectory(String path){

        File directory = new File(path);
        if (!directory.exists()){
            boolean result = directory.mkdir();
        }
    }

    /**
     *
     * @return Array of String if not null
     */
    public static String[] listAllPathContent(String path){

        File newPath = new File(Defaults.ROOT + path);
        File[] files = newPath.listFiles();

        if (files != null){
            int size = files.length;
            String[] content = new String[size];

            for (int i = 0; i < size; i++) {

                if (files[i].isDirectory()){
                    content[i] = String.format("/%s", files[i].getName());
                    continue;
                }
                content[i] = files[i].getName();
            }
            return content;
        }
        return null;
    }


}
