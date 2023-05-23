package com.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entities.BookPublisher;

public interface BookPubRepo extends JpaRepository<BookPublisher, Integer> {

}
