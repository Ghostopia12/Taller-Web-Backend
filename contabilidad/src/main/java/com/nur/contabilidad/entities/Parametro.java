package com.nur.contabilidad.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nur.contabilidad.enums.ETipoDeuda;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Entity
@Table(name = "parametros")
@EntityListeners(AuditingEntityListener.class)
public class Parametro {

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

    @JsonProperty("activo")
    private Boolean activo;

    @JsonProperty("monto")
    private Long monto;

    @JsonProperty("vencimiento")//dias
    private Integer vencimiento;

    @JsonProperty("tipo")
    @Enumerated(EnumType.STRING)
    private ETipoDeuda tipo;
}