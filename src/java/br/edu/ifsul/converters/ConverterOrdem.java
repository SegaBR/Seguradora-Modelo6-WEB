/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.dao.Ordem;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kakaz
 */
@FacesConverter(value="converterOrdem")
public class ConverterOrdem implements Serializable, Converter {
        
    private List<Ordem> listaOrdem;

    public ConverterOrdem(List<Ordem> listaOrdem) {
        this.listaOrdem = listaOrdem;
    }
    
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Ordem retorno=null;
        for(Ordem o : listaOrdem){
            if(o.getAtributo().equals(value)){
                retorno=o;
            }
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null){
            return null;
        }
        return value.toString();
    }

    public List<Ordem> getListaOrdem() {
        return listaOrdem;
    }

    public void setListaOrdem(List<Ordem> listaOrdem) {
        this.listaOrdem = listaOrdem;
    }
    
    
}
