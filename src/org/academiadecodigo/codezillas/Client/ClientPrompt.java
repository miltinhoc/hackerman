package org.academiadecodigo.codezillas.Client;


import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.Utils.NavigationUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//TODO DELETE THIS - REFERENCE CLASS

public abstract class ClientPrompt {
/*

    private Prompt prompt;


    public void setPrompt() {
        prompt = new Prompt(System.in, System.out);
    }

    public void setStreams(PrintWriter out, BufferedReader in) {
    }

    public int handleRequest(InputScanner<Integer> request){
        return prompt.getUserInput(request);
    }
    public void runClientPrompt() {
        initConnectionMenu();
    }

    private void initConnectionMenu() {
        int menuOption = showMenu(NavigationUtils.INIT_CONNECTION_MENU_OPTIONS, NavigationUtils.INIT_CONNECTION_MENU_MESSAGE);
        handleInitConnectionMenu(menuOption);
    }

    private void handleInitConnectionMenu(int menuOption) {
        switch (menuOption) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                exit();
        }
    }

    private void login() {
        String loginNickname = stringScanner(NavigationUtils.LOGIN_NICKNAME);
        if (!verifyClientLogin(loginNickname)) {
            initConnectionMenu();
        }
        initMenu();
    }

    private boolean verifyClientLogin(String loginID) {
        Map<String,Client> testerMap = new HashMap<>();
        if (!testerMap.containsKey(loginID)) {
            System.out.println(NavigationUtils.CLIENT_NOT_FOUND);
            return false;
        }
        return true;
    }

    private void register() {
        setClientNickname();
    }

    private void exit() {
        System.exit(0);
    }

    private void initMenu() {
        MenuInputScanner menuInput = new MenuInputScanner(NavigationUtils.SELECT_MENU_OPTION);
        menuInput.setMessage(NavigationUtils.SELECT_MENU_MESSAGE + client.getNickname());

        int menuOption = prompt.getUserInput(menuInput);

        handleMenu(menuOption);
    }

    private void handleMenu(int selectedMenuOption) {
        switch (selectedMenuOption) {
            case 1:
                handleUploadFile();
                break;
            case 2:
                handleDownloadFile();
                break;
            case 3:
                handleSendFileToUser();
                break;
        }
    }

    private void handleUploadFile() {

    }

    private void handleDownloadFile() {

    }

    private void handleSendFileToUser() {
        String selectedClient = showClientList();
        sendFileToClient(selectedClient);
    }

    private void sendFileToClient(String nickname) {
        File file = new File("");
        //client.initP2PTransfer(nickname, file);
    }

    private String showClientList() {
        Map<String,Client> testerMap = new HashMap<>();

        String[] clientsLoggedNicknames = getClientsLogged(testerMap);

        MenuInputScanner menuInput = new MenuInputScanner(clientsLoggedNicknames);
        menuInput.setMessage(NavigationUtils.SHOW_CLIENTS_MENU_MESSAGE);

        int selectedOption = prompt.getUserInput(menuInput) - 1;

        String selectedClient = clientsLoggedNicknames[selectedOption];

        return selectedClient;
    }

    /**
     *
     * @param loggedClientsMap Server Class: loggedClient.getMap()
     * @return an array with all clients currently logged

    private String[] getClientsLogged(Map<String,Client> loggedClientsMap) {
        return loggedClientsMap.keySet().toArray(new String[loggedClientsMap.size()]);
    }

    private void setClientNickname() {
        String nickname = stringScanner(NavigationUtils.SET_NICKNAME_MESSAGE);

        //client.setNickname(nickname);
    }

    public String stringScanner(String message){
        StringInputScanner stringScanner = new StringInputScanner();
        stringScanner.setMessage(message);

        String string = prompt.getUserInput(stringScanner);
        return string;
    }

    public void intScanner(String message){

    }

    private MenuInputScanner showMenu(String[] options, String message){
        MenuInputScanner menuInput = new MenuInputScanner(options);
        menuInput.setMessage(message);

        int selectedOption = prompt.getUserInput(menuInput);
        return menuInput;
    }
*/
}


