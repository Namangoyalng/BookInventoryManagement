package com.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entities.BookCategory;

public interface BookCatRepo extends JpaRepository<BookCategory, Integer>{

}
