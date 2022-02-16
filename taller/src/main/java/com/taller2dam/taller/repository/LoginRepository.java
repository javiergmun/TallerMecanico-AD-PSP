package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
