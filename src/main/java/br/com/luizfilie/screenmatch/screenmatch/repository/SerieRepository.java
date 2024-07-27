package br.com.luizfilie.screenmatch.screenmatch.repository;

import br.com.luizfilie.screenmatch.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
}
