package com.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import com.book.entities.Book;
import com.book.entities.BookCategory;
import com.book.entities.BookPublisher;
import com.book.services.BookManagementService;

@Controller
@RequestMapping("book")
public class BookController {

	@Autowired
	private BookManagementService bookService;

	@RequestMapping("login")
	public String getLogin() {
		
		return "home/login";
	}

	@RequestMapping("")
	public String getHomeView(String username,String password,Model model) {
		if(username==null || password==null) {
			username=password="";
		}
		if(username.equals(password))
		return "home/home-page";
		
		return "home/login";
	}

	@RequestMapping("manage/list")
	public String getBookList(Model model) {
		List<Book> blist = bookService.getList();
		model.addAttribute("blist", blist);
		
		
		return "book/book-list";
	}
	@RequestMapping("manage/add")
	public String addBook(Model model) {
		List<BookCategory> clist = bookService.getCategoryList();
		List<BookPublisher> plist = bookService.getPublisherList();
		model.addAttribute("plist", plist);
		model.addAttribute("clist", clist);
		return "book/add-book";
	}
	@RequestMapping("manage/save")
	public String getNewBookList(Model model, Book book) {
		String title = book.getTitle().toUpperCase();
		Book book1 = bookService.getBookByTitle(title);
		if (book1 != null) {
			List<Book> blist = bookService.getBookList();
			model.addAttribute("blist", blist);
			return "book/book-list";
		}
		book.setTitle(title);
		bookService.saveBook(book);
		List<Book> blist = bookService.getBookList();
		model.addAttribute("blist", blist);
		return "book/book-list";
	}
	
	@RequestMapping("manage/edit")
	public String edit(@RequestParam int bid, Model model) {
		Book book1 =bookService.getBookbyId(bid);
		model.addAttribute("book", book1);
		List<BookCategory> clist = bookService.getCategoryList();
		List<BookPublisher> plist = bookService.getPublisherList();
		model.addAttribute("plist", plist);
		model.addAttribute("clist", clist);
		return "book/edit-book";
	}
	@RequestMapping("manage/update")
	public String bookUpdateView(Model model, Book book) {
		int bid = book.getBookid();
		Book book1 = bookService.getBookbyId(bid);
		bookService.updateBook(book, book1);
		List<Book> blist = bookService.getBookList();
		model.addAttribute("blist", blist);
		return "book/book-list";
	}
	@RequestMapping("manage/remove")
	public String deleteBook(@RequestParam int bid, Model model) {

		bookService.deleteRecord(bid);
		return "redirect:list";
	}
	
	@RequestMapping("search")
	public String getSearchView(Model model) {
		List<BookCategory> clist = bookService.getCategoryList();
		List<BookPublisher> plist = bookService.getPublisherList();
		model.addAttribute("plist", plist);
		model.addAttribute("clist", clist);
		return "search/search-books";
	}
	@RequestMapping("search/byid")
	public String getSearchedListById(Model model,@RequestParam int bid) {
	    Book book=new Book();
	    book.setBookid(bid);
	    System.out.println(book);
	    List<Book> list=bookService.searchBookList(book);
	    if(list!=null) {
	    	model.addAttribute("blist",list);
			return "search/book-list";
	    }
	    list=new ArrayList();
	    model.addAttribute("blist",list);
		return "search/book-list";
	}
	@RequestMapping("search/byauthor")
	public String getSearchedListByAuthor(Model model,@RequestParam String author) {
	    Book book=new Book();
	    book.setAuthor(author);
	    List<Book> list=bookService.searchBookList(book);
	    if(list!=null) {
	    	model.addAttribute("blist",list);
			return "search/book-list";
	    }
	    list=new ArrayList();
	    model.addAttribute("blist",list);
		return "search/book-list";
	}
	@RequestMapping("search/bytitle")
	public String getSearchedListByTitle(Model model,@RequestParam String title) {
	    Book book=new Book();
	    book.setTitle(title);
	    List<Book> list=bookService.searchBookList(book);
	    if(list!=null) {
	    	model.addAttribute("blist",list);
			return "search/book-list";
	    }
	    list=new ArrayList();
	    model.addAttribute("blist",list);
		return "search/book-list";
	}
//	@RequestMapping("search/bycategory")
//	public String getSearchedListByCategory(Model model,@RequestParam int catid) {
//	    Book book=new Book();
//	    book.setCatid(catid);
//	    List<Book> list=bookService.searchBookList(book);
//	    if(!list.isEmpty()) {
//	    	model.addAttribute("blist",list);
//			return "search/book-list";
//	    }
//	    list=new ArrayList();
//	    model.addAttribute("blist",list);
//		return "search/book-list";
//	}
//	@RequestMapping("search/bypublisher")
//	public String getSearchedListByPublisher(Model model,@RequestParam int pubid) {
//	    Book book=new Book();
//	    book.setPubid(pubid);
//	    List<Book> list=bookService.searchBookList(book);
//	    model.addAttribute("blist",list);
//		return "search/book-list";
//	}
	@RequestMapping("search/edit")
	public String bookEditView(Model model, @RequestParam int bid) {
		Book book1 = bookService.getBookbyId(bid);
		model.addAttribute("book", book1);
		List<BookCategory> clist = bookService.getCategoryList();
		List<BookPublisher> plist = bookService.getPublisherList();
		model.addAttribute("plist", plist);
		model.addAttribute("clist", clist);
		return "book/edit-book";
	}
	
	@RequestMapping("search/update")
	public String getUpdatedSearchList(Model model, Book book) {
		int bid = book.getBookid();
		Book book1 = bookService.getBookbyId(bid);
		bookService.updateBook(book, book1);
		List<Book> blist = bookService.getBookList();
		model.addAttribute("blist", blist);
		return "book/book-list";
	}
	
	@RequestMapping("search/remove")
	public String removeEntry(@RequestParam int bid, Model model) {
		bookService.deleteRecord(bid);
		return "book/book-list";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "home/login";
	}
	@RequestMapping("search/bycategory")
	public String searchByCategory(@RequestParam int cid,Model model)
	{
		List<Book> blist=bookService.searchByCategory(cid);
		model.addAttribute("blist", blist);
		return "book/book-list";
	}
	@RequestMapping("bypublisher")
	public String searchByPublisher(@RequestParam int pubid,Model model)
	{
		List<Book> blist=bookService.searchByPublisher(pubid);
		model.addAttribute("blist", blist);
		return "book/book-list";
	}

}
