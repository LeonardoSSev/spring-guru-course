package com.example.course.guru.bootstrap;

import com.example.course.guru.model.Author;
import com.example.course.guru.model.Book;
import com.example.course.guru.model.Publisher;
import com.example.course.guru.repository.AuthorRepository;
import com.example.course.guru.repository.BookRepository;
import com.example.course.guru.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;

    private BookRepository bookRepository;

    private PublisherRepository publisherRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.initializeData();
    }

    public DevBootstrap(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository
    ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initializeData() {
        Publisher publisher = new Publisher("Foo", "Bar 123");
        publisherRepository.save(publisher);

        Author robert = new Author("Robert", "Martin");
        Book cleanCode = new Book("Clean Code", "123", publisher);

        robert.getBooks().add(cleanCode);
        cleanCode.getAuthors().add(robert);

        Author pete = new Author("Pete", "McBreen");
        Book softwareCraftsmanship = new Book ("Software Craftsmanship", "456", publisher);

        pete.getBooks().add(softwareCraftsmanship);
        softwareCraftsmanship.getAuthors().add(pete);

        this.authorRepository.save(robert);
        this.authorRepository.save(pete);

        this.bookRepository.save(cleanCode);
        this.bookRepository.save(softwareCraftsmanship);


    }
}
