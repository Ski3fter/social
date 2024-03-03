package com.example.social.repository;

import com.example.social.domain.model.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {

    @Query("select d from Dialog d where d.from = :from and d.to = :to")
     List<Dialog> getDialogs(@Param("from") Long from, @Param("to") Long to);

}
