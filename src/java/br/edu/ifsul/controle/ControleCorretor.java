package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CorretorDAO;
import br.edu.ifsul.dao.PermissaoDAO;
import br.edu.ifsul.modelo.Corretor;
import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author sega
 */
@Named(value = "controleCorretor")
@ViewScoped
public class ControleCorretor implements Serializable {

    @EJB
    private CorretorDAO<Corretor> dao;
    private Corretor objeto;
    @EJB
    private PermissaoDAO<Permissao> daoPermissao;
    private Permissao permissao;
    
    
    public ControleCorretor() {
        
    }
    
    public String listar() {
        return "/privado/corretor/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Corretor();
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
    
     public void salvarPermissao(){
        if(!objeto.getPermissoes().contains(permissao)){
            objeto.getPermissoes().add(permissao);
            Util.mensagemInformacao("Permissao adicionado com sucesso!");
        }else{
            Util.mensagemErro("Carro já possui este acessorio!");
        }
    }
    
    public void excluirPermissao(Permissao obj){
        objeto.getPermissoes().remove(obj);
        Util.mensagemInformacao("Permissao removido com sucesso!");
    }
    
    public CorretorDAO<Corretor> getDao() {
        return dao;
    }
    
    public void setDao(CorretorDAO<Corretor> dao) {
        this.dao = dao;
    }
    
    public Corretor getObjeto() {
        return objeto;
    }
    
    public void setObjeto(Corretor objeto) {
        this.objeto = objeto;
    }

    public PermissaoDAO<Permissao> getDaoPermissao() {
        return daoPermissao;
    }

    public void setDaoPermissao(PermissaoDAO<Permissao> daoPermissao) {
        this.daoPermissao = daoPermissao;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
    
}
