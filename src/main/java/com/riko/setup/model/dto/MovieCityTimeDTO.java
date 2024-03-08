package com.riko.setup.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieCityTimeDTO {

    private Long movieId;

    private Long cityId;

    private LocalDateTime localDateTime;
}
