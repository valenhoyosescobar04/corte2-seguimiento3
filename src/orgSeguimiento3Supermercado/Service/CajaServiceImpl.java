package orgSeguimiento3Supermercado.Service;
import orgSeguimiento3Supermercado.Domain.Cliente;
import orgSeguimiento3Supermercado.Interface.CajaService;
import orgSeguimiento3Supermercado.Interface.EstadoCaja;
import java.util.concurrent.atomic.AtomicInteger;

public class CajaServiceImpl extends Thread implements CajaService {

        private EstadoCaja estado;
        private AtomicInteger clientesAtendidos;
        private double totalVentas;

        public CajaServiceImpl() {
            this.estado = EstadoCaja.CERRADA;
            this.clientesAtendidos = new AtomicInteger(0);
            this.totalVentas = 0.0;
        }

        @Override
        public void procesarCliente(Cliente cliente) {
            // Simular el procesamiento del cliente (puede ser aleatorio)
            double totalCompra = cliente.getCestaCompra().size() * 10; // Precio unitario de los productos
            synchronized (this) {
                totalVentas += totalCompra;
            }
            clientesAtendidos.incrementAndGet();
        }

        @Override
        public void abrirCaja() {
            estado = EstadoCaja.ABIERTA;
        }

        @Override
        public void cerrarCaja() {
            estado = EstadoCaja.CERRADA;
        }

        @Override
        public EstadoCaja getEstado() {
            return estado;
        }

        @Override
        public int getClientesAtendidos() {
            return clientesAtendidos.get();
        }

        @Override
        public double getTotalVentas() {
            return totalVentas;
        }

    @Override
    public void setClientesAtendidos(int i) {

    }

    @Override
    public void setTotalVentas(double v) {

    }

}

