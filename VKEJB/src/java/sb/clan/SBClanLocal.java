/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.clan;

import domen.Clan;
import domen.Zaduzenje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joca
 */
@Local
public interface SBClanLocal {
    public List<Clan> vratiSveClanove() throws Exception;
    public void sacuvajClana(Clan clan) throws Exception;
    public Clan vratiClana(Clan c) throws Exception;


}
