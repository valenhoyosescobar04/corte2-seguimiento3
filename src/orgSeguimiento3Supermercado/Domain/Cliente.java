package orgSeguimiento3Supermercado.Domain;

import java.util.List;
public class Cliente {
        private int id;
        private List<String> cestaCompra;

        public Cliente(int id, List<String> cestaCompra) {
            this.id = id;
            this.cestaCompra = cestaCompra;
        }

        public int getId() {
            return id;
        }

        public List<String> getCestaCompra() {
            return cestaCompra;
        }
    }

