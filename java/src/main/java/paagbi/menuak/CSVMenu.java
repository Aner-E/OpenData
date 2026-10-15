package paagbi.menuak;

import java.util.List;
import java.util.Scanner;

import paagbi.modeloa.Neurketa;

public class CSVMenu {

    private final Scanner sn;
    private final List<Neurketa> neurketak;
    /**
     * CSV menua sortzen du, irakurritako neurketekin.
     *
     */
    public CSVMenu(List<Neurketa> neurketak) {
        this.sn = new Scanner(System.in);
        this.neurketak = neurketak;
    }

    /**
     * Aukeren menua erakusten du eta erabiltzailearen aukerak kudeatzen ditu.
     */
    public void erakutsi() {

        int aukera;

        do {
            System.out.println();
            System.out.println("==================================");
            System.out.println("          CSV DATUAK");
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
                    estazioaBilatu();
                    break;

                case 2:
                    probintziaBilatu();
                    break;

                case 3:
                    urteaBilatu();
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
     * Estazio baten neurketak bilatu eta erakusten ditu.
     */
    private void estazioaBilatu() {

        System.out.print("Sartu estazioaren kodea: ");
        String estazioa = sn.nextLine().trim();

        boolean aurkitua = false;

        for (Neurketa n : neurketak) {
            if (n.getEstazioa() != null
                    && n.getEstazioa().equalsIgnoreCase(estazioa)) {

                erakutsiNeurketa(n);
                aurkitua = true;
            }
        }

        if (!aurkitua) {
            System.out.println("Ez da estaziorik aurkitu.");
        }
    }

    /**
     * Probintzia baten neurketak bilatu eta erakusten ditu.
     */
    private void probintziaBilatu() {

        System.out.print("Sartu probintziaren kodea: ");
        String probintzia = sn.nextLine().trim();

        boolean aurkitua = false;

        for (Neurketa n : neurketak) {
            if (n.getProbintzia() != null
                    && n.getProbintzia().equalsIgnoreCase(probintzia)) {

                erakutsiNeurketa(n);
                aurkitua = true;
            }
        }

        if (!aurkitua) {
            System.out.println("Ez da probintziarik aurkitu.");
        }
    }

    /**
     * Urte baten neurketak bilatu eta erakusten ditu.
     */
    private void urteaBilatu() {

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
                System.out.println(
                        "Ez dago urte horretako neurketarik.");
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
        System.out.println("----------------------------------");
    }
}
