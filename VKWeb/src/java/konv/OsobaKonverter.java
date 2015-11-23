/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konv;

import domen.Osoba;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sb.film.SBFilm;
import sb.film.SBFilmLocal;

/**
 *
 * @author Joca
 */
@FacesConverter("osobaKNV")
public class OsobaKonverter implements Converter {

    @EJB
    SBFilmLocal sbFilm;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                String id = value;
                Osoba o = sbFilm.vratiOsobu(id);
                System.out.println("CLANCONVERTER: osoba - " + o);
                return o;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && (value instanceof Osoba)) {

            Osoba o = (Osoba) value;
            return o.getOsobaid();

        }

        return "";
    }

}
