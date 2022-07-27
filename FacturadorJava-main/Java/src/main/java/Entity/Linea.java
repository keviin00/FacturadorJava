package Entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


public class Linea implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer lineaid;

    private Integer cantidad;

    private String serial_number;

    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name="reciboid")
    private Recibo recibo;


    @ManyToOne
    @JoinColumn(name="productoid")
    private Producto producto;

    public Linea() {
    }

    public Integer getLineaid() {
        return this.lineaid;
    }

    public void setLineaid(Integer lineaId) {
        this.lineaid = lineaId;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getSerial_number() {
        return this.serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public BigDecimal getPrecio() {
        return this.precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Recibo getRecibo() {
        return this.recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Linea [lineaid=").append(lineaid).append(", cantidad=").append(cantidad).append(", ");
        if (serial_number != null)
            builder.append("serial_number=").append(serial_number).append(", ");
        if (precio != null)
            builder.append("precio=").append(precio);
        builder.append("]");
        return builder.toString();
    }
}
