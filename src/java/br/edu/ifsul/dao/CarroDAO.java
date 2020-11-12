/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
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
        //ordenações 
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("placa", "Placa", "like"));
        listaOrdem.add(new Ordem("renavam", "Renavam", "like"));
        listaOrdem.add(new Ordem("modelo", "Modelo", "like"));
        listaOrdem.add(new Ordem("fabricante", "Fabricante", "like"));
        listaOrdem.add(new Ordem("anoFabricacao", "Ano Fabricação", "="));
        listaOrdem.add(new Ordem("anoModelo", "Ano Modelo", "="));
        listaOrdem.add(new Ordem("pessoa.cpf", "Pessoa", "like]"));
        listaOrdem.add(new Ordem("acessorios.descricao", "Acessorios", "like"));
        ordemAtual=listaOrdem.get(1);
        converteOrdem=new ConverterOrdem(listaOrdem);
    }
    
    @Override
    public Carro getObjectById(Object id)throws Exception{
        Carro obj = em.find(Carro.class, id);
        //inicializa a coleção para evitar o erro LazyInicializationException
        obj.getAcessoriosCarro().size();
        return obj;
    }
    
}
