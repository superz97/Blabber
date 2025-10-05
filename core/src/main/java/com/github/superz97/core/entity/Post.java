package com.github.superz97.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 120, message = "Maximum text size is 120 symbols")
    private String text;

    @Pattern(regexp = "^#.*", message = "Tag name should start from '#'")
    private String tag;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    public Post(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }

}
