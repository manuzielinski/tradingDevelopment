package com.manudev.userService.repository;

import com.manudev.Trading.userService.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    List<RoleEntity> findRoleByRoleEnumIn(List<String> roleNames);
}
