/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codelearn.ed.stream.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * @author dpetr
 */
public interface Reader {

    List<String> read(String uriString) throws URISyntaxException, IOException;
}
