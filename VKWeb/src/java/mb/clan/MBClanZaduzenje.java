/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.clan;

import domen.Clan;
import domen.Zaduzenje;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;
import sb.zaduzenje.SBZaduzenje;
import sb.zaduzenje.SBZaduzenjeLocal;

/**
 *
 * @author Joca
 */
@ManagedBean
@RequestScoped
public class MBClanZaduzenje {

    private List<Zaduzenje> listaZaduzenja;
//    private Zaduzenje trenutnoZaduzenje;

    @ManagedProperty(value = "#{mBClan}")
    private MBClan mBClan;

    @EJB
    SBZaduzenjeLocal sBZaduzenje;

    /**
     * Creates a new instance of MBClanZaduzenje
     */
    public MBClanZaduzenje() {
        System.out.println("konsturktor MbClanZaduzenje");
    }

    @PostConstruct
    public void init() {
        try {
            listaZaduzenja = sBZaduzenje.vratiListuZaduzenjaZaClana(mBClan.getTrenutniClan());
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska", "Greska pri preuzimanju liste zaduzenja za clana");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }
    
    

    public List<Zaduzenje> getListaZaduzenja() {
        return listaZaduzenja;
    }

    public void setListaZaduzenja(List<Zaduzenje> listaZaduzenja) {
        this.listaZaduzenja = listaZaduzenja;
    }

    public MBClan getmBClan() {
        return mBClan;
    }

    public void setmBClan(MBClan mBClan) {
        this.mBClan = mBClan;
    }

}
