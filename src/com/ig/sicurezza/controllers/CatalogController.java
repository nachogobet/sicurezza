package com.ig.sicurezza.controllers;


import java.sql.SQLException;
import java.util.List;

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
	
	@RequestMapping("/catalogodigital")
	public String getCatalog(Model model) throws SQLException {
		List <Category> catalog = catalogService.getCatalog();
		model.addAttribute("catalogo", catalog);
		return "catalogo";
	}
	
	@RequestMapping(value = "/processCatalog", method=RequestMethod.POST)
	public String processForm(@RequestBody String purchase) throws SQLException {
		purchaseService.processPurchase(purchase);
		return "pedido_confirmado";
	}
}
