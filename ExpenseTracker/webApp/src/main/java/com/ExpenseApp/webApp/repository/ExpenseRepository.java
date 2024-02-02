package com.ExpenseApp.webApp.repository;

//ExpenseRepository.java

import com.ExpenseApp.webApp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}

