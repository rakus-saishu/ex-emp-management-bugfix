package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@SpringBootTest
@Transactional
public class EmployeeServiceTest {

    private EmployeeRepository repository;
    private EmployeeService service;

    @Autowired
    public EmployeeServiceTest(EmployeeRepository repository) {
        this.repository = repository;
        this.service = new EmployeeService(repository);
    }



    //従業員一覧表示テスト
    @Test
    void testShowList() {
        List<Employee> employeeList = service.showList();
        assertNotNull(employeeList);
        Integer Size = repository.findAll().size();
        assertEquals(Size, employeeList.size(), "従業員一覧表示テスト失敗");
    }

    //従業員詳細画面表示テスト
    @Test
    void testShowDetail() {
        Employee employee = service.showDetail(1);
        assertNotNull(employee);
        assertEquals("山田太郎", employee.getName(), "従業員詳細画面表示テスト失敗");
    }

}
