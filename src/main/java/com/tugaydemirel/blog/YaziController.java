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
import org.springframework.web.bind.annotation.RequestParam;

import com.tugaydemirel.database.AdminCrud;
import com.tugaydemirel.database.CategoryCrud;
import com.tugaydemirel.database.PictureCrud;
import com.tugaydemirel.database.WritingCrud;
import com.tugaydemirel.properties.Admin;
import com.tugaydemirel.properties.Category;
import com.tugaydemirel.properties.Picture;
import com.tugaydemirel.properties.Writing;
import com.tugaydemirel.utils.SifreleCoz;

@Controller
public class YaziController {
	private Admin currentAdmin = null;
	


	@RequestMapping(value = "/yaziEkle", method = RequestMethod.GET)
	public String yaziEkle(HttpServletRequest request, Model model) {
		System.out.println("yaziekle() controller");
		
		request = cookieKontrol(request);
		if (!sessionKontrol(request, model)) {
			return "redirect:/giris";
		}
		
		currentAdmin = (Admin) request.getSession().getAttribute("currentAdmin");
		if (request.getParameter("hataYaziEkle") != null) {
			model.addAttribute("hataYaziEkle", request.getParameter("hataYaziEkle"));
		}
		if (request.getParameter("kayitHata") != null) {
			model.addAttribute("kayitHata", true);
		}
		if (request.getParameter("success") != null) {
			model.addAttribute("success", "Yazı başarı ile eklendi...");
		}
		if (currentAdmin != null) {

			System.out.println(currentAdmin.getName());
		}
		getCategory(model);
		addAdminToModel(model, request);
		getPicture(model, request);
		return "admin/yazi_ekle";
	}

	@RequestMapping(value = "/yaziEkle", method = RequestMethod.POST)
	public String yaziEkle2(HttpServletRequest request, Model model, @RequestParam String content, String cmbKategori,
			String title) {
		System.out.println("yaziekle2() controller");
		request = cookieKontrol(request);
		if (!sessionKontrol(request, model)) {
			return "redirect:/giris";
		}
		
		boolean kayitHataVarMi = false;
		Admin admin = new Admin();
		
		Integer kulid = null;
		if (!request.getSession().getAttribute("kulid").equals(null)) {
			 kulid = (Integer) request.getSession().getAttribute("kulid");
		}
		admin.setId(kulid);
		currentAdmin = new AdminCrud().read(admin).get(0);
		if (currentAdmin != null) {

			System.out.println(currentAdmin.getName());
		}

		Writing writing = new Writing();

		if (currentAdmin != null) {
			writing.setWriter(currentAdmin.getName() + " " + currentAdmin.getSurname());
			System.out.println(currentAdmin.getName());
			System.out.println(currentAdmin.getSurname());
		} else {
			System.out.println("kullanicinin sahip oldugu id: " + request.getSession().getAttribute("kulid"));

			System.out.println("yaziEkle2 controllerdaki admin null");
		}
		writing.setTitle(title);
		writing.setContent(content);
		writing.setCategory(cmbKategori);
		writing.setDate(new Date());
		writing.setWriterId((Integer) request.getSession().getAttribute("kulid"));

		WritingCrud writingCrud = new WritingCrud();
		ArrayList<Writing> controlWriting = writingCrud.read(writing);
		if (controlWriting.size() == 0) {
			new WritingCrud().create(writing);
			model.addAttribute("success", "Yazı başarı ile eklendi...");
		} else {
			System.out.println("Ayni title hatasi");
			model.addAttribute("hataYaziEkle", "Ayni title hatasi");
			kayitHataVarMi = true;
			model.addAttribute("kayitHata", kayitHataVarMi);
//			return yaziEkle(request, model);
//			return "redirect:/yaziEkle";
		}
		System.out.println(title);
		System.out.println(content);
		System.out.println(cmbKategori);
		return "redirect:/yaziEkle";
	}

	
	@RequestMapping(value = "/yaziGuncelle/{id}", method = RequestMethod.GET)
	public String yaziGuncelle(HttpServletRequest request, Model model, @PathVariable(value = "id") String id) {
		System.out.println("yaziguncelle() controller");
		
		request = cookieKontrol(request);
		if (!sessionKontrol(request, model)) {
			return "redirect:/giris";
		}
		Writing writing = new Writing();
		if (!id.equals("")) {
			writing.setId(Integer.valueOf(id));
			System.out.println("id null degil guzel");
			System.out.println("hatta al degerine bak:"+ writing.getId());
		}
		ArrayList<Writing> ls = new WritingCrud().read(writing);
		if (ls != null) {
			
			Writing writing2 = ls.get(0);
			System.out.println("degerler burda");
			System.out.println(writing2.getTitle());
			System.out.println(writing2.getContent());
			String data = writing2.getContent();
			
			model.addAttribute("category", writing2.getCategory());
			model.addAttribute("writing", writing2);
			model.addAttribute("cntnt", data);
			model.addAttribute("ttl", writing2.getTitle());
			
			
		}
		
		
		
		getCategory(model);
		addAdminToModel(model, request);
		getPicture(model, request);
		return "admin/yazi_guncelle";
	}
	
	@RequestMapping(value = "/yaziGuncelle", method = RequestMethod.POST)
	public String yaziGuncelle2(HttpServletRequest request, Model model, Writing writing) {
		System.out.println("yaziguncelle2() controller");
		System.out.println(writing.getCategory());
		request = cookieKontrol(request);
		if (!sessionKontrol(request, model)) {
			return "redirect:/giris";
		}
		Admin admin = new Admin();
		
		Integer kulid = null;
		if (!request.getSession().getAttribute("kulid").equals(null)) {
			 kulid = (Integer) request.getSession().getAttribute("kulid");
		}
		admin.setId(kulid);
		currentAdmin = new AdminCrud().read(admin).get(0);
	
		System.out.println(writing.getTitle());
		System.out.println(writing.getContent());
		writing.setDate(new Date());
		writing.setWriterId(currentAdmin.getId());
		writing.setWriter(currentAdmin.getName() +" " +currentAdmin.getSurname());
		new WritingCrud().update(writing);
		
		System.out.println("yazilar tarafina gondermeden once: " +writing.getCategory());
		Category category = new Category();
		category.setName(writing.getCategory());
		category = new CategoryCrud().read(category).get(0);
		
		System.out.println("asdf"+category.getId());
		return "redirect:/yonetim/yazilar/"+category.getId();
	}

	
	@RequestMapping(value = "/yonetim/yazilar/{categoryId}", method = RequestMethod.GET)
	public String kategori(Model model, HttpServletRequest request,  @PathVariable(value = "categoryId") int categoryId) {
		System.out.println("yazilar() controller");
		System.out.println();
		Category category = null;
		if (categoryId != -1) {
			category = new Category();
			category.setId(categoryId);
			System.out.println(categoryId);
			System.out.println(category.getId());
			System.out.println("clistten once");
			ArrayList<Category> cList = new CategoryCrud().read(category);
			System.out.println("clistten sonra");
			
			if (cList != null && cList.size()>0) {
				
				category = cList.get(0);
			}
			model.addAttribute("category", category.getName());
		}
		
		System.out.println(category);
		System.out.println("şıöüğ");
		
		request = cookieKontrol(request);
		if (!sessionKontrol(request, model)) {
			return "redirect:/giris";
		}
		Writing writing = null;
		if (categoryId != -1) {
			writing = new Writing();
			writing.setId(-1);
			writing.setCategory(category.getName());
		}
		ArrayList<Writing> writings = new WritingCrud().read(writing);
		if (writings != null) {
			
			model.addAttribute("writings", writings);
			model.addAttribute("writing", writing);
		}
		getCategory(model);
		addAdminToModel(model, request);
		getPicture(model, request);
		System.out.println("Olusurulan writing listesinin size i : " + writings.size());
		return "admin/yazilar";
	}


	// silme i�lemi
	@RequestMapping(value = "/sil/{id}", method = RequestMethod.GET)
	public String sil(@PathVariable(value = "id") String id, HttpServletRequest request, Model model) {
		System.out.println("controller sil()");
		
//		request = cookieKontrol(request);
//		if (!sessionKontrol(request, model)) {
//			return "redirect:/giris";
//		}
		Writing writing = new Writing();
		if (!id.equals("")) {
			writing.setId(Integer.valueOf(id));
		}
		new WritingCrud().delete(writing);
		return "redirect:/yonetim/yazilar/*";
	}

	// d�zenle data getir
	@RequestMapping(value = "/duzenle/{id}", method = RequestMethod.POST)
	public String duzenle(@PathVariable(value = "id") String id, HttpServletRequest request, Model model) {
		System.out.println("controller duzenle()");

		request = cookieKontrol(request);
		if (!sessionKontrol(request, model)) {
			return "redirect:/giris";
		}
	
		return "home";
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

}
