package com.springboot.webflux.SpringBoot_WebFlux.repository;

import com.springboot.webflux.SpringBoot_WebFlux.models.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends ReactiveCrudRepository<Book, Integer> {
}
