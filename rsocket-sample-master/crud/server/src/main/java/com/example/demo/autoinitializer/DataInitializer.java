package com.example.demo.autoinitializer;


import com.example.demo.repository.PersonRepository;
import com.example.demo.data.Person;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
class DataInitializer implements ApplicationRunner {

    @Autowired
    PersonRepository personRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        DataFactory dataFactory=new DataFactory();
        Faker faker1=new Faker();
        log.info("start data initialization...");
        for(int i=1;i<10;i++) {
            String newName= dataFactory.getFirstName()+" "+dataFactory.getLastName();
            String newNumber = faker1.numerify("#########");
            personRepository
                    .saveAll(
                            List.of(
                                    Person.builder().name(newName).number(newNumber).build()
                                    //   Post.builder().name(UUID.randomUUID().toString()).number(UUID.randomUUID().toString()).build()
                            )

                    )
                    .log()
                    .thenMany(
                            personRepository.findAll()
                    )
                    .subscribe(
                            (data) -> log.info("post: " + data),
                            (err) -> log.error("error: " + err),
                            () -> log.info("initialization done...")
                    );
        }
    }
}

