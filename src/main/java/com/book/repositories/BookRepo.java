package com.book.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book.entities.Book;



public interface BookRepo extends JpaRepository<Book, Integer> {
	@Query("from Book where title=:arg")
	Book findByTitle(@Param("arg") String title);
	
	
    @Query("from Book where author=:arg")
	List<Book> findByAuthor(@Param("arg") String author);
    
    @Query("from Book where catid=:arg")
	List<Book> findByCategory(@Param("arg") int catid);
    
    @Query("from Book where pubid=:arg")
	List<Book> findByPublisher(@Param("arg") int pubid);
    
    @Query("from Book where title=:arg")
	List<Book> findBooksByTitle(@Param("arg") String title);

    @Query("from Book where catid=:arg")
	List<Book> searchByCategory(@Param("arg")int cid);
    
    @Query("from Book where pubid=:arg")
   	List<Book> searchByPublisher(@Param("arg")int pid);
    
}
