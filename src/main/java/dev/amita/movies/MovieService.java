package dev.amita.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// will write the db access methods
@Service
public class MovieService {
    @Autowired // it will instantiate MovieRepository for us
    private MovieRepository movieRepository;// reference of repo
    public List<Movie> allMovies(){//method

        return movieRepository.findAll();
    }
//    public Optional<Movie> singleMovie(ObjectId id){// used optional because may be objectid not find
//        return movieRepository.findById(id);
    //find by imdbId
public Optional<Movie> singleMovie(String imdbId){// used optional because may be objectid not find
     return movieRepository.findMoviesByImdbId(imdbId);
    }
}
