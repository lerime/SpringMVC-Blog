package com.tugaydemirel.blog;

import static org.hamcrest.CoreMatchers.nullValue;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tugaydemirel.database.AdminCrud;
import com.tugaydemirel.database.CategoryCrud;
import com.tugaydemirel.database.CommentCrud;
import com.tugaydemirel.database.PictureCrud;
import com.tugaydemirel.database.WritingCrud;
import com.tugaydemirel.properties.Admin;
import com.tugaydemirel.properties.Category;
import com.tugaydemirel.properties.Comment;
import com.tugaydemirel.properties.Picture;
import com.tugaydemirel.properties.Writing;
import com.tugaydemirel.utils.SifreleCoz;

@Controller
public class YonetimController {
	private Admin currentAdmin = null;
	private ArrayList<Category> categoryList = null;

	@RequestMapping(value = "/yonetim", method = RequestMethod.GET)
	public String yonetim(HttpServletRequest request, Model model) {
		System.out.println("Admin Controler");
		request = cookieKontrol(request);
		if (!sessionKontrol(request, model)) {
			return "redirect:/giris";
		}
		currentAdmin = (Admin) request.getSession().getAttribute("currentAdmin");
		if (currentAdmin != null) {

			System.out.println(currentAdmin.getName());
		}
		System.out.println("dur yolcu" + request.getSession().getAttribute("currentAdmin"));
		System.out.println("kullanicinin sahip oldugu id: " + request.getSession().getAttribute("kulid"));
		ArrayList<Writing> writings = new WritingCrud().read(null);
		model.addAttribute("writings", writings);
		
		getMessages(model);
		getCategory(model);
		addAdminToModel(model, request);
		getPicture(model, request);
		// return denetim(request, "admin/yonetim");
		return "admin/yonetim";

	}
	
	@RequestMapping(value = "/cikisYap", method = RequestMethod.GET)
	public String cikisYap(HttpServletResponse res, HttpServletRequest req) {
		
		// ��k�� yap�lma i�lemleri - �erez silme
		Cookie cc = new Cookie("kulcerez", "");
		cc.setMaxAge(0);
		res.addCookie(cc);
		
		// session silme
		req.getSession().invalidate(); // t�m sessionlar� sil
		req.getSession().removeAttribute("kulid"); // sadece adi sil
		return "redirect:/giris";
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
		} else {
			return false;
		}
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

		ArrayList<Picture> pictures = new PictureCrud().read(picture);
		if (pictures.size() > 0) {
			model.addAttribute("pictureId", pictures.get(0).getId());
			model.addAttribute("pictureName", pictures.get(0).getName());
		}
	}
	
	void getMessages(Model model) {
		Comment comment = new Comment();
		comment.setRead(false);
		ArrayList<Comment> nonReadComments = new CommentCrud().read(comment);
		model.addAttribute("nonReadComments", nonReadComments);
	}

	

}
