package fr.ul.miage.GenieLogiciel.utils;

public class Outil {

    public static void waitTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
        }
    }
}
