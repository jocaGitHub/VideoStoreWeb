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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Joca
 */
@Stateless
public class SBKopija implements SBKopijaLocal {

    @PersistenceContext(unitName = "VKEJBPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Kopija> vratiListuKopijaZaFilm(Film f) throws Exception {
        List<Kopija> lista = em.createNamedQuery("Kopija.findByFilmid").setParameter("filmid", f.getFilmid()).getResultList();
        return lista;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Kopija vratiKopijuPoID(KopijaPK pk) throws Exception {
        System.out.println("vratikopiju(): filmid - " + pk.getFilmid() + " kopijaid - " + pk.getKopijaid());
        List<Kopija> lista = em.createNamedQuery("Kopija.findByFilmidKopijaid")
                .setParameter("filmid", pk.getFilmid()).setParameter("kopijaid", pk.getKopijaid()).getResultList();
        
        Kopija m = lista.get(0);
        return m;
    }

}
