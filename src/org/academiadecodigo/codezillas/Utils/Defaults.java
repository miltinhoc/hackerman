package org.academiadecodigo.codezillas.Utils;

public class Defaults {

    public static final int CLIENT_PORT = 9001;
    public static final int SERVER_PORT = 43069;
    public static final String ROOT = "home";

    public static final String CONNECTION_ERROR = "Server failed to start.";
    public static final String CLIENT_CONNECT_ERROR = "Failed to connect to client!";
    public static final String CONNECTION_OK = "Successfully connected to client: ";

    public static String userDetails(String username, String ip){

        return "CLIENT NICKNAME SETUP: OK\n " +
                "\nClient nickname is " + username
                +"\nIP is " + ip + "\n";
    }

    public static int rng(){return (int) ((Math.random() * 10000) + 1000);}



}
