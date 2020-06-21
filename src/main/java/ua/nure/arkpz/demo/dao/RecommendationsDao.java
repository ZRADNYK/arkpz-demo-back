package ua.nure.arkpz.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.arkpz.demo.model.Recommendation;

@Repository
public interface RecommendationsDao extends JpaRepository<Recommendation, Long> {
    Recommendation findByMaximalDensityIsLessThanEqualAndMinimalDensityIsGreaterThanEqual(double density, double sameDensity);
    Recommendation findByMinimalDensityGreaterThanEqual(double density);
}
