package org.academiadecodigo.codezillas.FileServices;

import org.academiadecodigo.codezillas.Utils.Defaults;
<<<<<<< HEAD

import java.io.*;

public abstract class FileTransferer {

=======
import java.io.*;

public abstract class FileTransferer {
>>>>>>> bbe951bf6e496468b9d5d8c7c0fa59c54dc3baaa
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

                outputStream.writeObject(new FileData(data, name));
                outputStream.flush();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void download(ObjectInputStream inputStream ){

        try {

            System.out.println("Download is starting...");


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
