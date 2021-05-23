package ar.unrn.persistencia;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import ar.unrn.ConsumirServicion;

public class JdbcGuardarItem implements ConsumirServicion {

	ConsumirServicion servicio;

	public JdbcGuardarItem(ConsumirServicion servicio) {
		super();
		this.servicio = servicio;
	}

	@Override
	public String guardarItem() {
		Connection conexion = null;
		String items = this.servicio.guardarItem();

		JSONArray arregloJson = new JSONArray(items);

		try {
			conexion = ConexionBaseDeDatos.getConection();
			conexion.setAutoCommit(false);
			Statement sent = conexion.createStatement();

			for (int i = 0; i < arregloJson.length(); i++) {

				JSONObject json = arregloJson.getJSONObject(i);

				int id = json.getInt("id");
				String title = json.getString("title");
				int userId = json.getInt("userId");
				String body = json.getString("body");

				sent.executeUpdate("INSERT INTO `items_servicio`(`user_id`, `id`, `title`, `body`) VALUES ('" + userId
						+ "','" + id + "','" + title + "','" + body + "')");

			}

			conexion.commit();
			conexion.close();

		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return items;
	}

}
