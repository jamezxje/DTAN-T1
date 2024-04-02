package com.capstone.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class SystemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer optionId;

    @NotBlank
    @NotNull
    @Column(name = "option_alias_name", unique = true, length = 100)
    @Length(max = 100)
    private String optionAliasName;

    @NotBlank
    @NotNull
    @Length(max = 100)
    @Column(name = "option_key", unique = true, length = 100)
    private String optionkey;

    @NotBlank
    @NotNull
    @Length(max = 1000)
    @Column(name = "option_value", length = 1000)
    private String optionValue;

    public SystemOption(String optionAliasName, String optionkey, String optionValue) {
        this.optionAliasName = optionAliasName;
        this.optionkey = optionkey;
        this.optionValue = optionValue;
    }

}
