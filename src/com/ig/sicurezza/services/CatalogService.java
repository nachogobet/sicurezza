package com.ig.sicurezza.services;

import java.sql.SQLException;
import java.util.List;

import com.ig.sicurezza.models.Category;

public interface CatalogService {
	List<Category> getCatalog() throws SQLException;
}
