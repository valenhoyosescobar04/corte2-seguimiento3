package orgSeguimiento3Hospital.Service;
import orgSeguimiento3Hospital.Domain.Paciente;
import orgSeguimiento3Hospital.Enumeration.EspecialidadMedica;
import orgSeguimiento3Hospital.Infrastructure.Thread.MedicoService;

import java.util.Random;

public class MedicoServiceImpl implements MedicoService {

        private EspecialidadMedica especialidad;
        private int pacientesAtendidos;
    private Paciente paciente;

    public MedicoServiceImpl(EspecialidadMedica especialidad) {
            this.especialidad = especialidad;
            this.pacientesAtendidos = 0;
        }

        @Override
        public void run() {
            while (true) {
                atenderPaciente(paciente);
            }
        }

        @Override
        public void atenderPaciente(Paciente paciente) {
            Random random = new Random();
            // Simular tiempo de atención aleatorio entre 1 y 5 segundos
            int tiempoAtencion = random.nextInt(5) + 1;
            try {
                Thread.sleep(tiempoAtencion * 1000);
                pacientesAtendidos++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public EspecialidadMedica getEspecialidad() {
            return especialidad;
        }

        @Override
        public int getPacientesAtendidos() {
            return pacientesAtendidos;
        }

    @Override
    public void start() {
        
    }

    @Override
    public void join() {

    }

}

