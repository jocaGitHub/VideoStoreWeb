/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joca
 */
@Entity
@Table(name = "film")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f"),
    @NamedQuery(name = "Film.findByFilmid", query = "SELECT f FROM Film f WHERE f.filmid = :filmid"),
    @NamedQuery(name = "Film.findByNaziv", query = "SELECT f FROM Film f WHERE f.naziv = :naziv"),
    @NamedQuery(name = "Film.findByGodinasnimanja", query = "SELECT f FROM Film f WHERE f.godinasnimanja = :godinasnimanja"),
    @NamedQuery(name = "Film.findByZanr", query = "SELECT f FROM Film f WHERE f.zanr = :zanr")})
public class Film implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "filmid")
    private String filmid;
    @Size(max = 50)
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "godinasnimanja")
    private Integer godinasnimanja;
    @Size(max = 20)
    @Column(name = "zanr")
    private String zanr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    private List<Uloga> ulogaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    private List<Kopija> kopijaList;

    public Film() {
    }

    public Film(String filmid) {
        this.filmid = filmid;
    }

    public String getFilmid() {
        return filmid;
    }

    public void setFilmid(String filmid) {
        this.filmid = filmid;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getGodinasnimanja() {
        return godinasnimanja;
    }

    public void setGodinasnimanja(Integer godinasnimanja) {
        this.godinasnimanja = godinasnimanja;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    @XmlTransient
    public List<Uloga> getUlogaList() {
        return ulogaList;
    }

    public void setUlogaList(List<Uloga> ulogaList) {
        this.ulogaList = ulogaList;
    }

    @XmlTransient
    public List<Kopija> getKopijaList() {
        return kopijaList;
    }

    public void setKopijaList(List<Kopija> kopijaList) {
        this.kopijaList = kopijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (filmid != null ? filmid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Film)) {
            return false;
        }
        Film other = (Film) object;
        if ((this.filmid == null && other.filmid != null) || (this.filmid != null && !this.filmid.equals(other.filmid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Film[ filmid=" + filmid + " ]";
    }
    
}
