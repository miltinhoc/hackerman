package org.academiadecodigo.codezillas.Utils;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.codezillas.FileServices.FileManager;
import org.academiadecodigo.codezillas.Server.Navigation;

public enum NavigationPossibilitesType {

    INITIAL_MENU(Navigation.clientMenu()),
    DOWNLOAD_MENU(Navigation.downloadMenu(FileManager.listAllPathContent(""))), //TODO: add path
    ACCEPT_NEW_CONNECTION_MENU(Navigation.acceptConnectionMenu("An user")), //TODO: add user
    ONLINE_CLIENTS_MENU(Navigation.onlineClientsMenu(new String[]{"User1, User2"})), //TODO: add online clients
    UPLOAD_MESSAGE(Navigation.uploadMessage()),
    NICKNAME_MESSAGE(Navigation.setNickname());

    private InputScanner inputScanner;

    NavigationPossibilitesType(InputScanner inputScanner){
        this.inputScanner = inputScanner;
    }

    public InputScanner getInputScanner() {
        return inputScanner;
    }
}
