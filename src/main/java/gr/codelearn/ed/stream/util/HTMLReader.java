/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codelearn.ed.stream.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dpetr
 */
public class HTMLReader implements Reader {

    @Override
    public List<String> read(String uriString) throws URISyntaxException, IOException {
        URI uri = new URI(uriString);
        URL url = uri.toURL();
        List<String> covidStatisticsList = new ArrayList<>();

        try (InputStream in = url.openStream(); InputStreamReader inr = new InputStreamReader(in); BufferedReader br = new BufferedReader(inr)) {
            String line;

            while ((line = br.readLine()) != null) {
                covidStatisticsList.add(line);
            }
        }

        return covidStatisticsList;
    }
}
