package org.software.products;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.software.util.DataBase;

/**
 * Servlet implementation class ServletProducts
 */
@WebServlet("/Products")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		
		try {
			
			DataBase database = new DataBase();
			Connection con1 = database.getConnection(null);
			
			Statement stn1 = con1.createStatement();
			
			//String sql = "INSERT INTO public.products(category, name, price, foto)\r\n";
				//sql	+= "	VALUES (?, ?, ?, ?, ?)";
				//int insertados = stn1.executeUpdate(sql);
			
			String sql = "select * from products";
			
			ResultSet rs1 = stn1.executeQuery(sql);
			
			
			String html = "<table>";
			html += "<tr><td><strong>Id</strong></td>";
			html += "<td><strong>Category</strong></td>";
			html += "<td><strong>Name</strong></td>";
			html += "<td><strong>Price</strong></td>";
			html += "<td><strong>Foto</strong></td></tr>";
			
			//bof GEGINNING OF FILE
			while(rs1.next()) {
				int id = rs1.getInt("id");
				int category = rs1.getInt("category");
				String name = rs1.getString("name");
				double price = rs1.getDouble("price");
				String foto = rs1.getString("foto");
				html += "<tr><td>" + id + "</td>";
				html += "<td>" + category + "</td>";
				html += "<td>" + name + "</td>";
				html += "<td>" + price + "</td>";
				html += "<td>" + foto + "</td></tr>";
				
			}
			
			//eof END OF FILE 
			out.println(html);
			
			
		}
		catch(Exception e) {
			System.out.println("Error: " + e.toString());
		}
		
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
