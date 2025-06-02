package com.ss16.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto{
    private long id;

    private long userId;

    private long tripBusId;

    @NotBlank(message = "Chưa chọn ghế")
    private String listSeat;

    @DecimalMin(value = "0.01", message = "Giá vị trí không được để trống")
    private double totalMoney;

    @NotNull(message = "Chưa chọn ngày đi")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate departureDate;
}
