package com.example.demo.controller;


import com.example.demo.data.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/person")
public
class PersonController {

    @Autowired
    PersonController personController;
    @Autowired
    private PersonRepository personRepository;

    @MessageMapping("posts.findAll")
    public Flux<Person> all() {
        return personRepository.findAll();
    }

    @MessageMapping("posts.titleContains")
    public Flux<Person> nameContains(@Payload String name) {
        return personRepository.findByNameContains(name);
    }

    @MessageMapping("posts.findById.{id}")
    public Mono<Person> get(@DestinationVariable("id") Integer id) {
        return  personRepository.findById(id);
    }

    @MessageMapping("posts.save")
    public Mono<Person> create(@Payload Person person) {
        return personRepository.save(person);
    }

    @MessageMapping("posts.update.{id}")
    public Mono<Person> update(@DestinationVariable("id") Integer id, @Payload Person person) {
        return personRepository.findById(id)
                .map(p -> {
                    p.setName(person.getName());
                    p.setNumber(person.getNumber());

                    return p;
                })
                .flatMap(p -> personRepository.save(p));
    }

    @MessageMapping("posts.deleteById.{id}")
    public Mono<Void> delete(@DestinationVariable("id") Integer id) {
        return personRepository.deleteById(id);
    }

}
