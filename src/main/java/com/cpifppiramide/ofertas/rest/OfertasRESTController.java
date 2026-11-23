package com.cpifppiramide.ofertas.rest;

import com.cpifppiramide.ofertas.dao.DAOFactory;
import com.cpifppiramide.ofertas.entities.Oferta;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfertasRESTController {

    @GetMapping("/api/ofertas")
    List<Oferta> list(){
        return DAOFactory.getInstance().getDaoOfertas().getAll();
    }

    @PostMapping("/api/ofertas")
    Oferta add(@RequestBody Oferta oferta){
        DAOFactory.getInstance().getDaoOfertas().save(oferta);
        return oferta;
    }

    @PutMapping("/api/ofertas/{titulo}/editar")
    Oferta update(@RequestBody Oferta oferta, @PathVariable String titulo){
        DAOFactory.getInstance().getDaoOfertas().update(oferta,titulo);
        return oferta;
    }

    @DeleteMapping("/api/ofertas/{titulo}")
    Boolean delete(@PathVariable String titulo){
        DAOFactory.getInstance().getDaoOfertas().delete(titulo);
        return true;
    }


}
