package com.riko.setup.model;

import com.riko.setup.model.enums.ShowTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSetup {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long theatreId;

    @Column(nullable = false)
    private Long movieId;

    @Column(nullable = false)
    private LocalDate showDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShowTime showTime;
}
