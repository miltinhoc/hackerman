package org.academiadecodigo.codezillas.FileServices;

import java.io.File;
import java.io.Serializable;

public class FileContainer implements Serializable {

    private File file;

    public FileContainer(File file){
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
