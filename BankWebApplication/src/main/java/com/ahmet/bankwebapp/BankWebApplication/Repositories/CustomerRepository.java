package com.ahmet.bankwebapp.BankWebApplication.Repositories;

import com.ahmet.bankwebapp.BankWebApplication.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {



}
