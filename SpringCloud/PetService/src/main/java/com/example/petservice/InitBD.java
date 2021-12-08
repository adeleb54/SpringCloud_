package com.example.petservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@Component
public class InitBD implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Création et initialisation de la base de données");

        String sqlStatements[] = {
          "drop table pets if exists",
                "create table pets(id serial, name varchar(255), animal varchar(255), age int)",
                "INSERT INTO pets (name, animal, age) VALUES ('Malo', 'Dog', 6);",
                "INSERT INTO pets (name, animal, age) VALUES ('Pschiit', 'Cat', 3);",
                "INSERT INTO pets (name, animal, age) VALUES ('Pilou', 'Cat', 2);"
        };

        Arrays.asList(sqlStatements).stream().forEach(sql -> {
            System.out.println(sql);
            jdbcTemplate.execute(sql);
        });

        System.out.println("-------------- Affichage des potits animaux --------------");
        jdbcTemplate.query("SELECT * FROM pets",
                new RowMapper<Object>() {
                    @Override
                    public Object mapRow(ResultSet pet, int rowNum) throws SQLException {
                        System.out.println("id : "+pet.getString("id"));
                        System.out.println("name : "+ pet.getString("name"));
                        System.out.println("animal : "+pet.getString("animal"));
                        System.out.println("age : "+pet.getInt("age"));
                        return null;
                    }
                });
    }
}
