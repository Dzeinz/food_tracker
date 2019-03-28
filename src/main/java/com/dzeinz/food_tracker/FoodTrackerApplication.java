package com.dzeinz.food_tracker;

import io.github.kaiso.relmongo.config.EnableRelMongo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRelMongo
public class FoodTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodTrackerApplication.class, args);
    }

}
