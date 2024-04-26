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
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class KiralamaRepository extends RepositoryManager<Kiralama, Long> {
    private EntityManager entityManager = getEntityManager();
    private KisiRepository kisiRepository = new KisiRepository();
    private AracRepository aracRepository = new AracRepository();

    public KiralamaRepository() {
        super(Kiralama.class);
    }

    public Kiralama createRental(Kisi kisi, Long aracId) {
        Kiralama kiralama = new Kiralama();
        kiralama.setKisi(kisiRepository.findById(kisi.getId()).get());
        Arac kiralanacakArac = aracRepository.findById(aracId).get();
        kiralama.setArac(kiralanacakArac);
        kiralama.setBaslangicTarihi(LocalDateTime.now());
        int gunSayisi = GetValue.getIntVal("Kiralama yapılacak gün sayısını giriniz: ");
        kiralama.setBitisTarihi(LocalDateTime.now().plusDays(gunSayisi));
        kiralama.setToplamTutar(kiralanacakArac.getGunlukFiyat() * gunSayisi);
        kiralama.setDurum(KiralamaDurum.DEVAM_EDIYOR);
        kiralanacakArac.setDurum(AracDurum.KIRADA);
        return save(kiralama);
    }

    public void changeRentalStatus(Long kiralamaId, KiralamaDurum yeniDurum) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query query = entityManager.createQuery("UPDATE Kiralama k SET k.durum = :yeniDurum WHERE k.id = :kiralamaId");
            query.setParameter("yeniDurum", yeniDurum);
            query.setParameter("kiralamaId", kiralamaId);
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
