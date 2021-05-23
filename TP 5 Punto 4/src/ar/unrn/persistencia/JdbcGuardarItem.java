package ar.unrn.persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import ar.unrn.ConsumirServicion;

public class JdbcGuardarItem  implements ConsumirServicion{

	ConsumirServicion servicio;
	
	
	public JdbcGuardarItem(ConsumirServicion servicio) {
		super();
		this.servicio = servicio;
	}


	@Override
	public String guardarItem() {
		Connection conexion = null;
		String items = this.servicio.guardarItem();
		try {
			conexion = ConexionBaseDeDatos.getConection();
			conexion.setAutoCommit(false);
			Statement sent = conexion.createStatement();

			sent.executeUpdate("INSERT INTO `items_sevicio`(`descripcion`) VALUES ('"+ items +"')");

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
