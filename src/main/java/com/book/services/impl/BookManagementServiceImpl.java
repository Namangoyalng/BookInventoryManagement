package com.book.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.book.entities.Book;
import com.book.entities.BookCategory;
import com.book.entities.BookPublisher;
import com.book.repositories.BookCatRepo;
import com.book.repositories.BookPubRepo;
import com.book.repositories.BookRepo;
import com.book.services.BookManagementService;

@Service
public class BookManagementServiceImpl implements BookManagementService {

	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private BookCatRepo bookCatRepo;
	@Autowired
	private BookPubRepo bookPubRepo;

	public List<Book> getList() {
//		int upper = (int) bookRepo.count();
//		Pageable page = PageRequest.of(pn-1, upper);
		return bookRepo.findAll();
	}

	public List<BookCategory> getCategoryList() {

		return bookCatRepo.findAll();
	}

	public List<BookPublisher> getPublisherList() {
		return bookPubRepo.findAll();
	}

	public Book getBookByTitle(String title) {

		if (bookRepo.findByTitle(title) == null)
			return null;
		return bookRepo.findByTitle(title);
	}

	public List<Book> getBookList() {
		return bookRepo.findAll();
	}

	public void saveBook(Book book) {
		bookRepo.save(book);
	}

	public Book getBookbyId(int bid) {
		return bookRepo.findById(bid).orElse(null);
	}

	public void updateBook(Book book, Book book1) {
		if (!book.getAuthor().equals(book1.getAuthor()) || book.getCatid() != book1.getCatid()
				|| book1.getCopies() != book.getCopies() || book.getPrice() != book1.getPrice()
				|| book1.getTitle() != book.getTitle() || book1.getPubid() != book.getPubid()) {
			bookRepo.save(book);
		}

	}

	public void deleteRecord(int bid) {
		bookRepo.deleteById(bid);

	}

	@Override
	public List<Book> searchBookList(Book book) {
		if (book.getBookid() != 0) {
			List<Book> list = new ArrayList<Book>();
			Book book1 = bookRepo.findById(book.getBookid()).orElse(null);
			if (book1 != null)
				list.add(book1);
			return list;
		} else if (book.getAuthor() != null) {
			return bookRepo.findByAuthor(book.getAuthor());
		} else if (book.getTitle() != null) {
			return bookRepo.findBooksByTitle(book.getTitle());
		} else if (book.getCatid() != 0) {

			return bookRepo.findByCategory(book.getCatid());

		} else if (book.getPubid() != 0) {

			return bookRepo.findByPublisher(book.getPubid());

		}
		return null;

	}

	@Override
	public List<Book> searchByCategory(int cid) {
		
		return bookRepo.searchByCategory(cid);
	}

	@Override
	public List<Book> searchByPublisher(int pid) {
		
		return bookRepo.searchByPublisher(pid);
	}
}
