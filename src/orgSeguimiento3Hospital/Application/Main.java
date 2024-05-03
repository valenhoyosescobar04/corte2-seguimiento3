package orgSeguimiento3Hospital.Application;
import orgSeguimiento3Hospital.Domain.Paciente;
import orgSeguimiento3Hospital.Enumeration.EspecialidadMedica;
import orgSeguimiento3Hospital.Infrastructure.Repository.HospitalRepository;
import orgSeguimiento3Hospital.Infrastructure.Repository.HospitalRepositoryImpl;
import orgSeguimiento3Hospital.Infrastructure.Thread.MedicoService;
import orgSeguimiento3Hospital.Service.MedicoServiceImpl;

import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            HospitalRepository hospitalRepository = new HospitalRepositoryImpl();

            // Crear e iniciar hilos para cada médico
            MedicoService[] medicos = new MedicoService[EspecialidadMedica.values().length];
            for (EspecialidadMedica especialidad : EspecialidadMedica.values()) {
                int indice = especialidad.ordinal();
                medicos[indice] = new MedicoServiceImpl(especialidad);
                medicos[indice].start();
            }

            // Generar y atender pacientes
            for (int i = 0; i < 10; i++) { // Generar y atender 10 pacientes
                Paciente paciente = Paciente.generarPacienteAleatorio();
                int indiceMedico = paciente.isUrgencia() ? 0 : 1; // Si es urgente, atender al primer médico
                medicos[indiceMedico].atenderPaciente(paciente);
                System.out.println('1'+i);
            }

            // Esperar a que todos los hilos de los médicos terminen antes de continuar
            for (MedicoService medico : medicos) {
                medico.join();
                System.out.println('2');
            }

            // Actualizar las estadísticas de pacientes atendidos
            int[] pacientesAtendidos = new int[EspecialidadMedica.values().length];
            for (int i = 0; i < medicos.length; i++) {
                pacientesAtendidos[i] = medicos[i].getPacientesAtendidos();
                System.out.println('3'+i);
            }


            // Guardar estadísticas de médicos en la persistencia al finalizar el programa
            hospitalRepository.guardarEstadisticasMedicos(pacientesAtendidos);


            // Mostrar menú principal después de que se hayan atendido a todos los pacientes
            mostrarMenuPrincipal(pacientesAtendidos, scanner);



        }

        private static void mostrarMenuPrincipal(int[] pacientesAtendidos, Scanner scanner) {
            boolean salir = false;
            while (!salir) {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Ver pacientes atendidos por cada médico");
                System.out.println("2. Ver estadísticas de pacientes atendidos por cada médico");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        mostrarPacientesAtendidos(pacientesAtendidos);
                        break;
                    case 2:
                        mostrarEstadisticasMedicos(pacientesAtendidos);
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
            }
        }



        private static void mostrarPacientesAtendidos(int[] pacientesAtendidos) {
            System.out.println("\n=== PACIENTES ATENDIDOS POR CADA MÉDICO ===");
            for (EspecialidadMedica especialidad : EspecialidadMedica.values()) {
                int indice = especialidad.ordinal();
                System.out.println(especialidad + ": " + pacientesAtendidos[indice]);
            }
        }


        private static void mostrarEstadisticasMedicos(int[] pacientesAtendidos) {
            System.out.println("\n=== ESTADÍSTICAS DE PACIENTES ATENDIDOS POR CADA MÉDICO ===");
            for (EspecialidadMedica especialidad : EspecialidadMedica.values()) {
                int indice = especialidad.ordinal();
                System.out.println(especialidad + ": " + pacientesAtendidos[indice]);
            }
        }

}


