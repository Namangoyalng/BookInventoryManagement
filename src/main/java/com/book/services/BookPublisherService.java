package com.book.services;

import java.util.List;

import com.book.entities.BookPublisher;

public interface BookPublisherService  {

	BookPublisher getBookPublisher(int pubid);
	void saveRecord(BookPublisher bookpublisher);
	List<BookPublisher> getList();
	
	void deleteRecord(int pid);
	void updateBookPublisher(BookPublisher bookPublisher, BookPublisher bookPublisher1);

}
