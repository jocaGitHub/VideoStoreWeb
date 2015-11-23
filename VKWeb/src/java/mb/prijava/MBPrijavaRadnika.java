/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.prijava;

import domen.Radnici;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import sb.radnik.SBRadnikLocal;

/**
 *
 * @author Joca
 */
@ManagedBean
@SessionScoped
public class MBPrijavaRadnika {

    private Radnici prijavljeniRadnik;

    private Radnici trenutniRadnik;

    @EJB
    SBRadnikLocal sbRadnik;

    /**
     * Creates a new instance of MBPrijavaRadnika
     */
    public MBPrijavaRadnika() {
        System.out.println("MBPRIJAVARADNIKA KONSTRUKTOR");
        trenutniRadnik = new Radnici();

    }

    public String prijaviRadnika() {
        System.out.println("prijavaaaa: " + trenutniRadnik.getKorisnickoime() + trenutniRadnik.getKorisnickasifra());
        if (trenutniRadnik != null) {
            try {
                prijavljeniRadnik = sbRadnik.prijaviRadnika(trenutniRadnik.getKorisnickoime(), trenutniRadnik.getKorisnickasifra());       
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Prijava radnika", "Uspesno ste se prijavili na sistem");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return "index.xhtml";

            } catch (Exception ex) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska", ex.getMessage());
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return null;
                
            }
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska", "prijavljeni radnik je null");
            return null;
        }
        
    }

    public String odjavi() {
        prijavljeniRadnik = new Radnici();
        trenutniRadnik = new Radnici();
        return "prijavaRadnika.xhtml";
    }

    public Radnici getPrijavljeniRadnik() {
        return prijavljeniRadnik;
    }

    public void setPrijavljeniRadnik(Radnici prijavljeniRadnik) {
        this.prijavljeniRadnik = prijavljeniRadnik;
    }

    public Radnici getTrenutniRadnik() {
        return trenutniRadnik;
    }

    public void setTrenutniRadnik(Radnici trenutniRadnik) {
        this.trenutniRadnik = trenutniRadnik;
    }

}
