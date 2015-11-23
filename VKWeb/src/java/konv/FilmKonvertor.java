/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konv;

import domen.Film;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sb.film.SBFilmLocal;

/**
 *
 * @author Joca
 */
@FacesConverter(value = "filmKNV")
public class FilmKonvertor implements Converter {

    @EJB
    SBFilmLocal sbFilm;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            String id = value;
            Film f;
            try {
                f = sbFilm.vratiFilmPoID(id);
                return f;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && (value instanceof Film)) {
            Film f = (Film) value;
            return f.getFilmid();
        }
        return "";

    }

}
