/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Carro;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author kakaz
 */
@Stateful
public class CarroDAO<TIPO> extends DAOGenerico<Carro> implements Serializable {
    public CarroDAO(){
        super();
        classePersistente=Carro.class;
    }
}