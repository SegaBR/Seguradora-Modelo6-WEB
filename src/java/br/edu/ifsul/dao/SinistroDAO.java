/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Sinistro;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author kakaz
 */
@Stateful
public class SinistroDAO<TIPO> extends DAOGenerico<Sinistro> implements Serializable {
    public SinistroDAO(){
        super();
        classePersistente=Sinistro.class;
        //ordenações 
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("descricao", "Descricao", "like"));
        listaOrdem.add(new Ordem("data", "Data", "="));
        listaOrdem.add(new Ordem("cidade", "Cidade", "like"));
        listaOrdem.add(new Ordem("estado", "Estado", "like"));
        ordemAtual=listaOrdem.get(1);
        converteOrdem=new ConverterOrdem(listaOrdem);
    }
}
