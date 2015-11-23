/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.kopija;

import domen.Film;
import domen.Kopija;
import domen.KopijaPK;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joca
 */
@Local
public interface SBKopijaLocal {
    public List<Kopija> vratiListuKopijaZaFilm(Film f)throws Exception;
    public Kopija vratiKopijuPoID(KopijaPK pk)throws Exception;

}
