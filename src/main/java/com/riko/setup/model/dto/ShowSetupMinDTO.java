package com.riko.setup.model.dto;

import com.riko.setup.model.composite.TheatreSeatTypeId;
import com.riko.setup.model.enums.ShowTime;
import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSetupMinDTO {
    private Long theatreId;
    private Long movieId;
    private LocalDate showDate;
    private ShowTime showTime;
}
