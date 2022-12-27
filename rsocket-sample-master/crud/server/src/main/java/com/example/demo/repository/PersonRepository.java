package com.example.demo.repository;



import com.example.demo.data.Person;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, Integer> {

    @Query("SELECT * FROM posts WHERE title like $1")
    Flux<Person> findByNameContains(String name);



}
