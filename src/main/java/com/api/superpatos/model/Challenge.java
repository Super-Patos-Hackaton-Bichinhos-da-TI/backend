package com.api.superpatos.model;

import java.util.Date;
import java.util.List;

import com.api.superpatos.enums.Squad;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
// @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long xp_reward = 0L;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 2000)
    private String description_interface;

    @Column(length = 2000)
    private String description;
    
    @Column(length = 200)
    private String link_img;

    // @Enumerated(EnumType.STRING)
    private String difficult;
    
    @Enumerated(EnumType.STRING)
    private Squad squad;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(
        fetch = FetchType.EAGER, mappedBy = "challenge",
        cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags;

    @OneToMany(
        fetch = FetchType.EAGER, mappedBy = "challenge",
        cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<Solution> solutions;

    public Challenge(String title, String description_interface, String description, String difficult, List<Tag> tags,
            Squad squad, String link_img, Date date, Long xp_reward) {
        this.title = title;
        this.description_interface = description_interface;
        this.description = description;
        this.difficult = difficult;
        this.tags = tags;
        this.squad = squad;
        this.link_img = link_img;
        this.date = date;
        this.xp_reward = xp_reward;
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
