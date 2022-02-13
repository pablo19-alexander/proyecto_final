package org.software.category;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.software.util.DataBase;

@Path("/category")
public class CategoryService {
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces("application/json")
	public Response add(Category category) {
		DataBase database = new DataBase();
		Connection connection1 = null;
		PreparedStatement preparedStatement1 = null;
		String sql = "";
		String mensaje = "";
		int inserteds = 0;
		try {
			connection1 = database.getConnection("admin");
			sql = "INSERT INTO categories(name, icon, published)";
			sql += " VALUES (?, ?, ?)";
			preparedStatement1 = connection1.prepareStatement(sql);
			preparedStatement1.setString(1, category.getName());
			preparedStatement1.setString(2, category.getIcon());
			preparedStatement1.setInt(3, category.getPublished());
			inserteds = preparedStatement1.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(preparedStatement1);
			database.closeObject(connection1);
		}
		if (inserteds > 0) {
			mensaje = "{\"mensaje\":\"Adicionar OK\"}";
			return Response.status(200).entity(mensaje).build();
		} else {
			mensaje = "{\"mensaje\":\"Error al adicionar\"}";
			return Response.status(400).entity(mensaje).build();
		}
	}

	

	@PUT
	@Path("/{id}")
	@Consumes({ "application/json" })
	@Produces("application/json")
	public Response update(Category category, @PathParam(value = "id") int id) {
		DataBase database = new DataBase();
		Connection connection1 = null;
		PreparedStatement preparedStatement1 = null;
		String sql = "";
		String mensaje = "";
		int updates = 0;
		try {
			connection1 = database.getConnection("admin");
			sql = "UPDATE categories SET published=?, name=?, icon=?";
			sql += " WHERE id=?";
			preparedStatement1 = connection1.prepareStatement(sql);

			preparedStatement1.setInt(1, category.getPublished());
			preparedStatement1.setString(2, category.getName());
			preparedStatement1.setString(3, category.getIcon());
			preparedStatement1.setInt(4, id);
			updates = preparedStatement1.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(preparedStatement1);
			database.closeObject(connection1);
		}
		if (updates > 0) {
			mensaje = "{\"mensaje\":\"Modificar OK\"}";
			return Response.status(200).entity(mensaje).build();
		} else {
			mensaje = "{\"mensaje\":\"Error al modificar\"}";
			return Response.status(400).entity(mensaje).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Consumes({ "application/json" })
	@Produces("application/json")
	public Response adicionar(@PathParam(value = "id") int id) {
		DataBase database = new DataBase();
		Connection connection1 = null;
		PreparedStatement preparedStatement1 = null;
		String sql = "";
		String mensaje = "";
		int deleteds = 0;
		try {
			connection1 = database.getConnection("admin");
			sql = "DELETE FROM categories WHERE id=?";
			preparedStatement1 = connection1.prepareStatement(sql);
			preparedStatement1.setInt(1, id);
			deleteds = preparedStatement1.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(preparedStatement1);
			database.closeObject(connection1);
		}
		if (deleteds > 0) {
			mensaje = "{\"mensaje\":\"Eliminar OK\"}";
			return Response.status(200).entity(mensaje).build();
		} else {
			mensaje = "{\"mensaje\":\"Error al eliminar\"}";
			return Response.status(400).entity(mensaje).build();
		}
	}
}
