package com.tugaydemirel.utils;


public class SifreleCoz {

	   private char[] Alfabem = {'n', '&', '6', 'Z', '#', 'v', 'O', 'f', '=', 'H', '<', 'j', '3', 'X', 'W', '-', 's', '8', 'm', 'p', 'h', 'M', '_', ';', '5', '%', 'D', 'r', 'o', '0', 'Y', 'u', 'A', '/', '1', 'S', 'J', '7', 'k', 'U', '4', 'e', '2', 'a', '+', 'l', 'B', 'K', 'c', 'V', ':', 't', 'g', 'z', 'E', 'd', '?', 'q', '9', '*', 'Q', 'x', 'C', 'y', 'N', 'T', 'P', 'G', 'w', '>', 'F', 'i', 'L', '!', 'I', 'b', 'R'};


	    public String sifrele(String data) {
	        String sifre = "";
	        int ss = (int) Math.round(Math.random() * 1000);
	        for (int i = 0; i < data.length(); i++) {
	            int harf = ss + (int) data.charAt(i);
	            //int harf12 = ss + (int) data.charAt(i);
	            String kod = "";
	            while (harf > Alfabem.length) {
	                int sira = (harf % Alfabem.length);
	                kod += "" + Alfabem[sira];
	                harf = harf / Alfabem.length;
	            }
	            int sira = (harf % Alfabem.length);
	            kod = "" + Alfabem[sira] + kod;
	            if (kod.length() < 2) {
	                kod = Alfabem[0] + "" + Alfabem[0] + kod;
	            }
	            if (kod.length() < 3) {
	                kod = Alfabem[0] + kod;
	            }
	            sifre += kod;

	        }
	        //Rastgele bunulunan sayının sifreli koda eklenmesi
	        String kod = "";
	        while (ss > Alfabem.length) {
	            int sira = (ss % Alfabem.length);
	            kod += "" + Alfabem[sira];
	            ss = ss / Alfabem.length;
	        }
	        int sira = (ss % Alfabem.length);
	        kod = "" + Alfabem[sira] + kod;
	        if (kod.length() < 2) {
	            kod = Alfabem[0] + "" + Alfabem[0] + kod;
	        }
	        if (kod.length() < 3) {
	            kod = Alfabem[0] + kod;
	        }
	        sifre += kod;
	        //Rastgele bunulunan sayının sifreli koda eklenmesi

	        return sifre;

	    }

	    public String sifrecoz(String sifre) {
	        String cozumSifre = "";
	        int[] h = new int[3];
	        int s = 0;
	        for (int i = 0; i < 2; i++) {
	            for (int j = 0; j < Alfabem.length; j++) {
	                if (sifre.charAt(sifre.length() - i - 1) == Alfabem[j]) {
	                    h[2 - i] = j;
	                }
	            }
	        }
	        int sifrekod = h[0] * Alfabem.length * Alfabem.length + h[1] * Alfabem.length + h[2];
	        for (int i = 0; i < sifre.length() - 3; i++) {
	            for (int j = 0; j < Alfabem.length; j++) {
	                if (sifre.charAt(i) == Alfabem[j]) {
	                    h[s] = j;
	                    s++;
	                }
	            }
	            if (s > 2) {
	                s = 0;
	                int asci = h[0] * Alfabem.length * Alfabem.length + h[1] * Alfabem.length + h[2] - sifrekod;
	                cozumSifre += ((char) asci);
	            }
	        }
	        return cozumSifre;
	    }

	}
