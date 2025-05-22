package com.ss11.model;

import com.ss11.validator.PastOrPresentDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private int id;

    @NotBlank(message = "Title không được để trống đâu nhé!")
    @Size(max = 100, message = "Title phải dưới 100 ký tự thôi, đừng tham!")
    private String title;

    @NotBlank(message = "Director không thể để trống được!")
    @Size(max = 50, message = "Director tối đa 50 ký tự thôi nhé!")
    private String director;

    @NotNull(message = "Ngày phát hành phải nhập, đừng có quên!")
    @PastOrPresentDate(message = "Ngày phát hành không được là tương lai nhé!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @NotBlank(message = "Genre bắt buộc phải chọn nha!")
    @Size(max = 30, message = "Genre chỉ được tối đa 30 ký tự thôi!")
    private String genre;

    private String poster;
}
