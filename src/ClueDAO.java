import model.Clue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClueDAO {
    private String fileName;
    
    public ClueDAO(String fileName) {
        this.fileName = fileName;
    }
    
    public List<Clue> loadClues() {
        List<Clue> clues = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip lines that might be comments or empty
                if (line.trim().isEmpty() || line.trim().startsWith("//")) {
                    continue;
                }
                
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    clues.add(new Clue(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading clues: " + e.getMessage());
        }
        
        // Add a default clue if file is empty or not found
        if (clues.isEmpty()) {
            clues.add(new Clue("Who committed the crime?", "The butler"));
        }
        
        return clues;
    }
}
