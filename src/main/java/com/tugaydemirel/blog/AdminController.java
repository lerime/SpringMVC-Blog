package com.tugaydemirel.blog;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tugaydemirel.database.AdminCrud;
import com.tugaydemirel.database.CategoryCrud;
import com.tugaydemirel.database.PictureCrud;
import com.tugaydemirel.properties.Admin;
import com.tugaydemirel.properties.Category;
import com.tugaydemirel.properties.Picture;

@Controller
public class AdminController {
	
	private ArrayList<Category> categoryList = null;

	private Admin currentAdmin = null;

	@RequestMapping(value = "/userProfile", method = RequestMethod.GET)
	public String userProfile(Model model, HttpServletRequest request) {
		
		if (!sessionKontrol(request,model)) {
			return "redirect:/giris";
		}
		
		
		getCategory(model);
		addAdminToModel(model, request);
		getPicture(model, request);
		
		return "admin/userProfile";
	}
	
	@RequestMapping(value = "/adminEkle", method = RequestMethod.GET)
	public String adminEkle(Model model, HttpServletRequest request) {
		
		if (!sessionKontrol(request,model)) {
			return "redirect:/giris";
		}
		
		if (request.getParameter("hata") != null) {
			model.addAttribute("hata", "Email Sistemde kayitli, Lutfen baska bir email deneyin!!!");
		}
		if (request.getParameter("success") != null) {
			model.addAttribute("success", "Kullanıcı sisteme başarılı bir şekilde eklendi...");
		}
		
		getCategory(model);
		addAdminToModel(model, request);
		getPicture(model, request);
		return "admin/admin_register";
	}
	
	@RequestMapping(value = "/adminEkle", method = RequestMethod.POST)
	public String adminEkle(Model model, Admin admin, HttpServletRequest request) {
		
		if (!sessionKontrol(request,model)) {
			return "redirect:/giris";
		}
		
		if (mailValidation(admin.getEmail())) {
			model.addAttribute("hata", "Email Sistemde kayitli, Lutfen baska bir email deneyin!!!");
		}
		else {
			if (new AdminCrud().create(admin)) {
				model.addAttribute("success", "Kullanıcı sisteme başarılı bir şekilde eklendi...");
			}
		}
		
		return "redirect:/adminEkle";
	}
	
	public boolean mailValidation(String mail) {
		Admin admin = null;
		admin = new AdminCrud().read(mail);
		
		if (admin == null) {
			return false;
		}else {
			//admin exist
			return true;
		}
	}
	
	@RequestMapping(value = "/adminGuncelle", method = RequestMethod.GET)
	public String adminGuncelle(Model model, HttpServletRequest request) {

		if (request.getParameter("hata") != null) {
			model.addAttribute("hata", "Email Sistemde kayitli, Lutfen baska bir email deneyin!!!");
		}
		if (request.getParameter("success") != null) {
			model.addAttribute("success", "Kullanıcı  başarılı bir şekilde güncellendi...");
		}
		if (request.getParameter("kontrol") != null) {
			getCategory(model);
			addAdminToModel(model, request);
			getPicture(model, request);
			return "admin/admin_update";
		}
		
		Integer kulid = null;
		if (!request.getSession().getAttribute("kulid").equals(null)) {
			 kulid = (Integer) request.getSession().getAttribute("kulid");
		}
		Admin admin = new Admin();
		admin.setId(kulid);
		currentAdmin = new AdminCrud().read(admin).get(0);
		if (currentAdmin != null) {
			model.addAttribute("admin", currentAdmin);
		}
		
		getCategory(model);
		addAdminToModel(model, request);
		getPicture(model, request);
		return "admin/admin_update";
	}
	
	@RequestMapping(value = "/adminGuncelle", method = RequestMethod.POST)
	public String adminGuncelle(Model model, Admin admin, HttpServletRequest request) {
		Admin ad = null;
		ArrayList<Admin> admins = new AdminCrud().read(ad);		
		for (Admin admin2 : admins) {
			if (!(admin.getId() == admin2.getId())) {
				if (admin.getEmail() == admin2.getEmail()) {
					model.addAttribute("hata", "Email Sistemde kayitli, Lutfen baska bir email deneyin!!!");
					return "redirect:/adminGuncelle";
				}
			}
		}
		
		if (new AdminCrud().update(admin)) {
			model.addAttribute("success", "Kullanıcı  başarılı bir şekilde güncellendi...");
		}
		model.addAttribute("kontrol", 1);
		getCategory(model);
		addAdminToModel(model, request);
		getPicture(model, request);
		return "redirect:/adminGuncelle";
	}
	
	public void getCategory(Model model) {
		categoryList = (ArrayList<Category>) new CategoryCrud().read(null);
		model.addAttribute("categoryList", categoryList);
	}
	
	public void addAdminToModel(Model model, HttpServletRequest request) {
		Integer kulid = null;
		if (!request.getSession().getAttribute("kulid").equals(null)) {
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
		System.out.println("kulId"+id);
		
		ArrayList<Picture> pictures = new PictureCrud().read(picture);
		if (pictures.size() > 0) {
			model.addAttribute("pictureId", pictures.get(0).getId());
			model.addAttribute("pictureName", pictures.get(0).getName());
			System.out.println("resimId : " +pictures.get(0).getId());
			System.out.println("resimName : "+ pictures.get(0).getName());
		}
	}
	
	public boolean sessionKontrol(HttpServletRequest request, Model model) {
		if (request.getSession().getAttribute("kulid") != null) {
			model.addAttribute("kulId", request.getSession().getAttribute("kulid"));
			return true;
		}else {
			return false;
		}
	}
}
