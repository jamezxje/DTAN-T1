package com.capstone.app.entity;

import com.capstone.app.entity.type.MemberRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer memberId;

//    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
//    private Set<WishItem> wishlist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feature_image_id")
    private Image featureImage;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Transaction> transactions; // only for Customer and Owner

    @Column(name = "national_id", unique = true, length = 20)
    private String nationalId;

    @Column(name = "full_name", nullable = false, length = 20)
    @NotBlank
    private String fullName;

    @Column(name = "role", nullable = false)
    private MemberRole role;

    @Column(name = "wallet_balance", nullable = false, columnDefinition = "double default 0")
    private Double walletBalance;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "phone_number", unique = true, length = 15)
    @Size(min = 10, max = 15)
    private String phoneNumber;

    @Column(name = "address", length = 100)
    @Size(max = 100)
    private String address;

    @Column(name = "user_name", nullable = false, unique = true, length = 20)
    @Size(min = 5, max = 20)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_verified", columnDefinition = "boolean default false")
    private Boolean isVerified;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime lastModifiedDate;
}
