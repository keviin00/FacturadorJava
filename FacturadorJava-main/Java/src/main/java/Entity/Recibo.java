package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="recibo")
@NamedQuery(name="Recibo.findAll", query="SELECT c FROM Recibo c")
public class Recibo implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Integer reciboid;

        private Integer cantidad;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;


        private BigDecimal total;

        //bi-directional many-to-one association to Cliente
        @ManyToOne
        @JoinColumn(name="clienteid")
        private Cliente cliente;
    @OneToMany(mappedBy="recibo", cascade = CascadeType.ALL)
    private Set<Linea> lineas;

	public Recibo() {
        }

        public Integer getReciboid() {
            return this.reciboid;
        }

        public void setReciboid(Integer reciboid) {
            this.reciboid = reciboid;
        }

        public Integer getCantidad() {
            return this.cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

         public Date getFecha() {
        return this.fecha;
    }

         public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

        public BigDecimal getTotal() {
            return this.total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public Cliente getCliente() {
            return this.cliente;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }

    public Set<Linea> getLineas() {
        return this.lineas;
    }

    public void setLineas(Set<Linea> lineas) {
        this.lineas = lineas;
    }

        public Linea addLinea(Linea linea) {
            getLineas().add(linea);
            linea.setRecibo(this);

            return linea;
        }

        public Linea removeLinea(Linea linea) {
            getLineas().remove(linea);
            linea.setRecibo(null);

            return linea;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Recibo [");
            if (reciboid != null)
                builder.append("reciboid=").append(reciboid).append(", ");
            if (cantidad != null)
                builder.append("cantidad=").append(cantidad).append(", ");
            if (fecha != null)
                builder.append("fecha=").append(fecha).append(", ");
            if (total != null)
                builder.append("total=").append(total).append(", ");
            if (cliente != null)
                builder.append("cliente=").append(cliente).append(", ");
            if (lineas != null)
                builder.append("lineas=").append(lineas);
            builder.append("]");
            return builder.toString();
        }
}
