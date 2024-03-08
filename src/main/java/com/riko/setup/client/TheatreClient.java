package com.riko.setup.client;

import com.riko.setup.client.dto.theatre.Theatre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "${feign.client.theatre.name}",
        url = "${feign.client.theatre.url}"
)
public interface TheatreClient {

    @GetMapping
    ResponseEntity<List<Theatre>> getAllTheatres();

    @GetMapping("/{id}")
    ResponseEntity<Theatre> getTheatreById(@PathVariable Long id);

    @GetMapping("/theatre/city/{id}")
    ResponseEntity<List<Theatre>> getTheatreByCityId(@PathVariable Long id);
}
