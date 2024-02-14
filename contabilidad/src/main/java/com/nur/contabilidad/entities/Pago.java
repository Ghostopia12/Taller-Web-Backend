package com.nur.contabilidad.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nur.contabilidad.enums.ETipoPago;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Entity
@Table(name = "pago")
@EntityListeners(AuditingEntityListener.class)
public class Pago {

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

    @JsonProperty("deuda_id")
    private Integer deuda_id;

    @JsonProperty("usuario_id")
    private Integer usuario_id;

    @JsonProperty("contable_id")
    private Integer contable_id;

    @JsonProperty("fecha")
    private Date fecha;

    @JsonProperty("monto")
    private Long monto;

    @JsonProperty("tipo")
    @Enumerated(EnumType.STRING)
    private ETipoPago tipo;
}