package QuizzService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class QuestionService {
    ArrayList<Question> questions;
    ArrayList<Question> wrongQuestions;
    private int score = 0;

    public void readFromFile(String filePath) {
        //XulyFile va nem vao array of questions
        //Suppose that our file is normalized
        try {
            File file = new File(filePath);
            Scanner scan = new Scanner(file);
            int id = 1; 
            questions = new ArrayList<>();

            while (scan.hasNextLine()) {
                String question = scan.nextLine();
                String opt1 = scan.nextLine();
                String opt2 = scan.nextLine();
                String opt3 = scan.nextLine();
                String opt4 = scan.nextLine();
                String answer = scan.nextLine();
                questions.add(new Question(id, question, opt1, opt2, opt3, opt4, answer));
                id++;
            }

            scan.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displayQuestions() {
        //Display questions one by one; request users to enter the answer {A, B, C, D}
        //Xu ly answers, sai cu phap
        //if true score +1
        //return final score
        //return the true answer and question's id which were wrong 
        wrongQuestions = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        for (Question q : questions) {
            System.out.print(q.displayQuestion());

            //User enter answer:
            System.out.print("Enter your answer: ");
            String userAnswer = scan.next();
            scan.nextLine(); //This fix the duped line "Error syntax, enter again: "


            //Check the format of the answer and increase score if the ans is true
            if (Set.of("A", "B", "C", "D", "a", "b", "c", "d").contains(userAnswer)) {
                userAnswer = userAnswer.toUpperCase();
                if (userAnswer.equals(q.getAnswer())) {
                    score++;
                } else {
                    wrongQuestions.add(q);
                }
            }
            else {
                while (!Set.of("A", "B", "C", "D", "a", "b", "c", "d").contains(userAnswer)) {
                    System.out.print("Error syntax, enter again: ");
                    userAnswer = scan.nextLine();
                }
                
            }
        }

        //Final score
        System.out.println("Final score: " + this.score + "/" + questions.size());
        scan.close();
    }

    public void displayWrongQuestions() {
        System.out.println("Wrong questions: ");
        for (int i = 0; i < wrongQuestions.size() - 1; i++) {
            System.out.print(wrongQuestions.get(i).getId() + ", ");
        }
        System.out.print(wrongQuestions.get(wrongQuestions.size() - 1).getId() + ".");
    
    }
}
