package com.book.services;

import java.util.List;

import com.book.entities.BookCategory;

public interface BookCategoryService {

	List<BookCategory> getList();

	BookCategory getBookCat(int cid);

	void saveRecord(BookCategory bc);

	void updateBookCategory(BookCategory bookCategory, BookCategory bookCategory1);

	void deleteRecord(int cid);

	

	

}
