package ar.unrn.persistencia;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ar.unrn.ConsumirServicion;

public class DiscoGuardarItem implements ConsumirServicion {
	ConsumirServicion servicio;
	String nombreArchivo;

	public DiscoGuardarItem(String nombreArchivo, ConsumirServicion servicio) {
		super();
		this.nombreArchivo = nombreArchivo;
		this.servicio = servicio;
	}

	@Override
	public String guardarItem() {
		FileWriter archivoSalida;
		String items = this.servicio.guardarItem();
		try {

			File file = new File(this.nombreArchivo);
			archivoSalida = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(archivoSalida);
			buffer.write(items);
			buffer.close();
			Desktop.getDesktop().open(file);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return items;

	}

}
