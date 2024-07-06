package com.springboot.webflux.SpringBoot_WebFlux.service.impl;

import com.springboot.webflux.SpringBoot_WebFlux.models.Book;
import com.springboot.webflux.SpringBoot_WebFlux.repository.BookRepo;
import com.springboot.webflux.SpringBoot_WebFlux.service.BookService;
import jakarta.el.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public Mono<Book> create(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Flux<Book> getAll() {
        return bookRepo.findAll();
    }

    @Override
    public Mono<Book> get(int bookId) throws PropertyNotFoundException {
        Mono<Book> bookMono = bookRepo.findById(bookId);
        bookMono.hasElement().subscribe(
                hasElement -> {
                    if(!hasElement) throw new PropertyNotFoundException("There are no book with BookId : " + bookId);
                }
        );
        return bookMono;
    }

    @Override
    public Mono<Book> update(Book book, int bookId) {
        Mono<Book> bookMono = bookRepo.findById(bookId);
        bookMono.hasElement().subscribe(
                hasElement -> {
                    if(!hasElement) throw new PropertyNotFoundException("There are no book with BookId : " + bookId);
                }
        );
        return bookMono.flatMap(
                oldBook -> {
                    oldBook.setName(book.getName());
                    oldBook.setAuthor(book.getAuthor());
                    oldBook.setPublisher(book.getPublisher());
                    oldBook.setDescription(book.getDescription());
                    return bookRepo.save(oldBook);
                }
        );
    }

    @Override
    public Mono<Void> delete(int bookId) {
        Mono<Book> bookMono = bookRepo.findById(bookId);
        return bookMono.flatMap(
                book -> {
                    return bookRepo.delete(book);
                }
        );
    }

    @Override
    public Flux<Book> search(String query) {
        return null;
    }
}
