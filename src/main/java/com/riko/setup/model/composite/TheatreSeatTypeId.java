package com.riko.setup.model.composite;

import com.riko.setup.model.enums.SeatType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class TheatreSeatTypeId implements Serializable {
    private Long theatreId;
    private SeatType seatType;
    private String hallName;
}
