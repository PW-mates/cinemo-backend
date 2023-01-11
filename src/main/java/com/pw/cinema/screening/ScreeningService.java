package com.pw.cinema.screening;

import com.pw.cinema.exceptions.DateIsLaterException;
import com.pw.cinema.movie.Movie;
import com.pw.cinema.movie.MovieRepository;
import com.pw.cinema.payment.Payment;
import com.pw.cinema.payment.PaymentRepository;
import com.pw.cinema.room.Room;
import com.pw.cinema.room.RoomDto;
import com.pw.cinema.room.RoomRepository;
import com.pw.cinema.seat.Seat;
import com.pw.cinema.seat.SeatDto;
import com.pw.cinema.ticket.TicketRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.pw.cinema.utils.Utils.response;

@Service
public class ScreeningService {
    MovieRepository movieRepository;
    RoomRepository roomRepository;
    ScreeningRepository screeningRepository;
    ModelMapper modelMapper;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    PaymentRepository paymentRepository;

    public ScreeningService(MovieRepository movieRepository, RoomRepository roomRepository, ScreeningRepository screeningRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.screeningRepository = screeningRepository;
        this.modelMapper = modelMapper;
    }

    public ScreeningDto convertEntityToDto(Screening screening) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(screening, ScreeningDto.class);
    }

    public Object createScreening(ScreeningDtoCreate screening) throws DateIsLaterException {
        Screening newScreening = new Screening();
        Long date = screening.getDate();
        Long openSale = screening.getOpenSale();
        if (openSale == null) {
            Date screenDate = new Date(date * 1000);
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDate screenDateLocal = screenDate.toInstant().atZone(zoneId).toLocalDate();
            screenDateLocal = screenDateLocal.minusDays(7);
            openSale = screenDateLocal.atStartOfDay(zoneId).toEpochSecond();
        } else if (date < openSale)
            throw new DateIsLaterException("Open sale should be before screening date");
        newScreening.setDate(date);
        newScreening.setOpenSale(openSale);
        Movie movie = movieRepository.findById(screening.getMovieId()).orElseThrow(() -> new NoSuchElementException(
                "Not found movie with this id"));
        Room room = roomRepository.findById(screening.getRoomId()).orElseThrow(() -> new NoSuchElementException("Not " +
                "found room with this id."));
        newScreening.setMovie(movie);
        newScreening.setRoom(room);
        Screening savedScreening = screeningRepository.save(newScreening);
        return response(convertEntityToDto(savedScreening), "Successfully created screening");
    }

    public Object getScreening(Long id) {
        Screening screening = screeningRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Not found screening with this id"));
        return response(convertEntityToDto(screening), "Successfully found screening");
    }

    public Object updateScreening(Long id, ScreeningDtoUpdate screeningChanges) throws DateIsLaterException {
        if (!screeningRepository.existsById(id))
            throw new NoSuchElementException("Not found screening with this id");
        Screening screening = screeningRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not " +
                "found screening with this id"));
        Movie newMovie = movieRepository.findById(screeningChanges.getMovieId()).orElseThrow(() ->
                new NoSuchElementException("Not found movie with this id"));
        Room newRoom = roomRepository.findById(screeningChanges.getRoomId()).orElseThrow(() -> new NoSuchElementException("Not " +
                "found room with this id."));
        screening.setMovie(newMovie);
        screening.setRoom(newRoom);
        Long date = screeningChanges.getDate();
        Long openSale = screeningChanges.getOpenSale();
        if (date != null && openSale != null && date < openSale)
            throw new DateIsLaterException("Open sale should be before screening date");
        screening.setDate(date);
        screening.setOpenSale(openSale);
        Screening savedScreening = screeningRepository.save(screening);
        return response(convertEntityToDto(savedScreening), "Successfully updated screening");
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
        List<ScreeningDto> screeningList = screeningRepository.findAll()
                .stream().map(this::convertEntityToDto).collect(Collectors.toList());
        return response(screeningList, "Successfully found screenings");
    }

    public Object getStatistics() {
        int soldTickets = ticketRepository.findAll().size();
        int totalScreenings = screeningRepository.findAll().size();
        float totalRevenue = paymentRepository.findAll().stream().map(Payment::getAmount).reduce(0f, Float::sum);
        Map<String, Object> data = new HashMap<>();
        data.put("totalTicket", soldTickets);
        data.put("totalScreening", totalScreenings);
        data.put("totalRevenue", totalRevenue);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully fetching data");
        response.put("data", data);
        response.put("success", true);
        return response;
    }
}
