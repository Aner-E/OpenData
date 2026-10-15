package java.src.main;

import eus.opendata.csv.CsvIrakurlea;
import eus.opendata.json.JsonKudeatzailea;
import eus.opendata.modelo.Neurketa;
import eus.opendata.xml.XmlKudeatzailea;

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
            System.out.println("       AIREAREN KALITATEA");
            System.out.println("==================================");
            System.out.println("1. CSV fitxategia irakurri");
            System.out.println("2. Datuak JSON formatuan gorde");
            System.out.println("3. Datuak XML formatuan gorde");
            System.out.println("4. JSON fitxategia irakurri");
            System.out.println("5. XML fitxategia irakurri");
            System.out.println("6. Neurketa kopurua erakutsi");
            System.out.println("0. Irten");
            System.out.println("==================================");
            System.out.print("Aukeratu aukera bat: ");

            aukera = scanner.nextInt();

            try {

                switch (aukera) {

                    case 1:

                        neurketak =
                                csv.irakurri(
                                        "data/calidad_aire.csv"
                                );

                        System.out.println(
                                "CSV fitxategia behar bezala irakurri da."
                        );

                        System.out.println(
                                "Neurketa kopurua: "
                                        + neurketak.size()
                        );

                        break;

                    case 2:

                        if (neurketak == null) {
                            System.out.println(
                                    "Lehenengo CSV fitxategia irakurri."
                            );
                            break;
                        }

                        json.gorde(
                                neurketak,
                                "data/calidad_aire.json"
                        );

                        System.out.println(
                                "JSON fitxategia sortu da."
                        );

                        break;

                    case 3:

                        if (neurketak == null) {
                            System.out.println(
                                    "Lehenengo CSV fitxategia irakurri."
                            );
                            break;
                        }

                        xml.gorde(
                                neurketak,
                                "data/calidad_aire.xml"
                        );

                        System.out.println(
                                "XML fitxategia sortu da."
                        );

                        break;

                    case 4:

                        neurketak =
                                json.irakurri(
                                        "data/calidad_aire.json"
                                );

                        System.out.println(
                                "JSON fitxategia irakurri da."
                        );

                        System.out.println(
                                "Neurketa kopurua: "
                                        + neurketak.size()
                        );

                        break;

                    case 5:

                        neurketak =
                                xml.irakurri(
                                        "data/calidad_aire.xml"
                                );

                        System.out.println(
                                "XML fitxategia irakurri da."
                        );

                        System.out.println(
                                "Neurketa kopurua: "
                                        + neurketak.size()
                        );

                        break;

                    case 6:

                        if (neurketak == null) {
                            System.out.println(
                                    "Ez dago daturik kargatuta."
                            );
                        } else {
                            System.out.println(
                                    "Kargatutako neurketak: "
                                            + neurketak.size()
                            );
                        }

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