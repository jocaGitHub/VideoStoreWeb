/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joca
 */
@Entity
@Table(name = "clan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clan.findAll", query = "SELECT c FROM Clan c"),
    @NamedQuery(name = "Clan.findByIme", query = "SELECT c FROM Clan c WHERE c.ime = :ime"),
    @NamedQuery(name = "Clan.findByPrezime", query = "SELECT c FROM Clan c WHERE c.prezime = :prezime"),
    @NamedQuery(name = "Clan.findByJmbg", query = "SELECT c FROM Clan c WHERE c.jmbg = :jmbg"),
    @NamedQuery(name = "Clan.findByTelefon", query = "SELECT c FROM Clan c WHERE c.telefon = :telefon"),
    @NamedQuery(name = "Clan.findByUlicaibroj", query = "SELECT c FROM Clan c WHERE c.ulicaibroj = :ulicaibroj"),
    @NamedQuery(name = "Clan.findByDatumosnivanja", query = "SELECT c FROM Clan c WHERE c.datumosnivanja = :datumosnivanja"),
    @NamedQuery(name = "Clan.findByClanid", query = "SELECT c FROM Clan c WHERE c.clanid = :clanid")})
public class Clan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 30)
    @Column(name = "ime")
    private String ime;
    @Size(max = 30)
    @Column(name = "prezime")
    private String prezime;
    @Size(max = 13)
    @Column(name = "jmbg")
    private String jmbg;
    @Size(max = 30)
    @Column(name = "telefon")
    private String telefon;
    @Size(max = 30)
    @Column(name = "ulicaibroj")
    private String ulicaibroj;
    @Column(name = "datumosnivanja")
    @Temporal(TemporalType.DATE)
    private Date datumosnivanja;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "clanid")
    private String clanid;
    @JoinColumn(name = "mestoid", referencedColumnName = "mestoid")
    @ManyToOne
    private Mesto mestoid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clan")
    private List<Zaduzenje> zaduzenjeList;

    public Clan() {
    }

    public Clan(String clanid) {
        this.clanid = clanid;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getUlicaibroj() {
        return ulicaibroj;
    }

    public void setUlicaibroj(String ulicaibroj) {
        this.ulicaibroj = ulicaibroj;
    }

    public Date getDatumosnivanja() {
        return datumosnivanja;
    }

    public void setDatumosnivanja(Date datumosnivanja) {
        this.datumosnivanja = datumosnivanja;
    }

    public String getClanid() {
        return clanid;
    }

    public void setClanid(String clanid) {
        this.clanid = clanid;
    }

    public Mesto getMestoid() {
        return mestoid;
    }

    public void setMestoid(Mesto mestoid) {
        this.mestoid = mestoid;
    }

    @XmlTransient
    public List<Zaduzenje> getZaduzenjeList() {
        return zaduzenjeList;
    }

    public void setZaduzenjeList(List<Zaduzenje> zaduzenjeList) {
        this.zaduzenjeList = zaduzenjeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clanid != null ? clanid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clan)) {
            return false;
        }
        Clan other = (Clan) object;
        if ((this.clanid == null && other.clanid != null) || (this.clanid != null && !this.clanid.equals(other.clanid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Clan[ clanid=" + clanid + " ]";
    }
    
}
