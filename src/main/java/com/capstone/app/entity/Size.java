package com.capstone.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "size")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Size {

    @Id
    @Column(name = "size_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sizeId;

    @Column(name = "size_name", unique = true, nullable = false)
    private String sizeName;

}
