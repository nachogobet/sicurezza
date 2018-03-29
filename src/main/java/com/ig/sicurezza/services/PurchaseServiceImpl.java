package com.ig.sicurezza.services;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.ig.sicurezza.models.Customer;
import com.ig.sicurezza.models.PurchaseItem;
import javax.mail.*;
import javax.mail.internet.*;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private Connection connection;

	public PurchaseServiceImpl() throws SQLException, URISyntaxException{
		this.connection = getConnection();
	}

	@Override
	public void processPurchase(String purchase) throws SQLException {
		List<PurchaseItem> productos = parseProducts(purchase);
		Customer cliente = parseCustomer(purchase);
		sendNotification(cliente, productos);
	}

	private void sendNotification(Customer cliente, List<PurchaseItem> items) throws SQLException {
		String from = "sicurezza.pedidos";
		String to = "ventas@dsicurezza.com";
		String pass = "pedidos.sicurezza"; 
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress toAddress = new InternetAddress(to);

			message.addRecipient(Message.RecipientType.TO, toAddress);

			message.setSubject("Pedido Online - " + cliente.getCompany() );
			message.setText(getMailBody(cliente, items));
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		catch (AddressException ae) {
			ae.printStackTrace();
		}
		catch (MessagingException me) {
			me.printStackTrace();
		} finally {
			this.connection.close();
		}
	}

	private String getMailBody(Customer cliente, List<PurchaseItem> items) {
		String mailBody = "";
		mailBody += "Pedido recibido:";
		mailBody += "\nEmpresa: " + cliente.getCompany();
		mailBody += "\nNombre: " + cliente.getName();
		mailBody += "\nEmail: " + cliente.getEmail();

		mailBody += "\n\nItems:\n";

		for(PurchaseItem item : items){
			mailBody += "\n\n"+ item.getName() + " - Cantidad: " + item.getAmount();
			if(item.getPrice() != 0.0)
				mailBody += " - Precio unitario: $ " + item.getPrice();
			else
				mailBody += " - Precio unitario: U$S " + item.getPriceDollar();
		}

		return mailBody;
	}

	private Customer parseCustomer(String purchase) {
		JSONObject obj = new JSONObject(purchase);
		String company = obj.getString("empresa");
		String name = obj.getString("nombre");
		String email = obj.getString("email");

		return new Customer(company, name, email);
	}

	private List<PurchaseItem> parseProducts(String purchase) throws SQLException {
		List<PurchaseItem> productList= new ArrayList<PurchaseItem>();
		JSONObject obj = new JSONObject(purchase);
		JSONArray products = obj.getJSONArray("purchase");
		for (int i = 0; i < products.length(); i++)
		{
			Long id = products.getJSONObject(i).getLong("id");
			Integer amount = products.getJSONObject(i).getInt("amount");   
			String name = "";
			Float price = (float) 0.0;
			Float priceDollar = (float) 0.0;
			ResultSet rs = connection.createStatement().executeQuery("SELECT p.nombre, p.precio, p.precio_dolar  FROM public.producto AS p WHERE p.id = " + id + ";");
			if(rs.next()) {
				name = rs.getString(1);
				price = rs.getFloat(2);
				priceDollar = rs.getFloat(3);
			}
			productList.add(new PurchaseItem(id, name, amount, price, priceDollar));
		}

		return productList;
	}

	private static Connection getConnection() throws URISyntaxException, SQLException {
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		return DriverManager.getConnection(dbUrl);
	}

}
