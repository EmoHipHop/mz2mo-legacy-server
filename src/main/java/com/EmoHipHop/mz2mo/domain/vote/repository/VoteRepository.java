package com.EmoHipHop.mz2mo.domain.vote.repository;

import com.EmoHipHop.mz2mo.domain.vote.data.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, String> {
}
