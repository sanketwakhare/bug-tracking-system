package com.gaurav.bugtrackingsystem.repositories;

import com.gaurav.bugtrackingsystem.models.RoleType;
import com.gaurav.bugtrackingsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name=:username")
    Optional<User> findByName(@Param("username") String name);


    @Query("select u from User u where u.name=:username and u.password=:password")
    Optional<User> findByNameAndPassword(@Param("username") String name,
                          @Param("password") String password);

//    @Query("select u from User u where u.roleType=:roleType")
    @Query(value = "select * from users where role_type=:roleType", nativeQuery = true)
    List<User> findAllByRoleType(@Param("roleType") String roleType);
}
