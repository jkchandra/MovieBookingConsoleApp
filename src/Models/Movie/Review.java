package Models.Movie;

import Models.User.MovieGoer;
import Utilities.Entity;

/**
 * Class that represents a Movie Review
 * @author Lim De Quan, Chandra
 */
public class Review extends Entity{
    
    private static final long serialVersionUID = 12324134119L;
    
    /**
     * MovieGoer that wrote the review
     */
    private MovieGoer reviewer;
    
    /**
     * Review rating of the movie review
     */
    private int reviewRating;
    
    /**
     * Text of the movie review
     */
    private String review;
    
    /**
     * Creates a movie review
     * @param reviewer the MovieGoer that authored the review
     * @param reviewRating the rating given by the reviewer
     * @param review the text of the review
     */
    public Review(MovieGoer reviewer, int reviewRating, String review) {
        this.reviewer = reviewer;
        this.reviewRating = reviewRating;
        this.review = review;
    }
    
    /**
     * Edit a movie review
     * @param reviewRating the rating given by the reviewer
     * @param review the text of the review
     */
    public void editReview(int reviewRating, String review){
        this.reviewRating = reviewRating;
        this.review = review;
    }
    
    /**
     * Get the MovieGoer object of the author of this review
     * @return the username of the author
     */
    public MovieGoer getReviewer(){
        return reviewer;
    }
    
    /**
     * Get the name of the author of this review
     * @return the username of the author
     */
    public String getReviewerName(){
        return reviewer.getName();
    }
    
    /**
     * Get the review rating of this review
     * @return the rating of this review 
     */
    public int getReviewRating(){
        return reviewRating;
    }
    
    /**
     * Get the review text of this review
     * @return the review text of this review
     */
    public String getReview(){
        return review;
    }
}

