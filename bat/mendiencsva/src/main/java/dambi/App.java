package dambi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int aukera = 0;
        do {
            System.out.println();
            System.out.println("MENUAREN IZENBURUA");
            System.out.println("====================================");
            System.out.println("1.- Mendien zerrenda ikusi (taula formatuan)");
            System.out.println("2.- Mendirik altuena bistaratu");
            System.out.println("3.- Mendiak esportatu (Araba.csv, Bizkaia.csv, Gipuzkoa.csv)");
            System.out.println("4.- ...");
            System.out.println("5.- Irten");
            System.out.println("");
            System.out.print("Aukeratu zenbaki bat: ");
            aukera = in.nextInt();
            switch (aukera) {
                case 1:
                    bistaratu();
                    break;
                case 2:
                    Mendia altuena = altuena();
                    System.out.println("Mendirik altuena " + altuena.izena + " da, " + altuena.altuera + " metrorekin ("
                            + altuena.probintzia + ").");
                    break;
                case 3:
                    exportatu();
                    break;
                case 5:
                    System.out.println("Eskerrik asko programa hau erabiltzeagatik.");
                    break;
                default:
                    System.out.println("Aukera okerra. Saiatu berriz.");
            }
        } while (aukera != 5);
        in.next();
    }

    public static void bistaratu() {
        ArrayList<Mendia> mendiak = mendiakKargatu();

        System.out.printf("%12s  %10s  %12s", "MENDIA", "ALTUERA", "PROBINTZIA");
        System.out.println();
        for (Mendia m : mendiak) {
            System.out.format("%-12s | %10s | %-12s", m.izena, m.altuera + "m", m.probintzia);
            System.out.println();
        }
    }

    public static Mendia altuena() {
        ArrayList<Mendia> mendiak = mendiakKargatu();
        Mendia altuena = mendiak.get(0);
        for (Mendia m : mendiak) {
            if (m.altuera > altuena.altuera) {
                altuena = m;
            }
        }
        return altuena;
    }

    public static void exportatu() {
        ArrayList<Mendia> mendiak = mendiakKargatu();

        ArrayList<Mendia> arabaMendiak = new ArrayList<>();
        ArrayList<Mendia> bizkaiaMendiak = new ArrayList<>();
        ArrayList<Mendia> gipuzkoaMendiak = new ArrayList<>();
        ArrayList<Mendia> nafarroaMendiak = new ArrayList<>();
        ArrayList<Mendia> lapurdiMendiak = new ArrayList<>();
        ArrayList<Mendia> zuberoaMendiak = new ArrayList<>();

        for (Mendia m : mendiak) {
            switch (m.probintzia) {
                case "Araba":
                    arabaMendiak.add(m);
                    break;
                case "Bizkaia":
                    bizkaiaMendiak.add(m);
                    break;
                case "Gipuzkoa":
                    gipuzkoaMendiak.add(m);
                    break;
                case "Nafarroa":
                    nafarroaMendiak.add(m);
                    break;
                case "Lapurdi":
                    lapurdiMendiak.add(m);
                    break;
                case "Zuberoa":
                    zuberoaMendiak.add(m);
                    break;

            }
        }

        mendiakGorde("Araba", arabaMendiak);
        mendiakGorde("Bizkaia", bizkaiaMendiak);
        mendiakGorde("Gipuzkoa", gipuzkoaMendiak);
        mendiakGorde("Nafarroa", gipuzkoaMendiak);
        mendiakGorde("Lapurdi", gipuzkoaMendiak);
        mendiakGorde("Zuberoa", gipuzkoaMendiak);

        System.out.println("Mendien fitxategiak gorde dira.");
    }

    public static void mendiakGorde(String fitxategiIzena, ArrayList<Mendia> mendiak) {
        try {

            Writer out = new FileWriter(fitxategiIzena + ".csv");
            BufferedWriter stream = new BufferedWriter(out);

            stream.append("MENDIA;ALTUERA;PROBINTZIA\n");

            for (Mendia m : mendiak) {
                stream.append(m.izena + ";" + m.altuera + ";" + m.probintzia + "\n");
            }
            stream.close();
        } catch (Exception ex) {
            System.out.println("Arazoa mendiak esportatzerakoan.");
        }
    }

    public static ArrayList<Mendia> mendiakKargatu() {

        ArrayList<Mendia> mendiak = new ArrayList<>();

        try {
            Reader in = new FileReader("Mendiak.csv");
            BufferedReader stream = new BufferedReader(in);

            // lehen lerroa saltatu
            stream.readLine();

            String inString;
            while ((inString = stream.readLine()) != null) {
                String[] mendiaElements = inString.split(";");

                Mendia mendia = new Mendia(mendiaElements[0], Integer.parseInt(mendiaElements[1]), mendiaElements[2]);
                mendiak.add(mendia);
            }
            stream.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Mendiak.csv fitxategia ezin izan da aurkitu.");
        } catch (IOException ex) {
            System.out.println("Arazoa fitxategia irakurtzerakoan.");
        } catch (NumberFormatException ex) {
            System.out.println("Daturen bat gaizki formateatuta dago.");
        }
        return mendiak;
    }

    public static void met2() {
    }
}
