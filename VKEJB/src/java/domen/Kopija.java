/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joca
 */
@Entity
@Table(name = "kopija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kopija.findAll", query = "SELECT k FROM Kopija k"),
    @NamedQuery(name = "Kopija.findByKopijaid", query = "SELECT k FROM Kopija k WHERE k.kopijaPK.kopijaid = :kopijaid"),
    @NamedQuery(name = "Kopija.findByFilmid", query = "SELECT k FROM Kopija k WHERE k.kopijaPK.filmid = :filmid"),
    @NamedQuery(name = "Kopija.findByFilmidKopijaid", query = "SELECT k FROM Kopija k WHERE k.kopijaPK.filmid = :filmid AND k.kopijaPK.kopijaid = :kopijaid"),
    @NamedQuery(name = "Kopija.findByRbr", query = "SELECT k FROM Kopija k WHERE k.rbr = :rbr")})
public class Kopija implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KopijaPK kopijaPK;
    @Size(max = 5)
    @Column(name = "rbr")
    private String rbr;
    @JoinColumn(name = "filmid", referencedColumnName = "filmid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Film film;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kopija")
    private List<Zaduzenje> zaduzenjeList;

    public Kopija() {
    }

    public Kopija(KopijaPK kopijaPK) {
        this.kopijaPK = kopijaPK;
    }

    public Kopija(String kopijaid, String filmid) {
        this.kopijaPK = new KopijaPK(kopijaid, filmid);
    }

    public KopijaPK getKopijaPK() {
        return kopijaPK;
    }

    public void setKopijaPK(KopijaPK kopijaPK) {
        this.kopijaPK = kopijaPK;
    }

    public String getRbr() {
        return rbr;
    }

    public void setRbr(String rbr) {
        this.rbr = rbr;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
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
        hash += (kopijaPK != null ? kopijaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kopija)) {
            return false;
        }
        Kopija other = (Kopija) object;
        if ((this.kopijaPK == null && other.kopijaPK != null) || (this.kopijaPK != null && !this.kopijaPK.equals(other.kopijaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Kopija[ kopijaPK=" + kopijaPK + " ]";
    }
    
}
