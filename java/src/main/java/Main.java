import java.util.List;
import java.util.Scanner;

import paagbi.csv.CsvIrakurlea;
import paagbi.json.CsvJsonSortzailea;
import paagbi.menuak.CSVMenu;
import paagbi.menuak.JsonMenu;
import paagbi.menuak.XMLMenu;
import paagbi.modeloa.Neurketa;
import paagbi.xml.xmlKudeatzailea;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int aukera;

        // Fitxategiak kudeatzeko objektuak sortzen dira.
        CsvIrakurlea csv = new CsvIrakurlea();
        CsvJsonSortzailea jsonSortzailea = new CsvJsonSortzailea();
        xmlKudeatzailea xml = new xmlKudeatzailea("datuak/neurketak.xml");

        do {
            System.out.println();
            System.out.println("==================================");
            System.out.println("       DATUEKIN LAN EGITEA");
            System.out.println("==================================");
            System.out.println("1. CSV fitxategian bilatu");
            System.out.println("2. XML fitxategian bilatu");
            System.out.println("3. JSON fitxategian bilatu");
            System.out.println("0. Irten");
            System.out.println("==================================");
            System.out.print("Aukeratu aukera bat: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Sartu zenbaki bat, mesedez.");
                scanner.next();
                System.out.print("Aukeratu aukera bat: ");
            }

            aukera = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (aukera) {
                    case 1: {
                        // CSV fitxategia irakurri eta neurketak menuari pasatzen zaizkio.
                        List<Neurketa> neurketak = csv.irakurri();
                        new CSVMenu(neurketak).erakutsi();
                        break;
                    }

                    // XML fitxategiko neurketak irakurri eta menuari pasatzen zaizkio.
                    case 2: {
                        // XML fitxategiko neurketak irakurri eta menuari pasatzen zaizkio.
                        List<Neurketa> neurketak = xml.irakurri();
                        new XMLMenu(neurketak).erakutsi();
                        break;
                    }

                    case 3: {
                        // JSON fitxategia sortzen du.
                        jsonSortzailea.sortu();

                        // CSV fitxategiko neurketak irakurtzen ditu.
                        List<Neurketa> neurketak = csv.irakurri();

                        // Neurketak JSON menuari pasatzen dizkio.
                        new JsonMenu(neurketak).erakutsi();
                        break;
                    }

                    case 0:
                        System.out.println("Programa amaitzen...");
                        break;

                    default:
                        System.out.println("Aukera okerra.");
                }

            } catch (Exception e) {
                System.err.println("Errorea: " + e.getMessage());
            }

        } while (aukera != 0);

        scanner.close();
    }
}