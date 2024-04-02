package com.capstone.app.repository;

import com.capstone.app.entity.Member;
import com.capstone.app.entity.type.MemberRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepo extends JpaRepository<Member, Integer> {


    @Query("FROM Member m WHERE m.role IN :roles")
    Page<Member> getAll(@Param("roles") List<MemberRole> roles, Pageable pageable);

    Page<Member> getAllByRole(String role, Pageable pageable);


}
