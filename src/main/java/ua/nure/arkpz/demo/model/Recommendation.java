package ua.nure.arkpz.demo.model;

import lombok.*;
import net.sf.oval.constraint.MatchPattern;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "recommendation")
@Entity
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "recommendation_id")
    private Long recommendationId;

    @Column(name = "text_english", length = 1024)
    @MatchPattern(pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Incorrect symbols in text")
    private String textEnglish;

    @Column(name = "text_ukrainian", length = 1024)
    @MatchPattern(pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Incorrect symbols in text")
    private String textUkrainian;

    @Column(name = "minimal_density")
    private Double minimalDensity;

    @Column(name = "maximal_density")
    private Double maximalDensity;
}
