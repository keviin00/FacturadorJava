package Entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


public class ReciboDTO {
    private Integer reciboid;

    private Integer cantidad;

    private Date fecha;

    private BigDecimal total;

    private Cliente cliente;

    private Set<LineaDTO> lineas;

    public Integer getReciboid() {
        return reciboid;
    }

    public void setReciboid(Integer reciboid) {
        this.reciboid = reciboid;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<LineaDTO> getLineas() {
        return lineas;
    }

    public void setLineas(Set<LineaDTO> lineas) {
        this.lineas = lineas;
    }
}
