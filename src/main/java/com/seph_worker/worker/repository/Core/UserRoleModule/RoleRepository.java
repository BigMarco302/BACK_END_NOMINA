package com.seph_worker.worker.repository.Core.UserRoleModule;


import com.seph_worker.worker.core.entity.RoleModuleUser.CoreRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleRepository extends JpaRepository<CoreRole, Integer> {

    @Query(value = """
SELECT
ru.id AS id,
ru.name AS name,
ru1.name AS parentName,
pr.update AS editar,
pr.add AS agregar,
pr.delete AS eliminar
FROM core_roles ru
LEFT JOIN core_roles ru1 ON ru.parent_id=ru1.id
Left JOIN core_permissions pr ON pr.id=ru.permission_id
WHERE ru.id =:roleId 
AND ru.deleted = false 
""", nativeQuery = true)
    Map<String,Object> getRoleById(Integer roleId);

    @Query(value = """
SELECT
m.name,
m.id
FROM  core_roles_modules rm
INNER JOIN core_modules m ON rm.module_id = m.id
WHERE rm.role_id = :roleId
AND rm.deleted = false 
AND m.deleted = false
""", nativeQuery = true)
    List<Map<String,Object>> getModulesByRole(Integer roleId);

    @Query(value = """
SELECT
ru.id AS id,
ru.name AS name,
ru1.name AS parentName,
pr.update AS editar,
pr.add AS agregar,
pr.delete AS eliminar
FROM core_roles ru
LEFT JOIN core_roles ru1 ON ru.parent_id=ru1.id
Left JOIN core_permissions pr ON pr.id=ru.permission_id
WHERE ru.deleted = false 
""", nativeQuery = true)
    List<Map<String,Object>> getAllRolesAssigned();

    @Query(value = """
SELECT
ru1.name,
ru.permission_id AS permiso
FROM core_roles ru
LEFT JOIN core_roles ru1 ON ru.parent_id = ru1.id
WHERE ru.id =:rolId
""", nativeQuery = true)
    Map<String,Object> getAllPermisos(Integer rolId);

    @Query(value = """
SELECT
pr.id AS id,
pr.authorize AS auto,
pr.update AS editar,
pr.add AS agregar,
pr.delete AS eliminar
FROM core_permissions pr
WHERE pr.id =:permisoId
""", nativeQuery = true)
    Map<String,Byte> getPermiso(Integer permisoId);
}
