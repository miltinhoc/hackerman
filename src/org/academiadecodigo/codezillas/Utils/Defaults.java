package org.academiadecodigo.codezillas.Utils;

public class Defaults {

    public static final int CLIENT_PORT = 9001;
    public static final int SERVER_PORT = 43069;
    public static final String ROOT = "home";

    public static final String CONNECTION_ERROR = "SERVER FAILED TO START";
    public static final String CLIENT_CONNECT_ERROR = "FAILED TO CONNECT TO CLIENT";
    public static final String CONNECTION_OK = "SUCCESSFULLY CONNECTED TO CLIENT";
    public static final String DISCONNECT_FAIL = "FAILED TO DISCONNECT FROM CLIENT";
    public static final String SERVER_BOOT = "SERVER BOOT: OK";
    public static final String STREAMS = "CLIENT STREAMS SETUP: OK";
    public static final String CLIENT_SETUP = "CLIENT SETUP: OK";
    public static final String HANDLING_CLIENT = "HANDLING CLIENT: OK";
    public static final String NEW_CONNECTION = "NEW CONNECTION REQUEST \nESTABLISHING...";
    public static final String SHUTDOWN = "SHUTTING DOWN SERVER";
    public static final String SHUTDOWN_FAIL = "FAILED TO SHUTDOWN SERVER";
    public static final String WAITING = "NOW WAITING FOR CONNECTIONS...";
    public static final String WELCOME = "Welcome to BROS - Bros Relay-Over System";


    public static String userDetails(String nickname, String ip){

        return "CLIENT SETUP: OK\n " +
                "\nUser's IP address is " + ip
                +"\nUser is now authed as: " + nickname + "\n";
    }

    public static int rng(){return (int) ((Math.random() * 10000) + 1000);}



}
