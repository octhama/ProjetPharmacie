package io;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectureRegistrePreparation {
    public static @NotNull String lireRegistrePreparation(String s) throws IOException {
        BufferedReader reader = new BufferedReader (new FileReader("src/data/registrepreparation.csv"));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        reader.close();
        return stringBuilder.toString();
    }
}

