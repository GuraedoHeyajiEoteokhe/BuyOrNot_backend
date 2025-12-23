package com.ambiguous.buyornot.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tbl_token")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private Date expires;
}
