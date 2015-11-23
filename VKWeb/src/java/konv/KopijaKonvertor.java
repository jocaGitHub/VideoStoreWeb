/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konv;

import domen.Kopija;
import domen.KopijaPK;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sb.kopija.SBKopijaLocal;

/**
 *
 * @author Joca
 */
@FacesConverter(value = "kopijaKNV")
public class KopijaKonvertor implements Converter{

    @EJB
    SBKopijaLocal sbKopija;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value!=null && !value.isEmpty()){
            
            try {
                int rb = value.indexOf(" ");
                String kopijaID = value.substring(0, rb);
                String filmID = value.substring(rb + 1, value.length());
                KopijaPK pk = new KopijaPK(kopijaID, filmID);
                Kopija k = sbKopija.vratiKopijuPoID(pk);
                System.out.println("KONVERTOR VRATIO OBJEKAT: " + k);
                return k;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
        System.out.println("KONVERTOR JE VRATIO NUUUULLLLL");
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value!=null && (value instanceof Kopija)){
            
            Kopija m = (Kopija) value;
            return m.getKopijaPK().getKopijaid().toString()+" "+m.getKopijaPK().getFilmid();
            
        }
        System.out.println("KOVERTOR VRACA PRAZAN STRING");
        return "";
    }
    
}
