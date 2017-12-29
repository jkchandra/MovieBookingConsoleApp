
import Controllers.*;
import Models.Movie.*;
import Models.Cineplex.*;
import Models.Showtime.*;
import Models.User.*;
import Views.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * The main class
 * @author Kenneth
 */
public class MoblimaApplication {
    
    private static AccountController accountController;
    private static CineplexController cineplexController;
    private static MovieController movieController;
    private static ShowtimeController showtimeController;
    
    
    public static void main(String[] args) {
        init();
        LoginView.getInstance().login();
    }
    
    public static void init() {
        File directory = new File("Storage");
        if(!directory.exists()){
            directory.mkdir();
        }
        
        accountController = AccountController.getInstance();
        cineplexController = CineplexController.getInstance();
        movieController = MovieController.getInstance();
        showtimeController = ShowtimeController.getInstance();
        
        // Comment out to stop resetting.
        reset();
    }
    
    private static void reset() {
        File directory = new File("Storage");
        if(!directory.exists()){
            directory.delete();
        }
        
        accountController.resetList();
        cineplexController.resetList();
        movieController.resetList();
        showtimeController.resetList();
        
        accountController.addStaff("CathayStaff", "password1234");
        accountController.addMovieGoer("CathayCustomer"
                , "password1234"
                , MovieGoerType.NORMAL
                , "Chandra", 91234992, "chandra@gmail.com");
        
        accountController.addMovieGoer("ShawCustomer"
                , "password1234"
                , MovieGoerType.STUDENT
                , "Kelly", 91243242, "kelly@gmail.com");
        
        accountController.addMovieGoer("GVCustomer"
                , "password1234"
                , MovieGoerType.ELDERLY
                , "Jane", 92526642, "jane@gmail.com");
        populateMovies();
        populateCineplex();
        populateShowtime();
    }
    
    private static void populateShowtime() {
        ShowtimeController tempController = ShowtimeController.getInstance();
        Showtime tempShowtime;
        MovieGoer tempMovieGoer;
        
        Cineplex tempCineplex = CineplexController
                .getInstance().getCineplexByLocation("Jurong");
        
        Cinema tempCinema = CineplexController
                .getInstance()
                .getCinemaByCode(tempCineplex, "J01");
        
        Movie tempMovie = MovieController.getInstance().getMovie("Alpha");
        
        tempController.addShowtime(tempCinema
                , tempMovie, "20/11/2017 10:20:00");
        
        tempController.addShowtime(tempCinema
                , tempMovie, "20/11/2017 15:20:00");

        tempShowtime = tempController.addShowtime(tempCinema
                , tempMovie, "20/11/2017 20:20:00");
        
        tempMovieGoer = (MovieGoer) AccountController.getInstance()
                .getUser("CathayCustomer");
        
        showtimeController.bookSeat(tempMovieGoer, tempShowtime, 1, 1);
        showtimeController.bookSeat(tempMovieGoer, tempShowtime, 1, 2);
        showtimeController.bookSeat(tempMovieGoer, tempShowtime, 2, 1);
        showtimeController.bookSeat(tempMovieGoer, tempShowtime, 2, 2);
        
        tempMovieGoer = (MovieGoer) AccountController.getInstance()
                .getUser("ShawCustomer");
        
        showtimeController.bookSeat(tempMovieGoer, tempShowtime, 3, 1);
        showtimeController.bookSeat(tempMovieGoer, tempShowtime, 3, 2);
        
        tempMovieGoer = (MovieGoer) AccountController.getInstance()
                .getUser("GVCustomer");
        
        showtimeController.bookSeat(tempMovieGoer, tempShowtime, 4, 2);
        showtimeController.bookSeat(tempMovieGoer, tempShowtime, 4, 3);
    }
    
    private static void populateCineplex() {
        Cineplex tempCineplex;
        Cinema tempCinema;
        
        CineplexController tempController 
                = CineplexController.getInstance();
        
        tempCineplex = tempController.addCineplex("Jurong");
        
        tempCinema = tempController.createCinema("J01"
                , CinemaPreset.LARGE);
        
        tempController.addCinemaToCineplex(tempCineplex, tempCinema);
        
        tempCinema = tempController.createCinema("J02"
                , CinemaPreset.MEDIUM);
         
        tempController.addCinemaToCineplex(tempCineplex, tempCinema);
        
        tempCinema = tempController.createCinema("J03"
                , CinemaPreset.PLATINUM);
         
        tempController.addCinemaToCineplex(tempCineplex, tempCinema);
             
    }
    
    
    private static void populateMovies() {
        // Creating Movie Alpha
        ArrayList<String> castList1 = new ArrayList<>();
        ArrayList<MovieGenre> genreList1 = new ArrayList<>();
        castList1.add("Ghandi");
        castList1.add("Zedd");
        genreList1.add(MovieGenre.ACTION);
        genreList1.add(MovieGenre.ADVENTURE);
        MovieController.getInstance().addMovie("Alpha", 121, genreList1 
                ,MovieType._2D,MovieRating.G, "A cool little movie"
                , "Steven Spielberg" 
                , castList1 , ShowingStatus.NOWSHOWING);

        // Creating Movie Terminator 2
        ArrayList<String> castList2 = new ArrayList<>();
        ArrayList<MovieGenre> genreList2 = new ArrayList<>();
        castList2.add("Arnold");
        castList2.add("Linda");
        genreList2.add(MovieGenre.ACTION);
        genreList2.add(MovieGenre.THRILLER);
        MovieController.getInstance().addMovie("Terminator 2", 137, genreList2 
                ,MovieType._2D, MovieRating.PG
                ,  "A cyborg, identical to the one who failed "
                        + "to kill Sarah Connor,"
                        + " must now protect her ten year old son, John Connor, "
                        + "from a more advanced cyborg"
                , "James Cameron" 
                , castList2 , ShowingStatus.NOWSHOWING);

        // Creating Movie World War Z
        ArrayList<String> castList3 = new ArrayList<>();
        ArrayList<MovieGenre> genreList3 = new ArrayList<>();
        castList3.add("Brad Pitt");
        castList3.add("Enos");
        genreList3.add(MovieGenre.HORROR);
        genreList3.add(MovieGenre.THRILLER);
        genreList3.add(MovieGenre.ACTION);
        MovieController.getInstance().addMovie("World War Z", 116, genreList3 
                ,MovieType._2D, MovieRating.NC_17
                , "Former United Nations employee Gerry Lane traverses the "
                        + "world in a race against time to "
                        + "stop the Zombie pandemic that is toppling armies "
                        + "and governments, and threatening to "
                        + "destroy humanity itself." 
                , "Marc Forster"
                , castList3 , ShowingStatus.NOWSHOWING);

        // Creating Prometheus
        ArrayList<String> castList4 = new ArrayList<>();
        ArrayList<MovieGenre> genreList4 = new ArrayList<>();
        castList4.add("Noomi");
        castList4.add("Logan");
        genreList4.add(MovieGenre.HORROR);
        genreList4.add(MovieGenre.THRILLER);
        MovieController.getInstance().addMovie("Prometheus", 124, genreList4 
                ,MovieType._2D, MovieRating.NC_17
                , "Following clues to the origin of mankind,"
                        + " a team finds a structure on a distant"
                        + " moon. "
                , "Ridley Scott", castList4 
                ,ShowingStatus.COMINGSOON);

        // Creating Movie Salt
        ArrayList<String> castList5 = new ArrayList<>();
        ArrayList<MovieGenre> genreList5 = new ArrayList<>();
        castList5.add("Angelina Jolie");
        castList5.add("Liev");
        genreList5.add(MovieGenre.ACTION);
        genreList5.add(MovieGenre.ADVENTURE);
        MovieController.getInstance().addMovie("Salt", 100, genreList5 
                ,MovieType._2D, MovieRating.PG
                ,  "A CIA agent goes on the run after a "
                        + "defector accuses her of being a "
                        + "Russian spy" 
                ,"Phillip Noyce", castList5 
                , ShowingStatus.PREVIEW);
        
        // Creating Movie The Dark Knight
        ArrayList<String> castList6 = new ArrayList<>();
        ArrayList<MovieGenre> genreList6 = new ArrayList<>();
        castList6.add("Christian Bale");
        castList6.add("Heath Ledger");
        castList6.add("Michael Caine");
        genreList6.add(MovieGenre.ACTION);
        genreList6.add(MovieGenre.THRILLER);
        MovieController.getInstance().addMovie("The Dark Knight", 130
                , genreList6 ,MovieType._2D, MovieRating.PG
                , "When the menace known as the Joker emerges from his "
                        + "mysterious past, he wreaks "
                        + "havoc and chaos on the people of Gotham, "
                        + "the Dark Knight must accept one "
                        + "of the greatest psychological and physical tests "
                        + "of his ability to fight "
                        + "injustice."
                ,"Christopher Nolan", castList6 , ShowingStatus.NOWSHOWING);
        
        // Creating Movie The Notebook
        ArrayList<String> castList7 = new ArrayList<>();
        ArrayList<MovieGenre> genreList7 = new ArrayList<>();
        castList7.add("Guna Rowlands");
        castList7.add("James Garnaer");
        castList7.add("Rachel McAdams");
        genreList7.add(MovieGenre.DRAMA);
        genreList7.add(MovieGenre.ROMANCE);
        MovieController.getInstance().addMovie("The Notebook", 123, genreList7 
                ,MovieType._2D, MovieRating.PG_13, 
                        "WA poor yet passionate young man falls in love with "
                        + "a rich young woman, giving her a sense of freedom,"
                        + " but they are soon separated because of "
                        + "their social differences.","Nick Cassavetes"
                , castList7 , ShowingStatus.NOWSHOWING);
        
        // Creating Movie Shutter Island
        ArrayList<String> castList8 = new ArrayList<>();
        ArrayList<MovieGenre> genreList8 = new ArrayList<>();
        castList8.add("Leonardo DiCaprio");
        castList8.add("Emily Mortimer");
        castList8.add("Mark Ruffalo");
        genreList8.add(MovieGenre.ACTION);
        genreList8.add(MovieGenre.THRILLER);
        MovieController.getInstance().addMovie("Shutter Island", 138,
                genreList8 ,MovieType._2D, MovieRating.R
                , "In 1954, a U.S. Marshal investigates the disappearance of "
                        + "a murderer, who escaped from a hospital for the "
                        + "criminally insane."
                ,"Martin Scorsese", castList8 , ShowingStatus.PREVIEW);
        
        // Creating Movie Shrek
        ArrayList<String> castList9 = new ArrayList<>();
        ArrayList<MovieGenre> genreList9 = new ArrayList<>();
        castList9.add("Mike Myers");
        castList9.add("Eddie Murphy");
        castList9.add("Cameron Diaz");
        genreList9.add(MovieGenre.ANIMATION);
        genreList9.add(MovieGenre.ADVENTURE);
        genreList9.add(MovieGenre.COMEDY);
        MovieController.getInstance().addMovie("Shrek", 90, genreList9 
                ,MovieType._2D, MovieRating.PG
                , "After his swamp is filled with magical creatures, Shrek "
                        + "agreesmto rescue Princess Fiona for a "
                        + "villainous lord in order to get his land back."
                ,"Andrew Adamson", castList9 , ShowingStatus.PREVIEW);
        
        // Creating Movie UP
        ArrayList<String> castList10 = new ArrayList<>();
        ArrayList<MovieGenre> genreList10 = new ArrayList<>();
        castList10.add("Edward Asner");
        castList10.add("Jordan Nagai");
        castList10.add("John Ratzenberger");
        genreList10.add(MovieGenre.ANIMATION);
        genreList10.add(MovieGenre.ADVENTURE);
        genreList10.add(MovieGenre.COMEDY);
        MovieController.getInstance().addMovie("UP", 96, genreList10
                ,MovieType._2D, MovieRating.PG
                , "Seventy-eight year old Carl Fredricksen travels to "
                        + "Paradise Falls in his home equipped with balloons, "
                        + "inadvertently taking a young stowaway."
                ,"Andrew Adamson", castList10 , ShowingStatus.PREVIEW);
        
        // Creating Movie How to Train Your Dragon
        ArrayList<String> castList11 = new ArrayList<>();
        ArrayList<MovieGenre> genreList11 = new ArrayList<>();
        castList11.add("Jay Baruchel");
        castList11.add("Gerard Butler");
        genreList11.add(MovieGenre.ANIMATION);
        genreList11.add(MovieGenre.ADVENTURE);
        genreList11.add(MovieGenre.COMEDY);
        MovieController.getInstance().addMovie("How to Train Your Dragon", 98
                , genreList11 ,MovieType._2D, MovieRating.PG
                , "A hapless young Viking who aspires to hunt dragons becomes "
                        + "the unlikely friend of a young dragon himself, and "
                        + "learns there may be more to the creatures than he "
                        + "assumed."
                ,"Dean DeBlois", castList11 , ShowingStatus.PREVIEW);
        
        // Creating Movie The X-Files
        ArrayList<String> castList12 = new ArrayList<>();
        ArrayList<MovieGenre> genreList12 = new ArrayList<>();
        castList12.add("David Duchovny");
        castList12.add("Gillian Anderson");
        castList12.add("Mitch ouef Anderson");
        genreList12.add(MovieGenre.ANIMATION);
        genreList12.add(MovieGenre.ADVENTURE);
        genreList12.add(MovieGenre.COMEDY);
        MovieController.getInstance().addMovie("The X-Files", 148, genreList12 
                ,MovieType._2D, MovieRating.PG
                , "Two FBI agents, Fox Mulder the believer and Dana Scully "
                        + "the skeptic,investigate the strange and unexplained, "
                        + "while hidden forces work to impede their efforts."
                , "Chris Carter", castList12 , ShowingStatus.COMINGSOON);
        
        // Creating Movie Black Swan
        ArrayList<String> castList13 = new ArrayList<>();
        ArrayList<MovieGenre> genreList13 = new ArrayList<>();
        castList13.add("Natalie Portman");
        castList13.add("Mila Kunis");
        castList13.add("Vincent Cassel");
        genreList13.add(MovieGenre.DRAMA);
        genreList13.add(MovieGenre.THRILLER);
        MovieController.getInstance().addMovie("Black Swan", 108, genreList13 
                ,MovieType._3D, MovieRating.R
                , "A committed dancer wins the lead role in a production of "
                        + "Tchaikovsky's \"Swan Lake\" only to "
                        + "find herself struggling to maintain her sanity."
                , "Darren Aronofsky", castList13 , ShowingStatus.PREVIEW);
        
         // Creating Movie  The Avengers
        ArrayList<String> castList14 = new ArrayList<>();
        ArrayList<MovieGenre> genreList14 = new ArrayList<>();
        castList14.add("Robert Downey Jr.");
        castList14.add("Chris Evans");
        castList14.add("Scarlett Johansson");
        genreList14.add(MovieGenre.ACTION);
        genreList14.add(MovieGenre.ADVENTURE);
        MovieController.getInstance().addMovie("Black Swan 2", 143, genreList14
                ,MovieType._3D, MovieRating.PG_13
                , "Earth's mightiest heroes must come "
                        + "together and learn to fight as a team if they "
                        + "are going to stop the mischievous Loki and his "
                        + "alien army from enslaving humanity."
                , "Darren Aronofsky", castList14 , ShowingStatus.NOWSHOWING);
        
         // Creating Movie Star Wars
        ArrayList<String> castList15 = new ArrayList<>();
        ArrayList<MovieGenre> genreList15 = new ArrayList<>();
        castList15.add("Daisy Ridley");
        castList15.add("John Boyega");
        castList15.add("Mark Hamill");
        genreList15.add(MovieGenre.ACTION);
        genreList15.add(MovieGenre.ADVENTURE);
        genreList15.add(MovieGenre.FANTASY);
        MovieController.getInstance().addMovie("Star Wars", 150, genreList15
                ,MovieType._3D, MovieRating.PG_13
                , "Having taken her first steps into the Jedi world, Rey "
                        + "joins Luke Skywalker on an adventure with Leia,"
                        + " Finn and Poe that unlocks mysteries of "
                        + "the Force and secrets of the past."
                , "Rian Johnson", castList15 , ShowingStatus.NOWSHOWING);
    }
}
