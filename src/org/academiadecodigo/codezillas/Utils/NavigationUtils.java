package org.academiadecodigo.codezillas.Utils;

public class NavigationUtils {

    public static String WELCOME_MESSAGE = ASCII.CLIENTINTRO + "\n" + "Welcome to <BROS Relay Oriented System >";

    public static String SET_NICKNAME_MESSAGE = "Choose Nickname: ";

    public static String SELECT_MENU_MESSAGE = "Logged in as ";
    public static String[] SELECT_MENU_OPTION = {"Upload File", "Download File", "Direct File Transfer"};

    public static String SHOW_CLIENTS_MENU_MESSAGE = "Currently logged users: ";

    public static String CLIENT_NOT_FOUND = "User Not Found";

    public static String[] INIT_CONNECTION_MENU_OPTIONS = {"LOGIN", "CREATE ACCOUNT", "QUIT"};
    public static String INIT_CONNECTION_MENU_MESSAGE = WELCOME_MESSAGE;

    public static String LOGIN_NICKNAME = "Enter Nickname:";

    public static String UPLOAD_MESSAGE = "Enter path to file:";

    public static String DOWNLOAD_MESSAGE = "Select file to download:";

    public static String TRANSFER_FILE_MESSAGE = "Choose user to send file: ";

    public static String[] ACCEPT_CONNECTION_OPTIONS = {"Yes","No"};
    public static String ACCEPT_CONNECTION_MESSAGE = " wants to send you file xyz. \n Accept transfer?";

    public static String LOGGEDIN(String nickname){
        return SELECT_MENU_MESSAGE + nickname;
    }
    //TODO: check messages;
}
