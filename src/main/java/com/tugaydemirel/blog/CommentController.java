package com.tugaydemirel.blog;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tugaydemirel.database.AdminCrud;
import com.tugaydemirel.database.CategoryCrud;
import com.tugaydemirel.database.CommentCrud;
import com.tugaydemirel.database.PictureCrud;
import com.tugaydemirel.properties.Admin;
import com.tugaydemirel.properties.Category;
import com.tugaydemirel.properties.Comment;
import com.tugaydemirel.properties.Picture;
import com.tugaydemirel.utils.SifreleCoz;

@Controller
public class CommentController {
	
	private Admin currentAdmin = null;
	
	@RequestMapping(value="/commentEkle", method=RequestMethod.POST)
	public String commentEkle(Model model, Comment comment) {
		System.out.println("commentEkle controller()");
		if (comment != null) {
			comment.setDate(new Date());
			comment.setRead(false);
		}
		if (new CommentCrud().create(comment)) {
			model.addAttribute("success", "Yorum eklendi.");
		}else {
			model.addAttribute("error", "Yorum eklenemedi.");
		}
		return "redirect:/"+comment.getWritingId()+"/"+comment.getWritingId();
	}
	
	@RequestMapping(value="/yorumGoster/{id}", method=RequestMethod.GET)
	public String commentGoster(HttpServletRequest request,Model model, @PathVariable (value= "id") String id) {
		System.out.println("Comment Conroller - commentGoster()");
		System.out.println(id);
		
		Comment comment = new Comment();
		comment.setId(Integer.valueOf(id));
		
		CommentCrud commentCrud = new  CommentCrud();
		comment = commentCrud.read(comment).get(0);
		System.out.println(comment.getContent());
		comment.setRead(true);
		commentCrud.update(comment);
		
		model.addAttribute("comment", comment);
		getCategory(model);
		addAdminToModel(model, request);
		getPicture(model, request); 
		getMessages(model);
		System.out.println("Sona yaklastik");
		//TODO yorum goster jsp sayfasi olustur
		return "admin/yorum_goster";
	}
	
	private void getCategory(Model model) {
		ArrayList<Category> categoryList = (ArrayList<Category>) new CategoryCrud().read(null);
		model.addAttribute("categoryList", categoryList);
	}
	
	public void addAdminToModel(Model model, HttpServletRequest request) {
		Integer kulid = null;
		if (request.getSession().getAttribute("kulid") != null) {
			 kulid = (Integer) request.getSession().getAttribute("kulid");
		}
		Admin admin = new Admin();
		admin.setId(kulid);
		currentAdmin = new AdminCrud().read(admin).get(0);
		if (currentAdmin != null) {
			model.addAttribute("admin", currentAdmin);
		}
	}
	
	void getPicture(Model model, HttpServletRequest request) {
		int id = (Integer) request.getSession().getAttribute("kulid");
		Picture picture = new Picture();
		picture.setId(id);
		ArrayList<Picture> pictures = new PictureCrud().read(picture);
		if (pictures.size() > 0) {
			model.addAttribute("pictureId", pictures.get(0).getId());
			model.addAttribute("pictureName", pictures.get(0).getName());
			System.out.println("resimId : " +pictures.get(0).getId());
			System.out.println("resimName : "+ pictures.get(0).getName());
		}
	}
	
	public  HttpServletRequest cookieKontrol(HttpServletRequest request) {

		if (request.getCookies() != null) {
			Cookie[] cDizi = request.getCookies();
			for (int i = 0; i < cDizi.length; i++) {
				if (cDizi[i].getName().equals("kulcerez")) {
					// çerez var !
					String kuldata = cDizi[i].getValue();
					request.getSession().setAttribute("kulid", new SifreleCoz().sifrele(kuldata));
					break;
				}
			}
		}
		return request;
	}
	
	public boolean sessionKontrol(HttpServletRequest request, Model model) {
		if (request.getSession().getAttribute("kulid") != null) {
			model.addAttribute("kulId", request.getSession().getAttribute("kulid"));
			return true;
		}else {
			return false;
		}
	}
	
	void getMessages(Model model) {
		Comment comment = new Comment();
		comment.setRead(false);
		ArrayList<Comment> nonReadComments = new CommentCrud().read(comment);
		model.addAttribute("nonReadComments", nonReadComments);
	}
}
