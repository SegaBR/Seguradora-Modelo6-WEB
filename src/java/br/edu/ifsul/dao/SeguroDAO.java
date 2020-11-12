/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Seguro;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author kakaz
 */
@Stateful
public class SeguroDAO<TIPO> extends DAOGenerico<Seguro> implements Serializable {
    public SeguroDAO(){
        super();
        classePersistente=Seguro.class;
        //ordenações 
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("inicioVigencia", "Inicio Vigencia", "="));
        listaOrdem.add(new Ordem("fimVigencia", "Fim Vigencia", "="));
        listaOrdem.add(new Ordem("valorTotal", "Valor Total", "="));
        listaOrdem.add(new Ordem("valorFipe", "Valor Fipe", "="));
        listaOrdem.add(new Ordem("corretor.cpf", "Corretor", "like"));
        listaOrdem.add(new Ordem("carro.placa", "Carro", "like"));
        ordemAtual=listaOrdem.get(1);
        converteOrdem=new ConverterOrdem(listaOrdem);
    }
    @Override
    public Seguro getObjectById(Object id)throws Exception{
        Seguro obj = em.find(Seguro.class, id);
        //inicializa a coleção para evitar o erro LazyInicializationException
        obj.getCobertura().size();
        obj.getSinistro().size();
        return obj;
    }
}
