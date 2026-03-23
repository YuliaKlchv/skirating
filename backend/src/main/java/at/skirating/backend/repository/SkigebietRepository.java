package at.skirating.backend.repository;

import at.skirating.backend.model.Skigebiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkigebietRepository extends JpaRepository<Skigebiet, Long> {
}