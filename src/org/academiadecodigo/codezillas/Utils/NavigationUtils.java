package org.academiadecodigo.codezillas.Utils;

import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.Server.Navigation;

import java.util.HashMap;
import java.util.Map;

public class NavigationUtils {


    public static final String SET_NICKNAME_MESSAGE = "???WTF REIMAO STOP CHANGING SHIT" ;
    public static Map<NavigationPossibilitiesType, NavigationPossibilities> menuMap;

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

    public static void initMap(){

        menuMap = new HashMap<>();

        menuMap.put(NavigationPossibilitiesType.INITIAL_MENU, new NavigationPossibilities(Navigation.clientMenu(), setPossibilitiesType(), new String[]{Commands.QUIT, Commands.UPLOAD, Commands.MENU,Commands.MENU}));

        menuMap.put(NavigationPossibilitiesType.DOWNLOAD_MENU, new NavigationPossibilities(Navigation.downloadMenu(FileManager.listAllFiles()), new String[]{Commands.DOWNLOAD})); //TODO: check path

        menuMap.put(NavigationPossibilitiesType.ACCEPT_NEW_CONNECTION_MENU, new NavigationPossibilities(Navigation.acceptConnectionMenu("An user"), new String[]{Commands.IP})); //TODO: add nick

        menuMap.put(NavigationPossibilitiesType.ONLINE_CLIENTS_MENU, new NavigationPossibilities(Navigation.onlineClientsMenu(new String[]{"User1, User2"}), new String[]{Commands.CONNECTION})); //TODO: add online clients

        menuMap.put(NavigationPossibilitiesType.UPLOAD_MESSAGE, new NavigationPossibilities(Navigation.uploadMessage(), new String[]{Commands.MENU}));

        menuMap.put(NavigationPossibilitiesType.NICKNAME_MESSAGE, new NavigationPossibilities(Navigation.setNickname(), new String[]{Commands.MENU}));

    }

    private static NavigationPossibilitiesType[] setPossibilitiesType(){

        return new NavigationPossibilitiesType[]{NavigationPossibilitiesType.QUIT, NavigationPossibilitiesType.UPLOAD_MESSAGE, NavigationPossibilitiesType.DOWNLOAD_MENU, NavigationPossibilitiesType.ONLINE_CLIENTS_MENU};
    }

}
