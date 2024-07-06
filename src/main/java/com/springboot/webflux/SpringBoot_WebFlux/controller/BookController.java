package com.springboot.webflux.SpringBoot_WebFlux.controller;

import com.springboot.webflux.SpringBoot_WebFlux.models.Book;
import com.springboot.webflux.SpringBoot_WebFlux.service.BookService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Mono<Book> create(@RequestBody Book book){
        return bookService.create(book);
    }

    @GetMapping
    public Flux<Book> getAllMapping(){
        return bookService.getAll();
    }

    @GetMapping("/{bookId}")
    public Mono<Book> get(@PathVariable("bookId") int bid){
        return bookService.get(bid);
    }

    @PutMapping("/{bookId}")
    public Mono<Book> update(@RequestBody Book book, @PathVariable("bookId") int bid){
        return bookService.update(book, bid);
    }

    @DeleteMapping("/{bookId}")
    public Mono<Void> delete(@PathVariable("bookId") int bid){
        return bookService.delete(bid);
    }

}
