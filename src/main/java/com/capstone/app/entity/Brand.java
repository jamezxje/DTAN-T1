package com.capstone.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Brand {
    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brandId;

    @OneToMany(mappedBy = "brand")
    private List<Car> cars;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_logo_id")
    private Image brandLogo;

    @Column(unique = true, nullable = false,length = 100,name = "brand_name")
    @NotBlank
    @Size(max = 100)
    private String brandName;

    @Column(name = "brand_description", columnDefinition = "TEXT")
    private String brandDescription;

    public Brand(Integer brandId, String brandName, String brandDescription, Image brandLogo) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
        this.brandLogo = brandLogo;
    }
}
