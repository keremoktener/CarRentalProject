package com.kerem.repository;

import com.kerem.entity.Arac;
import com.kerem.entity.Kiralama;
import com.kerem.entity.Kisi;
import com.kerem.enums.AracDurum;
import com.kerem.enums.KiralamaDurum;
import com.kerem.utility.GetValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class AracRepository extends RepositoryManager<Arac, Long> {
    private EntityManager entityManager = getEntityManager();
    private KisiRepository kisiRepository = new KisiRepository();

    public AracRepository() {
        super(Arac.class);
    }

    //Enum durumuna göre müsait olan araçları namedQuery ile getiren metod
    public List<Arac> getAvailableVehicles() {
        TypedQuery<Arac> query = entityManager.createNamedQuery("Arac.findMusaitVehicles", Arac.class);
        query.setParameter("musaitDurum", AracDurum.MUSAIT);
        return query.getResultList();
    }

    //Enum durumuna göre kirada olan araçları JPQL ile  getiren metod
    public List<Arac> getRentedVehicles() {
        String jpql = "SELECT a FROM Arac a WHERE a.durum = :durum" ;
        TypedQuery<Arac> query = entityManager.createQuery(jpql, Arac.class);
        query.setParameter("durum", AracDurum.KIRADA);
        return query.getResultList();
    }

    public List<Kiralama> getRentedVehiclesByCustomer(Kisi kisi) {
        String jpql = "SELECT k FROM Kiralama k WHERE k.kisi = :kisi AND k.durum = :durum";
        TypedQuery<Kiralama> query = entityManager.createQuery(jpql, Kiralama.class);
        query.setParameter("kisi", kisi);
        query.setParameter("durum", KiralamaDurum.DEVAM_EDIYOR);
        return query.getResultList();
    }

    //Query ile aracın durumunu güncelleyen metod
    public void changeVehicleStatus(Long aracId, AracDurum yeniDurum) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query query = entityManager.createQuery("UPDATE Arac a SET a.durum = :yeniDurum WHERE a.id = :aracId");
            query.setParameter("yeniDurum", yeniDurum);
            query.setParameter("aracId", aracId);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }



}
