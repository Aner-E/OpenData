package paagbi.menuak;

import java.util.Scanner;
import java.util.List;

public class JsonMenu {

    Scanner sn = new Scanner(System.in);
    int aukera;

    public JsonMenu() {
        System.out.println();
        System.out.println("==================================");
        System.out.println("       Datuekin Lan Egitea");
        System.out.println("==================================");
        System.out.println("1. CSV Fitxategian Bilatu");
        System.out.println("2. XML Fitxategian Bilatu");
        System.out.println("3. JSON Fitxategian Bilatu");
        System.out.println("==================================");
        System.out.print("Aukeratu aukera bat: ");

        aukera = sn.nextInt();
    }

}
