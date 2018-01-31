package com.ig.sicurezza.controllers;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ig.sicurezza.models.Category;
import com.ig.sicurezza.services.CatalogService;
import com.ig.sicurezza.services.PurchaseService;

@Controller
public class CatalogController {

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private PurchaseService purchaseService;

	@RequestMapping("/")
	public String getCatalog(Model model) throws SQLException {
		try{
			List <Category> catalog = catalogService.getCatalog();
			Set<Category> hs = new HashSet<Category>();
			hs.addAll(catalog);
			catalog.clear();
			catalog.addAll(hs);
			model.addAttribute("catalogo", catalog);
		} catch(SQLException e){
			e.printStackTrace();
		}

		return "catalogo";
	}

	@RequestMapping(value = "/processCatalog", method=RequestMethod.POST)
	public String processForm(@RequestBody String purchase) throws SQLException {
		purchaseService.processPurchase(purchase);
		return "pedido_confirmado";
	}
}
