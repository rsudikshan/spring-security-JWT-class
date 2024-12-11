package com.sr.ecommerce_g1.Repository;

import com.sr.ecommerce_g1.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

    public Users findByUsername(String username);

}
