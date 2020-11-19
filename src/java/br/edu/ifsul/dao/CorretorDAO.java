/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Corretor;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author kakaz
 */
@Stateful
public class CorretorDAO<TIPO> extends DAOGenerico<Corretor> implements Serializable {
    public CorretorDAO(){
        super();
        classePersistente=Corretor.class;
        //ordenações 
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        listaOrdem.add(new Ordem("rg", "RG", "like"));
        listaOrdem.add(new Ordem("cpf", "CPF", "like"));
        listaOrdem.add(new Ordem("telefone", "Telefone", "like"));
        listaOrdem.add(new Ordem("email", "Email", "like"));
        listaOrdem.add(new Ordem("percentualComissao", "Percentual Comissão", "="));
        listaOrdem.add(new Ordem("nomeUsuario", "Nome Usuário", "like"));
        listaOrdem.add(new Ordem("senha", "Senha", "like"));
        ordemAtual=listaOrdem.get(1);
        converteOrdem=new ConverterOrdem(listaOrdem);
    }
    
    @Override
    public Corretor getObjectById(Object id)throws Exception{
        Corretor obj = em.find(Corretor.class, id);
        //inicializa a coleção para evitar o erro LazyInicializationException
        obj.getPermissoes().size();
        return obj;
    }
    
    // verifica se o nome de usuario já existe
    public boolean verificarUnicidadeNomeUsuario(String nomeUsuario) throws Exception{
        String jpql="from Corretor where nomeUsuario = :pNomeUsuario";
        Query query = em.createQuery(jpql);
        query.setParameter("pNomeUsuario",nomeUsuario);
        if(query.getResultList().size()>0) return false;
        else return true;
    }
    
     // retorna o usuario para o autenticador
    public Corretor retornarUsuario(String nomeUsuario) throws Exception{
        String jpql="from Corretor where nomeUsuario = :pNomeUsuario";
        Query query = em.createQuery(jpql);
        query.setParameter("pNomeUsuario",nomeUsuario);
        return (Corretor)query.getSingleResult();
    }
    
}
