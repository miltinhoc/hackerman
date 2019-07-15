package org.academiadecodigo.codezillas.Utils;

public class ASCII {

    public static final String PIPE = "  |  ";
    public static final String VERSION = "BROS Server - Version 0.47";
    public static final String CLIENTVERSION = "BROS Client - Version 0.47";
    public static final String CREDITS = "Developed by CODEzillas TeamBRO \n" + PIPE + "@ <AC_>'s #28_Bootcamp. ";
    public static final String GITHUB = "github.com/sleepdeals/hackerman";

    public static final String USER_DETAILS = PIPE + VERSION + "\n" + PIPE + GITHUB + "\n" + PIPE + CREDITS;
    public static final String CLIENT_DETAILS = PIPE + CLIENTVERSION + "\n" + PIPE + GITHUB + "\n" + PIPE + CREDITS;

    public static final String CLIENTINTRO =
            ASCII.DIVIDER +"\n"
                    + ASCII.BROS + "\n"
                    + USER_DETAILS
                    + ASCII.DIVIDER;

    public static final String SERVERINTRO =
            ASCII.DIVIDER +"\n"
                    + ASCII.BROS + "\n"
                    + CLIENT_DETAILS
                    + ASCII.DIVIDER;

    public static final String BROS =
            "    ____     ____     ____     _____\n" +
                    "   / __ )   / __ \\   / __ \\   / ___/\n" +
                    "  / __  |  / /_/ /  / / / /   \\__ \\ \n" +
                    " / /_/ /  / _, _/  / /_/ /   ___/ / \n" +
                    "/_____/  /_/ |_|   \\____/   /____/  \n" +
                    "                                    ";
    public static final String LATER =
            "    __         __               ____  ____  ____  _____      __          \n" +
                    "   / /  ____ _/ /____  _____   / __ )/ __ \\/ __ \\/ ___/      \\ \\    ____ \n" +
                    "  / /  / __ `/ __/ _ \\/ ___/  / __  / /_/ / / / /\\__ \\        \\ \\  / __ \\\n" +
                    " / /__/ /_/ / /_/  __/ /     / /_/ / _, _/ /_/ /___/ /         \\ \\/ /_/ /\n" +
                    "/_____|__,_/\\__/\\___/_/     /_____/_/ |_|\\____//____/           \\_\\____/ \n" +
                    "                                                                         ";

    public static final String DIVIDER =
            "                                     \n" +
                    "                                     \n" +
                    " ____________________________________\n" +
                    "/_____/_____/_____/_____/_____/_____/\n" +
                    "                                     \n" +
                    "                                     ";
}

