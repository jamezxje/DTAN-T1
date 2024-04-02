package com.capstone.app.repository;

import com.capstone.app.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, Integer> {
    Member findByUserName(String userName);
}
