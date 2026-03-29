package com.example.Jdbc;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.jpa.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    private final JdbcTemplate jdbcTemplate;
    private final HouseRepository houseRepository;

    @Autowired
    public WebController(JdbcTemplate jdbcTemplate, HouseRepository houseRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.houseRepository = houseRepository;
    }

    @RequestMapping("/")
    public String helloWorld() {
        return "hello appengine and cloud sql";
    }

    @GetMapping("/getTuples")
    public List<List<Object>> getTuples() {
        return this.jdbcTemplate.queryForList("SELECT * FROM users").stream()
                .map(Map::values)
                .map(values -> values.stream().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

}