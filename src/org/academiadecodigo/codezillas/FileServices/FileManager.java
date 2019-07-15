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
            FileOutputStream fileOutputStream = new FileOutputStream(Defaults.SERVER_ROOT + "gg.txt");

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

    public static String[] listAllFiles(){
        File path = new File(Defaults.SERVER_ROOT);
        File[] files = path.listFiles();
        int count = 0;

        String[] content = null;

        if (files != null){
            for (File file : files) {
                if (file.isFile()) {
                    count++;
                }
            }

            content = new String[count];
            for (int i = 0; i < count; i++) {
                if (files[i].isFile()){
                    content[i] = files[i].getName();
                }
            }
        }
        return content;
    }

}
