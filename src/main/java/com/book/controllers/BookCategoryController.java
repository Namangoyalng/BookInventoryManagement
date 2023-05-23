package com.book.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.entities.BookCategory;
import com.book.services.BookCategoryService;

@Controller
@RequestMapping("category")
public class BookCategoryController {

	@Autowired
	private BookCategoryService bookCatSer;

	@RequestMapping("manage/list")
	public String getCategoryList(Model model) {
		List<BookCategory> list = bookCatSer.getList();
		model.addAttribute("clist", list);
		return "category/category-list";
	}

	@RequestMapping("manage/add")
	public String insert() {
		return "category/add-category";

	}

	@RequestMapping("manage/save")
	public String addCategory(BookCategory bc, Model model) {
		int cid = bc.getCatid();
		BookCategory bc1 = bookCatSer.getBookCat(cid);
		if (bc1 != null) {
			model.addAttribute("catid", cid);
			return "redirect:list";
		}
		bookCatSer.saveRecord(bc);
		return "redirect:list";
	}

	@RequestMapping("manage/edit")
	public String edit(@RequestParam int cid, Model model) {
		BookCategory bc = bookCatSer.getBookCat(cid);
		model.addAttribute("cat", bc);
		return "category/edit-category";
	}

	@RequestMapping("manage/update")
	public String bookUpdateView(Model model, BookCategory bookCategory) {
		BookCategory bookCategory1 = bookCatSer.getBookCat(bookCategory.getCatid());
		bookCatSer.updateBookCategory(bookCategory, bookCategory1);
		List<BookCategory> slist = bookCatSer.getList();
		model.addAttribute("blist", slist);
		return "category/category-list";
	}

	@RequestMapping("manage/remove")
	public String deletPublisher(@RequestParam int cid, Model model) {
		BookCategory cat = bookCatSer.getBookCat(cid);
		if (cat != null) {
			bookCatSer.deleteRecord(cid);
		}

		List<BookCategory> clist = bookCatSer.getList();
		model.addAttribute("clist", clist);
		return "category/category-list";
	}
}
