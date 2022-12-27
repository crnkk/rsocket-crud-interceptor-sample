package com.example.demo.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("posts")

public class Person {


        @Id
        @Column("id")
        private Integer id;

        @Column("name")
        private String name;

        @Column("number")
        private String number;

    }



