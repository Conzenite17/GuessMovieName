import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Movie {
    //Fields:
    char[] movieName;
    boolean[] correctLetters;

    //Constructor:
    Movie(){
        String movNameStr = Movie.getMovieNameString();
        movieName = new char[movNameStr.length()];
        correctLetters = new boolean[movNameStr.length()];

        for(int i = 0; i < movNameStr.length(); i++){
            movieName[i] = movNameStr.charAt(i);
            correctLetters[i] = false;
        }
    }

    //Methods:
    public boolean allCorrectLetters(){
       for(int i = 0; i < correctLetters.length; i++){
           if(correctLetters[i] == false){
               return false;
           }
       }
       return true;
    }

    /**
     * method to return string of revealed
     * @return name of movie as string
     */
    public String revealName(){
        String name ="";
        for(int i = 0; i < this.movieName.length; i++){
            name+= movieName[i];
        }
        return name;
    }

    public String hiddenMovieName(){
        String string = "";
        for(int i = 0; i < movieName.length; i++){
            if(correctLetters[i]){
                string+= movieName[i];
            }
            else{
                string+= "_";
            }
        }
        return string;
    }

    public void revealLetters(char userInp){
        for(int i = 0; i < correctLetters.length; i++){
            if (movieName[i] == userInp){
                correctLetters[i] = true;
            }
        }
    }

    public int nameLength(){
        return movieName.length;
    }

    /**
     * method to read in text fil and pick a random line
     * @return random line in text file!
     */
    public static String getMovieNameString(){
        try {

            File movieListFile = new File("MovieList.txt");
            Scanner fileScanner = new Scanner(movieListFile);
            String[] moviesOnList = new String[(int) movieListFile.length()];
            int lineNumber = 1;
            while (fileScanner.hasNextLine()) {
                moviesOnList[lineNumber] = fileScanner.nextLine();
                lineNumber++;
            }
            int rndLineNumber = (int) (Math.random() * (lineNumber - 1)) + 1;
            return moviesOnList[rndLineNumber];


        } catch (FileNotFoundException exception){
            System.out.println("Error loading file");
            return "!";
        }
    }
}
