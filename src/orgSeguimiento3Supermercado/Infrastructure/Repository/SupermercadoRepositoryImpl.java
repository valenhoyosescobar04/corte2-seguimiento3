package orgSeguimiento3Supermercado.Infrastructure.Repository;
import java.io.*;

public class SupermercadoRepositoryImpl implements SupermercadoRepository {

        private static final String ARCHIVO_DATOS = "datos_supermercado.txt"; // Ruta relativa al directorio del proyecto

        @Override
        public void guardarEstadisticasCajas(int[] clientesAtendidos) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_DATOS))) {
                for (int clientes : clientesAtendidos) {
                    writer.println(clientes);
                }
            } catch (IOException e) {
                System.err.println("Error al guardar las estadísticas de las cajas: " + e.getMessage());
            }
        }

        @Override
        public void cargarEstadisticasCajas(int[] clientesAtendidos) {
            File archivo = new File(ARCHIVO_DATOS);
            if (!archivo.exists()) {
                System.out.println("El archivo de datos no existe. No se cargaron estadísticas previas.");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                int indice = 0;
                while ((linea = reader.readLine()) != null) {
                    clientesAtendidos[indice] = Integer.parseInt(linea);
                    indice++;
                }
            } catch (IOException e) {
                System.err.println("Error al cargar las estadísticas de las cajas: " + e.getMessage());
            }
        }

    @Override
    public void guardarEstadisticasCajas(int[] clientesAtendidos, double[] ventasTotales) {

    }

    @Override
    public void cargarEstadisticasCajas(int[] clientesAtendidos, double[] ventasTotales) {

    }

    @Override
    public void borrarPersistencia() {

    }
}


