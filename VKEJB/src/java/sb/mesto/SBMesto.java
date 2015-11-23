/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.mesto;

import domen.Mesto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Joca
 */
@Stateless
public class SBMesto implements SBMestoLocal {

    @PersistenceContext(unitName = "VKEJBPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Mesto> vratiSvaMesta() throws Exception {
        List<Mesto> l = em.createQuery("SELECT m FROM Mesto m ORDER BY m.naziv").getResultList();
        if (l != null) {
            return l;
        } else {
            throw new Exception("Sistem ne moze da vrati mesta");
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Mesto vratiMestoPoID(String id) throws Exception {
        Mesto m = em.find(Mesto.class, id);
        if (m != null) {
            return m;
        } else {
            throw new Exception("Sistem ne moze da nadje mesto pod tim id-jem");
        }

    }
}
