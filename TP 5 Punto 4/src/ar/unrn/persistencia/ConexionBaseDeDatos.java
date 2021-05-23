package ar.unrn.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBaseDeDatos {
	public static Connection getConection()  {

		Connection conexion = null;
		Properties propiedades = new Properties();

		InputStream entrada = null;

		try {
			entrada = new FileInputStream("datos.properties");

			propiedades.load(entrada);

			Class.forName(propiedades.getProperty("CONTROLADOR"));
			conexion = DriverManager.getConnection(propiedades.getProperty("URL"), propiedades.getProperty("USUARIO"),
					propiedades.getProperty("CONTRASEÑA"));


		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return conexion;
	}
}
