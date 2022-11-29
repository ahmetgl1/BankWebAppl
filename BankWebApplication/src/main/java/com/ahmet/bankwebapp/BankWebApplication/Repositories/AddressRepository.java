package com.ahmet.bankwebapp.BankWebApplication.Repositories;

import com.ahmet.bankwebapp.BankWebApplication.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address , String> {
}
