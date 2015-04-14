package de.swm.example.persistence.repository;

import de.swm.example.persistence.domain.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
public interface TimestampRepository extends JpaRepository<Timestamp, Long> {
}
