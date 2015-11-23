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
@Table(name = "mesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesto.findAll", query = "SELECT m FROM Mesto m"),
    @NamedQuery(name = "Mesto.findByMestoid", query = "SELECT m FROM Mesto m WHERE m.mestoid = :mestoid"),
    @NamedQuery(name = "Mesto.findByPtt", query = "SELECT m FROM Mesto m WHERE m.ptt = :ptt"),
    @NamedQuery(name = "Mesto.findByNaziv", query = "SELECT m FROM Mesto m WHERE m.naziv = :naziv")})
public class Mesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "mestoid")
    private String mestoid;
    @Column(name = "ptt")
    private Integer ptt;
    @Size(max = 30)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(mappedBy = "mestoid")
    private List<Clan> clanList;

    public Mesto() {
    }

    public Mesto(String mestoid) {
        this.mestoid = mestoid;
    }

    public String getMestoid() {
        return mestoid;
    }

    public void setMestoid(String mestoid) {
        this.mestoid = mestoid;
    }

    public Integer getPtt() {
        return ptt;
    }

    public void setPtt(Integer ptt) {
        this.ptt = ptt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Clan> getClanList() {
        return clanList;
    }

    public void setClanList(List<Clan> clanList) {
        this.clanList = clanList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mestoid != null ? mestoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesto)) {
            return false;
        }
        Mesto other = (Mesto) object;
        if ((this.mestoid == null && other.mestoid != null) || (this.mestoid != null && !this.mestoid.equals(other.mestoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Mesto[ mestoid=" + mestoid + " ]";
    }
    
}
