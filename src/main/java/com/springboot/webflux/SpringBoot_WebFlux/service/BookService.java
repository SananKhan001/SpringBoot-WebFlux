package com.springboot.webflux.SpringBoot_WebFlux.service;

import com.springboot.webflux.SpringBoot_WebFlux.models.Book;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

    public Mono<Book> create(Book book);
    public Flux<Book> getAll();
    public Mono<Book> get(int bookId);
    public Mono<Book> update(Book book, int bookId);
    public Mono<Void> delete(int bookId);
    public Flux<Book> search(String query);

}
