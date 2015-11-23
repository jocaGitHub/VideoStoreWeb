/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.film;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import domen.Film;
import domen.Kopija;
import domen.Osoba;
import domen.Uloga;
import domen.Zaduzenje;
import exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Joca
 */
@Stateless
public class SBFilm implements SBFilmLocal {

    @PersistenceContext(unitName = "VKEJBPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Osoba> vratiListuOsoba() throws Exception {
        try {
            List<Osoba> lista = em.createNamedQuery("Osoba.findAll").getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da vrati listu osoba");
        }
    }

    @Override
    public Osoba vratiOsobu(String id) throws Exception {
        try {
            Osoba o = em.find(Osoba.class, id);
            return o;
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da vrati listu osoba");
        }
    }

    @Override
    public void sacuvajFilm(Film f, boolean b) throws Exception {
        if (b == true) {
            List<Film> listaFilmova = em.createNamedQuery("Film.findByNaziv").setParameter("naziv", f.getNaziv()).getResultList();
            if (listaFilmova.size() > 0) {
                throw new ValidationException("Vec postoji film sa takvim nazivom");
            }
            String filmid = vratiID();
            f.setFilmid(filmid);
        }
        try {
            if (f.getKopijaList() != null) {
                for (Kopija k : f.getKopijaList()) {
                    k.getKopijaPK().setFilmid(f.getFilmid());
                }
            }
            if (f.getUlogaList() != null) {
                for (Uloga u : f.getUlogaList()) {
                    u.getUlogaPK().setFilmid(f.getFilmid());
                }
            }
            em.merge(f);
            em.flush();
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da sacuva film");
        }
    }

    public String vratiID() {
        List<String> lista = em.createNativeQuery("SELECT filmid FROM film ORDER BY filmid DESC").getResultList();
        if (lista.size() > 0) {
            String id = lista.get(0);
            int najID = Integer.parseInt(id);
            najID++;
            return najID + "";
        }
        return 10001 + "";
    }

    @Override
    public List<Film> vratiListuFilmova() throws Exception {
        List<Film> lista = em.createQuery("SELECT f FROM Film f").getResultList();
        List<Zaduzenje> zad = em.createQuery("SELECT z FROM Zaduzenje z").getResultList();
        if (lista != null) {
            for (Film f1 : lista) {
                f1.getKopijaList().size();
                
            }
            for (Film f : lista) {
                List<Kopija> Nkop = new ArrayList<>();
                for (Kopija k : f.getKopijaList()) {
                    for (Zaduzenje z : zad) {
                        if (z.getKopija().equals(k) && z.getStatuszaduzenja().equals("zauzeto")) {
                            Nkop.add(k);
                        }
                    }
                }
                for (Kopija kk : Nkop) {
                    f.getKopijaList().remove(kk);
                }
            }
            return lista;
        } else {
            throw new Exception("Sistem ne moze da vrati listu filmova");
        }
    }

    @Override
    public Film vratiFilmPoID(String id) throws Exception {
        Film f = em.find(Film.class, id);
        if (f != null) {
            f.getKopijaList().size();
            return f;
        } else {
            throw new Exception("Sistem ne moze da vrati mesto po id-ju");
        }

    }

    @Override
    public List<Uloga> vratiListuUloga(Film f) throws Exception {
        List<Uloga> lista = em.createNamedQuery("Uloga.findByFilmid").setParameter("filmid", f.getFilmid()).getResultList();
        if (lista != null) {
            return lista;
        } else {
            throw new Exception("Sistem ne moze da vrati listu uloga");
        }
    }

}
