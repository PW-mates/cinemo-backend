package com.pw.cinema.screening;

import com.pw.cinema.movie.Movie;
import com.pw.cinema.movie.MovieRepository;
import com.pw.cinema.room.Room;
import com.pw.cinema.room.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ScheduledExecutorService;

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
        Movie movie = movieRepository.findById(screening.getMovieId()).orElseThrow(() -> new NoSuchElementException(
                "Not found movie with this id"));
        Room room = roomRepository.findById(screening.getRoomId()).orElseThrow(() -> new NoSuchElementException("Not " +
                "found room with this id."));
        newScreening.setMovie(movie);
        newScreening.setRoom(room);
        return response(screeningRepository.save(newScreening), "Successfully created screening");
    }

    public Object getScreening(Long id) {
        Screening screening = screeningRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Not found screening with this id"));
        return response(screening, "Successfully found screening");
    }

    public Object updateScreening(Long id, ScreeningDtoPure screeningChanges) {
        Screening screening = screeningRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not " +
                "found screening with this id"));
        Movie newMovie = movieRepository.findById(screeningChanges.getMovieId()).orElseThrow(() ->
                new NoSuchElementException("Not found movie with this id"));
        Room newRoom = roomRepository.findById(screeningChanges.getRoomId()).orElseThrow(() -> new NoSuchElementException("Not " +
                "found room with this id."));
        screening.setMovie(newMovie);
        screening.setRoom(newRoom);
        screening.setDate(screeningChanges.getDate());
        screening.setOpenSale(screeningChanges.getOpenSale());
        return response(screeningRepository.save(screening), "Successfully updated screening");
    }

    public Object deleteScreening(Long id) {
        if (!screeningRepository.existsById(id))
            throw new NoSuchElementException("Not found screening with this id");
        screeningRepository.deleteById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully deleted screening");
        response.put("success", true);
        return response;
    }

    public Object getAllScreenings() {
        List<Screening> screeningList = screeningRepository.findAll();
        return response(screeningList, "Successfully found screenings");
    }
}
