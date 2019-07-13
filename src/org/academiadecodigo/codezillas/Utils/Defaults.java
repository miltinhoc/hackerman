package org.academiadecodigo.codezillas.Utils;

public class Defaults {

    public static final int CLIENT_PORT = 9001;
    public static final int SERVER_PORT = 8080;
    public static final String ROOT = "home";

    public static final String CONNECTION_ERROR = "Server failed to start.";
    public static final String CLIENT_CONNECT_ERROR = "Failed to connect to client!";
    public static final String CONNECTION_OK = " Sucessfully connected to client: ";

    public static int rng(){return (int) ((Math.random() * 10000) + 1000);}
}
