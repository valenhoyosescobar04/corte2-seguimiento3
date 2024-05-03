package orgSeguimiento3Supermercado.Infrastructure.Repository;

public interface SupermercadoRepository {

    void guardarEstadisticasCajas(int[] clientesAtendidos);

    void cargarEstadisticasCajas(int[] clientesAtendidos);

    void guardarEstadisticasCajas(int[] clientesAtendidos, double[] ventasTotales);
        void cargarEstadisticasCajas(int[] clientesAtendidos, double[] ventasTotales);
        void borrarPersistencia();
    }

