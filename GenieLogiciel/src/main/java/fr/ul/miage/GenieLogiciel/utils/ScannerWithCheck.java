package fr.ul.miage.GenieLogiciel.utils;

import java.util.ArrayList;
import java.util.Scanner;

public class ScannerWithCheck {
    public static int scannerIntUtilisateur(boolean isZeroPossible, int maxValue) {
        Scanner scan = new Scanner(System.in);
        boolean isFinish = false;
        int val = 0;
        do {
            if (scan.hasNextInt()) {
                val = scan.nextInt();
                if ((val < 0 || val == 0 && !isZeroPossible) || (val > maxValue && maxValue != -1)) {
                    scan.nextLine();
                    System.err.println("Erreur de saisie");
                } else {
                    isFinish = true;
                }
            } else {
                scan.nextLine();
                System.err.println("Erreur de saisie");
            }
        } while (!isFinish);
        return val;
    }

    public static String scannerStringUtilisateur(int maxLength) {
        Scanner scan = new Scanner(System.in);
        boolean isFinish = false;
        String val = "";
        do {
            if (scan.hasNext()) {
                val = scan.next();
                if (val.length() > maxLength) {
                    scan.nextLine();
                    System.err.println("Trop long !");
                } else {
                    isFinish = true;
                }
            } else {
                scan.nextLine();
                System.err.println("Erreur de saisie");
            }
        } while (!isFinish);
        return val;
    }
    
    public static String scannerStringPreciseValue(int maxLength, ArrayList<String> autorizedValues) {
        Scanner scan = new Scanner(System.in);
        boolean isFinish = false;
        String val = "";
        do {
            if (scan.hasNext()) {
                val = scan.next();
                if (val.length() > maxLength) {
                    scan.nextLine();
                    System.err.println("Trop long !");
                } else if (!autorizedValues.contains(val)) {
                	scan.nextLine();
                    System.err.println("Valeur attendues : y ou n !");
                } else {
                    isFinish = true;
                }
            } else {
                scan.nextLine();
                System.err.println("Erreur de saisie");
            }
        }while (!isFinish);
        return val;
    }
}
