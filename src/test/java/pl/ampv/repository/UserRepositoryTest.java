package pl.ampv.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import pl.ampv.registration.model.User;
import pl.ampv.registration.repository.UserRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // actual database
@Rollback(false) //commit the changes
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setFirstName("Malwina");
        user.setLastName("Klocek");
        user.setPassword("movie1");
        user.setEmail("user1@mail.com");

        User saveUser = repository.save(user);
        User existUser = entityManager.find(User.class, saveUser.getId());
        Assertions.assertThat(user.getEmail()).isEqualTo(existUser.getEmail());


    }

}