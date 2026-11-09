package paagbi.json;

import jakarta.json.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import paagbi.modeloa.*;

public class JsonIrakurlea {
    private final String fitxategia;

    public JsonIrakurlea(String fitxategia) { this.fitxategia = fitxategia; }

    public List<Neurketa> irakurri() throws IOException {
        List<Neurketa> neurketak = new ArrayList<>();
        try (JsonReader r = Json.createReader(
                new FileReader(fitxategia, StandardCharsets.UTF_8))) {
            for (JsonObject o : r.readArray().getValuesAs(JsonObject.class)) {
                Neurketa n = new Neurketa(
                    o.getString("probintzia"), o.getString("udalerria"),
                    o.getString("estazioa"), o.getString("magnitudea"),
                    o.getString("puntuMuestreo"),
                    o.getInt("urtea"), o.getInt("hilabetea"));
                for (JsonObject d : o.getJsonArray("egunekoDatuak")
                                     .getValuesAs(JsonObject.class)) {
                    n.gehituDatu(new EgunekoDatu(d.getInt("eguna"),
                        d.getString("balioa"), d.getString("baliozkotzea")));
                }
                neurketak.add(n);
            }
        } catch (JsonException e) {
            throw new IOException("JSON baliogabea: " + e.getMessage(), e);
        }
        return neurketak;
    }
}