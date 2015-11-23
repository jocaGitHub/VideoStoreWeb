/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.radnik;

import domen.Radnici;
import javax.ejb.Local;

/**
 *
 * @author Joca
 */
@Local
public interface SBRadnikLocal {
     Radnici prijaviRadnika(String ime, String sifra)throws Exception;
}
