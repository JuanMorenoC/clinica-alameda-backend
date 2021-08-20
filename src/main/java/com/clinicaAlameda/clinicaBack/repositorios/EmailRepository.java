package com.clinicaAlameda.clinicaBack.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicaAlameda.clinicaBack.entidades.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

}
