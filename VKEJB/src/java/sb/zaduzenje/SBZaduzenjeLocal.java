/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.zaduzenje;

import domen.Clan;
import domen.Zaduzenje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joca
 */
@Local
public interface SBZaduzenjeLocal {
    public List<Zaduzenje> vratiListuZaduzenja()throws Exception;
    public void sacuvajZaduzenje(Zaduzenje trenutnoZaduzenje)throws Exception;
    public List<Zaduzenje>vratiListuZaduzenjaZaClana(Clan c) throws Exception;
    public List<Zaduzenje> vratiListuZauzetihZaduzenja()throws Exception;
}
