package org.academiadecodigo.codezillas.FileServices;

import java.io.Serializable;

public class FileData implements Serializable {

    private byte[] data;
    private String name;

    public FileData(byte[] data, String name){
        this.data = data;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }
}
