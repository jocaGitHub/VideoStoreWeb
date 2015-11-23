/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.mesto;

import domen.Mesto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joca
 */
@Local
public interface SBMestoLocal {
    List<Mesto> vratiSvaMesta()throws Exception;
    public Mesto vratiMestoPoID(String id)throws Exception;
}
