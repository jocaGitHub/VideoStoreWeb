/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joca
 */
@Entity
@Table(name = "uloga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uloga.findAll", query = "SELECT u FROM Uloga u"),
    @NamedQuery(name = "Uloga.findByFilmid", query = "SELECT u FROM Uloga u WHERE u.ulogaPK.filmid = :filmid"),
    @NamedQuery(name = "Uloga.findByOsobaid", query = "SELECT u FROM Uloga u WHERE u.ulogaPK.osobaid = :osobaid"),
    @NamedQuery(name = "Uloga.findByUlogaid", query = "SELECT u FROM Uloga u WHERE u.ulogaPK.ulogaid = :ulogaid"),
    @NamedQuery(name = "Uloga.findByNazivuloge", query = "SELECT u FROM Uloga u WHERE u.nazivuloge = :nazivuloge")})
public class Uloga implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UlogaPK ulogaPK;
    @Size(max = 20)
    @Column(name = "nazivuloge")
    private String nazivuloge;
    @JoinColumn(name = "filmid", referencedColumnName = "filmid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Film film;
    @JoinColumn(name = "osobaid", referencedColumnName = "osobaid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Osoba osoba;

    public Uloga() {
    }

    public Uloga(UlogaPK ulogaPK) {
        this.ulogaPK = ulogaPK;
    }

    public Uloga(String filmid, String osobaid, String ulogaid) {
        this.ulogaPK = new UlogaPK(filmid, osobaid, ulogaid);
    }

    public UlogaPK getUlogaPK() {
        return ulogaPK;
    }

    public void setUlogaPK(UlogaPK ulogaPK) {
        this.ulogaPK = ulogaPK;
    }

    public String getNazivuloge() {
        return nazivuloge;
    }

    public void setNazivuloge(String nazivuloge) {
        this.nazivuloge = nazivuloge;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ulogaPK != null ? ulogaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uloga)) {
            return false;
        }
        Uloga other = (Uloga) object;
        if ((this.ulogaPK == null && other.ulogaPK != null) || (this.ulogaPK != null && !this.ulogaPK.equals(other.ulogaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Uloga[ ulogaPK=" + ulogaPK + " ]";
    }
    
}
