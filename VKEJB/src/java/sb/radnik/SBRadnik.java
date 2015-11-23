/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.radnik;

import domen.Radnici;
import exception.ValidationException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Joca
 */
@Stateless
public class SBRadnik implements SBRadnikLocal {
    @PersistenceContext(unitName = "VKEJBPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Radnici prijaviRadnika(String ime, String sifra)throws Exception {
        List<Radnici> lista = em.createNamedQuery("Radnici.findAll").getResultList();
        if(lista != null && lista.size() > 0){
            for (Radnici r : lista) {
                if(r.getKorisnickasifra().equals(sifra) && r.getKorisnickoime().equals(ime)){
                    return r;
                }
            }
        }
        throw new ValidationException("Sistem ne moze da nadje radnika na osnovu unetih vredosti");
    }
}
