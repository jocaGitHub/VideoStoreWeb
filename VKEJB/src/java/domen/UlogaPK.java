/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Joca
 */
@Embeddable
public class UlogaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "filmid")
    private String filmid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "osobaid")
    private String osobaid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ulogaid")
    private String ulogaid;

    public UlogaPK() {
    }

    public UlogaPK(String filmid, String osobaid, String ulogaid) {
        this.filmid = filmid;
        this.osobaid = osobaid;
        this.ulogaid = ulogaid;
    }

    public String getFilmid() {
        return filmid;
    }

    public void setFilmid(String filmid) {
        this.filmid = filmid;
    }

    public String getOsobaid() {
        return osobaid;
    }

    public void setOsobaid(String osobaid) {
        this.osobaid = osobaid;
    }

    public String getUlogaid() {
        return ulogaid;
    }

    public void setUlogaid(String ulogaid) {
        this.ulogaid = ulogaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (filmid != null ? filmid.hashCode() : 0);
        hash += (osobaid != null ? osobaid.hashCode() : 0);
        hash += (ulogaid != null ? ulogaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UlogaPK)) {
            return false;
        }
        UlogaPK other = (UlogaPK) object;
        if ((this.filmid == null && other.filmid != null) || (this.filmid != null && !this.filmid.equals(other.filmid))) {
            return false;
        }
        if ((this.osobaid == null && other.osobaid != null) || (this.osobaid != null && !this.osobaid.equals(other.osobaid))) {
            return false;
        }
        if ((this.ulogaid == null && other.ulogaid != null) || (this.ulogaid != null && !this.ulogaid.equals(other.ulogaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.UlogaPK[ filmid=" + filmid + ", osobaid=" + osobaid + ", ulogaid=" + ulogaid + " ]";
    }
    
}
