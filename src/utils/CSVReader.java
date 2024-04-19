package utils;

import enums.ETypeMedicament;
import pharmacie.Medicament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private String filePath;

    public CSVReader(String filePath) {
        this.filePath = filePath;
    }

    public List<Medicament> readMedicamentsFromCSV() {
        List<Medicament> medicaments = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header if necessary
            // br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Assuming CSV format: nom,prix,type,generique,quantiteEnStock
                String nom = data[0];
                double prix = Double.parseDouble(data[1]);
                ETypeMedicament type = ETypeMedicament.valueOf(data[2]);
                boolean generique = Boolean.parseBoolean(data[3]);
                int quantiteEnStock = Integer.parseInt(data[4]);

                Medicament medicament = new Medicament(nom, prix, type, generique, quantiteEnStock);
                medicaments.add(medicament);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return medicaments;
    }
}
