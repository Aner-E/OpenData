package main.java.eus.opendata.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import main.java.eus.opendata.modeloa.EgunekoDatu;
import main.java.eus.opendata.modeloa.Neurketa;

public class CsvIrakurlea {

    private static final String URL_CSV = "https://raw.githubusercontent.com/Aner-E/OpenData/refs/heads/main/201410-10-calidad-aire-diario-csv.csv";

    public List<Neurketa> irakurri() throws IOException {

        List<Neurketa> neurketak = new ArrayList<>();

        BufferedReader irakurlea = null;

        try {

            URI uri = URI.create(URL_CSV.trim());
            URL url = uri.toURL();

            irakurlea = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            irakurlea.readLine();

            String lerroa;

            while ((lerroa = irakurlea.readLine()) != null) {

                if (lerroa.trim().isEmpty()) {
                    continue;
                }

                String[] datuak = lerroa.split(";", -1);

                String probintzia = datuak[0];
                String udalerria = datuak[1];
                String estazioa = datuak[2];
                String magnitudea = datuak[3];
                String puntuMuestreo = datuak[4];

                int urtea = Integer.parseInt(datuak[5]);
                int hilabetea = Integer.parseInt(datuak[6]);

                Neurketa neurketa = new Neurketa(
                        probintzia,
                        udalerria,
                        estazioa,
                        magnitudea,
                        puntuMuestreo,
                        urtea,
                        hilabetea);

                int zutabea = 7;

                for (int eguna = 1; eguna <= 31; eguna++) {

                    if (zutabea + 1 >= datuak.length) {
                        break;
                    }

                    String balioa = datuak[zutabea];
                    String baliozkotzea = datuak[zutabea + 1];

                    if (!balioa.isBlank()) {

                        EgunekoDatu datua = new EgunekoDatu(
                                eguna,
                                balioa,
                                baliozkotzea);

                        neurketa.gehituDatu(datua);
                    }

                    zutabea += 2;
                }

                neurketak.add(neurketa);
            }

        } finally {

            if (irakurlea != null) {
                irakurlea.close();
            }
        }

        return neurketak;
    }
}