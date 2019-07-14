package org.academiadecodigo.codezillas.Utils;

import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.Server.Navigation;

import java.util.HashMap;
import java.util.Map;

public class NavigationUtils {

    public static Map<NavigationPossibilitesType, NavigationPossibilites> menuMap;

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

        menuMap.put(NavigationPossibilitesType.INITIAL_MENU, new NavigationPossibilites(Navigation.clientMenu(), setPossibilitiesType(), new String[]{Commands.UPLOAD, Commands.MENU, Commands.MENU}));

        menuMap.put(NavigationPossibilitesType.DOWNLOAD_MENU, new NavigationPossibilites(Navigation.downloadMenu(FileManager.listAllPathContent("")), new String[]{Commands.DOWNLOAD})); //TODO: check path

        menuMap.put(NavigationPossibilitesType.ACCEPT_NEW_CONNECTION_MENU, new NavigationPossibilites(Navigation.acceptConnectionMenu("An user"), new String[]{Commands.IP})); //TODO: add nick

        menuMap.put(NavigationPossibilitesType.ONLINE_CLIENTS_MENU, new NavigationPossibilites(Navigation.onlineClientsMenu(new String[]{"User1, User2"}), new String[]{Commands.CONNECTION})); //TODO: add online clients

        menuMap.put(NavigationPossibilitesType.UPLOAD_MESSAGE, new NavigationPossibilites(Navigation.uploadMessage(), new String[]{Commands.MENU}));

        menuMap.put(NavigationPossibilitesType.NICKNAME_MESSAGE, new NavigationPossibilites(Navigation.setNickname(), new String[]{Commands.MENU}));

    }

    private static NavigationPossibilitesType[] setPossibilitiesType(){

        return new NavigationPossibilitesType[]{NavigationPossibilitesType.UPLOAD_MESSAGE, NavigationPossibilitesType.DOWNLOAD_MENU, NavigationPossibilitesType.ONLINE_CLIENTS_MENU};
    }

}
