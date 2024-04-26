package com.kerem.repository;

import com.kerem.entity.Kisi;



public class KisiRepository extends RepositoryManager<Kisi, Long>{
    public KisiRepository() {
        super(Kisi.class);
    }

    public void listAllCustomers() {
        findAll().forEach(System.out::println);
    }
}
