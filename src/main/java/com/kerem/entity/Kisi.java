package com.kerem.entity;

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
@Table(name = "tblkisi")
public class Kisi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ad;
    String soyad;
    @Column(unique = true)
    String email;
    String telefon;
    String adres;
    String dogumTarihi;
    String cinsiyet;
    String ehliyetYili;

    @OneToMany(mappedBy = "kisi", fetch = FetchType.EAGER)
    List<Kiralama> kiralamaList;

    @Override
    public String toString() {
        return " ===========================================" + '\n' +
                "ID: " + id + '\n' +
                "Ad: " + ad + '\n' +
                "Soyad: " + soyad + '\n' +
                "Email: " + email + '\n' +
                "Telefon: " + telefon + '\n' +
                "Adres: " + adres + '\n' +
                " ===========================================" ;
    }
}
