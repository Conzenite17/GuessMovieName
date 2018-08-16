import java.util.Scanner;

public class Game {
    //Fields:
    private static char[] incorrectLetters; //used to store any newly guessed incorrect letters
    private static int points;
    private static Movie movie;
    private static boolean hasWon;


    //Constructors: (unsure definition on constructors. Find out how to use auto constructors for efficiency
    Game(){
        movie = new Movie();
        incorrectLetters = new char[10];
        points = 0;
        hasWon = false;
    }


    //Methods:
    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        char userInp;
        System.out.println("Movie name: " + movie.revealName());
        while(points < 10 && !hasWon) {
            System.out.println("You are guessing: " + movie.hiddenMovieName() + "\n" +
                    "You have guessed (" + points + ") wrong letters: " + Game.displayIncorrectLetters() + "\n" +
                    "Guess a letter: ");
            userInp = scanner.nextLine().charAt(0);

            if (newLetter(userInp)){
                if(letterInName(userInp)){
                    movie.revealLetters(userInp);
                }
                else{
                    incorrectLetters[points] = userInp;
                    points++;
                }
            }
            else{
                System.out.println("This letter has already been guessed");
            }

            if(movie.allCorrectLetters()){
                hasWon = true;
            }
        }
        if(hasWon){
            System.out.println("You Win! \n" +
                    "You have guessed '"+ movie.revealName() +"' correctly.");
        }
        else{
            System.out.println("Game Over! \n" +
                    "You had 10 incorrect guesses" +
                    "The movie was'"+ movie.revealName() +"' \n" +
                    "You lost");
        }
    }

    /**
     * checks to see if the userInp is within incorrectLetters OR correctLetters
     * @param userInp letter the user entered
     * @return true if letter has not been guessed already.
     */
    public static boolean newLetter(char userInp){
        for (int i = 0; i < points; i++){
            if(userInp == incorrectLetters[i]){
                return false; // letter already incorrect
            }
        }
        for (int i = 0; i < movie.nameLength(); i++){
            if (movie.correctLetters[i] == true && userInp == movie.movieName[i]){
                return false; // letter already correct
            }
        }
        return true;
    }

    public static boolean letterInName(char userInp){
        for(int i = 0; i < movie.nameLength(); i++){
            if(movie.movieName[i] == userInp){
                return true;
            }
        }
        return false;
    }

    public static String displayIncorrectLetters(){
        String string = ""+incorrectLetters[0];
        for (int i = 1; i < points; i++){
            string+= ", " + incorrectLetters[i];
        }
        return string;
    }
}
