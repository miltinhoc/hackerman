package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.codezillas.Connectable;

import java.io.File;

public abstract class Peer implements Connectable {

    //change return type if needed.
    public static void write(File file) {}
    public static void read(File file, String path) {}
    public void saveFile(File file) {}
    public void shutdown() {}

}
