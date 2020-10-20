package br.edu.ifsul.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sega
 */
public class DAOGenerico<TIPO> implements Serializable {
    private List<TIPO> listaObjetos;
    private List<TIPO> listaTodos;
    @PersistenceContext(unitName="Seguradora-Modelo6PU")
    protected EntityManager em;
    protected Class classePersistente;

    
    public List<TIPO> getListaObjetos() {
        String jpql="from " +classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }

    public void setListaObjetos(List<TIPO> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<TIPO> getListaTodos() {
        String jpql="from " +classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }

    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }
    
    public void persist(TIPO obj) throws Exception{
        em.persist(obj);
    }
    
    public void merge(TIPO obj) throws Exception{
        em.merge(obj);
    }
    
    public void remove(TIPO obj) throws Exception{
        obj=em.merge(obj);
        em.remove(obj);
    }
    
    public TIPO getObjectById(Object id) throws Exception{
        return (TIPO)em.find(classePersistente, id);
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }
    
}
