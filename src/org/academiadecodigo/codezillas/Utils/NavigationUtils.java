package org.academiadecodigo.codezillas.Utils;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.Server.Navigation;
import org.academiadecodigo.codezillas.Server.Server;

import java.util.HashMap;
import java.util.Map;

public class NavigationUtils {


    public static Map<Server.NavigationPossibilitiesType, NavigationPossibilities> menuMap;

    public static String WELCOME_MESSAGE = ASCII.CLIENTINTRO + "\n" + "Welcome to <BROS Relay Oriented System >";

    public static String SET_NICKNAME = "Choose Nickname: ";

    public static String SET_VALID_NICKNAME = "Nickname already in use, please choose a different one: ";

    public static String SELECT_MENU_MESSAGE = "Logged in as ";

    public static String[] SELECT_MENU_OPTION = {"Quit", "Upload File", "Download File", "Direct File Transfer"};

    public static String SHOW_CLIENTS_MENU = "Currently logged users: ";

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

    public static void initMap(InputScanner inputScanner){

        menuMap = new HashMap<>();

        menuMap.put(Server.NavigationPossibilitiesType.INITIAL_MENU, new NavigationPossibilities(Navigation.clientMenu(), setPossibilitiesType(), new String[]{Commands.QUIT, Commands.UPLOAD, Commands.MENU,Commands.MENU}));

        menuMap.put(Server.NavigationPossibilitiesType.DOWNLOAD_MENU, new NavigationPossibilities(Navigation.downloadMenu(FileManager.listAllFiles()), new String[]{Commands.DOWNLOAD})); //TODO: check path

        menuMap.put(Server.NavigationPossibilitiesType.ACCEPT_NEW_CONNECTION_MENU, new NavigationPossibilities(Navigation.acceptConnectionMenu("An user"), new String[]{Commands.IP})); //TODO: add nick

        menuMap.put(Server.NavigationPossibilitiesType.ONLINE_CLIENTS_MENU, new NavigationPossibilities(inputScanner, new String[]{Commands.CONNECTION}));

        menuMap.put(Server.NavigationPossibilitiesType.UPLOAD_MESSAGE, new NavigationPossibilities(Navigation.uploadMessage(), new String[]{Commands.MENU}));

        menuMap.put(Server.NavigationPossibilitiesType.NICKNAME_MESSAGE, new NavigationPossibilities(Navigation.setNickname(), new String[]{Commands.MENU}));

    }

    private static Server.NavigationPossibilitiesType[] setPossibilitiesType(){

        return new Server.NavigationPossibilitiesType[]{Server.NavigationPossibilitiesType.QUIT, Server.NavigationPossibilitiesType.UPLOAD_MESSAGE, Server.NavigationPossibilitiesType.DOWNLOAD_MENU, Server.NavigationPossibilitiesType.ONLINE_CLIENTS_MENU};
    }

}
