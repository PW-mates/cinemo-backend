package com.pw.cinema.screening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScreeningService {
    @Autowired
    ScreeningRepository screeningRepository;
}
