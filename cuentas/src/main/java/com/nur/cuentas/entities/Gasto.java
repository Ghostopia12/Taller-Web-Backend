package com.nur.cuentas.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Entity
@Table(name = "gastos")
@EntityListeners(AuditingEntityListener.class)
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

//    @CreatedBy
//    @Column(updatable = false)
//    private Long createdBy;
//
//    @LastModifiedBy
//    private Long updatedBy;

    @JsonProperty("condominio_id")
    private Integer condominioId;

    @JsonProperty("fecha")
    private Date fecha;

    @JsonProperty("monto")
    private Long monto;

    @JsonProperty("concepto")
    private String concepto;

    @JsonProperty("cancelada")
    private Boolean cancelada;
}