/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.clan;

import domen.Clan;
import domen.Zaduzenje;
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
public class SBClan implements SBClanLocal {

    @PersistenceContext(unitName = "VKEJBPU")
    private EntityManager em;

    @Override
    public List<Clan> vratiSveClanove() throws Exception {
        List<Clan> lista = em.createQuery("SELECT c FROM Clan c").getResultList();
        if (lista != null) {
            for (Clan cl : lista) {
                cl.getZaduzenjeList().size();
            }
            return lista;
        }
        throw new ValidationException("Sistem ne moze da vrati listu clanova");
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public void sacuvajClana(Clan clan) throws Exception {
        
        for (int i = 0; i < clan.getIme().length(); i++) {
            if (Character.isDigit(clan.getIme().charAt(i))) {
                throw new ValidationException("ne mozete uneti broj u polje imena");
            } else {
            }
        }
        for (int i = 0; i < clan.getPrezime().length(); i++) {
            if (Character.isDigit(clan.getPrezime().charAt(i))) {
                throw new ValidationException("ne mozete uneti broj u polje prezimena");
            } else {
            }
        }
        for (int i = 0; i < clan.getJmbg().length(); i++) {
            if (Character.isDigit(clan.getJmbg().charAt(i))) {
            } else {
                throw new ValidationException("ne mozete uneti slovo u polje jmbg");
            }
        }
        for (int i = 0; i < clan.getTelefon().length(); i++) {
            if (Character.isDigit(clan.getTelefon().charAt(i))) {
            } else {
                throw new ValidationException("ne mozete uneti slovo u polje telefon");
            }
        }

        if (clan.getClanid() == "-1") {
            clan.setClanid(vratiID());
        }
        try {
            em.merge(clan);
            em.flush();
        } catch (Exception e) {
            throw  new Exception("Sistem ne moze da sacuva clana");
        }

    }

    public String vratiID() {
        List<String> lista = em.createNativeQuery("SELECT clanid FROM clan ORDER BY clanid DESC").getResultList();
        if (lista.size() > 0) {
            String id = lista.get(0);
            int najID = Integer.parseInt(id);
            najID++;
            return najID + "";
        }
        return 10001 + "";
    }

    @Override
    public Clan vratiClana(Clan c) throws Exception {
        try {
            Clan c_db = em.find(Clan.class, c.getClanid());
            c_db.getZaduzenjeList().size();
            return c_db;
        } catch (Exception e) {
            throw new Exception("Sistem nije uspeo da vrati clana");
        }
    }

}
