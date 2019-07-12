package org.academiadecodigo.codezillas.Client;

import java.io.File;

public interface Peer {

    //change return type if needed.
    void write();
    void read();
    void saveFile(File file);
    void shutdown();

}
