package orgSeguimiento3Hospital.Infrastructure.Repository;

public interface HospitalRepository {
        void guardarEstadisticasMedicos(int[] pacientesAtendidos);
        void cargarEstadisticasMedicos(int[] pacientesAtendidos);
    }


