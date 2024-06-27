package com.api.superpatos.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.api.superpatos.enums.Squad;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Challenge")
@Table(name = "challenge")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 2000)
    private String description_interface;

    @Column(length = 2000)
    private String description;

    @Column(length = 50)
    private String difficult;

    @Column(length = 200)
    private String link_img;
    
    @Enumerated(EnumType.STRING)
    private Squad squad;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solution> solutions;


    public Challenge(String title, String description_interface, String description, String difficult, List<Tag> tags,
            Squad squad, String link_img, Date date) {
        this.title = title;
        this.description_interface = description_interface;
        this.description = description;
        this.difficult = difficult;
        this.tags = tags;
        this.squad = squad;
        this.link_img = link_img;
        this.date = date;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.setChallenge(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.setChallenge(null);
    }
}
