package com.ss16.dto.bustrip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BustripDto{
    private Long id;

    @NotBlank(message = "Địa điểm đón không được để trống")
    private String departure;

    @NotBlank(message = "Địa điểm kết thúc không được để trống")
    private String destination;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime departureTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime arrivalTime;

    @Min(value = 1, message = "Số ghế không được để trống")
    private int busId;
    @Min(value = 1, message = "Không thể không chọn ghế")
    private int seatsAvailable;

    private MultipartFile file;
    private String image;
}
