package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.CoberturaDAO;
import br.edu.ifsul.dao.CorretorDAO;
import br.edu.ifsul.dao.SeguroDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.SinistroDAO;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Cobertura;
import br.edu.ifsul.modelo.Corretor;
import br.edu.ifsul.modelo.Seguro;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Sinistro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author sega
 */
@Named(value = "controleSeguro")
@ViewScoped
public class ControleSeguro implements Serializable {

    @EJB
    private SeguroDAO<Seguro> dao;
    private Seguro objeto;
    @EJB
    private CarroDAO<Carro> daoCarro;
    @EJB
    private CorretorDAO<Corretor> daoCorretor;
    @EJB
    private CoberturaDAO<Cobertura> daoCobertura;
    @EJB
    private SinistroDAO<Sinistro> daoSinistro;
    
    private Cobertura cobertura;
    private Boolean novaCobertura;
    private Sinistro sinistro;
    private Boolean novoSinistro;
    
    public ControleSeguro() {
        
    }
    
    public String listar() {
        return "/privado/seguro/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Seguro();
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
    
    public void novaCobertura(){
        cobertura=new Cobertura();
        novaCobertura=true;
    }
    
    public void novoSinistro(){
        sinistro=new Sinistro();
        novoSinistro=true;
    }
    
    public void alterarCobertura(int index){
        cobertura = objeto.getCobertura().get(index);
        novaCobertura=false;
    }
    
    public void alterarSinistro(int index){
        sinistro = objeto.getSinistro().get(index);
        novoSinistro=false;
    }
    
    public void salvarCobertura(){
        if(novaCobertura){
            objeto.adicionarCobertura(cobertura);
        }
        Util.mensagemInformacao("Cobertura adicionada com sucesso!");
    }
    
    public void salvarSinistro(){
        if(novoSinistro){
            objeto.adicionarSinistro(sinistro);
        }
        Util.mensagemInformacao("Sinistro adicionado com sucesso!");
    }
    
    public void removerCobertura(int index){
        objeto.removerCobertura(index);
        Util.mensagemInformacao("Cobertura removida com sucesso!");
    }
    
     public void removerSinistro(int index){
        objeto.removerSinistro(index);
        Util.mensagemInformacao("Sinistro removido com sucesso!");
    }
    
    public SeguroDAO<Seguro> getDao() {
        return dao;
    }
    
    public void setDao(SeguroDAO<Seguro> dao) {
        this.dao = dao;
    }
    
    public Seguro getObjeto() {
        return objeto;
    }
    
    public void setObjeto(Seguro objeto) {
        this.objeto = objeto;
    }

    public CarroDAO<Carro> getDaoCarro() {
        return daoCarro;
    }

    public void setDaoCarro(CarroDAO<Carro> daoCarro) {
        this.daoCarro = daoCarro;
    }

    public CorretorDAO<Corretor> getDaoCorretor() {
        return daoCorretor;
    }

    public void setDaoCorretor(CorretorDAO<Corretor> daoCorretor) {
        this.daoCorretor = daoCorretor;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public Boolean getNovaCobertura() {
        return novaCobertura;
    }

    public void setNovaCobertura(Boolean novaCobertura) {
        this.novaCobertura = novaCobertura;
    }

    public Sinistro getSinistro() {
        return sinistro;
    }

    public void setSinistro(Sinistro sinistro) {
        this.sinistro = sinistro;
    }

    public Boolean getNovoSinistro() {
        return novoSinistro;
    }

    public void setNovoSinistro(Boolean novoSinistro) {
        this.novoSinistro = novoSinistro;
    }

    public CoberturaDAO<Cobertura> getDaoCobertura() {
        return daoCobertura;
    }

    public void setDaoCobertura(CoberturaDAO<Cobertura> daoCobertura) {
        this.daoCobertura = daoCobertura;
    }

    public SinistroDAO<Sinistro> getDaoSinistro() {
        return daoSinistro;
    }

    public void setDaoSinistro(SinistroDAO<Sinistro> daoSinistro) {
        this.daoSinistro = daoSinistro;
    }
    
    
    
}
