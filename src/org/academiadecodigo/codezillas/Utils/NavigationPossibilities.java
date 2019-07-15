package org.academiadecodigo.codezillas.Utils;


import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.codezillas.Server.Server;

public class NavigationPossibilities {

    private Server.NavigationPossibilitiesType[] optionsType;
    private InputScanner inputScanner;
    private String[] nextCommand;

    public NavigationPossibilities(InputScanner inputScanner, Server.NavigationPossibilitiesType[] optionsType, String[] nextCommand){
        this.inputScanner = inputScanner;
        this.optionsType = optionsType;
        this.nextCommand = nextCommand;
    }

    public NavigationPossibilities(InputScanner inputScanner, String[] nextCommand){
        this.inputScanner = inputScanner;
        this.nextCommand = nextCommand;
    }

    public InputScanner getInputScanner() {
        return inputScanner;
    }

    public Server.NavigationPossibilitiesType[] getOptionsType() {
        return optionsType;
    }

    public String[] getNextCommand() {
        return nextCommand;
    }
}
