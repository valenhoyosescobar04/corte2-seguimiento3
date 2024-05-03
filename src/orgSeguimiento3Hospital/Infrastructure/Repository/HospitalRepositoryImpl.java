package orgSeguimiento3Hospital.Infrastructure.Repository;
import java.io.*;

public class HospitalRepositoryImpl implements HospitalRepository {

    private static final String ARCHIVO_DATOS = "datos_hospital.txt";

    @Override
    public void guardarEstadisticasMedicos(int[] pacientesAtendidos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_DATOS))) {

                writer.println(pacientesAtendidos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargarEstadisticasMedicos(int[] pacientesAtendidos) {
        File archivo = new File(ARCHIVO_DATOS);
        if (!archivo.exists()) {
            // Si el archivo no existe, no hay estadísticas para cargar, mantener las estadísticas actuales
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int indice = 0;
            while ((linea = reader.readLine()) != null) {
                pacientesAtendidos[indice] = Integer.parseInt(linea); // Reemplazar el valor actual
                indice++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
