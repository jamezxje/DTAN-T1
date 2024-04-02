package com.capstone.app.repository;

import com.capstone.app.entity.SystemOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemOptionRepo extends JpaRepository<SystemOption, Integer> {
    SystemOption findByOptionkey(String optionKey);

    @Query(value = "FROM SystemOption s WHERE s.optionkey NOT IN :optionKeys")
    List<SystemOption> findAllExceptForDashboardOption(@Param("optionKeys") List<String> optionKeys);
}
