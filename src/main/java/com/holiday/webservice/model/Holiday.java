package com.holiday.webservice.model;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "holiday")
@Setter
@Getter
@Builder
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}