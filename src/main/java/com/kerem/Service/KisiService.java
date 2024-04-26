package com.kerem.Service;

import com.kerem.entity.Kisi;
import com.kerem.repository.KisiRepository;
import com.kerem.utility.GetValue;


import java.time.LocalDate;

public class KisiService {
    private KisiRepository kisiRepository;

    public KisiService() {
        kisiRepository = new KisiRepository();
    }

    public void register() {
        String name = GetValue.getStringVal("Müşterinin Adı: ").toUpperCase();
        String surname = GetValue.getStringVal("Müşterinin Soyadı: ").toUpperCase();
        //unique email kontrolü
        String email = "";
        while (true) {
            email = GetValue.getStringVal("Müşterinin Email'i: ");
            boolean isFormat = emailFormatControl(email);
            boolean isUnique = uniqueControl("email", email);
            if (isFormat && isUnique) {
                break;
            }else {
                System.out.println("Email formatı uygun değil veya daha önce alınmış.");
            }
        }
        String phone = GetValue.getStringVal("Müşterinin Telefonu: ");
        String address = GetValue.getStringVal("Müşterinin Adresi: ");
        String birthDate = GetValue.getStringVal("Müşterinin Doğum tarihi: (gg-aa-yyyy) ");
        String cinsiyet = GetValue.getStringVal("Cinsiyet (E/K): ").toUpperCase();
        String ehliyetYili = GetValue.getStringVal("Ehliyet alma yılı (yyyy): ");

        boolean ehliyetKontrol = ehliyetYiliControl(ehliyetYili); //ehliyet süresi beş yıldan azsa kiralama yapılamaz

        if (!ehliyetKontrol){
            return;
        } else {
            Kisi kisi = Kisi.builder()
                    .ad(name)
                    .soyad(surname)
                    .email(email)
                    .telefon(phone)
                    .adres(address)
                    .dogumTarihi(birthDate)
                    .cinsiyet(cinsiyet)
                    .ehliyetYili(ehliyetYili)
                    .build();
            kisiRepository.save(kisi);
            System.out.println("Müşteri başarıyla eklendi.");
            System.out.println("Devam etmek için ENTER tuşuna basınız.");
            GetValue.getEmptyLine();
        }
    }

    private boolean emailFormatControl(String email){
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    private boolean uniqueControl(String columnName, String value) {
        boolean isEmpty = kisiRepository.findByColumnAndValue(columnName, value).isEmpty();
        if (!isEmpty) {
            System.out.println("Bu " + columnName + " daha önce alınmış.");
            return false;
        } else {
            return true;
        }
    }

    private boolean ehliyetYiliControl(String yil){
        int ehliyetYili = Integer.parseInt(yil);
        int guncelYil = LocalDate.now().getYear();

        if ((guncelYil-ehliyetYili) < 5){
            System.out.println("Ehliyet yaşı 5 yıldan az olanlar araç kiralayamaz.");
            System.out.println("Devam etmek için ENTER tuşuna basınız.");
            GetValue.getEmptyLine();
            return false;
        } else {
            return true;
        }
    }


}
