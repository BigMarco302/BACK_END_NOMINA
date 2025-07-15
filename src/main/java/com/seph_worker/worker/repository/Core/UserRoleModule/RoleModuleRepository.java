package com.seph_worker.worker.repository.Core.UserRoleModule;


import com.seph_worker.worker.core.entity.RoleModuleUser.CoreRoleModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleModuleRepository extends JpaRepository<CoreRoleModule, Integer> {


    @Query(value = """

    SELECT
        ur.id,
        ur.module_id
    FROM core_roles_modules ur 
    INNER JOIN core_modules m ON ur.module_id = m.id
    WHERE ur.role_id=:roleId
    AND ur.deleted = false
    AND m.deleted = false
""", nativeQuery = true)
    List<Map<String,Integer>> getModulesByRole(Integer roleId);

    @Query(value = """

    SELECT
        ur.id,
        ur.role_id
    FROM core_roles_modules ur 
    INNER JOIN core_roles r ON ur.role_id = r.id
    WHERE ur.module_id=:moduleId
    AND ur.deleted = false
    AND r.deleted = false
""", nativeQuery = true)
    List<Map<String,Integer>> getRolesByModule(Integer moduleId);
}
