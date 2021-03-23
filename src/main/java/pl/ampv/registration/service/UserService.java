package pl.ampv.registration.service;


import pl.ampv.registration.dto.UserData;
import pl.ampv.registration.exception.UserAlreadyExistException;
import pl.ampv.registration.model.User;

public interface UserService {
    User save(UserData userData);

    void register(UserData userData) throws UserAlreadyExistException;

    boolean checkIfUserExist(String email);

    User findUserByUsername(String email);

    User findById(Long userId);

    void update(User user);
}

