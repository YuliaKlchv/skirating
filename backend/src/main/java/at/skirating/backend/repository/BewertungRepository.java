package at.skirating.backend.repository;

import at.skirating.backend.model.Bewertung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BewertungRepository extends JpaRepository<Bewertung, Long> {

    List<Bewertung> findBySkigebietId(Long skigebietId);
}