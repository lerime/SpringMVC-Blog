package com.tugaydemirel.blog;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tugaydemirel.database.CategoryCrud;
import com.tugaydemirel.database.CommentCrud;
import com.tugaydemirel.database.WritingCrud;
import com.tugaydemirel.properties.Category;
import com.tugaydemirel.properties.Comment;
import com.tugaydemirel.properties.Writing;

@Controller
public class AnasayfaController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String anasayfa(Model model) {
		System.out.println("Anasayfa Controller");
		Writing writing = null;
		ArrayList<Writing> writings = new WritingCrud().read(writing);
		if (writings != null) {
			model.addAttribute("writings", writings);
			System.out.println(writings.size());
		}
		ArrayList<Category> categoryList =  (ArrayList<Category>) new CategoryCrud().read(null);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("kulid", "");
		return "home/anasayfa";
	}
	
	@RequestMapping(value = "/{categoryId}")
	public String kategoriYazilari(Model model,@PathVariable(value = "categoryId") int categoryId) {
		System.out.println("kategoriYazilari controller");
		
		Category category = new Category();
		category.setId(categoryId);
		category = new CategoryCrud().read(category).get(0);
		
		System.out.println("categoris adi:"+category.getName());
		System.out.println("categoris adi:"+category.getId());
		
		Writing writing = null;
		if (!category.getName().equals("*")) {
			writing = new Writing();
			writing.setId(-1);
			writing.setCategory(category.getName());
		}
		ArrayList<Writing> writings = new WritingCrud().read(writing);
		if (writings != null) {
			model.addAttribute("category", category.getName());
			model.addAttribute("writings", writings);
			System.out.println(writings.size());
		}
		System.out.println("alsdf");
		ArrayList<Category> categoryList =  (ArrayList<Category>) new CategoryCrud().read(null);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("kulid", "");
		return "home/kategori_yazilari";
	}
	
	@RequestMapping(value = "/{writingId}/{writingId}")
	public String yaziGoster(HttpServletRequest request, Model model,@PathVariable(value = "writingId") int writingId) {
		System.out.println("yaziGoster controller");
		Writing writing = new Writing();
		writing.setId(writingId);
		ArrayList<Writing> writings = new WritingCrud().read(writing);
		if (writings.size() > 0) {
			System.out.println("buyukmus");
			System.out.println(writings.get(0).getTitle());
			model.addAttribute("category", writing.getCategory());
			model.addAttribute("writing", writings.get(0));
			System.out.println(writings.size());
			System.out.println(writings.get(0).getContent());
		}
		if (request.getSession().getAttribute("success") != null) {
			model.addAttribute("success", "Yorum eklendi.");
		}
		if (request.getSession().getAttribute("error") != null) {
			model.addAttribute("error", "Yorum eklenemedi.");
		}
		Comment comment = new Comment();
		comment.setWritingId(writingId);
		ArrayList<Comment> comments = new ArrayList<Comment>();
		comments = new CommentCrud().read(comment);
		System.out.println("comment listesinin boyutu : " + comments.size());
		model.addAttribute("comments", comments);
		
		System.out.println("12212");
		ArrayList<Category> categoryList =  (ArrayList<Category>) new CategoryCrud().read(null);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("kulid", "");
		return "home/yazi_icerigi";
	}
	
	
	
}
