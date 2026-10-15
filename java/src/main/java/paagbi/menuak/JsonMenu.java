package paagbi.menuak;

import java.util.List;
import java.util.Scanner;

import paagbi.modeloa.Neurketa;

public class JsonMenu {

    private final Scanner sn;
    private final List<Neurketa> neurketak;
    public JsonMenu(List<Neurketa> neurketak) {
        this.sn = new Scanner(System.in);
        this.neurketak = neurketak;
    }

    /**
     * Aukeren menua erakusten du eta erabiltzailearen aukera kudeatzen du.
     */
    public void erakutsi() {

        int aukera;

        do {
            System.out.println();
            System.out.println("==================================");
            System.out.println("       Datuekin Lan Egitea");
            System.out.println("==================================");
            System.out.println("1. Estazioarekiko datuak ikusi");
            System.out.println("2. Probintziarekiko datuak ikusi");
            System.out.println("3. Urtearekiko datuak ikusi");
            System.out.println("0. Atzera");
            System.out.println("==================================");
            System.out.print("Aukeratu aukera bat: ");

            while (!sn.hasNextInt()) {
                System.out.println("Sartu zenbaki bat, mesedez.");
                sn.next();
                System.out.print("Aukeratu aukera bat: ");
            }

            aukera = sn.nextInt();
            sn.nextLine();

            switch (aukera) {
                case 1:
                    estazioarenAraberaBilatu();
                    break;

                case 2:
                    probintziarenAraberaBilatu();
                    break;

                case 3:
                    urtearenAraberaBilatu();
                    break;

                case 0:
                    System.out.println("Aurreko menura itzultzen...");
                    break;

                default:
                    System.out.println("Aukera okerra.");
            }

        } while (aukera != 0);
    }

    /**
     * Estazio baten neurketak erakusten ditu.
     */
    private void estazioarenAraberaBilatu() {

        System.out.print("Sartu estazioaren kodea: ");
        String estazioa = sn.nextLine().trim();

        boolean aurkitua = false;

        for (Neurketa n : neurketak) {
            if (n.getEstazioa().equalsIgnoreCase(estazioa)) {
                erakutsiNeurketa(n);
                aurkitua = true;
            }
        }

        if (!aurkitua) {
            System.out.println("Ez da estaziorik aurkitu.");
        }
    }

    /**
     * Probintzia baten neurketak erakusten ditu.
     */
    private void probintziarenAraberaBilatu() {

        System.out.print("Sartu probintziaren kodea: ");
        String probintzia = sn.nextLine().trim();

        boolean aurkitua = false;

        for (Neurketa n : neurketak) {
            if (n.getProbintzia().equalsIgnoreCase(probintzia)) {
                erakutsiNeurketa(n);
                aurkitua = true;
            }
        }

        if (!aurkitua) {
            System.out.println("Ez da probintziarik aurkitu.");
        }
    }

    /**
     * Urte bateko neurketak erakusten ditu.
     */
    private void urtearenAraberaBilatu() {

        System.out.print("Sartu urtea: ");

        String testua = sn.nextLine().trim();

        try {
            int urtea = Integer.parseInt(testua);
            boolean aurkitua = false;

            for (Neurketa n : neurketak) {
                if (n.getUrtea() == urtea) {
                    erakutsiNeurketa(n);
                    aurkitua = true;
                }
            }

            if (!aurkitua) {
                System.out.println("Ez dago urte horretako neurketarik.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Urtea zenbaki bat izan behar da.");
        }
    }


    private void erakutsiNeurketa(Neurketa n) {

        System.out.println("----------------------------------");
        System.out.println("Probintzia: " + n.getProbintzia());
        System.out.println("Udalerria: " + n.getUdalerria());
        System.out.println("Estazioa: " + n.getEstazioa());
        System.out.println("Magnitudea: " + n.getMagnitudea());
        System.out.println("Urtea: " + n.getUrtea());
        System.out.println("Hilabetea: " + n.getHilabetea());
        System.out.println("Eguneko datuen kopurua: "
                + n.getDatuak().size());
    }
}