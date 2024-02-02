package com.ExpenseApp.webApp.repository;

// src/main/java/com/ExpenseApp/webApp/repository/GroupRepository.java

import com.ExpenseApp.webApp.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}

