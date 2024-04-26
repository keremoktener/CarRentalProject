package com.kerem.Service;

import com.kerem.entity.Arac;
import com.kerem.entity.Kiralama;
import com.kerem.entity.Kisi;
import com.kerem.enums.AracDurum;
import com.kerem.enums.KiralamaDurum;
import com.kerem.repository.AracRepository;
import com.kerem.repository.KiralamaRepository;
import com.kerem.repository.KisiRepository;
import com.kerem.utility.GetValue;


import java.util.List;
import java.util.Optional;

public class AracService {
    private KiralamaRepository kiralamaRepository = new KiralamaRepository();
    private AracRepository aracRepository;
    private KisiRepository kisiRepository = new KisiRepository();

    public AracService() {
        this.aracRepository = new AracRepository();
    }

    public void listAllVehicles() {
        aracRepository.findAll().forEach(System.out::println);
    }

    //aracRepository'den çağırılan getAvailableVehicles metodu namedQuery ile müsait olan araçların listesini getirir
    public void listAvailableVehicles() {
        aracRepository.getAvailableVehicles().forEach(System.out::println);
    }

    //aracRepository'den çağırılan getRentedVehicles metodu JPQL ile kirada olan araçların listesini getirir
    public void listRentedVehicles() {
        List<Arac> rentedVehicles = aracRepository.getRentedVehicles();
        if (rentedVehicles.isEmpty()) {
            System.out.println("Kirada araç bulunmamaktadır");
        } else {
            rentedVehicles.forEach(System.out::println);
        }
    }

    public void listRentedVehiclesByCustomer() {
        while (true) {
            kisiRepository.listAllCustomers();
            Long kisiId = GetValue.getLongVal("Kiralama bilgilerini görmek istediğiniz müşterinin id'sini giriniz: ");
            Optional<Kisi> kisi = kisiRepository.findById(kisiId);
            if (kisi.isPresent()) {
                List<Kiralama> rentedVehiclesByCustomer = aracRepository.getRentedVehiclesByCustomer(kisi.get());
                if (rentedVehiclesByCustomer.isEmpty()) {
                    System.out.println("MÜŞTERİNİN KİRALADIĞI ARAÇ BULUNMAMAKTADIR");
                } else {
                    rentedVehiclesByCustomer.forEach(System.out::println);
                }
                break;
            } else {
                System.out.println("Müşteri bulunamadı");
                System.out.println("Lütfen tekrar deneyiniz");
            }
        }
    }

    public List<Kiralama> listMyRentals(Kisi kisi) {
        return aracRepository.getRentedVehiclesByCustomer(kisi);
    }


    public void rentVehicle() {
        while (true) {
            kisiRepository.listAllCustomers();
            Long kisiId = GetValue.getLongVal("Kiralamak istediğiniz müşterinin id'sini giriniz: ");
            Optional<Kisi> kisi = kisiRepository.findById(kisiId);
            if (kisi.isPresent()) {
                while (true) {
                    aracRepository.getAvailableVehicles().forEach(System.out::println);
                    Long aracId = GetValue.getLongVal("Kiralanacak olan aracın id'sini giriniz: ");
                    Optional<Arac> arac = aracRepository.findById(aracId);
                    if (arac.isPresent()) {
                        Kiralama kiralama = kiralamaRepository.createRental(kisi.get(), aracId);
                        kisi.get().getKiralamaList().add(kiralama);
                        aracRepository.changeVehicleStatus(aracId, AracDurum.KIRADA);
                        System.out.println("Araç kiralandı!");
                        System.out.println("Devam etmek için ENTER tuşuna basınız");
                        GetValue.getEmptyLine();
                        break;
                    } else {
                        System.out.println("Arac bulunamadı");
                        System.out.println("Lütfen tekrar deneyiniz");
                    }
                }
                break;
            } else {
                System.out.println("Müşteri bulunamadı");
                System.out.println("Lütfen tekrar deneyiniz");
            }
        }
    }


    public void returnVehicle() {
        while (true) {
            kisiRepository.listAllCustomers();
            Long kisiId = GetValue.getLongVal("İade yapacak olan müşterinin id'sini giriniz: ");
            Optional<Kisi> kisi = kisiRepository.findById(kisiId);
            if (kisi.isPresent()) {
                while (true) {
                    List<Kiralama> kiralamaList = listMyRentals(kisi.get());
                    if (!kiralamaList.isEmpty()) {
                        kiralamaList.forEach(System.out::println);
                        Long kiralamaId = GetValue.getLongVal("Kiralama id'yi giriniz: ");
                        Optional<Kiralama> kiralama = kiralamaRepository.findById(kiralamaId);
                        if (kiralama.isPresent()) {
                            kiralamaRepository.changeRentalStatus(kiralamaId, KiralamaDurum.SONLANDI);
                            aracRepository.changeVehicleStatus(kiralama.get().getArac().getId(), AracDurum.MUSAIT);
                            kisi.get().getKiralamaList().remove(kiralama.get());
                            System.out.println("Araç iade edildi!");
                            System.out.println("Devam etmek için ENTER tuşuna basınız");
                            GetValue.getEmptyLine();
                            break;
                        } else {
                            System.out.println("Kiralama bulunamadı");
                            System.out.println("Lütfen tekrar deneyiniz");
                        }
                    } else {
                        System.out.println("MÜŞTERİNİN KİRALADIĞI ARAÇ BULUNMAMAKTADIR");
                        break;
                    }
                }
                break;
            } else {
                System.out.println("Müşteri bulunamadı");
                System.out.println("Lütfen tekrar deneyiniz");
            }
        }
    }

    public void searchVehicle() {
        String marka = GetValue.getStringVal("Aramak istediğiniz aracın markasını giriniz: ").toUpperCase();
        List<Arac> vehicleByBrand = aracRepository.findByColumnAndValue("marka", marka);
        if (vehicleByBrand.isEmpty()) {
            System.out.println("ARAÇ BULUNAMADI");
        } else {
            vehicleByBrand.forEach(System.out::println);
        }
    }

    public void addVehicle(){
        String marka = GetValue.getStringVal("Marka Giriniz: ").toUpperCase();
        String model = GetValue.getStringVal("Model Giriniz: ").toUpperCase();
        String yil = GetValue.getStringVal("Yılı Giriniz: ");
        String yakit = GetValue.getStringVal("Yakıt Tipini Giriniz (Dizel - D/Benzin - B): ");
        if (yakit.equalsIgnoreCase("d")){
            yakit = ("Dizel");
        } else {
            yakit = ("Benzin");
        }
        String vites = GetValue.getStringVal("Vites Türünü Seçiniz (Otomatik - O/Manuel - M): ");
        if (vites.equalsIgnoreCase("o")){
            vites = "Otomatik";
        } else {
            vites = "Manuel";
        }
        String km = GetValue.getStringVal("KM'yi Giriniz: ");
        String renk = GetValue.getStringVal("Aracın Rengini Giriniz: ");
        Double gunlukFiyat = GetValue.getDoubleVal("Günlük Fiyatı Giriniz: ");
        String plaka = GetValue.getStringVal("Aracın Plakasını Giriniz (Hepsi birleşik olacak şekilde): ").toUpperCase();
        AracDurum aracDurum = AracDurum.MUSAIT;
        String resim = marka+model+".jpg";
        String aciklama = marka+" "+model+" "+yil+" Model";
        String sehir = GetValue.getStringVal("Aracın Bulunduğu Şehri Giriniz: ");

        Arac arac = Arac.builder()
                .marka(marka)
                .model(model)
                .yil(yil)
                .yakit(yakit)
                .vites(vites)
                .km(km)
                .renk(renk)
                .gunlukFiyat(gunlukFiyat)
                .plaka(plaka)
                .durum(aracDurum)
                .resim(resim)
                .aciklama(aciklama)
                .sehir(sehir)
                .build();

        aracRepository.save(arac);

        System.out.println("Araç Başarıyla Eklendi!");
        System.out.println("Devam Etmek İçin ENTER'a Basınız");
        GetValue.getEmptyLine();
    }


}
