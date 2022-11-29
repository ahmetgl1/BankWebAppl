package com.ahmet.bankwebapp.BankWebApplication;

import com.ahmet.bankwebapp.BankWebApplication.Entities.*;
import com.ahmet.bankwebapp.BankWebApplication.Repositories.AccountRepository;
import com.ahmet.bankwebapp.BankWebApplication.Repositories.AddressRepository;
import com.ahmet.bankwebapp.BankWebApplication.Repositories.CustomerRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class BankWebApplication implements CommandLineRunner {


	private final CustomerRepository customerRepository;
	private final AccountRepository accountRepository;
	private final AddressRepository addressRepository;


	public BankWebApplication(CustomerRepository customerRepository, AccountRepository accountRepository, AddressRepository addressRepository) {
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
		this.addressRepository = addressRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BankWebApplication.class, args);
		System.out.println("Hello World !");
	}

	@Override
	public void run(String... args) throws Exception {

		Customer customer1 = Customer.builder()
				.id(Long.valueOf("51772693358"))
				.name("Ahmet Fatih")
				.surName("Güzeller")
				.city(City.Ankara)
				.dateOfBirth("1999")

				.address(Address.builder()
						.city(City.Ankara)
						.postCode("06100")
						.addressDetail("Ev Adresi")
						.build())
				.account(Account.builder()
						.customerId(Long.valueOf("51772693358"))
						.city(City.Ankara)
						.balance(100.0)
						.currency(Currency.TL)
						.build())

				.build();

		customerRepository.save(customer1);
//////////////////////////////////////////////////////
		Customer customer2 = Customer.builder()
				.id(Long.valueOf("51763693642"))
				.id(Long.valueOf("51763693642"))
				.name("Yusuf")
				.surName("Enes")
				.city(City.Ankara)
				.dateOfBirth("2001")

				.address(Address.builder()
						.city(City.İstanbul)
						.postCode("34100")
						.addressDetail("Ev Adresi")
						.build())
				.account(Account.builder()
						.customerId(Long.valueOf("51763693642"))
						.city(City.Ankara)
						.balance(1500.0)
						.currency(Currency.TL)
						.build())

				.build();

		customerRepository.save(customer2);


	}
}
