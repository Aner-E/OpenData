package paagbi.csv;

import paagbi.modeloa.Neurketa;
import paagbi.modeloa.EgunekoDatu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV fitxategiko airearen kalitatearen datuak irakurri eta
 * Neurketa objektuen zerrenda batean gordetzen ditu.
 */
public class CsvIrakurlea {

    // CSV fitxategia Internetetik irakurtzeko helbidea.
    private static final String URL_CSV = "https://raw.githubusercontent.com/Aner-E/OpenData/refs/heads/main/201410-10-calidad-aire-diario-csv.csv";

    /**
     * CSV fitxategia irakurri eta neurketen zerrenda sortzen du.
     */
    public List<Neurketa> irakurri() throws IOException {

        // Irakurritako neurketak gordetzeko zerrenda sortzen du.
        List<Neurketa> neurketak = new ArrayList<>();

        // CSV fitxategiaren helbidea prestatu eta konexioa sortzen du.
        URI uri = URI.create(URL_CSV);
        URL url = uri.toURL();

        // Fitxategia UTF-8 kodifikazioarekin irakurtzen du.
        try (BufferedReader irakurlea = new BufferedReader(
                new InputStreamReader(
                        url.openStream(),
                        StandardCharsets.UTF_8))) {

            // Lehen lerroak zutabeen izenak dituenez, goiburu gisa irakurtzen da.
            String goiburua = irakurlea.readLine();

            // Fitxategia hutsik badago, zerrenda hutsa itzultzen du.
            if (goiburua == null) {
                return neurketak;
            }

            String lerroa;

            // Fitxategiko gainerako lerroak banan-banan prozesatzen ditu.
            while ((lerroa = irakurlea.readLine()) != null) {

                // Lerro hutsak saltatzen ditu.
                if (lerroa.isBlank()) {
                    continue;
                }

                // Lerroa zutabeetan banatzen du, puntu eta koma erabiliz.
                String[] datuak = lerroa.split(";", -1);

                // Gutxieneko zutabe kopurua ez badago, lerroa saltatzen du.
                if (datuak.length < 7) {
                    continue;
                }

                try {
                    // Neurketa berri bat sortzen du.
                    Neurketa neurketa = new Neurketa();

                    // Kokapenaren eta neurketaren oinarrizko datuak esleitzen ditu.
                    neurketa.setProbintzia(datuak[0].trim());
                    neurketa.setUdalerria(datuak[1].trim());
                    neurketa.setEstazioa(datuak[2].trim());
                    neurketa.setMagnitudea(datuak[3].trim());
                    neurketa.setLaginketaPuntua(datuak[4].trim());

                    // Urtea eta hilabetea zenbaki oso bihurtzen ditu.
                    neurketa.setUrtea(
                            Integer.parseInt(datuak[5].trim()));
                    neurketa.setHilabetea(
                            Integer.parseInt(datuak[6].trim()));

                    // Eguneko datuak zazpigarren zutabetik aurrera hasten dira.
                    int zutabea = 7;

                    // Hilabeteko 31 egunetara arteko datuak aztertzen ditu.
                    for (int eguna = 1; eguna <= 31; eguna++) {

                        // Balioa eta baliozkotzea irakurtzeko zutaberik ez badago, amaitu.
                        if (zutabea + 1 >= datuak.length) {
                            break;
                        }

                        // Eguneko balioa eta baliozkotze-egoera irakurtzen ditu.
                        String balioa = datuak[zutabea].trim();
                        String baliozkotzea =
                                datuak[zutabea + 1].trim();

                        // Balioa badago, eguneko datua neurketari gehitzen dio.
                        if (!balioa.isEmpty()) {
                            neurketa.gehituDatu(
                                    new EgunekoDatu(
                                            eguna,
                                            balioa,
                                            baliozkotzea
                                    )
                            );
                        }

                        // Hurrengo egunaren bi zutabeetara pasatzen da.
                        zutabea += 2;
                    }

                    // Osatutako neurketa zerrendan gordetzen du.
                    neurketak.add(neurketa);

                } catch (NumberFormatException e) {
                    // Urtea edo hilabetea zenbaki bihurtu ezin bada, errorea adierazten du.
                    System.err.println(
                            "Errorea lerro bateko zenbakizko datuak irakurtzean: "
                                    + e.getMessage());
                }
            }
        }

        // Irakurritako neurketa guztiak itzultzen ditu.
        return neurketak;
    }
}