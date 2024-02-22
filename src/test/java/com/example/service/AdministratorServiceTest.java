package com.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

@SpringBootTest
@Transactional
public class AdministratorServiceTest {

    private AdministratorRepository repository;
    private AdministratorService service;

    @Autowired
    public AdministratorServiceTest(AdministratorRepository repository) {
        this.repository = repository;
        // (Sec)PasswordEncoderはnullでOK?
        this.service = new AdministratorService(repository);
    }

    //管理者登録テスト
   @Test
    public void testInsert() {
        Administrator admin = new Administrator();
        admin.setName("テスト");
        admin.setMailAddress("abc@mail");
        admin.setPassword("testtest");
        service.insert(admin);

        Administrator insertAdmin = repository.findByMailAddress("abc@mail");
        assertEquals("テスト", insertAdmin.getName(), "管理者登録テスト失敗");
        
    }

    //ログインテスト
    // @Test
    // public void testLogin() {
    //     String mail = "iga@sample.com";
    //     String pass = "testtest";

    //     Administrator admin = service.login(mail, pass);
    //     assertNotNull(admin);
    //     assertEquals("伊賀将之", admin.getName(), "ログインテスト失敗");
    // }
}
