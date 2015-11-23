/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "radnici")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Radnici.findAll", query = "SELECT r FROM Radnici r"),
    @NamedQuery(name = "Radnici.findByRadniciid", query = "SELECT r FROM Radnici r WHERE r.radniciid = :radniciid"),
    @NamedQuery(name = "Radnici.findByIme", query = "SELECT r FROM Radnici r WHERE r.ime = :ime"),
    @NamedQuery(name = "Radnici.findByPrezime", query = "SELECT r FROM Radnici r WHERE r.prezime = :prezime"),
    @NamedQuery(name = "Radnici.findByKorisnickoime", query = "SELECT r FROM Radnici r WHERE r.korisnickoime = :korisnickoime"),
    @NamedQuery(name = "Radnici.findByKorisnickasifra", query = "SELECT r FROM Radnici r WHERE r.korisnickasifra = :korisnickasifra"),
    @NamedQuery(name = "Radnici.findByTelefon", query = "SELECT r FROM Radnici r WHERE r.telefon = :telefon")})
public class Radnici implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "radniciid")
    private String radniciid;
    @Size(max = 10)
    @Column(name = "ime")
    private String ime;
    @Size(max = 20)
    @Column(name = "prezime")
    private String prezime;
    @Size(max = 20)
    @Column(name = "korisnickoime")
    private String korisnickoime;
    @Size(max = 20)
    @Column(name = "korisnickasifra")
    private String korisnickasifra;
    @Size(max = 10)
    @Column(name = "telefon")
    private String telefon;
    @OneToMany(mappedBy = "razduzio")
    private List<Zaduzenje> zaduzenjeList;
    @OneToMany(mappedBy = "zaduzio")
    private List<Zaduzenje> zaduzenjeList1;

    public Radnici() {
    }

    public Radnici(String radniciid) {
        this.radniciid = radniciid;
    }

    public String getRadniciid() {
        return radniciid;
    }

    public void setRadniciid(String radniciid) {
        this.radniciid = radniciid;
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

    public String getKorisnickoime() {
        return korisnickoime;
    }

    public void setKorisnickoime(String korisnickoime) {
        this.korisnickoime = korisnickoime;
    }

    public String getKorisnickasifra() {
        return korisnickasifra;
    }

    public void setKorisnickasifra(String korisnickasifra) {
        this.korisnickasifra = korisnickasifra;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @XmlTransient
    public List<Zaduzenje> getZaduzenjeList() {
        return zaduzenjeList;
    }

    public void setZaduzenjeList(List<Zaduzenje> zaduzenjeList) {
        this.zaduzenjeList = zaduzenjeList;
    }

    @XmlTransient
    public List<Zaduzenje> getZaduzenjeList1() {
        return zaduzenjeList1;
    }

    public void setZaduzenjeList1(List<Zaduzenje> zaduzenjeList1) {
        this.zaduzenjeList1 = zaduzenjeList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (radniciid != null ? radniciid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Radnici)) {
            return false;
        }
        Radnici other = (Radnici) object;
        if ((this.radniciid == null && other.radniciid != null) || (this.radniciid != null && !this.radniciid.equals(other.radniciid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Radnici[ radniciid=" + radniciid + " ]";
    }
    
}
