package com.seph_worker.worker.repository.Core.UserRoleModule;


import com.seph_worker.worker.core.entity.RoleModuleUser.CoreUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRoleRepository extends JpaRepository<CoreUserRole, Integer> {


    @Query(value = """

    SELECT
        ur.id,
        ur.role_id
    FROM core_users_roles ur 
    INNER JOIN core_roles r ON ur.role_id = r.id
    WHERE ur.user_id=:userId
    AND ur.deleted = false
    AND r.deleted = false
""", nativeQuery = true)
    List<Map<String,Integer>> getRolesByUser(Integer userId);
}
