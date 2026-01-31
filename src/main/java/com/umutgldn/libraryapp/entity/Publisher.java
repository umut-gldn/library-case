package com.umutgldn.libraryapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publisher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherId;

    @Column(nullable = false)
    private String publisherName;

    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Book> books=new ArrayList<>();
}
