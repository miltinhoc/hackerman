package org.academiadecodigo.codezillas.Utils;


import org.academiadecodigo.bootcamp.InputScanner;

public class NavigationPossibilities {

    private NavigationPossibilitiesType[] optionsType;
    private InputScanner inputScanner;
    private String[] nextCommand;

    public NavigationPossibilities(InputScanner inputScanner, NavigationPossibilitiesType[] optionsType, String[] nextCommand){
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

    public NavigationPossibilitiesType[] getOptionsType() {
        return optionsType;
    }

    public String[] getNextCommand() {
        return nextCommand;
    }
}
