import csv.CsvIrakurlea;
import eus.opendata.json.JsonKudeatzailea;
import eus.opendata.modelo.Neurketa;
import eus.opendata.xml.XmlKudeatzailea;
import menuak.CSVMenu;
import menuak.XMLMenu;

import java.util.List;
import java.util.Scanner;

/**
 * Aplikazioaren menu nagusia kudeatzen du.
 * Erabiltzaileak CSV, XML edo JSON fitxategiekin lan egin dezake.
 */
public class Main {

    public static void main(String[] args) {

        // Erabiltzailearen aukera irakurtzeko.
        Scanner scanner = new Scanner(System.in);

        List<Neurketa> neurketak = null;

        // Fitxategiak kudeatzeko objektuak sortzen dira.
        CsvIrakurlea csv = new CsvIrakurlea();
        JsonKudeatzailea json = new JsonKudeatzailea();
        XmlKudeatzailea xml = new XmlKudeatzailea();

        int aukera;

        do {
            System.out.println();
            System.out.println("==================================");
            System.out.println("       Datuekin Lan Egitea");
            System.out.println("==================================");
            System.out.println("1. CSV Fitxategian Bilatu");
            System.out.println("2. XML Fitxategian Bilatu");
            System.out.println("3. JSON Fitxategian Bilatu");
            System.out.println("0. Irten");
            System.out.println("==================================");
            System.out.print("Aukeratu aukera bat: ");

            // Aukeratutako menu-zenbakia irakurtzen da.
            aukera = scanner.nextInt();

            try {
                switch (aukera) {

                    case 1:
                        // CSV fitxategiaren menua irekitzen da.
                        CSVMenu();
                        break;

                    case 2:
                        // XML fitxategiaren menua irekitzen da.
                        XMLMenu();
                        break;

                    case 3:
                        // JSON fitxategiaren menua irekitzen da.
                        JSonMenu();
                        break;

                    case 0:
                        System.out.println("Programa amaitzen...");
                        break;

                    default:
                        System.out.println("Aukera okerra.");
                }

            } catch (Exception e) {
                // Errore bat gertatuz gero, mezua erakusten da.
                System.out.println("Errorea: " + e.getMessage());
            }

        } while (aukera != 0);

        // Scanner-a ixten da programa amaitzean.
        scanner.close();
    }
}