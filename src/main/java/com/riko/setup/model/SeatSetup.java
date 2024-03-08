package com.riko.setup.model;

import com.riko.setup.model.composite.TheatreSeatTypeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatSetup {
    @EmbeddedId
    private TheatreSeatTypeId theatreSeatType;

    @Column(nullable = false)
    private Integer rowCount;

    @Column(nullable = false)
    private Integer seatCount;

    @Column(nullable = false)
    private Integer seatCost;
}
