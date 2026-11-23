package com.cpifppiramide.ofertas.dao;

import com.cpifppiramide.ofertas.dao.ofertas.DAOOfertas;
import com.cpifppiramide.ofertas.dao.ofertas.DAOOfertasMySQL;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOOfertas daoOfertas;

    private DAOFactory(){}

    public static DAOFactory getInstance() {
        if(daoFactory == null) daoFactory = new DAOFactory();
        return daoFactory;
    }

    public DAOOfertas getDaoOfertas() {
        if(daoOfertas == null) daoOfertas = new DAOOfertasMySQL();
        return daoOfertas;
    }
}
