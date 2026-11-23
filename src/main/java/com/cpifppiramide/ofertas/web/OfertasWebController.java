package com.cpifppiramide.ofertas.web;

import com.cpifppiramide.ofertas.dao.DAOFactory;
import com.cpifppiramide.ofertas.entities.Oferta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @Controller (y no @RestController) -> devuelve PÁGINAS HTML, no JSON
@Controller
public class OfertasWebController {

    // GET: muestra la página con el formulario y la lista de ofertas
    @GetMapping({"/", "/ofertas"})
    public String listar(Model model) {
        // Pedimos las ofertas a MySQL (a través del DAO) y las mandamos a la página
        model.addAttribute("ofertas", DAOFactory.getInstance().getDaoOfertas().getAll());
        return "ofertas"; // -> templates/ofertas.html
    }

    // POST: recibe el formulario de "Añadir oferta"
    // @RequestParam lee cada campo del formulario por su name=""
    @PostMapping("/ofertas/guardar")
    public String guardar(@RequestParam("titulo") String titulo,
                          @RequestParam("descripcion") String descripcion) {
        Oferta oferta = new Oferta();
        oferta.setTitulo(titulo);
        oferta.setDescripcion(descripcion);
        DAOFactory.getInstance().getDaoOfertas().save(oferta);
        // redirect: vuelve a /ofertas para ver la lista actualizada
        return "redirect:/ofertas";
    }

    // POST: recibe el formulario de "Editar" de una oferta
    // tituloOriginal es el título que tenía antes, para saber CUÁL hay que cambiar
    @PostMapping("/ofertas/editar")
    public String editar(@RequestParam("tituloOriginal") String tituloOriginal,
                         @RequestParam("titulo") String titulo,
                         @RequestParam("descripcion") String descripcion) {
        Oferta oferta = new Oferta();
        oferta.setTitulo(titulo);
        oferta.setDescripcion(descripcion);
        DAOFactory.getInstance().getDaoOfertas().update(oferta, tituloOriginal);
        return "redirect:/ofertas";
    }

    // POST: recibe el formulario de "Borrar" de una oferta
    @PostMapping("/ofertas/borrar")
    public String borrar(@RequestParam("titulo") String titulo) {
        DAOFactory.getInstance().getDaoOfertas().delete(titulo);
        return "redirect:/ofertas";
    }
}
