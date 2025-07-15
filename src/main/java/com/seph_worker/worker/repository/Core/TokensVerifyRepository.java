package com.seph_worker.worker.repository.Core;


import com.seph_worker.worker.core.entity.CoreTokensVerify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface TokensVerifyRepository extends JpaRepository<CoreTokensVerify, Integer> {
    @Query(value = """
    SELECT
        tv.token,
        tv.ts_created
    FROM core_tokens_verify tv
    WHERE tv.deleted = false
      AND tv.user_id = :userId
    ORDER BY tv.ts_created DESC
    LIMIT 1
""", nativeQuery = true)
    Map<String,Object> getTokenCurrent(Integer userId);
}
