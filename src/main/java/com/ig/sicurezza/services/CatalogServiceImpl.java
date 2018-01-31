package com.ig.sicurezza.services;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ig.sicurezza.models.Category;
import com.ig.sicurezza.models.Product;
import com.ig.sicurezza.models.SubCategory;

@Service
public class CatalogServiceImpl implements CatalogService {
	
	private Connection connection;
	
	private List<Category> catalog;
	
	public CatalogServiceImpl() throws SQLException, URISyntaxException{
		this.connection = getConnection();
		this.catalog = new ArrayList<Category>();
	}
	
	private static Connection getConnection() throws URISyntaxException, SQLException {
	    String dbUrl = System.getenv("JDBC_DATABASE_URL");
	    return DriverManager.getConnection(dbUrl);
	}

	@Override
	public List<Category> getCatalog() throws SQLException {
		this.catalog.clear();
		ResultSet rs = connection.createStatement().executeQuery("SELECT p.id, p.nombre, p.precio, p.precio_dolar, sc.nombre, c.nombre FROM public.producto AS p JOIN public.subcategoria AS sc ON p.subcategoria=sc.id JOIN public.categoria AS c ON sc.categoria=c.id;");
		while (rs.next()){
			mapProduct(rs.getLong(1),rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getString(5), rs.getString(6));
		}
		
		return this.catalog;
	}

	private void mapProduct(Long id, String name, float price, float priceDollars, String subCategory, String category) {
		Product newProduct =  new Product(id, name, price, priceDollars, subCategory, category);
		addProduct(newProduct);
	}

	private void addProduct(Product newProduct) {
		Category category = null;
		SubCategory subCat = null;
		
		looper:
		for(Category cat : catalog){
			if(cat.getName().equals(newProduct.getCategory())){
				category = cat;
				for(SubCategory sc : cat.getSubcategories()){
					if(sc.getName().equals(newProduct.getSubCategory())){
						subCat = sc;
						sc.addProduct(newProduct);
						break looper;
					}	
				}
			}		
		}
		
		if(subCat == null){
			if(category != null){
				SubCategory newSub = new SubCategory(newProduct.getSubCategory());
				newSub.addProduct(newProduct);
				category.addSubCategory(newSub);
			} else {
				Category newCat = new Category(newProduct.getCategory());
				SubCategory newSub = new SubCategory(newProduct.getSubCategory());
				newSub.addProduct(newProduct);
				newCat.addSubCategory(newSub);
				catalog.add(newCat);
			}
		}
	}
}
