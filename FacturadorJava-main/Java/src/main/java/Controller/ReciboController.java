package Controller;

import Entity.Recibo;
import Entity.ReciboDTO;
import Service.ReciboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/Recibo")
public class ReciboController {
    @Autowired
    private ReciboService reciboService;

    @GetMapping
    public List<ReciboDTO> findAll() {
        return this.reciboService.findAll();
    }

    // Single item

    @GetMapping("/{id}")
    public ReciboDTO one(@PathVariable Integer id) {

        return this.reciboService.findById(id);
    }

    @PostMapping
    public ReciboDTO newEntity(@RequestBody Recibo recibo) {
        return this.reciboService.save(recibo);
    }
}
