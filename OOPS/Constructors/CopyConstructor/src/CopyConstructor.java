package Constructors.CopyConstructor.src;


public class CopyConstructor {
    public static void main (String[] args){
        Movie movie1 = new Movie("Interstellar");
        Movie movie2 = new Movie(movie1);

        movie1.displayMovie();
        movie2.displayMovie();
    }
}
