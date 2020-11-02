package application;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {
		//testando a classe
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("12/06/1995");
		Seller  obj = new Seller(1, "Alex Brown", "alex@gmail.com", date , 10000.0, new Department(1, "Tecnology"));
		//test
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println(obj);

	}

}
