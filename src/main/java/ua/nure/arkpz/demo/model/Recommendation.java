package ua.nure.arkpz.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.MatchPattern;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "recommendation")
@Entity
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "recommendation_id")
    private Long recommendationId;

    @Column(name = "text", length = 1024)
    @MatchPattern(pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Incorrect symbols in text")
    private String text;

    @Column(name = "type")
    private String type;

    @Column(name = "minimal_density")
    private Double minimalDensity;

    @Column(name = "maximal_density")
    private Double maximalDensity;
}
