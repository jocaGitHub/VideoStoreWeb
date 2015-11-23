/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.film;

import domen.Film;
import domen.Osoba;
import domen.Uloga;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joca
 */
@Local
public interface SBFilmLocal {
    List<Osoba> vratiListuOsoba()throws Exception;
    Osoba vratiOsobu(String id)throws Exception;
    void sacuvajFilm(Film f, boolean b)throws Exception;
    List<Film> vratiListuFilmova()throws Exception;
    Film vratiFilmPoID(String id)throws Exception;
    List<Uloga>vratiListuUloga(Film f)throws Exception;
}

