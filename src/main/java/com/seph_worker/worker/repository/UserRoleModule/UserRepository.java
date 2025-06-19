package com.seph_worker.worker.repository.UserRoleModule;

import com.seph_worker.worker.core.entity.RoleModuleUser.CoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CoreUser, Integer> {

   Optional<CoreUser> findOneByUsername(String username);

 @Query(value = """
SELECT
r.role_id
FROM core_users u
LEFT JOIN core_users_roles r ON u.id = r.user_id
WHERE u.id=:userId
AND r.deleted = false
""", nativeQuery = true)
 List<Integer> getRoles(Integer userId);


    @Query(value = """
SELECT
*
FROM  core_emails_system r
WHERE r.deleted = false
""", nativeQuery = true)
    List<Map<String,String>> getEmailsSystem();


 @Query(value = """
SELECT
        m.path AS config,
    m.description AS description,
    m.id AS moduleId,
    m.name AS moduleName,
    m.parent_id  AS parentId,
    m1.name AS parentName,
    i.icon AS icon,
    m.visible AS vista
FROM core_roles_modules rm
    LEFT JOIN core_modules m ON rm.module_id = m.id
    LEFT JOIN core_modules m1 ON m.parent_id = m1.id
    LEFT JOIN core_icons i ON m1.icon_id = i.id 
WHERE rm.role_id IN(:roleId)
""", nativeQuery = true)
 List<Map<String,Object>> getCredentialsByUser(List<Integer> roleId);

    @Query(value = """
SELECT
    ur.role_id
FROM core_users_roles ur
WHERE ur.user_id=:userId
AND ur.deleted = false
""", nativeQuery = true)
    List<Integer> getRolesIdByUser(Integer userId);

 @Query(value = """
SELECT
        m.path AS config,
    m.description AS description,
    m.id AS moduleId,
    m.name AS moduleName,
    m.parent_id AS parentId,
    m1.name AS parentName,
    i.icon AS icon,
    m.visible AS vista
FROM core_modules m 
    LEFT JOIN core_modules m1 ON m.parent_id = m1.id
    LEFT JOIN core_icons i ON m1.icon_id = i.id 
WHERE m.id IN (:moduleId)
""", nativeQuery = true)
 List<Map<String,Object>> getCredentialsByModule(List<Integer> moduleId);


}
