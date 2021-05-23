package ar.unrn.main;

import javax.swing.JOptionPane;

import ar.unrn.ConsumirServicion;
import ar.unrn.RestCall;
import ar.unrn.persistencia.DiscoGuardarItem;
import ar.unrn.persistencia.JdbcGuardarItem;

public class Main {

	public static void main(String[] args) {
		try {
			
			String nombreArchivo = "Archivo De Items.txt";

			
			ConsumirServicion servicioConArchivoYJdbc = new JdbcGuardarItem(new DiscoGuardarItem(nombreArchivo,
				new RestCall("https://jsonplaceholder.typicode.com/posts")));

			servicioConArchivoYJdbc.guardarItem();
			
			ConsumirServicion servicioConArchivo = new DiscoGuardarItem(nombreArchivo,
					new RestCall("https://jsonplaceholder.typicode.com/posts"));

			servicioConArchivo.guardarItem();

			ConsumirServicion servicioConBaseDeDatos = new JdbcGuardarItem(
					new RestCall("https://jsonplaceholder.typicode.com/posts"));

			servicioConBaseDeDatos.guardarItem();
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

}
