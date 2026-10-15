package java.src.main;

import eus.opendata.csv.CsvIrakurlea;
import eus.opendata.json.JsonKudeatzailea;
import eus.opendata.modelo.Neurketa;
import eus.opendata.xml.XmlKudeatzailea;
import menuak.CSVMenu;
import menuak.XMLMenu;
import menuak.JsonMenu;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Neurketa> neurketak = null;

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
            System.out.println("==================================");
            System.out.print("Aukeratu aukera bat: ");

            aukera = scanner.nextInt();

            try {

                switch (aukera) {

                    case 1:
                        CSVMenu();
                        break;

                    case 2:
                        XMLMenu();
                        break;
                    case 3:
                        JSonMenu();
                        break;
                    case 0:

                        System.out.println(
                                "Programa amaitzen..."
                        );
                        break;
                    default:
                        System.out.println(
                                "Aukera okerra."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "Errorea: " + e.getMessage()
                );
            }

        } while (aukera != 0);

        scanner.close();
    }
}