package com.infy.demo.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;
@Data
public class SearchFlights {
	@NotNull(message ="Airport must not be blank.")
    private Integer airportId;
	@NotNull(message ="Destination must not be blank.")
    private String destination;
	@NotNull(message ="Date must not be blank.")
    @JsonDeserialize(using = LocalDateDeserializer.class)  
    @JsonSerialize(using = LocalDateSerializer.class)  
    private LocalDate date;
	@NotNull(message ="Number of tickets must not be blank.")
    private Integer numberOfTickets;
}
