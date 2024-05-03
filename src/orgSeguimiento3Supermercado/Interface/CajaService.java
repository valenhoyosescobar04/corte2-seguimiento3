package orgSeguimiento3Supermercado.Interface;
import orgSeguimiento3Supermercado.Domain.Cliente;

public interface CajaService {

        void procesarCliente(Cliente cliente);
        void abrirCaja();
        void cerrarCaja();
        EstadoCaja getEstado();
        int getClientesAtendidos();
        double getTotalVentas();

        void setClientesAtendidos(int i);

        void setTotalVentas(double v);
}
