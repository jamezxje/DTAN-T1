package com.capstone.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "color")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Color {

    @Id
    @Column(name = "color_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer colorId;

    @Column(name = "color_name", unique = true, nullable = false)
    private String colorName;
}
