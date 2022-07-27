package Entity;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="producto")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer productoid;

    private Integer cantidad;

    private String  serial_number;

    private String marca;

    private BigDecimal precio;

    public Producto() {
    }

    public Integer getProductoid() {
        return this.productoid;
    }

    public void setProductoid(Integer productoid) {
        this.productoid = productoid;
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

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String Marca) {
        this.marca = marca;
    }

    public BigDecimal getPrecio() {
        return this.precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Producto [");
        if (productoid != null)
            builder.append("productoid=").append(productoid).append(", ");
        if (cantidad != null)
            builder.append("cantidad=").append(cantidad).append(", ");
        if (serial_number!= null)
            builder.append("serial_number=").append(serial_number);
        builder.append("]");
        return builder.toString();
    }



}
