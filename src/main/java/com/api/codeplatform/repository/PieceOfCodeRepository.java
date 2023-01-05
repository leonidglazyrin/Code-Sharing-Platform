package com.api.codeplatform.repository;

import com.api.codeplatform.model.PieceOfCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PieceOfCodeRepository extends JpaRepository<PieceOfCode, UUID> {
    @Query(value="SELECT * FROM snippets s WHERE s.views = 0 AND s.time = 0 ORDER BY s" +
            ".load_date DESC", nativeQuery = true)
    List<PieceOfCode> findTop10ByViewsAndTimeOrderByLoadDate(Pageable pageable);
}
