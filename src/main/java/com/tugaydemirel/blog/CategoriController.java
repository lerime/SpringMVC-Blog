package com.tugaydemirel.blog;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tugaydemirel.database.AdminCrud;
import com.tugaydemirel.database.CategoryCrud;
import com.tugaydemirel.database.PictureCrud;
import com.tugaydemirel.properties.Admin;
import com.tugaydemirel.properties.Category;
import com.tugaydemirel.properties.Picture;
import com.tugaydemirel.utils.SifreleCoz;

@Controller
public class CategoriController {

	private ArrayList<Category> categoryList = null;
	private Admin currentAdmin = null;


	@RequestMapping(value = "/kategori", method = RequestMethod.GET)
	public String kategori(Model model, HttpServletRequest request) {
		System.out.println("kategori() controller");
		
		request = cookieKontrol(request);
		if (!sessionKontrol(request, model)) {
			return "redirect:/giris";
		}
		
		if (request.getParameter("categorySaveError") != null) {
			model.addAttribute("categorySaveError", "Eklenmek istenilen Kategori sistemde mevcuttur!!!");
		}
		if (request.getParameter("categorySaveSuccess") != null) {
			model.addAttribute("categorySaveError", "Kategori eklendi...");
		}
		
		getCategory(model);
		addAdminToModel(model, request);
		getPicture(model, request);
		// TODO ekleme islemi gelirse kategori ekleme icin yeni controller method yap
		return "admin/kategori";
	}



	@RequestMapping(value = "/kategoriEkle", method = RequestMethod.POST)
	public String kategoriEkle(Model model, @RequestParam String categoryName, HttpServletRequest request) {
		System.out.println("kategoriEkle() controller");
		request = cookieKontrol(request);
		if (!sessionKontrol(request, model)) {
			return "redirect:/giris";
		}
		
		boolean isSaved = false;
		// TODO text den gelecek yeni category ismini once karsilastir sistemde var mi
		// diye sonrasinda da
		// o degeri crud a gonder sonuc dogru olursa boolean deger dondur geri o da on
		// yuzde alert versin basarili diye veya tam tersi
		for (Category category : categoryList) {
			if (category.getName().equalsIgnoreCase(categoryName)) {
				model.addAttribute("categorySaveError", "Eklenmek istenilen Kategori sistemde mevcuttur!!!");
				return "redirect:/kategori";
			}
		}
		Category category = new Category();
		category.setName(categoryName.toUpperCase());
		if (new CategoryCrud().create(category)) {
			model.addAttribute("categorySaveSuccess", "Kategori eklendi...");
		}
		return "redirect:/kategori";
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
		System.out.println("getPicture()");
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
	
	

}
