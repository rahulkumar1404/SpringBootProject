package com.example.demo.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.annotation.MergedAnnotation.Adapt;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AccountDto;
import com.example.demo.entity.Account;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account = AccountMapper.mapToAccount(accountDto);
		Account saveAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
//		return null;
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists") );
	
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists") );
		double total = account.getBalance()+amount;
		account.setBalance(total);
		Account saveAccount =  accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
		
		
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {

		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists") );
		double total = account.getBalance()-amount;
		account.setBalance(total);
		Account saveAccount =  accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(Long id) {

		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists") );
		
		accountRepository.deleteById(id);
		
		
	}
	
}
