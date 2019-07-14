package org.academiadecodigo.codezillas.Utils;

public class Defaults {

    public static final int CLIENT_PORT = 9001;
    public static final int SERVER_PORT = 43069;
    public static final String ROOT = "home";

    public static final String CONNECTION_ERROR = "Server failed to start.";
    public static final String CLIENT_CONNECT_ERROR = "Failed to connect to client!";
    public static final String CONNECTION_OK = "Sucessfully connected to client: ";

    public static int rng(){return (int) ((Math.random() * 10000) + 1000);}

    public static final String BROS = "    ____  ____  ____  _____    ____       __               ____                     _____            __               \n" +
            "   / __ )/ __ \\/ __ \\/ ___/   / __ \\___  / /___ ___  __   / __ \\_   _____  _____   / ___/__  _______/ /____  ____ ___ \n" +
            "  / __  / /_/ / / / /\\__ \\   / /_/ / _ \\/ / __ `/ / / /  / / / / | / / _ \\/ ___/   \\__ \\/ / / / ___/ __/ _ \\/ __ `__ \\\n" +
            " / /_/ / _, _/ /_/ /___/ /  / _, _/  __/ / /_/ / /_/ /  / /_/ /| |/ /  __/ /      ___/ / /_/ (__  ) /_/  __/ / / / / /\n" +
            "/_____/_/ |_|\\____//____/  /_/ |_|\\___/_/\\__,_/\\__, /   \\____/ |___/\\___/_/      /____/\\__, /____/\\__/\\___/_/ /_/ /_/ \n" +
            "                                              /____/                                  /____/                          ";
}
