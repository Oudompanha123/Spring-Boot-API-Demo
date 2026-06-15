package com.ppcb.cafemerchant.common.domain.user;

import com.ppcb.cafemerchant.common.enums.LoginType;
import com.ppcb.cafemerchant.common.domain.user.profile.ProfileResponseInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByLoginLinkUrl(String loginLinkUrl);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.lastLogin = INSTANT WHERE u.userId = ?1")
    void updateLastLogin(Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.loginType = ?1, u.lastLogin = INSTANT WHERE u.userId = ?2")
    void updateLoginType(LoginType loginType, Integer userId);

    @Query(
            value = """
                SELECT * FROM tb_users 
                WHERE user_id = :id
                """,
            nativeQuery = true
    )
    ProfileResponseInterface findUserProfileById(@Param("id") long id);
}
