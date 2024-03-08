package com.riko.setup.model.dto;

import com.riko.setup.model.composite.TheatreSeatTypeId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatSetupMinDTO {
    private TheatreSeatTypeId theatreSeatTypeId;
    private Integer rowCount;
    private Integer seatCount;
    private Integer seatCost;
}
