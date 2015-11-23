/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.zaduzenje;

import domen.Clan;
import domen.Film;
import domen.Kopija;
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
public class SBZaduzenje implements SBZaduzenjeLocal {

    @PersistenceContext(unitName = "VKEJBPU")
    private EntityManager em;

    @Override
    public List<Zaduzenje> vratiListuZaduzenja() throws Exception {
        List<Zaduzenje> lista = em.createQuery("SELECT z FROM Zaduzenje z").getResultList();
        if (lista != null) {
            return lista;
        } else {
            throw new Exception("Sistem ne moze da vrati listu zaduzenja");
        }
    }

    @Override
    public void sacuvajZaduzenje(Zaduzenje trenutnoZaduzenje) throws Exception {
        try {
            if (trenutnoZaduzenje.getZaduzenjePK().getZaduzenjeid().equals("-1")) {
                String zadid = vratiID();
                trenutnoZaduzenje.getZaduzenjePK().setZaduzenjeid(zadid);
            }else {
                Kopija k = em.find(Kopija.class, trenutnoZaduzenje.getKopija().getKopijaPK());
                Film f = em.find(Film.class, k.getKopijaPK().getFilmid());
                f.getKopijaList().add(k);
            }
            if(trenutnoZaduzenje.getDatumrazduzenja()!=null){
                if(trenutnoZaduzenje.getDatumrazduzenja().before(trenutnoZaduzenje.getDatumzaduzenja())){
                    throw new ValidationException("Ne mozete uneti datum razduzenja koji je pre datuma zaduzenja!");
                }
            }
            System.out.println("Cuva zaduzenje: " + trenutnoZaduzenje + " status: " + trenutnoZaduzenje.getStatuszaduzenja());
            em.merge(trenutnoZaduzenje);
            em.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Sistem ne moze da sacuva zaduzenje");

        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public String vratiID() {
        List<String> lista = em.createNativeQuery("SELECT zaduzenjeid FROM zaduzenje ORDER BY zaduzenjeid DESC").getResultList();
        if (lista.size() > 0) {
            String id = lista.get(0);
            int najID = Integer.parseInt(id);
            najID++;
            return najID + "";
        }
        return 10001 + "";
    }

    @Override
    public List<Zaduzenje> vratiListuZaduzenjaZaClana(Clan c) throws Exception {
        List<Zaduzenje> lista = em.createNamedQuery("Zaduzenje.findByClanid").setParameter("clanid", c.getClanid()).getResultList();
        return lista;
    }

    @Override
    public List<Zaduzenje> vratiListuZauzetihZaduzenja() throws Exception {
        List<Zaduzenje> lista = em.createQuery("SELECT z FROM Zaduzenje z").getResultList();
        List<Zaduzenje>listaVracenih = new ArrayList<>();
        for (Zaduzenje z : lista) {
            if(z.getStatuszaduzenja().equals("vraceno")){
                listaVracenih.add(z);
            }
        }
        for (Zaduzenje zz : listaVracenih) {
            lista.remove(zz);
        }
        
        return lista;
    }

}
