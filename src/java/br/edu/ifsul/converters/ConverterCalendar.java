/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author kakaz
 */
@FacesConverter(value = "converterCalendar")
public class ConverterCalendar implements Serializable, Converter{

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Calendar data = Calendar.getInstance();
            data.setTime(sdf.parse(value));
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null){
            return null;
        }
        Calendar data = (Calendar) value;
        return sdf.format(data.getTime());
    }

}