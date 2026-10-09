package main.java.eus.opendata.json;

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

    private static final String URL_CSV = "https://raw.githubusercontent.com/Aner-E/OpenData/refs/heads/main/201410-10-calidad-aire-diario-csv.csv";

    private static final String JSON_BIDEA = "calidad-aire.json";

    public void sortu() throws IOException {

        URI uri = URI.create(URL_CSV.trim());
        URL url = uri.toURL();

        Path irteera = Paths.get(JSON_BIDEA);

        try (BufferedReader irakurlea = new BufferedReader(
                new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
             BufferedWriter idazlea = Files.newBufferedWriter(irteera, StandardCharsets.UTF_8)) {

            irakurlea.readLine(); // goiburua saltatu

            idazlea.write("[");
            idazlea.newLine();

            String lerroa;
            boolean lehena = true;

            while ((lerroa = irakurlea.readLine()) != null) {

                if (lerroa.trim().isEmpty()) {
                    continue;
                }

                String[] datuak = lerroa.split(";", -1);

                if (datuak.length < 7) {
                    continue;
                }

                if (!lehena) {
                    idazlea.write(",");
                    idazlea.newLine();
                }
                lehena = false;

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

                int zutabea = 7;
                boolean lehenDatua = true;

                for (int eguna = 1; eguna <= 31; eguna++) {

                    if (zutabea + 1 >= datuak.length) {
                        break;
                    }

                    String balioa = datuak[zutabea];
                    String baliozkotzea = datuak[zutabea + 1];

                    if (!balioa.isBlank()) {

                        if (!lehenDatua) {
                            sb.append(",");
                        }
                        lehenDatua = false;

                        sb.append("\n      {")
                          .append("\"eguna\": ").append(eguna).append(", ")
                          .append("\"balioa\": ").append(testua(balioa)).append(", ")
                          .append("\"baliozkotzea\": ").append(testua(baliozkotzea))
                          .append("}");
                    }

                    zutabea += 2;
                }

                if (!lehenDatua) {
                    sb.append("\n    ");
                }
                sb.append("]\n  }");

                idazlea.write(sb.toString());
            }

            idazlea.newLine();
            idazlea.write("]");
        }

        System.out.println("JSON fitxategia sortuta: " + irteera.toAbsolutePath());
    }

    // JSON string bat sortzen du karaktere bereziak escapatuz
    private String testua(String s) {
        StringBuilder sb = new StringBuilder("\"");
        for (char c : s.trim().toCharArray()) {
            switch (c) {
                case '"':  sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                case '\n': sb.append("\\n");  break;
                case '\r': sb.append("\\r");  break;
                case '\t': sb.append("\\t");  break;
                default:
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.append("\"").toString();
    }

    public static void main(String[] args) throws IOException {
        new CsvJsonSortzailea().sortu();
    }
}