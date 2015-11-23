/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dario
 */
@Entity
@Table(name = "zaduzenje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaduzenje.findAll", query = "SELECT z FROM Zaduzenje z"),
    @NamedQuery(name = "Zaduzenje.findByZaduzenjeid", query = "SELECT z FROM Zaduzenje z WHERE z.zaduzenjePK.zaduzenjeid = :zaduzenjeid"),
    @NamedQuery(name = "Zaduzenje.findByClanid", query = "SELECT z FROM Zaduzenje z WHERE z.zaduzenjePK.clanid = :clanid"),
    @NamedQuery(name = "Zaduzenje.findByKopijaid", query = "SELECT z FROM Zaduzenje z WHERE z.zaduzenjePK.kopijaid = :kopijaid"),
    @NamedQuery(name = "Zaduzenje.findByFilmid", query = "SELECT z FROM Zaduzenje z WHERE z.zaduzenjePK.filmid = :filmid"),
    @NamedQuery(name = "Zaduzenje.findByDatumzaduzenja", query = "SELECT z FROM Zaduzenje z WHERE z.datumzaduzenja = :datumzaduzenja"),
    @NamedQuery(name = "Zaduzenje.findByDatumrazduzenja", query = "SELECT z FROM Zaduzenje z WHERE z.datumrazduzenja = :datumrazduzenja"),
    @NamedQuery(name = "Zaduzenje.findByStatuszaduzenja", query = "SELECT z FROM Zaduzenje z WHERE z.statuszaduzenja = :statuszaduzenja")})
public class Zaduzenje implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ZaduzenjePK zaduzenjePK;
    @Column(name = "datumzaduzenja")
    @Temporal(TemporalType.DATE)
    private Date datumzaduzenja;
    @Column(name = "datumrazduzenja")
    @Temporal(TemporalType.DATE)
    private Date datumrazduzenja;
    @Size(max = 10)
    @Column(name = "statuszaduzenja")
    private String statuszaduzenja;
    @JoinColumns({
        @JoinColumn(name = "kopijaid", referencedColumnName = "kopijaid", insertable = false, updatable = false),
        @JoinColumn(name = "filmid", referencedColumnName = "filmid", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Kopija kopija;
    @JoinColumn(name = "clanid", referencedColumnName = "clanid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clan clan;
    @JoinColumn(name = "razduzio", referencedColumnName = "radniciid")
    @ManyToOne
    private Radnici razduzio;
    @JoinColumn(name = "zaduzio", referencedColumnName = "radniciid")
    @ManyToOne
    private Radnici zaduzio;

    public Zaduzenje() {
    }

    public Zaduzenje(ZaduzenjePK zaduzenjePK) {
        this.zaduzenjePK = zaduzenjePK;
    }

    public Zaduzenje(String zaduzenjeid, String clanid, String kopijaid, String filmid) {
        this.zaduzenjePK = new ZaduzenjePK(zaduzenjeid, clanid, kopijaid, filmid);
    }

    public ZaduzenjePK getZaduzenjePK() {
        return zaduzenjePK;
    }

    public void setZaduzenjePK(ZaduzenjePK zaduzenjePK) {
        this.zaduzenjePK = zaduzenjePK;
    }

    public Date getDatumzaduzenja() {
        return datumzaduzenja;
    }

    public void setDatumzaduzenja(Date datumzaduzenja) {
        this.datumzaduzenja = datumzaduzenja;
    }

    public Date getDatumrazduzenja() {
        return datumrazduzenja;
    }

    public void setDatumrazduzenja(Date datumrazduzenja) {
        this.datumrazduzenja = datumrazduzenja;
    }

    public String getStatuszaduzenja() {
        return statuszaduzenja;
    }

    public void setStatuszaduzenja(String statuszaduzenja) {
        this.statuszaduzenja = statuszaduzenja;
    }

    public Kopija getKopija() {
        return kopija;
    }

    public void setKopija(Kopija kopija) {
        this.kopija = kopija;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Radnici getRazduzio() {
        return razduzio;
    }

    public void setRazduzio(Radnici razduzio) {
        this.razduzio = razduzio;
    }

    public Radnici getZaduzio() {
        return zaduzio;
    }

    public void setZaduzio(Radnici zaduzio) {
        this.zaduzio = zaduzio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zaduzenjePK != null ? zaduzenjePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zaduzenje)) {
            return false;
        }
        Zaduzenje other = (Zaduzenje) object;
        if ((this.zaduzenjePK == null && other.zaduzenjePK != null) || (this.zaduzenjePK != null && !this.zaduzenjePK.equals(other.zaduzenjePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Zaduzenje[ zaduzenjePK=" + zaduzenjePK + " ]";
    }
    
}
