package com.ss16.dto.bus;

import com.ss16.model.bus.BusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusDto{
    private Long id;

    @NotBlank(message = "Số xe không được để trống")
    @Size(max = 8, message = "Số xe không được vượt quá 8 ký tự")
    @UniqueLicenseplate
    private String licensePlate;

    @Pattern(regexp = "^VIP|LUXURY|NORMAL$", message = "Loại xe không hợp lệ")
    private String busType;

    @Min(value = 1, message = "Số ghế không hợp lệ")
    private int rowSeat;

    @Min(value = 1, message = "Số ghế không hợp lệ")
    private int colSeat;

    private MultipartFile file;
    private String image;
}
