package org.example.fx;

import java.util.Base64;

public class Prueba {

    public static void main(String[] args) {
        String originalInput = "hola";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println(encodedString);
        String originalInput2 = "hola";
        String encodedString2 = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println(encodedString);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
    }
}
