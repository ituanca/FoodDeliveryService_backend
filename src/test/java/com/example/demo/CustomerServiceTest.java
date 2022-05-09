package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.model.dto.CustomerDTO;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.utils.CustomerMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    Dummy dummy = new Dummy();

    @Test
    public void findAllTest(){

        List<Customer> expectedList =
                new ArrayList<>(List.of(dummy.getC1(), dummy.getC2(), dummy.getC3()));

        when(customerRepository.findAll()).thenReturn(expectedList);
        List<Customer> list = customerService.findAll();

        assertEquals(3, list.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void findByIdTest(){
        when(customerRepository.findById(492)).thenReturn(Optional.of(dummy.getExpectedCustomer()));
        Customer customer = customerService.findById(492).orElse(null);
        if(customer != null){
            assertEquals(dummy.getExpectedCustomer().getName(), customer.getName());
            assertEquals(dummy.getExpectedCustomer().getEmail(), customer.getEmail());
            assertEquals(dummy.getExpectedCustomer().getUsername(), customer.getUsername());
            assertEquals(dummy.getExpectedCustomer().getPassword(), customer.getPassword());
        }
    }

    @Test
    public void findByUsernameTest(){
        when(customerRepository.findByUsername("ancaitu12")).thenReturn(Optional.of(dummy.getExpectedCustomer()));
        Customer customer = customerService.findByUsername("ancaitu12").orElse(null);
        if(customer != null){
            assertEquals(dummy.getExpectedCustomer().getName(), customer.getName());
            assertEquals(dummy.getExpectedCustomer().getEmail(), customer.getEmail());
            assertEquals(dummy.getExpectedCustomer().getUsername(), customer.getUsername());
            assertEquals(dummy.getExpectedCustomer().getPassword(), customer.getPassword());
        }
    }

    @Test
    public void createAndValidateTestExistentEmail(){
        CustomerMapper customerMapper = new CustomerMapper();
        Customer expectedCustomer = customerMapper.convertDTOtoCustomer(dummy.getExistentCustomerDTO());
        when(customerRepository.findByEmail(dummy.getExistentCustomerDTO().getEmail())).thenReturn(Optional.of(expectedCustomer)); // when findByEmail will be called(in createAndValidate) it will return expectedCustomer
        String returnedString = customerService.createAndValidate(dummy.getExistentCustomerDTO());
        assertEquals("email_exists", returnedString);
    }

    @Test
    public void createAndValidateTestExistentUsername(){
        CustomerMapper customerMapper = new CustomerMapper();
        Customer expectedCustomer = customerMapper.convertDTOtoCustomer(dummy.getExistentCustomerDTO());
        when(customerRepository.findByUsername(dummy.getExistentCustomerDTO().getUsername())).thenReturn(Optional.of(expectedCustomer));
        String returnedString = customerService.createAndValidate(dummy.getExistentCustomerDTO());
        assertEquals("username_exists", returnedString);
    }

    @Test
    public void createAndValidateTestInvalidEmail(){
        String returnedString = customerService.createAndValidate(dummy.getInvalidEmailNewCustomerDTO());
        assertEquals("invalid_email", returnedString);
    }

    @Test
    public void createAndValidateTestSuccess(){
        when(customerRepository.findByEmail(dummy.getNewCustomerDTO().getEmail())).thenReturn(Optional.empty());
        when(customerRepository.findByUsername(dummy.getNewCustomerDTO().getUsername())).thenReturn(Optional.empty());
        String returnedString = customerService.createAndValidate(dummy.getNewCustomerDTO());
        assertEquals("ok", returnedString);
    }

    @Test
    public void loginTestNonExistentUsername(){
        when(customerRepository.findByUsername(
                dummy.getNonExistentUsernameCustomer().getUsername()))
                .thenReturn(Optional.empty());
        String returnedString = customerService.login(
                dummy.getNonExistentUsernameCustomer().getUsername(),
                dummy.getNonExistentUsernameCustomer().getPassword());
        assertEquals("username_error", returnedString);
    }

    @Test
    public void loginTestIncorrectPassword(){
        Customer expectedCustomerFoundByUsername = dummy.getExpectedCustomer(); // by searching by username, the expected customer data will contain the correct data (the data from the database)
        when(customerRepository.findByUsername(
                dummy.getIncorrectPasswordExistentCustomer().getUsername()))
                .thenReturn(Optional.of(expectedCustomerFoundByUsername));
        String returnedString = customerService.login(
                dummy.getIncorrectPasswordExistentCustomer().getUsername(),
                dummy.getIncorrectPasswordExistentCustomer().getPassword());
        assertEquals("password_error", returnedString);
    }

    @Test
    public void loginTestSuccess(){
        CustomerMapper customerMapper = new CustomerMapper();
        Customer expectedCustomer = customerMapper.convertDTOtoCustomer(dummy.getExistentCustomerDTO()); // by searching by username, the expected customer data will contain the correct data (the data from the database)

        when(customerRepository.findByUsername(dummy.getExistentCustomerDTO().getUsername()))
                .thenReturn(Optional.of(expectedCustomer));
        String returnedString = customerService.login(
                dummy.getExistentCustomerDTO().getUsername(),
                dummy.getExistentCustomerDTO().getPassword());
        assertEquals("ok", returnedString);
    }

}
