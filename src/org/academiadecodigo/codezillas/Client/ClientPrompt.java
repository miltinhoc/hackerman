package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.Server.Server;
import org.academiadecodigo.codezillas.Utils.PromptUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ClientPrompt {

    private Prompt prompt;
    private Client client;
    private Server server;

    public void setPeer(Client client) {
        this.client = client;
        prompt = new Prompt(System.in, System.out);
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void runClientPrompt() {
        init();
    }

    private void init() {
        initConnectionMenu();
    }

    private void initConnectionMenu() {
        int menuOption = showMenu(PromptUtils.INIT_CONNECTION_MENU_OPTIONS, PromptUtils.INIT_CONNECTION_MENU_MESSAGE);
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
        String loginNickname = stringScanner(PromptUtils.LOGIN_NICKNAME);
        if (!verifyClientLogin(loginNickname)) {
            initConnectionMenu();
        }
        initMenu();
    }

    private boolean verifyClientLogin(String loginID) {
        Map<String,Client> testerMap = new HashMap<>();
        if (!testerMap.containsKey(loginID)) {
            System.out.println(PromptUtils.CLIENT_NOT_FOUND);
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
        MenuInputScanner menuInput = new MenuInputScanner(PromptUtils.SELECT_MENU_OPTION);
        menuInput.setMessage(PromptUtils.SELECT_MENU_MESSAGE + client.getNickname());

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
        menuInput.setMessage(PromptUtils.SHOW_CLIENTS_MENU_MESSAGE);

        int selectedOption = prompt.getUserInput(menuInput) - 1;

        String selectedClient = clientsLoggedNicknames[selectedOption];

        return selectedClient;
    }

    /**
     *
     * @param loggedClientsMap Server Class: loggedClient.getMap()
     * @return an array with all clients currently logged
     */
    private String[] getClientsLogged(Map<String,Client> loggedClientsMap) {
        return loggedClientsMap.keySet().toArray(new String[loggedClientsMap.size()]);
    }

    private void setClientNickname() {
        String nickname = stringScanner(PromptUtils.SET_NICKNAME_MESSAGE);

        client.setNickname(nickname);
    }

    public String stringScanner(String message){
        StringInputScanner stringScanner = new StringInputScanner();
        stringScanner.setMessage(message);

        String string = prompt.getUserInput(stringScanner);
        return string;
    }

    public void intScanner(String message){

    }

    private int showMenu(String[] options, String message){
        MenuInputScanner menuInput = new MenuInputScanner(options);
        menuInput.setMessage(message);

        int selectedOption = prompt.getUserInput(menuInput);
        return selectedOption;
    }


}
