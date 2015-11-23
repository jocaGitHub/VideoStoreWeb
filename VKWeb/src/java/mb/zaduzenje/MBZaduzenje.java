/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.zaduzenje;

import domen.Film;
import domen.Zaduzenje;
import domen.ZaduzenjePK;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import mb.film.MBFilm;
import mb.prijava.MBPrijavaRadnika;
import org.primefaces.context.RequestContext;
import sb.zaduzenje.SBZaduzenje;
import sb.zaduzenje.SBZaduzenjeLocal;

/**
 *
 * @author Joca
 */
@ManagedBean
@ViewScoped
public class MBZaduzenje {

    private List<Zaduzenje> listaZaduzenja;
    private Zaduzenje trenutnoZaduzenje;
    private boolean daLiIzmena;

    public boolean isDaLiIzmena() {
        return daLiIzmena;
    }

    public void setDaLiIzmena(boolean daLiIzmena) {
        this.daLiIzmena = daLiIzmena;
    }

    @EJB
    SBZaduzenjeLocal sbZaduzenje;

    @ManagedProperty(value = "#{mBPrijavaRadnika}")
    MBPrijavaRadnika mBPrijavaRadnika;
    
    @ManagedProperty(value = "#{mBFilm}")
    private MBFilm mBFilm;

    /**
     * Creates a new instance of MBClan
     */
    public MBZaduzenje() {
        System.out.println("MBZADUZENJE KONSTRUKTOR");

    }

    public MBPrijavaRadnika getmBPrijavaRadnika() {
        return mBPrijavaRadnika;
    }

    public void setmBPrijavaRadnika(MBPrijavaRadnika mBPrijavaRadnika) {
        this.mBPrijavaRadnika = mBPrijavaRadnika;
    }

    

    @PostConstruct
    public void init() {
        try {
            listaZaduzenja = sbZaduzenje.vratiListuZaduzenja();
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        trenutnoZaduzenje = new Zaduzenje(new ZaduzenjePK());
        daLiIzmena = false;
    }

    public String sacuvajZaduzenje() {
        System.out.println("MBCLAN: sacuvajZaduzenje() - clan: " + trenutnoZaduzenje);
        try {
            String kopijaID = trenutnoZaduzenje.getKopija().getKopijaPK().getKopijaid();
            String filmID = trenutnoZaduzenje.getKopija().getKopijaPK().getFilmid();
            String clanID = trenutnoZaduzenje.getClan().getClanid();
            trenutnoZaduzenje.getZaduzenjePK().setClanid(clanID);
            trenutnoZaduzenje.getZaduzenjePK().setFilmid(filmID);
            trenutnoZaduzenje.getZaduzenjePK().setKopijaid(kopijaID);
            trenutnoZaduzenje.setZaduzio(mBPrijavaRadnika.getPrijavljeniRadnik());
            trenutnoZaduzenje.setStatuszaduzenja("zauzeto");
            
            sbZaduzenje.sacuvajZaduzenje(trenutnoZaduzenje);
//            listaZaduzenja.add(trenutnoZaduzenje);
            
            //da osvezi kopije
//            mBFilm.uzmiNovuListuFilmova();
            
            trenutnoZaduzenje = null;
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sacuvaj zaduzenje", "Zaduzenje je sacuvano");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "pretragaZaduzenja.xhtml";
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", "Sistem ne moze da sacuva zaduzenje:"+ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        return null;
    }

    public List<Zaduzenje> vratiNovuListuZaduzenja() {
        try {
            listaZaduzenja = sbZaduzenje.vratiListuZauzetihZaduzenja();
            return listaZaduzenja;
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }

    }
    public String izmeniZaduzenje(){
        try {
            trenutnoZaduzenje.setRazduzio(mBPrijavaRadnika.getPrijavljeniRadnik());
            trenutnoZaduzenje.setStatuszaduzenja("vraceno");
            System.out.println("ZAD"+trenutnoZaduzenje);
            sbZaduzenje.sacuvajZaduzenje(trenutnoZaduzenje);
//            mBFilm.uzmiNovuListuFilmova();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Izmena zaduzenja", "Zaduzenje je izmenjeno");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", "Sistem ne moze da sacuva zaduzenje:"+ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        return null;
    }
    
    public String kreirajZaduzenje() {
        System.out.println("MBCLAN: kreirajClana()");
        trenutnoZaduzenje = new Zaduzenje(new ZaduzenjePK());
        mBFilm.setTrenutniFilm(new Film());
        uzmiNovuListuFilmova();
        return "unosZaduzenja.xhtml";
    }
    
    public String prikaziPretragu(){
        trenutnoZaduzenje = new Zaduzenje(new ZaduzenjePK());
         try {
            listaZaduzenja = sbZaduzenje.vratiListuZauzetihZaduzenja();
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        return "pretragaZaduzenja.xhtml";
    }

    /**
     * Creates a new instance of MBZaduzenja
     */
    public List<Zaduzenje> getListaZaduzenja() {
        return listaZaduzenja;
    }

    public void setListaZaduzenja(List<Zaduzenje> listaZaduzenja) {
        this.listaZaduzenja = listaZaduzenja;
    }

    public Zaduzenje getTrenutnoZaduzenje() {
        return trenutnoZaduzenje;
    }

    public void setTrenutnoZaduzenje(Zaduzenje trenutnoZaduzenje) {
        this.trenutnoZaduzenje = trenutnoZaduzenje;
    }

    public MBFilm getmBFilm() {
        return mBFilm;
    }

    public void setmBFilm(MBFilm mBFilm) {
        this.mBFilm = mBFilm;
    }

    private void uzmiNovuListuFilmova() {
        mBFilm.uzmiNovuListuFilmova();
    }

}
