package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author sega
 */
@Named(value = "controleCarro")
@ViewScoped
public class ControleCarro implements Serializable {

    @EJB
    private CarroDAO<Carro> dao;
    private Carro objeto;
    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    
    public ControleCarro() {
        
    }
    
    public String listar() {
        return "/privado/carro/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Carro();
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
    
    public CarroDAO<Carro> getDao() {
        return dao;
    }
    
    public void setDao(CarroDAO<Carro> dao) {
        this.dao = dao;
    }
    
    public Carro getObjeto() {
        return objeto;
    }
    
    public void setObjeto(Carro objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }
    
}
