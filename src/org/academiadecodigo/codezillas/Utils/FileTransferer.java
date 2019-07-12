package org.academiadecodigo.codezillas.Utils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class FileTransferer {
    //can be made non-abstract if necessary

    public static void upload(OutputStream outputStream, File file){};
    public static File download(InputStream inputStream, String path){return null;}

}
