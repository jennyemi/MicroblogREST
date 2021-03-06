/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog.DAO;

import com.mycompany.microblog.DAO.exceptions.NonexistentEntityException;
import com.mycompany.microblog.entities.Commento;
import com.mycompany.microblog.entities.Post;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Jennifer
 */
public class CommentoJpaController implements Serializable {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void create(Commento commento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(commento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void edit(Commento commento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            commento = em.merge(commento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = commento.getId();
                if (findCommento(id) == null) {
                    throw new NonexistentEntityException("The commento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commento commento;
            try {
                commento = em.getReference(Commento.class, id);
                commento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The commento with id " + id + " no longer exists.", enfe);
            }
            em.remove(commento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static List<Commento> findCommentoEntities() {
        return findCommentoEntities(true, -1, -1);
    }

    public static List<Commento> findCommentoEntities(int maxResults, int firstResult) {
        return findCommentoEntities(false, maxResults, firstResult);
    }

    private static List<Commento> findCommentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Commento.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static Commento findCommento(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Commento.class, id);
        } finally {
            em.close();
        }
    }

    public static int getCommentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Commento> rt = cq.from(Commento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public static List<Commento> findCommentoByPost(Post p){
        
        EntityManager em = getEntityManager();
        List<Commento> commentoList = em.createQuery("SELECT c FROM Commento c "
        + "WHERE c.post = :p").setParameter("p", p).getResultList();
        
        return commentoList;
    }

}
