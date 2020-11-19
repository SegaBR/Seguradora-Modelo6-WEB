/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CorretorDAO;
import br.edu.ifsul.modelo.Corretor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kakaz
 */
@Named(value="controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{
    
    private Corretor corretorAutenticado;
    @EJB
    private CorretorDAO<Corretor> dao;
    private String usuario;
    private String senha;
    
    public ControleLogin(){
    
    }
    
    public String irPaginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        try{
            HttpServletRequest request = (HttpServletRequest) 
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(this.usuario, this.senha);
            if(request.getUserPrincipal()!=null){
                corretorAutenticado=dao.retornarUsuario(request.getUserPrincipal().getName());
                Util.mensagemInformacao("Login realizado com sucesso!"+corretorAutenticado);
                usuario="";
                senha="";
            }
            return "/index";
        }catch(Exception e){
            Util.mensagemErro("Usuário ou senha inválidos!"+Util.getMensagemErro(e));
            return "/login";
        }
    }
    
    public String efetuarLogout(){
        try{
            corretorAutenticado=null;
            HttpServletRequest request = (HttpServletRequest) 
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
            Util.mensagemInformacao("Logout realizado com sucesso!");
            return "/index";
        }catch(Exception e){
            Util.mensagemErro("Erro! "+Util.getMensagemErro(e));
            return "/index";
        }
    }
    
    public Corretor getCorretorAutenticado() {
        return corretorAutenticado;
    }

    public void setCorretorAutenticado(Corretor corretorAutenticado) {
        this.corretorAutenticado = corretorAutenticado;
    }

    public CorretorDAO<Corretor> getDao() {
        return dao;
    }

    public void setDao(CorretorDAO<Corretor> dao) {
        this.dao = dao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
