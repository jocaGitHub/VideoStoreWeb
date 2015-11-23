/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konv;

import domen.Clan;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sb.clan.SBClanLocal;

/**
 *
 * @author Joca
 */
@FacesConverter(value = "clanKNV")
public class ClanKonvertor implements Converter{

    @EJB
    SBClanLocal sbClan;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            Clan cl = new Clan();
            cl.setClanid(value);
            Clan c;
            try {
                c = sbClan.vratiClana(cl);
                return c;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         if (value != null && (value instanceof Clan)) {
            Clan c = (Clan) value;
            return c.getClanid();
        }
        return "";

    }
    
}
