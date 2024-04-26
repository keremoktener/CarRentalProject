package com.kerem;

import com.kerem.controller.Menu;
import com.kerem.entity.Arac;
import com.kerem.enums.AracDurum;
import com.kerem.repository.AracRepository;
import com.kerem.repository.KiralamaRepository;
import com.kerem.repository.KisiRepository;

public class Runner {
    public static void main(String[] args) {
        KisiRepository kisiRepository = new KisiRepository();
        AracRepository aracRepository = new AracRepository();
        KiralamaRepository kiralamaRepository = new KiralamaRepository();

        //Örnek araçlar
        Arac arac = Arac.builder()
                .marka("AUDI")
                .model("A3")
                .yil("2015")
                .yakit("Dizel")
                .vites("Otomatik")
                .km("100.000")
                .renk("Siyah")
                .gunlukFiyat(250.0D)
                .plaka("34ABC123")
                .durum(AracDurum.MUSAIT)
                .resim("audi.jpg")
                .aciklama("Audi A3 2015 model")
                .sehir("İstanbul")
                .build();

        Arac arac1 = Arac.builder()
                .marka("BMW")
                .model("320i")
                .yil("2016")
                .yakit("Benzin")
                .vites("Otomatik")
                .km("120.000")
                .renk("Beyaz")
                .gunlukFiyat(300.0D)
                .plaka("34DEF456")
                .durum(AracDurum.MUSAIT)
                .resim("bmw.jpg")
                .aciklama("BMW 320i 2016 model")
                .sehir("İstanbul")
                .build();

        Arac arac2 = Arac.builder()
                .marka("MERCEDES")
                .model("C180")
                .yil("2017")
                .yakit("Benzin")
                .vites("Otomatik")
                .km("80.000")
                .renk("Gri")
                .gunlukFiyat(350.0D)
                .plaka("34GHI789")
                .durum(AracDurum.MUSAIT)
                .resim("mercedes.jpg")
                .aciklama("Mercedes C180 2017 model")
                .sehir("İstanbul")
                .build();

        Arac arac3 = Arac.builder()
                .marka("FIAT")
                .model("EGEA")
                .yil("2018")
                .yakit("Dizel")
                .vites("Manuel")
                .km("60.000")
                .renk("Beyaz")
                .gunlukFiyat(200.0D)
                .plaka("34JKL012")
                .durum(AracDurum.MUSAIT)
                .resim("fiat.jpg")
                .aciklama("Fiat Egea 2018 model")
                .sehir("İstanbul")
                .build();

        Arac arac4 = Arac.builder()
                .marka("RENAULT")
                .model("CLIO")
                .yil("2019")
                .yakit("Benzin")
                .vites("Manuel")
                .km("40.000")
                .renk("Siyah")
                .gunlukFiyat(150.0D)
                .plaka("34MNO345")
                .durum(AracDurum.MUSAIT)
                .resim("renault.jpg")
                .aciklama("Renault Clio 2019 model")
                .sehir("İstanbul")
                .build();

        Arac arac5 = Arac.builder()
                .marka("TOYOTA")
                .model("COROLLA")
                .yil("2020")
                .yakit("Hibrit")
                .vites("Otomatik")
                .km("20.000")
                .renk("Gri")
                .gunlukFiyat(400.0D)
                .plaka("34PQR678")
                .durum(AracDurum.MUSAIT)
                .resim("toyota.jpg")
                .aciklama("Toyota Corolla 2020 model")
                .sehir("İstanbul")
                .build();

        Arac arac6 = Arac.builder()
                .marka("HYUNDAI")
                .model("I20")
                .yil("2021")
                .yakit("Benzin")
                .vites("Manuel")
                .km("10.000")
                .renk("Beyaz")
                .gunlukFiyat(175.0D)
                .plaka("34STU901")
                .durum(AracDurum.MUSAIT)
                .resim("hyundai.jpg")
                .aciklama("Hyundai i20 2021 model")
                .sehir("İstanbul")
                .build();

        Arac arac7 = Arac.builder()
                .marka("PEUGEOT")
                .model("208")
                .yil("2022")
                .yakit("Dizel")
                .vites("Otomatik")
                .km("5.000")
                .renk("Siyah")
                .gunlukFiyat(225.0D)
                .plaka("34VWX123")
                .durum(AracDurum.MUSAIT)
                .resim("peugeot.jpg")
                .aciklama("Peugeot 208 2022 model")
                .sehir("İstanbul")
                .build();

        Arac arac8 = Arac.builder()
                .marka("VOLKSWAGEN")
                .model("GOLF")
                .yil("2023")
                .yakit("Benzin")
                .vites("Otomatik")
                .km("2.500")
                .renk("Beyaz")
                .gunlukFiyat(300.0D)
                .plaka("34YZA456")
                .durum(AracDurum.MUSAIT)
                .resim("volkswagen.jpg")
                .aciklama("Volkswagen Golf 2023 model")
                .sehir("İstanbul")
                .build();

//       aracRepository.save(arac);
//       aracRepository.save(arac1);
//       aracRepository.save(arac2);
//       aracRepository.save(arac3);
//       aracRepository.save(arac4);
//       aracRepository.save(arac5);
//       aracRepository.save(arac6);
//       aracRepository.save(arac7);
//       aracRepository.save(arac8);


        Menu menu = new Menu();
        menu.mainMenu();

    }
}