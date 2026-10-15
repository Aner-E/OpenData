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

public class CsvIrakurlea {

    private static final String URL_CSV = "https://raw.githubusercontent.com/Aner-E/OpenData/refs/heads/main/201410-10-calidad-aire-diario-csv.csv";

    public List<Neurketa> irakurri() throws IOException {

        List<Neurketa> neurketak = new ArrayList<>();

        URI uri = URI.create(URL_CSV);
        URL url = uri.toURL();

        try (BufferedReader irakurlea = new BufferedReader(
                new InputStreamReader(
                        url.openStream(),
                        StandardCharsets.UTF_8))) {

            String goiburua = irakurlea.readLine();

            if (goiburua == null) {
                return neurketak;
            }

            String lerroa;

            while ((lerroa = irakurlea.readLine()) != null) {

                if (lerroa.isBlank()) {
                    continue;
                }

                String[] datuak = lerroa.split(";", -1);

                if (datuak.length < 7) {
                    continue;
                }

                try {
                    Neurketa neurketa = new Neurketa();

                    neurketa.setProbintzia(datuak[0].trim());
                    neurketa.setUdalerria(datuak[1].trim());
                    neurketa.setEstazioa(datuak[2].trim());
                    neurketa.setMagnitudea(datuak[3].trim());
                    neurketa.setLaginketaPuntua(datuak[4].trim());
                    neurketa.setUrtea(
                            Integer.parseInt(datuak[5].trim()));
                    neurketa.setHilabetea(
                            Integer.parseInt(datuak[6].trim()));

                    int zutabea = 7;

                    for (int eguna = 1; eguna <= 31; eguna++) {

                        if (zutabea + 1 >= datuak.length) {
                            break;
                        }

                        String balioa = datuak[zutabea].trim();
                        String baliozkotzea =
                                datuak[zutabea + 1].trim();

                        if (!balioa.isEmpty()) {
                            neurketa.gehituDatu(
                                    new EgunekoDatu(
                                            eguna,
                                            balioa,
                                            baliozkotzea
                                    )
                            );
                        }

                        zutabea += 2;
                    }

                    neurketak.add(neurketa);

                } catch (NumberFormatException e) {
                    System.err.println(
                            "Errorea lerro bateko zenbakizko datuak irakurtzean: "
                                    + e.getMessage());
                }
            }
        }

        return neurketak;
    }
}