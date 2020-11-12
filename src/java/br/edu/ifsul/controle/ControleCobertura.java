package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CoberturaDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.SeguroDAO;
import br.edu.ifsul.modelo.Cobertura;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Seguro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author sega
 */
@Named(value = "controleCobertura")
@ViewScoped
public class ControleCobertura implements Serializable {

    @EJB
    private CoberturaDAO<Cobertura> dao;
    private Cobertura objeto;
    @EJB
    private SeguroDAO<Seguro> daoSeguro;
    
    public ControleCobertura() {
        
    }
    
    public String listar() {
        return "/privado/cobertura/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Cobertura();
    }
    
    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao excluir objeto: " + Util.getMensagemErro(e));
        }        
    }
    
    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }        
    }
    
    public CoberturaDAO<Cobertura> getDao() {
        return dao;
    }
    
    public void setDao(CoberturaDAO<Cobertura> dao) {
        this.dao = dao;
    }
    
    public Cobertura getObjeto() {
        return objeto;
    }
    
    public void setObjeto(Cobertura objeto) {
        this.objeto = objeto;
    }

    public SeguroDAO<Seguro> getDaoSeguro() {
        return daoSeguro;
    }

    public void setDaoSeguro(SeguroDAO<Seguro> daoSeguro) {
        this.daoSeguro = daoSeguro;
    }

    
    
}
