/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konv;

import domen.Mesto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sb.mesto.SBMestoLocal;

/**
 *
 * @author student1
 */
@FacesConverter(value = "mestoKNV")
public class MestoKonverter implements Converter{

    @EJB
    SBMestoLocal sbMesto;
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (value!=null && !value.isEmpty()){
            
            
            try {
                Mesto m = sbMesto.vratiMestoPoID(value);
                System.out.println("mesto konverter:"+m.getNaziv());
                return m;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if (value!=null && (value instanceof Mesto)){
            
            Mesto m = (Mesto) value;
            return m.getMestoid().toString();
            
        }
        
        return "";
        
        
    }
    
}
