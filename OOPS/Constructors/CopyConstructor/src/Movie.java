package Constructors.CopyConstructor.src;

public class Movie {
    String title;

    Movie(String title){
        this.title = title;
    }

    Movie(Movie movie){
        this.title = movie.title;
    }

    void displayMovie(){
        System.out.println("Title of the Movie is :  " + title);
    }
}
