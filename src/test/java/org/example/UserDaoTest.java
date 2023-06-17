package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @DisplayName("UserDao 를 생성한다.")
    @Test
    void create() throws SQLException {
        //given
        UserDao userDao = new UserDao();

        //when
        User saveUser = new User("userId", "password", "name", "email");
        userDao.create(saveUser);
        User user = userDao.findByUserId("userId");

        //then
        assertThat(user).isEqualTo(saveUser);
    }
}