package com.book.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entities.BookCategory;
import com.book.repositories.BookCatRepo;
import com.book.services.BookCategoryService;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

	@Autowired
	private BookCatRepo bookCatRepo;

	public List<BookCategory> getList() {
		return bookCatRepo.findAll();
	}

	public BookCategory getBookCat(int cid) {
		return bookCatRepo.findById(cid).orElse(null);
	}

	public void saveRecord(BookCategory bc) {
		bookCatRepo.save(bc);

	}

	public void updateBookCategory(BookCategory bookCategory, BookCategory bookCategory1) {
		if (!bookCategory.getCategory().equals(bookCategory1.getCategory())
				|| bookCategory.getCatid() != bookCategory1.getCatid()
				|| !bookCategory.getDescription().equals(bookCategory1.getDescription())) {
			bookCatRepo.save(bookCategory);
		}

	}

	@Override
	public void deleteRecord(int cid) {
		bookCatRepo.deleteById(cid);
	}

}
