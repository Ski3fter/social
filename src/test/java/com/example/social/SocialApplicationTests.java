package com.example.social;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SocialApplicationTests {

    @Test
    void contextLoads() {

        List<PersonData> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/people.v2.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String[] name = values[0].split(" ");
                records.add(new PersonData(name[1], name[0], values[1], values[2]));
            }


            PrintWriter writer = new PrintWriter("the-file-name.csv", "UTF-8");


            int i = 5;
            for (PersonData record : records) {
                String userName = String.format("userTest%s", i);
                writer.println(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        i,
                        userName,
                        record.firstName,
                        record.lastName,
                        record.date,
                        "Sport",
                        record.city,
                        "$2a$10$gKQhdrFJqkOdrmi5Oa1fTu9Jx4YK53L9Nua9uDNF2DQeK/.uqqjku",
                        String.format("%s@gmail.com", userName),
                        "ROLE_USER"));
                i++;
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Data
    @AllArgsConstructor
    class PersonData {
        private String firstName;
        private String lastName;
        private String date;
        private String city;

    }

}
