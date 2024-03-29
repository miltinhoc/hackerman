package org.academiadecodigo.codezillas.Server;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.Utils.ASCII;
import org.academiadecodigo.codezillas.Utils.Defaults;
import org.academiadecodigo.codezillas.Utils.NavigationUtils;

public class Navigation {

    public Navigation() {}

    public static InputScanner clientMenu(String nickname) {
        return showMenu(NavigationUtils.SELECT_MENU_OPTION, NavigationUtils.LOGGEDIN(nickname));
    }

    public static InputScanner clientMenu(){
        return showMenu(NavigationUtils.SELECT_MENU_OPTION, Defaults.EXTENDED);
    }

    public static InputScanner uploadMessage() {
        return stringScanner(NavigationUtils.UPLOAD_MESSAGE);
    }

    public static InputScanner downloadMenu(String[] availableDownloadFiles) {
        return showMenu(menuOptions(availableDownloadFiles), NavigationUtils.DOWNLOAD_MESSAGE);
    }

    public static InputScanner onlineClientsMenu(String[] clientsLogged) {
        return showMenu(menuOptions(clientsLogged), NavigationUtils.TRANSFER_FILE_MESSAGE);
    }

    public static InputScanner setNickname(){
        return stringScanner(NavigationUtils.SET_NICKNAME_MESSAGE);
    }

    public static InputScanner acceptConnectionMenu(String requestingClientNickname) {
        return showMenu(NavigationUtils.ACCEPT_CONNECTION_OPTIONS, requestingClientNickname
                + NavigationUtils.ACCEPT_CONNECTION_MESSAGE);
    }

    public static InputScanner getLogin(){
        return stringScanner(ASCII.CLIENTINTRO + "\n" + NavigationUtils.SET_NICKNAME);
    }

    public static InputScanner getValidLogin() {
        return stringScanner(ASCII.CLIENTINTRO + "\n" + NavigationUtils.SET_VALID_NICKNAME);
    }

    private static String[] menuOptions(String[] array) {

        String[] newArray = new String[array.length + 1];

        System.arraycopy(array, 0, newArray, 1, newArray.length - 1);

        newArray[0] = "Quit";

        return newArray;
    }

    private static StringInputScanner stringScanner(String message){
        StringInputScanner stringScanner = new StringInputScanner();
        stringScanner.setMessage(message);

        return stringScanner;
    }

    private static MenuInputScanner showMenu(String[] options, String message){
        MenuInputScanner menuInput = new MenuInputScanner(options);
        menuInput.setMessage(message);

        return menuInput;
    }
}
