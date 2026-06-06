package com.rishiproject.job.Repositories;

import com.rishiproject.job.Modals.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Boolean existsByEmail(String email);
}
