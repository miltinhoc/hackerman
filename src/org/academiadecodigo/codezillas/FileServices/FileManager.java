package org.academiadecodigo.codezillas.FileServices;

import org.academiadecodigo.codezillas.Utils.Defaults;

import java.io.*;

public abstract class FileManager {

    /**
     *
     * @param file String of the wanted path to save the file
     */
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
    public static String[] listAllPathContent(){ //TODO: Differentiate between files and directories

        File file = new File(Defaults.ROOT);
        String[] content = file.list();

        if (content != null){
            return content;
        }
        return null;
    }

}
