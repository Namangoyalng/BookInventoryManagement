package com.book.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.entities.BookPublisher;
import com.book.services.BookPublisherService;

@Controller
@RequestMapping("publisher")
public class BookPublisherController {

	@Autowired
	private BookPublisherService bookpublisherservice;

	@RequestMapping("manage")
	public String homeView() {
		return "home/home-page";
	}

	@RequestMapping("manage/add")
	public String insert() {
		return "publisher/add-publisher";

	}

	@RequestMapping("manage/save")
	public String addPublisher(BookPublisher bp, Model model) {
		int pubid = bp.getPubid();
		BookPublisher bp1 = bookpublisherservice.getBookPublisher(pubid);
		if (bp1 != null) {
			model.addAttribute("pubid", pubid);
			return "redirect:list";
		}
		bookpublisherservice.saveRecord(bp);
		return "redirect:list";

	}

	@RequestMapping("manage/list")
	public String getPublisherList(Model model) {
		List<BookPublisher> list = bookpublisherservice.getList();
		model.addAttribute("plist", list);
		return "publisher/publisher-list";
	}

	@RequestMapping("manage/edit")
	public String edit(@RequestParam int pid, Model model) {
		BookPublisher bp = bookpublisherservice.getBookPublisher(pid);
		model.addAttribute("pub", bp);
		return "publisher/edit-publisher";
	}

	@RequestMapping("manage/update")
	public String bookUpdateView(Model model, BookPublisher bookPublisher) {
		BookPublisher bookPublisher1 = bookpublisherservice.getBookPublisher(bookPublisher.getPubid());
		bookpublisherservice.updateBookPublisher(bookPublisher, bookPublisher1);
		List<BookPublisher> plist = bookpublisherservice.getList();
		model.addAttribute("plist", plist);
		return "publisher/publisher-list";
	}

	@RequestMapping("manage/remove")
	public String deletPublisher(@RequestParam int pid, Model model) {

		bookpublisherservice.deleteRecord(pid);
		return "redirect:list";
	}

}
