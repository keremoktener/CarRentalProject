package com.kerem.controller;

import com.kerem.Service.AracService;
import com.kerem.Service.KisiService;
import com.kerem.entity.Kisi;
import com.kerem.utility.GetValue;

public class Menu {
    private KisiService kisiService = new KisiService();
    private AracService aracService = new AracService();


    public void mainMenu() {
        while (true) {
            System.out.println("=======================================");
            System.out.println("              HOŞGELDİNİZ              ");
            System.out.println("=======================================");
            System.out.println("1- Araç Kiralama/İade İşlemleri");
            System.out.println("2- Araç Arama");
            System.out.println("3- Araç EKleme");
            System.out.println("4- Kişi Ekleme");
            System.out.println("5- Araç Rapor İşlemleri");
            System.out.println("0- Çıkış");
            int secim = GetValue.getIntVal("Lütfen seçim yapın (0-5): ");

            switch (secim) {
                case 1:
                    aracKiralamaMenu();
                    break;
                case 2:
                    aracService.searchVehicle();
                    break;
                case 3:
                    aracService.addVehicle();
                    break;
                case 4:
                    kisiService.register();
                    break;
                case 5:
                    aracRaporlari();
                    break;

                case 0:
                    System.exit(0);
            }
        }
    }

    public void aracKiralamaMenu() {
        while (true) {
            System.out.println("1- Araç Kirala");
            System.out.println("2- Araç İade Et");
            System.out.println("3- Önceki Menü");
            System.out.println("0- Çıkış");
            int secim = GetValue.getIntVal("Lütfen seçim yapın (0-3): ");

            switch (secim) {
                case 1:
                    aracService.rentVehicle();
                    break;
                case 2:
                    aracService.returnVehicle();
                    break;
                case 3:
                    mainMenu();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    public void aracRaporlari() {
        while (true) {
            System.out.println("1- Bütün Araçlar");
            System.out.println("2- Müsait Araçlar");
            System.out.println("3- Kirada Olan Araçlar");
            System.out.println("4- Herhangi Bir Müşterinin Kiraladığı Araçlar");
            System.out.println("5- Önceki Menü");
            System.out.println("0- Çıkış");
            int secim = GetValue.getIntVal("Lütfen seçim yapın (0-5): ");

            switch (secim) {
                case 1:
                    aracService.listAllVehicles();
                    break;
                case 2:
                    aracService.listAvailableVehicles();
                    break;
                case 3:
                    aracService.listRentedVehicles();
                    break;
                case 4:
                    aracService.listRentedVehiclesByCustomer();
                    break;
                case 5:
                    mainMenu();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }


}

