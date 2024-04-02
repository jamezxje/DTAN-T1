package com.capstone.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

//    @OneToMany(mappedBy = "category")
//    private List<Product> products;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_size_chart_id")
    private Image categorySizeChart;

    @Column(name = "category_name", unique = true, nullable = false, length = 100)
    private String categoryName;

    @Column(name = "category_description", columnDefinition = "TEXT")
    private String categoryDescription;

}
