package com.kerem.entity;

import com.kerem.enums.AracDurum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblarac")
@NamedQueries({
        @NamedQuery(name = "Arac.findMusaitVehicles", query = "select a from Arac a where a.durum = :musaitDurum")
})
public class Arac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String marka;
    String model;
    String yil;
    String yakit;
    String vites;
    String km;
    String renk;
    Double gunlukFiyat;
    String plaka;
    @Enumerated(EnumType.STRING)
    AracDurum durum;
    String resim;
    String aciklama;
    String sehir;

    @OneToMany(mappedBy = "arac", fetch = FetchType.EAGER)
    List<Kiralama> kiralamaList;



    @Override
    public String toString() {
        return " ===========================================" + '\n' +
                "ID: " + id + '\n' +
                "Marka: " + marka + '\n' +
                "Model: " + model + '\n' +
                "Yıl=" + yil + '\n' +
                "Yakıt: " + yakit + '\n' +
                "Vites: " + vites + '\n' +
                "KM: " + km + '\n' +
                "Renk: " + renk + '\n' +
                "Gunluk Fiyat: " + gunlukFiyat + " TL" + '\n' +
                "Durum: " + durum + '\n' +
                "Resim: " + resim + '\n' +
                "Aciklama: " + aciklama + '\n' +
                "Sehir: " + sehir + '\n' +
                "===========================================" ;
    }
}
