package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.Server.Server;
import org.academiadecodigo.codezillas.Utils.PromptUtils;

import java.util.HashMap;
import java.util.Map;

public class ClientPrompt {

    private Prompt prompt;
    private Client client;
    private Server server;
    private StringInputScanner stringInputScanner;

    public void setPeer(Client client) {
        this.client = client;
        prompt = new Prompt(System.in, System.out);
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void runClientPrompt() {
        stringInputScanner = new StringInputScanner();

        init();
    }

    private void init() {
        welcomeMessage();
        setClientNickname();

        initMenu();
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
        int selectedClient = showClientList();
        sendFileToClient();
    }

    //TODO: create method sendFileToClient
    //can access the selected client
    //depends on Client method implementation
    private void sendFileToClient() {}

    private int showClientList() {
        Map<String,Client> testerMap = new HashMap<>();
        Client[] clientsLogged = getClientsLogged(testerMap);

        String[] clientsLoggedNicknames = getAllClientNicknames(clientsLogged);

        MenuInputScanner menuInput = new MenuInputScanner(clientsLoggedNicknames);
        menuInput.setMessage(PromptUtils.SHOW_CLIENTS_MENU_MESSAGE);

        int selectedOption = prompt.getUserInput(menuInput);

        return selectedOption;
    }

    private String[] getAllClientNicknames(Client[] clientsLogged) {
        String[] clientNicKnames = new String[clientsLogged.length];
        int i = 0;

        for (Client client : clientsLogged) {
            clientNicKnames[i] = client.getNickname();
        }
        return clientNicKnames;
    }

    /**
     *
     * @param loggedClientsMap Server Class: loggedClient.getMap()
     * @return an array with all clients currently logged
     */
    private Client[] getClientsLogged(Map<String,Client> loggedClientsMap) {

        return loggedClientsMap.values().toArray(new Client[0]);

    }


    private void welcomeMessage() {
        System.out.println(PromptUtils.WELCOME_MESSAGE);
    }

    private void setClientNickname() {
        stringInputScanner.setMessage(PromptUtils.SET_NICKNAME_MESSAGE);
        String nickname = prompt.getUserInput(stringInputScanner);

        client.setNickname(nickname);
    }

    public void StringScanner(String message){

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
