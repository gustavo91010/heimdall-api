package com.ajudaqui.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "application")
@Entity
public class Application extends PanacheEntityBase {

  @Id
  @SequenceGenerator(name = "application_seq", sequenceName = "APPLICATION_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_seq")
  public Long id;

  @Column(nullable = false, length = 100)
  public String name;

  @Column(nullable = false, length = 100, unique = true)
  public String apiKey;

  @Column(nullable = false, length = 200)
  public String url;

  public boolean active;

  @CreationTimestamp
  @Column(name = "CREATEDAT")
  public LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "UPDATEDAT")
  public LocalDateTime updatedAt;
}
