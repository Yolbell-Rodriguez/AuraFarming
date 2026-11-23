package com.cpifppiramide.ofertas.dao.ofertas;

import com.cpifppiramide.ofertas.entities.Oferta;

import java.util.ArrayList;
import java.util.List;

public class DAOOfertasRAM implements DAOOfertas{

    private List<Oferta> ofertas;

    public DAOOfertasRAM(){
        this.ofertas = new ArrayList<>();
    }

    @Override
    public List<Oferta> getAll() {
        return this.ofertas;
    }

    @Override
    public void save(Oferta oferta) {
        this.ofertas.add(oferta);
    }

    @Override
    public void update(Oferta oferta, String titulo) {
        for (int i = 0; i < this.ofertas.size(); i++) {
            if(this.ofertas.get(i).getTitulo().equals(titulo)){
                this.ofertas.set(i,oferta);
                break;
            }
        }
    }

    @Override
    public void delete(String titulo) {
        for (int i = 0; i < this.ofertas.size(); i++) {
            if(this.ofertas.get(i).getTitulo().equals(titulo)){
                this.ofertas.remove(i);
                break;
            }
        }
    }
}
