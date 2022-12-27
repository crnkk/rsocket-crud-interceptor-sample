package com.example.client.controller;

import com.example.client.data.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/posts")
public class PersonController {
        private final RSocketRequester requester;

        @GetMapping("")
        Flux<Person> all(@RequestParam(name = "title", required = false) String title) {
            if (StringUtils.hasText(title)) {
                return this.requester.route("posts.titleContains")
                        .data(title).retrieveFlux(Person.class);
            } else {
                return this.requester.route("posts.findAll")
                        .retrieveFlux(Person.class);
            }
        }

        @GetMapping("{id}")
        Mono<Person> findById(@PathVariable Integer id) {
            return this.requester.route("posts.findById." + id)
                    .retrieveMono(Person.class);
        }

        @PostMapping("")
        Mono<Person> save(@RequestBody Person person) {
            return this.requester.route("posts.save")
                    .data(person)
                    .retrieveMono(Person.class);
        }

        @PutMapping("{id}")
        Mono<Person> update(@PathVariable Integer id, @RequestBody Person person) {
            return this.requester.route("posts.update."+ id)
                    .data(person)
                    .retrieveMono(Person.class);
        }

        @DeleteMapping("{id}")
        Mono<Void> delete(@PathVariable Integer id) {
            return this.requester.route("posts.deleteById."+ id).send();
        }

    }



