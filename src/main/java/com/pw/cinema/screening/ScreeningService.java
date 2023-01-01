package com.pw.cinema.screening;

import com.pw.cinema.movie.Movie;
import com.pw.cinema.movie.MovieRepository;
import com.pw.cinema.room.Room;
import com.pw.cinema.room.RoomRepository;
import org.springframework.stereotype.Service;
import static com.pw.cinema.utils.Utils.response;

@Service
public class ScreeningService {
    MovieRepository movieRepository;
    RoomRepository roomRepository;
    ScreeningRepository screeningRepository;

    public ScreeningService(MovieRepository movieRepository, RoomRepository roomRepository, ScreeningRepository screeningRepository) {
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.screeningRepository = screeningRepository;
    }

    public Object createScreening(ScreeningDtoPure screening) {
        Screening newScreening = new Screening();
        newScreening.setDate(screening.getDate());
        newScreening.setOpenSale(screening.getOpenSale());
        Movie movie = movieRepository.findById(screening.getMovieId()).orElseThrow(() -> new IllegalStateException(
                "Not found movie with this id"));
        Room room = roomRepository.findById(screening.getRoomId()).orElseThrow(() -> new IllegalStateException("Not " +
                "found room with this id."));
        newScreening.setMovie(movie);
        newScreening.setRoom(room);
        return response(screeningRepository.save(newScreening), "Successfully created screening");
    }
}
