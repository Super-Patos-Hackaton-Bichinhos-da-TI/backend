package com.api.superpatos.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Column(length = 3000)
    private String description;

    @OneToMany(
        mappedBy = "challenge",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Tag> tags;
    
    public Challenge(String title, String description) {
        this.title = title;
        this.description = description;
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
