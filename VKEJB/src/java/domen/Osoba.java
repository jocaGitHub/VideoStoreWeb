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
@Table(name = "osoba")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Osoba.findAll", query = "SELECT o FROM Osoba o"),
    @NamedQuery(name = "Osoba.findByOsobaid", query = "SELECT o FROM Osoba o WHERE o.osobaid = :osobaid"),
    @NamedQuery(name = "Osoba.findByIme", query = "SELECT o FROM Osoba o WHERE o.ime = :ime"),
    @NamedQuery(name = "Osoba.findByPrezime", query = "SELECT o FROM Osoba o WHERE o.prezime = :prezime")})
public class Osoba implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "osobaid")
    private String osobaid;
    @Size(max = 20)
    @Column(name = "ime")
    private String ime;
    @Size(max = 20)
    @Column(name = "prezime")
    private String prezime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "osoba")
    private List<Uloga> ulogaList;

    public Osoba() {
    }

    public Osoba(String osobaid) {
        this.osobaid = osobaid;
    }

    public String getOsobaid() {
        return osobaid;
    }

    public void setOsobaid(String osobaid) {
        this.osobaid = osobaid;
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

    @XmlTransient
    public List<Uloga> getUlogaList() {
        return ulogaList;
    }

    public void setUlogaList(List<Uloga> ulogaList) {
        this.ulogaList = ulogaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (osobaid != null ? osobaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Osoba)) {
            return false;
        }
        Osoba other = (Osoba) object;
        if ((this.osobaid == null && other.osobaid != null) || (this.osobaid != null && !this.osobaid.equals(other.osobaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Osoba[ osobaid=" + osobaid + " ]";
    }
    
}
