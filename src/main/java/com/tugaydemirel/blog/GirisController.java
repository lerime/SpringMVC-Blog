package com.tugaydemirel.blog;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tugaydemirel.database.AdminCrud;
import com.tugaydemirel.properties.Admin;
import com.tugaydemirel.utils.SifreleCoz;

@Controller
public class GirisController {
	boolean hataliGiris = false;
	private Admin currentAdmin = new Admin();

	@RequestMapping(value = "/giris", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Giris Controller");
		hataliGiris = false;
		return "admin/giris";
	}

	// giris yap
	@RequestMapping(value = "/giris", method = RequestMethod.POST)
	public String giris(@RequestParam String mail, @RequestParam String sifre, Model model, HttpServletRequest req,
			HttpServletResponse res) {
		
		hataliGiris = false;
		System.out.println("ikinci giris kontrol");
		
		if (girisValidation(mail, sifre)) {
			System.out.println("Girisi yapilan kullanici id:"+currentAdmin.getId());
			req.getSession().setAttribute("kulid", currentAdmin.getId());
			req.getSession().setAttribute("currentAdmin", currentAdmin);
			
			boolean hDurum = req.getParameter("beni_hatirla") != null;
			if (hDurum) {
				Cookie cerez = new Cookie("kulcerez", new SifreleCoz().sifrele("" + 10));
				res.addCookie(cerez);
			}
			return "redirect:/yonetim";
		} else {
			hataliGiris = true;
			model.addAttribute("hataliGiris", hataliGiris);
		}
		return "admin/giris";
	}
	
	
	public boolean girisValidation(String email, String sifre) {
		Admin ad = null;
		ArrayList<Admin> admins = (ArrayList<Admin>) new AdminCrud().read(ad);
		for (Admin admin : admins) {
			if (email.equals(admin.getEmail()) && sifre.equals(admin.getPassword())) {
				System.out.println("Validation yapiliyor:" +admin.getName());
				currentAdmin = admin;
				return true;
			}
		}
		return false;
	}

}
