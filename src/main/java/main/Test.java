package main;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    private static String wczytajPlik(String nazwa) {
        try(BufferedReader br = new BufferedReader(new FileReader(nazwa))) {
            StringBuilder sb = new StringBuilder();
            String linia = br.readLine();

            while (linia != null) {
                sb.append(linia);
                sb.append(System.lineSeparator());
                linia = br.readLine();
            }

            System.out.println("TEST");

            return sb.toString();
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) throws JSONException {
        String outputName = args[0];
        String expectedOutputName = args[1];

        String json = wczytajPlik(outputName);
        String expectedJson = wczytajPlik(expectedOutputName);

        assert json != null;
        assert expectedJson != null;

        JSONCompareResult result = JSONCompare.compareJSON(json, expectedJson, JSONCompareMode.STRICT);
        System.out.println(result.toString());
    }
}
