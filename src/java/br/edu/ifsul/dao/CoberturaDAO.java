/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Cobertura;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author kakaz
 */
@Stateful
public class CoberturaDAO<TIPO> extends DAOGenerico<Cobertura> implements Serializable {
    public CoberturaDAO(){
        super();
        classePersistente=Cobertura.class;
        //ordenações 
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("descricao", "Descricao", "like"));
        listaOrdem.add(new Ordem("valor", "Valor", "="));
        ordemAtual=listaOrdem.get(1);
        converteOrdem=new ConverterOrdem(listaOrdem);
    }
}
