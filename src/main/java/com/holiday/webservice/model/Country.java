package com.holiday.webservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Country {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String name;
//    private String code;
//
//    @OneToMany(mappedBy = "country")
//    private Set<Holiday> holidays;
//
//}
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "country")
@Builder
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;


}
