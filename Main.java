package QuizzService;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\EGlaciers\\Desktop\\Projects\\QuizzService\\Quizz.txt";
        QuestionService service = new QuestionService();
        service.readFromFile(filePath);
        service.displayQuestions();
        service.displayWrongQuestions();
    }
}
