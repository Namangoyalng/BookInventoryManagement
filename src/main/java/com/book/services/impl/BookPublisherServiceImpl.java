package com.book.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entities.BookPublisher;
import com.book.repositories.BookPubRepo;
import com.book.services.BookPublisherService;

@Service
public class BookPublisherServiceImpl implements BookPublisherService {

	@Autowired
	private BookPubRepo bookPubRepo;

	public BookPublisher getBookPublisher(int pubid) {
		return bookPubRepo.findById(pubid).orElse(null);
	}

	@Override
	public void saveRecord(BookPublisher bookpublisher) {
		bookPubRepo.save(bookpublisher);

	}

	public List<BookPublisher> getList() {
		return bookPubRepo.findAll();
	}

	

	public void deleteRecord(int pid) {
		bookPubRepo.deleteById(pid);

	}

	public void updateBookPublisher(BookPublisher bookPublisher, BookPublisher bookPublisher1) {
		if (!bookPublisher.getEmail().equals(bookPublisher1.getEmail())
				|| !bookPublisher.getPhone().equals(bookPublisher1.getPhone())
				|| !bookPublisher.getPublisher().equals(bookPublisher1.getPublisher())
				|| bookPublisher.getPubid() != bookPublisher1.getPubid()) {
			bookPubRepo.save(bookPublisher);
		}

	}
}
