package dev.amita.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;// call/create  template use for talk to database means form a new query without going to db
    public  Review createReview(String reviewBody, String imdbId){
        Review review =  reviewRepository.insert(new Review(reviewBody));//save review on insert


//we have empty reviewIds array we need to update it and push ew reviewId
        //matching means which movie we are updating matching with imdbId
        //then apply push it will push inside the review
        //.first make sure we are getting single movie and updating that
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;

        //now associate with the movie

    }
}
