package com.kerem.entity;

import com.kerem.enums.KiralamaDurum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblkiralama")
public class Kiralama {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "aracid")
    Arac arac;

    @ManyToOne
    @JoinColumn(name = "kisiid")
    Kisi kisi;

    LocalDateTime baslangicTarihi;
    LocalDateTime bitisTarihi;
    Double toplamTutar;

    @Enumerated(EnumType.STRING)
    KiralamaDurum durum;


    @Override
    public String toString() {
        return " ===========================================" + '\n' +
                "ID: " + id + '\n' +
                "Arac Id: " + arac.getId() + '\n' +
                "Kiralayan Id : " + kisi.getId() + '\n' +
                "Başlangıç Tarihi: " + baslangicTarihi.toString() + '\n' +
                "Bitiş Tarihi: " + bitisTarihi.toString() + '\n' +
                "Toplam Tutar: " + toplamTutar + '\n' +
                "Durum: " + durum + '\n' +
                " ===========================================" ;
    }
}
