import dao.ClueDAO;
import java.util.List;
import model.Clue;
import model.Game;
import model.Player;
import utils.InputUtils;
import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Virtual Detective Application");
        
        try {
            // Check if clues file exists, if not create with default values
            File cluesFile = new File("clues.txt");
            if (!cluesFile.exists()) {
                createDefaultCluesFile();
            }
            
            String playerName = InputUtils.getStringInput("Enter your name: ");
            Player player = new Player(playerName);
            Game game = new Game(player);

            ClueDAO clueDAO = new ClueDAO("clues.txt");
            List<Clue> clues = clueDAO.loadClues();

            System.out.println("\nWelcome to the Virtual Detective game, " + player.getName() + "!");
            System.out.println("You will be presented with clues. Try to solve them correctly.\n");

            for (Clue clue : clues) {
                System.out.println("CLUE: " + clue.getQuestion());
                String answer = InputUtils.getStringInput("Your answer: ");
                if (answer.equalsIgnoreCase(clue.getAnswer())) {
                    System.out.println("Correct! +1 point\n");
                    player.increaseScore(1);
                } else {
                    System.out.println("Wrong! The correct answer is: " + clue.getAnswer() + "\n");
                }
            }

            System.out.println("Game Over! Your final score: " + player.getScore());
            
            // Close the scanner when done
            InputUtils.closeScanner();

        } catch (Exception e) {
            System.err.println("Error running the application: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Application finished running");
    }
    
    private static void createDefaultCluesFile() {
        try {
            PrintWriter writer = new PrintWriter("clues.txt", "UTF-8");
            writer.println("Who was at the crime scene last night?|The butler");
            writer.println("What weapon was used in the murder?|Candlestick");
            writer.println("Where was the body found?|Library");
            writer.println("What time did the crime occur?|Midnight");
            writer.println("What was the motive for the crime?|Inheritance");
            writer.close();
            System.out.println("Created default clues file.");
        } catch (Exception e) {
            System.err.println("Error creating default clues file: " + e.getMessage());
        }
    }
}
