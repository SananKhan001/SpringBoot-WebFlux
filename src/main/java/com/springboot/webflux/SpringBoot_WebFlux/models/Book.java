package com.springboot.webflux.SpringBoot_WebFlux.models;

import jakarta.annotation.Generated;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "book")
public class Book {

    @Id
    @Column("book_id")
    private int bookId;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("publisher")
    private String publisher;

    @Column("author")
    private String author;

}
