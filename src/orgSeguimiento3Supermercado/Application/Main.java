package orgSeguimiento3Supermercado.Application;
import orgSeguimiento3Supermercado.Domain.Cliente;
import orgSeguimiento3Supermercado.Infrastructure.Repository.SupermercadoRepository;
import orgSeguimiento3Supermercado.Infrastructure.Repository.SupermercadoRepositoryImpl;
import orgSeguimiento3Supermercado.Interface.CajaService;
import orgSeguimiento3Supermercado.Service.CajaServiceImpl;

import java.util.*;

public class Main {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            List<CajaService> cajas = new ArrayList<>();
            Random random = new Random();
            SupermercadoRepository supermercadoRepository = new SupermercadoRepositoryImpl();

            // Cargar estadísticas de cajas desde la persistencia
            int[] clientesAtendidos = new int[3];
            double[] ventasTotales = new double[3];
            supermercadoRepository.cargarEstadisticasCajas(clientesAtendidos, ventasTotales);

            // Antes de llamar a guardarEstadisticasCajas()
            System.out.println("Clientes atendidos actualizados: " + Arrays.toString(clientesAtendidos));

            // Crear instancias de cajas registradoras
            for (int i = 1; i <= 3; i++) {
                CajaService caja = new CajaServiceImpl();
                caja.abrirCaja(); // Abrir todas las cajas al inicio
                caja.setClientesAtendidos(clientesAtendidos[i - 1]);
                caja.setTotalVentas(ventasTotales[i - 1]);
                cajas.add(caja);
            }

            boolean salir = false;
            while (!salir) {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Abrir caja");
                System.out.println("2. Cerrar caja");
                System.out.println("3. Ver estadísticas de cajas");
                System.out.println("4. Reiniciar cajas (borrar persistencia)");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el número de la caja que desea abrir: ");
                        int numCajaAbrir = scanner.nextInt();
                        if (numCajaAbrir >= 0 && numCajaAbrir < cajas.size()) {
                            cajas.get(numCajaAbrir).abrirCaja();
                            System.out.println("Caja " + numCajaAbrir + " abierta.");

                            // Generar clientes aleatorios y procesarlos en la caja abierta
                            Cliente cliente = generarCliente();
                            cajas.get(numCajaAbrir).procesarCliente(cliente);
                            System.out.println("Cliente " + cliente.getId() + " atendido en la caja " + numCajaAbrir);
                        } else {
                            System.out.println("Número de caja inválido.");
                        }
                        break;
                    case 2:
                        System.out.print("Ingrese el número de la caja que desea cerrar: ");
                        int numCajaCerrar = scanner.nextInt();
                        if (numCajaCerrar >= 0 && numCajaCerrar < cajas.size()) {
                            cajas.get(numCajaCerrar).cerrarCaja();
                            System.out.println("Caja " + numCajaCerrar + " cerrada.");
                        } else {
                            System.out.println("Número de caja inválido.");
                        }
                        break;
                    case 3:
                        // Mostrar estadísticas de cada caja
                        for (int i = 0; i < cajas.size(); i++) {
                            CajaService caja = cajas.get(i);
                            System.out.println("Caja " + i + ": Clientes atendidos - " + caja.getClientesAtendidos() +
                                    ", Ventas totales - $" + caja.getTotalVentas());
                        }
                        break;
                    case 4:
                        // Reiniciar cajas (borrar persistencia)
                        supermercadoRepository.borrarPersistencia();
                        for (CajaService caja : cajas) {
                            caja.setClientesAtendidos(0);
                            caja.setTotalVentas(0.0);
                        }
                        System.out.println("Persistencia borrada. Cajas reiniciadas.");
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
            }

            // Guardar estadísticas de cajas en la persistencia al finalizar el programa
            for (int i = 0; i < cajas.size(); i++) {
                clientesAtendidos[i] = cajas.get(i).getClientesAtendidos();
                ventasTotales[i] = cajas.get(i).getTotalVentas();
            }
            supermercadoRepository.guardarEstadisticasCajas(clientesAtendidos, ventasTotales);


            scanner.close();
        }
        private static Cliente generarCliente() {
            Random random = new Random();
            int id = random.nextInt(1000); // ID aleatorio
            int numArticulos = random.nextInt(5) + 1; // Entre 1 y 5 artículos
            List<String> cestaCompra = new ArrayList<>();
            for (int i = 1; i <= numArticulos; i++) {
                cestaCompra.add("Producto " + i);
            }
            return new Cliente(id, cestaCompra);
        }
    }