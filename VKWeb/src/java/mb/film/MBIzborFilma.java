/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.film;

import domen.Film;
import domen.Kopija;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import mb.clan.MBClan;
import org.primefaces.context.RequestContext;
import sb.film.SBFilmLocal;

/**
 *
 * @author Joca
 */
@ManagedBean
@ViewScoped
public class MBIzborFilma {
    
    private List<Film> listaFilmova;
    private Film trenutniFilm;
    
    @EJB
    private SBFilmLocal sbFilm;
         
    @ManagedProperty(value = "#{mBClan}")
    private MBClan mBClan;

    /**
     * Creates a new instance of MBIzborFilma
     */
    public MBIzborFilma() {
    }

    @PostConstruct
    public void init(){
        System.out.println("pozvao se konstrukotr mbizborfilma");
        try {
            listaFilmova = sbFilm.vratiListuFilmova();
        } catch (Exception ex) {
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", "Nije uspeo da vrati listu filmova");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public void sacuvajZaduzenje(){
        trenutniFilm = null;
        mBClan.sacuvajZaduzenje();
        uzmiNovuListu();
    }
    
    public void izmeniZaduzenje(){
        trenutniFilm = null;
        mBClan.izmeniZaduzenje();
        uzmiNovuListu();
    }
    
    public boolean imaDostupnihKopija(){
        if(trenutniFilm != null && trenutniFilm.getKopijaList() != null){
            return trenutniFilm.getKopijaList().size() > 0;
        }
        return false;
    }
    
    public List<Film> getListaFilmova() {
        return listaFilmova;
    }

    public void setListaFilmova(List<Film> listaFilmova) {
        this.listaFilmova = listaFilmova;
    }

    public SBFilmLocal getSbFilm() {
        return sbFilm;
    }

    public void setSbFilm(SBFilmLocal sbFilm) {
        this.sbFilm = sbFilm;
    }

    public Film getTrenutniFilm() {
        return trenutniFilm;
    }

    public void setTrenutniFilm(Film trenutniFilm) {
        this.trenutniFilm = trenutniFilm;
    }

    public MBClan getmBClan() {
        return mBClan;
    }

    public void setmBClan(MBClan mBClan) {
        this.mBClan = mBClan;
    }

    private void uzmiNovuListu() {
        try {
            listaFilmova = sbFilm.vratiListuFilmova();
        } catch (Exception ex) {
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", "Nije uspeo da vrati listu filmova");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
}
