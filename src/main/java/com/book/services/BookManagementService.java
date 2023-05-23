package com.book.services;

import java.util.List;



import com.book.entities.Book;
import com.book.entities.BookCategory;
import com.book.entities.BookPublisher;

public interface BookManagementService {

	List<Book> getList();

	List<BookCategory> getCategoryList();

	List<BookPublisher> getPublisherList();

	Book getBookByTitle(String title);

	List<Book> getBookList();

	void saveBook(Book book);

	Book getBookbyId(int bid);

	void updateBook(Book book, Book book1);

	void deleteRecord(int bid);

	List<Book> searchBookList(Book book);

	List<Book> searchByCategory(int cid);

	List<Book> searchByPublisher(int pid);

	
	

}
