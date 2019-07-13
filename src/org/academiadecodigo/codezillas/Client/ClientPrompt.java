package org.academiadecodigo.codezillas.Client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.Utils.PromptUtils;

public class ClientPrompt {

    private Prompt prompt;
    private Client client;
    private StringInputScanner stringInputScanner;

    public void setPeer(Client client) {
        this.client = client;
        prompt = new Prompt(System.in, System.out);
    }

    public void runClientPrompt() {
        stringInputScanner = new StringInputScanner();

        initMenu();
    }

    private void initMenu() {
        setClientNickname();
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
