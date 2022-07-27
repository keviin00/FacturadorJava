package Service;

import Entity.*;
import Repository.ClienteRepository;
import Repository.ProductoRepository;
import Repository.ReciboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Service
public class ReciboService {
    @Autowired
    private ReciboRepository reciboRepository;
@Autowired
private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<ReciboDTO> findAll() {
        return crearRecibosDTO(this.reciboRepository.findAll());
    }

    public ReciboDTO save(Recibo recibo) {

        Boolean existeCliente = existeCliente(recibo.getCliente());

        Boolean existenProductos = existenProductos(recibo.getLineas());

        Boolean existeStock = existeStock(recibo.getLineas());

        if (existeCliente && existenProductos && existeStock) {

            var reciboAGuardar = armarRecibo(recibo);

            actualizarStock(reciboAGuardar.getLineas());

            return crearReciboDTO(this.reciboRepository.save(reciboAGuardar));
        } else {
            return new ReciboDTO();
        }
    }

    private void actualizarStock(Set<Linea> lineas) {
        for (Linea linea : lineas) {

            var cantidadVendida = linea.getCantidad();
            var producto = linea.getProducto();

            var productoDB = this.productoRepository.getById(producto.getProductoid());
            var stock = productoDB.getCantidad();
            var nuevoStock = stock - cantidadVendida;
            productoDB.setCantidad(nuevoStock);

            this.productoRepository.save(productoDB);

        }

    }

    public ReciboDTO findById(Integer id) {

        var opCliente =  this.reciboRepository.findById(id);
        if (opCliente.isPresent()) {
            return crearReciboDTO(opCliente.get());
        } else {
            return new ReciboDTO();
        }
    }

    private List<ReciboDTO> crearRecibosDTO(List<Recibo> recibos) {
        List<ReciboDTO> recibosDTOs = new ArrayList<ReciboDTO>();
        for (Recibo recibo : recibos) {
            recibosDTOs.add(this.crearReciboDTO(recibo));
        }

        return recibosDTOs;
    }

    private ReciboDTO crearReciboDTO(Recibo recibo) {
        ReciboDTO dto = new ReciboDTO();

        dto.setReciboid(recibo.getReciboid());

        dto.setCantidad(recibo.getCantidad());

        dto.setFecha(recibo.getFecha());

        dto.setTotal(recibo.getTotal());

        dto.setCliente(recibo.getCliente());

        dto.setLineas(crearLineasDTO(recibo.getLineas()));


        return dto;
    }

    private Set<LineaDTO> crearLineasDTO(Set<Linea> lineas) {
        Set<LineaDTO> dtos = new HashSet<LineaDTO>();

        for (Linea linea : lineas) {

            LineaDTO dto = new LineaDTO();

            dto.setLineaid(linea.getLineaid());

            dto.setCantidad(linea.getCantidad());

            dto.setSerial_number(linea.getSerial_number());

            dto.setPrecio(linea.getPrecio());

            dtos.add(dto);

        }

        return dtos;
    }

    private Recibo armarRecibo(Recibo recibo) {
        var reciboAGuardar = new Recibo();

        reciboAGuardar.setCliente(this.clienteRepository.findById(recibo.getCliente().getClienteid()).get());

        WorldClock worldClock = this.restTemplate.getForObject("http://worldclockapi.com/api/json/utc/now", WorldClock.class);

        String currentDateTime = worldClock.getCurrentDateTime();
        // "2021-12-08T17:36Z"
        try {
            Date date1=new SimpleDateFormat("yyyy-MM-dd'T'mm:ss'Z'").parse(currentDateTime);
            reciboAGuardar.setFecha(date1);
        } catch (ParseException e) {
            e.printStackTrace();
            reciboAGuardar.setFecha(new Date());
        }
        reciboAGuardar.setLineas(new HashSet<Linea>());
        for (Linea linea : recibo.getLineas()) {
            reciboAGuardar.addLinea(crearLinea(linea));
        }

//		ReciboAGuardar.setLineas(armarLineas(Recibo.getLineas(), ReciboAGuardar));


        reciboAGuardar.setTotal(calcularTotal(reciboAGuardar.getLineas()));
        reciboAGuardar.setCantidad(recibo.getLineas().size());

        return reciboAGuardar;
    }

    private BigDecimal calcularTotal(Set<Linea> lineas) {
        BigDecimal total = new BigDecimal("0");

        for (Linea linea : lineas) {
            total = total.add(new BigDecimal(linea.getPrecio().toString()));
        }

        return total;
    }

    private Linea crearLinea(Linea linea) {
        Linea lineaAGuardar = new Linea();

        Producto productoDB = this.productoRepository.findById(linea.getProducto().getProductoid()).get();
        lineaAGuardar.setCantidad(linea.getCantidad());
        lineaAGuardar.setSerial_number(productoDB.getSerial_number());
        lineaAGuardar.setPrecio(productoDB.getPrecio());
        lineaAGuardar.setProducto(productoDB);

        return lineaAGuardar;
    }

    private Boolean existeStock(Set<Linea> lineas) {
        for (Linea linea : lineas) {
            var productoid = linea.getProducto().getProductoid();
            var opProducto = this.productoRepository.findById(productoid);
            if (opProducto.isEmpty()) {
                return false;
            }
            if (linea.getCantidad() < opProducto.get().getCantidad()) {
                return true;
            }
        }
        return false;
    }

    private Boolean existenProductos(Set<Linea> lineas) {
        for (Linea linea : lineas) {
            var productoId = linea.getProducto().getProductoid();
            var opProducto = this.productoRepository.findById(productoId);
            if (opProducto.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private Boolean existeCliente(Cliente cliente) {
        var opCliente = this.clienteRepository.findById(cliente.getClienteid());
        return !opCliente.isEmpty();
    }
}
