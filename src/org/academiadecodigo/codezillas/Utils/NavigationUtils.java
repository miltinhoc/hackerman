package org.academiadecodigo.codezillas.Utils;

import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.Server.Navigation;

import java.util.HashMap;
import java.util.Map;

public class NavigationUtils {

    public static Map<NavigationPossibilitiesType, NavigationPossibilities> menuMap;

    public static String WELCOME_MESSAGE = "<Welcome to WireMonkey>\n";

    public static String SET_NICKNAME_MESSAGE = "Enter Nickname: ";

    public static String SELECT_MENU_MESSAGE = "Logged as ";

    public static String[] SELECT_MENU_OPTION = {"Upload File to Server", "Download File from Server", "Send File to User"};

    public static String[] ACCEPT_CONNECTION_OPTIONS = {"Yes","No"};

    public static String SHOW_CLIENTS_MENU_MESSAGE = "Clients currently Logged to Server:";

    public static String CLIENT_NOT_FOUND = "Client not Found";

    public static String[] INIT_CONNECTION_MENU_OPTIONS = {"LOGIN", "CREATE ACCOUNT", "QUIT"};

    public static String INIT_CONNECTION_MENU_MESSAGE = WELCOME_MESSAGE;

    public static String LOGIN_NICKNAME = "Enter Nickname:";

    public static String UPLOAD_MESSAGE = "Enter upload path: ";

    public static String DOWNLOAD_MESSAGE = "Select file to download:";

    public static String TRANSFER_FILE_MESSAGE = "Select Client you wish to connect:";


    public static String ACCEPT_CONNECTION_MESSAGE = " wants to connect. Accept connection:";
    //TODO: check messages;

    public static void initMap(){

        menuMap = new HashMap<>();

        menuMap.put(NavigationPossibilitiesType.INITIAL_MENU, new NavigationPossibilities(Navigation.clientMenu(), setPossibilitiesType(), new String[]{Commands.UPLOAD, Commands.MENU, Commands.MENU}));

        menuMap.put(NavigationPossibilitiesType.DOWNLOAD_MENU, new NavigationPossibilities(Navigation.downloadMenu(new String[]{"File1, File2"}), new String[]{Commands.DOWNLOAD})); //TODO: check path

        menuMap.put(NavigationPossibilitiesType.ACCEPT_NEW_CONNECTION_MENU, new NavigationPossibilities(Navigation.acceptConnectionMenu("An user"), new String[]{Commands.IP})); //TODO: add nick

        menuMap.put(NavigationPossibilitiesType.ONLINE_CLIENTS_MENU, new NavigationPossibilities(Navigation.onlineClientsMenu(new String[]{"User1, User2"}), new String[]{Commands.CONNECTION})); //TODO: add online clients

        menuMap.put(NavigationPossibilitiesType.UPLOAD_MESSAGE, new NavigationPossibilities(Navigation.uploadMessage(), new String[]{Commands.MENU}));

        menuMap.put(NavigationPossibilitiesType.NICKNAME_MESSAGE, new NavigationPossibilities(Navigation.setNickname(), new String[]{Commands.MENU}));

    }

    private static NavigationPossibilitiesType[] setPossibilitiesType(){

        return new NavigationPossibilitiesType[]{NavigationPossibilitiesType.UPLOAD_MESSAGE, NavigationPossibilitiesType.DOWNLOAD_MENU, NavigationPossibilitiesType.ONLINE_CLIENTS_MENU};
    }

}
