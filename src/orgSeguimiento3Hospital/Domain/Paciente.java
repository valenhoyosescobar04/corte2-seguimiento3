package orgSeguimiento3Hospital.Domain;
import java.util.Random;

public class Paciente {

        private String nombre;
        private int edad;
        private boolean urgencia;

        public Paciente(String nombre, int edad, boolean urgencia) {
            this.nombre = nombre;
            this.edad = edad;
            this.urgencia = urgencia;
        }

        // Método para generar un paciente aleatorio
        public static Paciente generarPacienteAleatorio() {
            Random random = new Random();
            String[] nombres = {"Juan", "María", "Luis", "Ana", "Pedro", "Sofía"};
            String nombre = nombres[random.nextInt(nombres.length)];
            int edad = random.nextInt(100) + 1; // Edad entre 1 y 100
            boolean urgencia = random.nextBoolean();
            return new Paciente(nombre, edad, urgencia);
        }

        // Getters
        public String getNombre() {
            return nombre;
        }

        public int getEdad() {
            return edad;
        }

        public boolean isUrgencia() {
            return urgencia;
        }

        // toString para facilitar la impresión
        @Override
        public String toString() {
            return "Paciente{" +
                    "nombre='" + nombre + '\'' +
                    ", edad=" + edad +
                    ", urgencia=" + urgencia +
                    '}';
        }
    }
