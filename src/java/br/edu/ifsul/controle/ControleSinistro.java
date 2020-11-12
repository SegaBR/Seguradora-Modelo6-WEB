package br.edu.ifsul.controle;

import br.edu.ifsul.dao.SinistroDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.SeguroDAO;
import br.edu.ifsul.modelo.Sinistro;
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
@Named(value = "controleSinistro")
@ViewScoped
public class ControleSinistro implements Serializable {

    @EJB
    private SinistroDAO<Sinistro> dao;
    private Sinistro objeto;
    @EJB
    private SeguroDAO<Seguro> daoSeguro;
    
    public ControleSinistro() {
        
    }
    
    public String listar() {
        return "/privado/sinistro/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Sinistro();
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
    
    public SinistroDAO<Sinistro> getDao() {
        return dao;
    }
    
    public void setDao(SinistroDAO<Sinistro> dao) {
        this.dao = dao;
    }
    
    public Sinistro getObjeto() {
        return objeto;
    }
    
    public void setObjeto(Sinistro objeto) {
        this.objeto = objeto;
    }

    public SeguroDAO<Seguro> getDaoSeguro() {
        return daoSeguro;
    }

    public void setDaoSeguro(SeguroDAO<Seguro> daoSeguro) {
        this.daoSeguro = daoSeguro;
    }

    
    
}
