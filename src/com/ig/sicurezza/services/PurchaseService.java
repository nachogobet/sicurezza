package com.ig.sicurezza.services;

import java.sql.SQLException;

public interface PurchaseService {
	void processPurchase(String purchase) throws SQLException;
}
