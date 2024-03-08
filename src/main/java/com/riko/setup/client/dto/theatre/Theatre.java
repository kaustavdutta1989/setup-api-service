package com.riko.setup.client.dto.theatre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theatre {
    private Long id;
    private String name;
    private Partner partner;
    private City city;
    private String location;
}
