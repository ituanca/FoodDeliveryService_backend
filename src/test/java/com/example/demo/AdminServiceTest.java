package com.example.demo;

import com.example.demo.model.Admin;
import com.example.demo.model.Customer;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import com.example.demo.utils.AdminMapper;
import com.example.demo.utils.CustomerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;

    Dummy dummy = new Dummy();

    @Test
    public void findAllTest(){

        List<Admin> expectedList = new ArrayList<>(List.of(dummy.getA1(), dummy.getA2(), dummy.getA3()));

        when(adminRepository.findAll()).thenReturn(expectedList);
        List<Admin> list = adminService.findAll();

        assertEquals(3, list.size());
        verify(adminRepository, times(1)).findAll();
    }

    @Test
    public void findByIdTest(){
        when(adminRepository.findById(464)).thenReturn(Optional.of(dummy.getExpectedAdmin()));
        Admin admin = adminService.findById(464).orElse(null);
        if(admin != null){
            assertEquals(dummy.getExpectedAdmin().getUsername(), admin.getUsername());
            assertEquals(dummy.getExpectedAdmin().getPassword(), admin.getPassword());
        }
    }

    @Test
    public void findByUsernameTest(){
        when(adminRepository.findByUsername("adminLemnulVerde")).thenReturn(Optional.of(dummy.getExpectedAdmin()));
        Admin admin = adminService.findByUsername("adminLemnulVerde").orElse(null);
        if(admin != null){
            assertEquals(dummy.getExpectedAdmin().getUsername(), admin.getUsername());
            assertEquals(dummy.getExpectedAdmin().getPassword(), admin.getPassword());
        }
    }

    @Test
    public void checkIfValidTrueTest(){
        when(adminRepository.findByEmail(dummy.getNewAdmin().getEmail())).thenReturn(Optional.empty());
        when(adminRepository.findByUsername(dummy.getNewAdmin().getUsername())).thenReturn(Optional.empty());
        String returnedString =
                adminService.checkIfValid(
                    dummy.getNewAdmin().getEmail(),
                    dummy.getNewAdmin().getUsername());
        assertEquals("ok", returnedString);
    }

    @Test
    public void checkIfValidExistentUsernameTest(){
        when(adminRepository
                .findByEmail(dummy.getNewAdminExistentUsername().getEmail()))
                .thenReturn(Optional.empty());
        when(adminRepository
                .findByUsername(dummy.getNewAdminExistentUsername().getUsername()))
                .thenReturn(Optional.of(dummy.getExpectedAdmin()));
        String returnedString =
                adminService.checkIfValid(
                        dummy.getNewAdminExistentUsername().getEmail(),
                        dummy.getNewAdminExistentUsername().getUsername());
        assertEquals("username_exists", returnedString);
    }

    @Test
    public void checkIfValidExistentEmailTest(){
        when(adminRepository
                .findByEmail(dummy.getNewAdminExistentEmail().getEmail()))
                .thenReturn(Optional.of(dummy.getExpectedAdmin()));
        String returnedString =
                adminService.checkIfValid(
                        dummy.getExpectedAdmin().getEmail(),
                        dummy.getExpectedAdmin().getUsername());
        assertEquals("email_exists", returnedString);
    }

    @Test
    public void checkIfValidInvalidEmailTest(){
        String returnedString =
                adminService.checkIfValid(
                        dummy.getNewAdminInvalidEmail().getEmail(),
                        dummy.getNewAdminInvalidEmail().getUsername());
        assertEquals("invalid_email", returnedString);
    }

    @Test
    public void loginTestNonExistentUsername(){
        when(adminRepository
                .findByUsername(dummy.getNonExistentUsernameAdminDTO().getUsername()))
                .thenReturn(Optional.empty());
        String returnedString =
                adminService.login(
                        dummy.getNonExistentUsernameAdminDTO().getUsername(),
                        dummy.getNonExistentUsernameAdminDTO().getPassword());
        assertEquals("username_error", returnedString);
    }

    @Test
    public void loginTestIncorrectPassword(){
        Admin expectedAdminFoundByUsername = dummy.getExpectedAdmin();
        when(adminRepository
                .findByUsername(dummy.getIncorrectPasswordExistentAdminDTO().getUsername()))
                .thenReturn(Optional.of(expectedAdminFoundByUsername));
        String returnedString =
                adminService.login(
                        dummy.getIncorrectPasswordExistentAdminDTO().getUsername(),
                        dummy.getIncorrectPasswordExistentAdminDTO().getPassword());
        assertEquals("password_error", returnedString);
    }

    @Test
    public void loginTestSuccess(){
        AdminMapper adminMapper = new AdminMapper();
        Admin expectedAdmin = adminMapper.convertDTOtoAdmin(dummy.getExistentAdminDTO());

        when(adminRepository.findByUsername(dummy.getExistentAdminDTO().getUsername()))
                .thenReturn(Optional.of(expectedAdmin));
        String returnedString = adminService.login(
                dummy.getExistentAdminDTO().getUsername(),
                dummy.getExistentAdminDTO().getPassword());
        assertEquals("ok", returnedString);
    }

}
