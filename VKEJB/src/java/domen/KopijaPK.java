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
public class KopijaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "kopijaid")
    private String kopijaid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "filmid")
    private String filmid;

    public KopijaPK() {
    }

    public KopijaPK(String kopijaid, String filmid) {
        this.kopijaid = kopijaid;
        this.filmid = filmid;
    }

    public String getKopijaid() {
        return kopijaid;
    }

    public void setKopijaid(String kopijaid) {
        this.kopijaid = kopijaid;
    }

    public String getFilmid() {
        return filmid;
    }

    public void setFilmid(String filmid) {
        this.filmid = filmid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kopijaid != null ? kopijaid.hashCode() : 0);
        hash += (filmid != null ? filmid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KopijaPK)) {
            return false;
        }
        KopijaPK other = (KopijaPK) object;
        if ((this.kopijaid == null && other.kopijaid != null) || (this.kopijaid != null && !this.kopijaid.equals(other.kopijaid))) {
            return false;
        }
        if ((this.filmid == null && other.filmid != null) || (this.filmid != null && !this.filmid.equals(other.filmid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.KopijaPK[ kopijaid=" + kopijaid + ", filmid=" + filmid + " ]";
    }
    
}
