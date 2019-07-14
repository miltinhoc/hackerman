package org.academiadecodigo.codezillas.Utils;


import org.academiadecodigo.bootcamp.InputScanner;

public class NavigationPossibilites {

    private NavigationPossibilitesType[] optionsType;
    private InputScanner inputScanner;
    private String[] nextCommand;

    public NavigationPossibilites(InputScanner inputScanner, NavigationPossibilitesType[] optionsType, String[] nextCommand){
        this.inputScanner = inputScanner;
        this.optionsType = optionsType;
        this.nextCommand = nextCommand;
    }

    public NavigationPossibilites(InputScanner inputScanner, String[] nextCommand){
        this.inputScanner = inputScanner;
        this.nextCommand = nextCommand;
    }

    public InputScanner getInputScanner() {
        return inputScanner;
    }

    public NavigationPossibilitesType[] getOptionsType() {
        return optionsType;
    }

    public String[] getNextCommand() {
        return nextCommand;
    }
}
