/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.clan;

import domen.Clan;
import domen.Film;
import domen.Mesto;
import domen.Zaduzenje;
import domen.ZaduzenjePK;
import java.io.Serializable;
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
import sb.clan.SBClanLocal;
import sb.mesto.SBMestoLocal;
import sb.zaduzenje.SBZaduzenje;
import sb.zaduzenje.SBZaduzenjeLocal;

/**
 *
 * @author Joca
 */
@ManagedBean
@SessionScoped
public class MBClan implements Serializable {

    private List<Clan> listaClanova;
    private List<Mesto> listaMesta;
    private Clan trenutniClan;
    private Zaduzenje zaduzenje;
    private boolean vidljivostZaduzenja;
    private boolean vidljivost2;

    @EJB
    SBClanLocal sbClan;

    @EJB
    SBMestoLocal sbMesto;

    @EJB
    SBZaduzenjeLocal sbZaduzenje;

    @ManagedProperty(value = "#{mBPrijavaRadnika}")
    MBPrijavaRadnika mBPrijavaRadnika;

    @ManagedProperty(value = "#{mBFilm}")
    private MBFilm mBFilm;

    /**
     * Creates a new instance of MBClan
     */
    public MBClan() {
        System.out.println("MBCLAN KONSTRUKTOR");

    }

    @PostConstruct
    public void init() {
        try {
            listaClanova = sbClan.vratiSveClanove();
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska", ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        try {
            listaMesta = sbMesto.vratiSvaMesta();
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska", ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        trenutniClan = new Clan();
        vidljivostZaduzenja = false;
        vidljivost2 = false;
    }

    public String izmeniClana() {
        return "unosClana.xhtml";
    }

    public List<Clan> getListaClanova() {
        return listaClanova;
    }

    public void setListaClanova(List<Clan> listaClanova) {
        this.listaClanova = listaClanova;
    }

    public Clan getTrenutniClan() {
        return trenutniClan;
    }

    public void setTrenutniClan(Clan trenutniClan) {
        this.trenutniClan = trenutniClan;
    }

    public void vidljivostIzmene(boolean b) {
        vidljivost2 = b;
    }

    public String sacuvajNovogClana() {
        System.out.println("sacuvajNovogClana(): " + trenutniClan);
        try {
            sbClan.sacuvajClana(trenutniClan);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unos clana", "Clan je sacuvan");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "prikazClana_1.xhtml";
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska", "Sistem ne moze da sacuva clana: "+ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }

    }

    public List<Clan> vratiNovuListuClanova() {
        try {
            listaClanova = sbClan.vratiSveClanove();
            return listaClanova;
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska", ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }

    public String kreirajClana() {
        System.out.println("kreirajClana()");
        trenutniClan = new Clan("-1");
        mBFilm.setTrenutniFilm(new Film());
        return "unosClana.xhtml";
    }


    public String kreirajZaduzenje() {
        System.out.println("MBCLAN: kreirajZaduzenje()");
        zaduzenje = new Zaduzenje(new ZaduzenjePK());
        vidljivostZaduzenja = true;
        return null;
    }

    public String prikaziPretragu() {
        trenutniClan = null;
        vratiNovuListuClanova();
        return "pretragaClanova.xhtml";
    }

    public String sacuvajZaduzenje() {
        System.out.println("MBCLAN: sacuvajZaduzenje() - clan: " + zaduzenje);
        try {
            String kopijaID = zaduzenje.getKopija().getKopijaPK().getKopijaid();
            String filmID = zaduzenje.getKopija().getKopijaPK().getFilmid();
            zaduzenje.setClan(trenutniClan);
            zaduzenje.getZaduzenjePK().setClanid(trenutniClan.getClanid());
            zaduzenje.getZaduzenjePK().setFilmid(filmID);
            zaduzenje.getZaduzenjePK().setKopijaid(kopijaID);
            zaduzenje.setZaduzio(mBPrijavaRadnika.getPrijavljeniRadnik());
            zaduzenje.setStatuszaduzenja("zauzeto");

            sbZaduzenje.sacuvajZaduzenje(zaduzenje);
            trenutniClan.getZaduzenjeList().add(zaduzenje);
//            vratiNovuListuClanova();
//            mBFilm.uzmiNovuListuFilmova();
            zaduzenje = null;
            vidljivostZaduzenja = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sacuvaj zaduzenje", "Zaduzenje je sacuvano");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", "Sistem ne moze da sacuva zaduzenje:"+ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        
        return null;
    }

    public String izmeniZaduzenje() {
        try {
            zaduzenje.setRazduzio(mBPrijavaRadnika.getPrijavljeniRadnik());
            zaduzenje.setStatuszaduzenja("vraceno");
            System.out.println("ZAD" + zaduzenje);
            sbZaduzenje.sacuvajZaduzenje(zaduzenje);
            vidljivost2 = false;
            trenutniClan = sbClan.vratiClana(trenutniClan);
//            mBFilm.uzmiNovuListuFilmova();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Izmena zaduzenja", "Zaduzenje je izmenjeno");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        return null;

    }

    public String otkaziZaduzenje() {
        vidljivostZaduzenja = false;
        zaduzenje = null;
        return null;
    }

    public List<Mesto> getListaMesta() {
        return listaMesta;
    }

    public void setListaMesta(List<Mesto> listaMesta) {
        this.listaMesta = listaMesta;
    }

    public String prikaziClana() {
        try {
            trenutniClan = sbClan.vratiClana(trenutniClan);
            return "prikazClana_1.xhtml";
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska", ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }

    public Zaduzenje getZaduzenje() {
        return zaduzenje;
    }

    public void setZaduzenje(Zaduzenje zaduzenje) {
        this.zaduzenje = zaduzenje;
    }

    public boolean isVidljivostZaduzenja() {
        return vidljivostZaduzenja;
    }

    public void setVidljivostZaduzenja(boolean vidljivostZaduzenja) {
        this.vidljivostZaduzenja = vidljivostZaduzenja;
    }

    public MBPrijavaRadnika getmBPrijavaRadnika() {
        System.out.println("MBPRIJAVARADNIKA KONSTRUKTOR");
        return mBPrijavaRadnika;
    }

    public void setmBPrijavaRadnika(MBPrijavaRadnika mBPrijavaRadnika) {
        this.mBPrijavaRadnika = mBPrijavaRadnika;
    }

    public MBFilm getmBFilm() {
        return mBFilm;
    }

    public void setmBFilm(MBFilm mBFilm) {
        this.mBFilm = mBFilm;
    }

    public boolean isVidljivost2() {
        return vidljivost2;
    }

    public void setVidljivost2(boolean vidljivost2) {
        this.vidljivost2 = vidljivost2;
    }

}
