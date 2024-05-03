package orgSeguimiento3Hospital.Infrastructure.Thread;

import orgSeguimiento3Hospital.Domain.Paciente;
import orgSeguimiento3Hospital.Enumeration.EspecialidadMedica;

public interface MedicoService extends Runnable{

        void atenderPaciente(Paciente paciente);
        EspecialidadMedica getEspecialidad();
        int getPacientesAtendidos();

    void start();

    void join();
}

