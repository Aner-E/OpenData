package paagbi.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvJsonSortzailea {

    // CSV fitxategia Internetetik irakurtzeko helbidea.
    private static final String URL_CSV = "https://raw.githubusercontent.com/Aner-E/OpenData/refs/heads/main/201410-10-calidad-aire-diario-csv.csv";

    // Sortuko den JSON fitxategiaren izena.
    private static final String JSON_BIDEA = "calidad-aire.json";

    /**
     * CSV fitxategia irakurri eta JSON fitxategia sortzen du.
     */
    public void sortu() throws IOException {

        // URL helbidea prestatu eta CSV fitxategira konektatzen da.
        URI uri = URI.create(URL_CSV.trim());
        URL url = uri.toURL();

        // Irteerako fitxategiaren bidea zehazten du.
        Path irteera = Paths.get(JSON_BIDEA);

        // Irakurlea eta idazlea ireki, eta amaieran automatikoki ixten ditu.
        try (BufferedReader irakurlea = new BufferedReader(
                new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
             BufferedWriter idazlea = Files.newBufferedWriter(irteera, StandardCharsets.UTF_8)) {

            // CSV fitxategiaren lehen lerroa goiburua denez, saltatu egiten da.
            irakurlea.readLine();

            // JSON arraya irekitzen du.
            idazlea.write("[");
            idazlea.newLine();

            String lerroa;
            boolean lehena = true;

            // CSV fitxategiko lerro guztiak banan-banan irakurtzen ditu.
            while ((lerroa = irakurlea.readLine()) != null) {

                // Lerro hutsak ez dira prozesatzen.
                if (lerroa.trim().isEmpty()) {
                    continue;
                }

                // Lerroa zutabeetan banatzen du, puntu eta koma erabiliz.
                String[] datuak = lerroa.split(";", -1);

                // Beharrezko zutabe guztiak ez badaude, lerroa saltatzen da.
                if (datuak.length < 7) {
                    continue;
                }

                // Objektuen artean koma jartzen du, lehenengoa izan ezik.
                if (!lehena) {
                    idazlea.write(",");
                    idazlea.newLine();
                }
                lehena = false;

                // Neurketa bakoitzaren JSON objektua osatzen hasten da.
                StringBuilder sb = new StringBuilder();
                sb.append("  {\n");
                sb.append("    \"probintzia\": ").append(testua(datuak[0])).append(",\n");
                sb.append("    \"udalerria\": ").append(testua(datuak[1])).append(",\n");
                sb.append("    \"estazioa\": ").append(testua(datuak[2])).append(",\n");
                sb.append("    \"magnitudea\": ").append(testua(datuak[3])).append(",\n");
                sb.append("    \"puntuMuestreo\": ").append(testua(datuak[4])).append(",\n");
                sb.append("    \"urtea\": ").append(Integer.parseInt(datuak[5].trim())).append(",\n");
                sb.append("    \"hilabetea\": ").append(Integer.parseInt(datuak[6].trim())).append(",\n");
                sb.append("    \"egunekoDatuak\": [");

                // Eguneko datuak zazpigarren zutabetik aurrera hasten dira.
                int zutabea = 7;
                boolean lehenDatua = true;

                // Hilabeteko 31 egunetara arteko balioak aztertzen ditu.
                for (int eguna = 1; eguna <= 31; eguna++) {

                    // Balioa eta baliozkotzea irakurtzeko zutabe nahikorik ez badago, amaitu.
                    if (zutabea + 1 >= datuak.length) {
                        break;
                    }

                    String balioa = datuak[zutabea];
                    String baliozkotzea = datuak[zutabea + 1];

                    // Balioa badago bakarrik gehitzen da eguneko datua.
                    if (!balioa.isBlank()) {

                        // Eguneko objektuen artean koma gehitzen du.
                        if (!lehenDatua) {
                            sb.append(",");
                        }
                        lehenDatua = false;

                        // Egunaren zenbakia, balioa eta baliozkotzea JSONen gordetzen ditu.
                        sb.append("\n      {")
                          .append("\"eguna\": ").append(eguna).append(", ")
                          .append("\"balioa\": ").append(testua(balioa)).append(", ")
                          .append("\"baliozkotzea\": ").append(testua(baliozkotzea))
                          .append("}");
                    }

                    // Hurrengo egunaren bi zutabeetara pasatzen da.
                    zutabea += 2;
                }

                // Eguneko datuen arraya eta neurketaren objektua ixten ditu.
                if (!lehenDatua) {
                    sb.append("\n    ");
                }
                sb.append("]\n  }");

                // Osatutako JSON objektua fitxategian idazten du.
                idazlea.write(sb.toString());
            }

            // JSON arraya ixten du.
            idazlea.newLine();
            idazlea.write("]");
        }

        // Sortutako fitxategiaren kokapen osoa pantailan erakusten du.
        System.out.println("JSON fitxategia sortuta: " + irteera.toAbsolutePath());
    }

    /**
     * Testua JSON formaturako prestatzen du, karaktere bereziak ihes eginez.
     */
    private String testua(String s) {
        StringBuilder sb = new StringBuilder("\"");

        // Testuko karaktereak banan-banan aztertzen ditu.
        for (char c : s.trim().toCharArray()) {
            switch (c) {
                case '"':  sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                case '\n': sb.append("\\n");  break;
                case '\r': sb.append("\\r");  break;
                case '\t': sb.append("\\t");  break;
                default:
                    // Kontrol-karaktereak Unicode formatuan idazten ditu.
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
            }
        }

        // Testuaren amaierako komatxoa gehitzen du.
        return sb.append("\"").toString();
    }
}