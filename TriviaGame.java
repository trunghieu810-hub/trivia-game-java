import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class TriviaGame {
    private Player player1;
    private Player player2;
    private Question[] questions;
    private Scanner kb;
    private Random rd;
    private int questionCount;

    public static void main(String[] args) throws IOException {
        TriviaGame game = new TriviaGame();
        game.run();
    }

    public TriviaGame() throws IOException{
        kb = new Scanner(System.in);
        rd = new Random();
        questions = new Question[15];
        questionCount = 0;
        loadQuestionsFromFile("TriviaQuestions.txt");
    }

    
    private void loadQuestionsFromFile(String filename) throws IOException {
            Scanner fileScanner = new Scanner(locateQuestionFile(filename));
            int index = 0; //index for questions array
            while (fileScanner.hasNextLine() && index < questions.length) {
                String line = fileScanner.nextLine();
                int barPos = line.indexOf('|');
                String questionText = line.substring(0, barPos);
                String answer = line.substring(barPos + 1);
                questions[index] = new Question(questionText, answer);
            
                
                index++;
                }
            questionCount = index;
            fileScanner.close();
    }

    private File locateQuestionFile(String filename) throws IOException {
        File file = new File(filename);
        if (file.exists()) {
            return file;
        }

        try {
            File classLocation = new File(TriviaGame.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File baseDirectory = classLocation.isDirectory() ? classLocation : classLocation.getParentFile();
            file = new File(baseDirectory, filename);
        }
        catch (URISyntaxException e) {
            throw new IOException("Unable to locate the question file.", e);
        }

        if (!file.exists()) {
            throw new IOException("Question file not found: " + filename);
        }

        return file;
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            choice = readMenuChoice();
            process(choice);
        } while (choice != 4);

        displayFinalResults();
    }

    private int readMenuChoice() {
        while (true) {
            String input = kb.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 4) {
                    return choice;
                }
                System.out.print("Please enter a number from 1 to 4 >> ");
            }
            catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number from 1 to 4 >> ");
            }
        }
    }


    public void displayMenu() {
        System.out.println("\nTrivia Quiz Game - Main Menu");
        System.out.println("1. Add Player");
        System.out.println("2. Ask Question");
        System.out.println("3. Display Players");
        System.out.println("4. Exit");
        System.out.print("Enter choice >> ");
    }


    public void process(int choice) {
        switch (choice) {
            case 1: addPlayer(); break;
            case 2: askQuestion(); break;
            case 3: displayPlayers(); break;
            case 4: System.out.println("Exiting game..."); break;
            default: System.out.println("Invalid choice!");
        }
    }

    public void addPlayer() {

       if (player1 != null && player2 != null) {
            System.out.println("Already have two player. Cannot add more.");
            return;
       }

        System.out.println("Enter player ID >> ");
        String id = kb.nextLine();

        System.out.println("Enter player name >> ");
        String name = kb.nextLine();

        if ((player1 != null && player1.getPlayerId().equalsIgnoreCase(id)) || (player2 != null && player2.getPlayerId().equalsIgnoreCase(id))) {
            System.out.println("Player ID already exists. Please try a different ID");
            return;
        }
        Player p = new Player(id, name);

        if (player1 == null) {
            player1 = p;
        }
        else {
            player2 = p;
        }
        System.out.println("Player added successfully");

    }

    public void askQuestion() {
       if (player1 == null && player2 == null) {
            System.out.println("No players exist. Add at least one player first.");
            return;
       }

       Player target;
       if (player1 != null && player2 == null) {
            target = player1;
            System.out.println(player1.getName() + " will answer the question.");
       }
       else if (player1 == null && player2 != null) {
            target = player2;
            System.out.println(player2.getName() + " will answer the question.");
       }
       else {
            target = null;
            while (target == null) {
                System.out.println("Select player to answer >> ");
                System.out.println("1. " + player1.getName());
                System.out.println("2. " + player2.getName());
                System.out.println("Enter choice >> ");
                String choice = kb.nextLine();

                if (choice.isEmpty()) {
                    return;
                }
                if (choice.equals("1")) {
                    target = player1;
                }
                else if (choice.equals("2")) {
                    target = player2;
                }
                else {
                    System.out.println("Invalid choice! Try again ");
                }
            }
       }
       if (questionCount == 0) {
            System.out.println("No questions are available.");
            return;
       }
       int idx = rd.nextInt(questionCount);
       Question q = questions[idx];
       System.out.println("Question: " + q.getText());
       System.out.println("Your answer: ");
       String userAnswer = kb.nextLine();

       if (q.checkAnswer(userAnswer)) {
            target.incrementScore(2);
            System.out.println("Correct! +2 points");
       }
       else {
            target.incrementScore(-1);
            System.out.println("Wrong! -1 point. Correct answer: " + q.getAnswer());
       }
    }

    public void displayPlayers() {
       if (player1 == null && player2 == null) {
            System.out.println("No players added yet");
            return;
       }
       if (player1 != null) {
            System.out.println(player1.toString()); 
       }
       if (player2 != null) {
            System.out.println(player2.toString());
       }
    }

    private void displayFinalResults() {
        System.out.println("--- Final Scores ---");
        if (player1 == null && player2 == null) {
            System.out.println("No players were added.");
            return;
        }
        if (player1 == null && player2 != null) {
            System.out.println(player2.toString());
            return;
        }
        if (player1 != null && player2 == null) {
            System.out.println(player1.toString());
            return;
        }

       System.out.println(player1.toString());
       System.out.println(player2.toString());

       if (player1.getScore() > player2.getScore()) {
            System.out.println("Winner: " + player1.getName());
            return;
       }
       if (player2.getScore() > player1.getScore()) {
            System.out.println("Winner: " + player2.getName());
            return;
       }
       if (player2.getScore() == player1.getScore()) {
            System.out.println("It's a tie!");
       }
    }
}
