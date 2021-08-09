package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Products {
	
	ArrayList<Product> items = new ArrayList<Product>();
	
	public Products() {
	try {
        Connection conn;
        Statement stm;
        ResultSet rst;
      
        
        String sql = "select * from Products";

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Crystallio_Eder", "root", "pass");

        stm = conn.createStatement();
        rst = stm.executeQuery(sql);
        
        while (rst.next()) //next read the current record and move to next,if end of record found it return false
        {
            String product_id = rst.getString("productid");
            String product_name = rst.getString("productname");
            String product_category = rst.getString("category");
            double show_price = rst.getDouble("price");
            String description = rst.getString("description");

            Product p= new Product();
            p.setProduct_id(product_id);
            p.setProduct_name(product_name);
            p.setProduct_category(product_category);
            p.setShow_price(show_price);
            p.setDescription(description);

            items.add(p);
        }
	}
	catch(ClassNotFoundException | SQLException ex){}
	}
	
	public Product[] getItems() 
	{
        Product[] returnItems = new Product[items.size()];
        items.toArray(returnItems);

        return returnItems;
}
}