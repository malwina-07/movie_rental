package pl.ampv.registration.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ampv.registration.dto.UserData;
import pl.ampv.registration.exception.UserAlreadyExistException;
import pl.ampv.registration.model.Role;
import pl.ampv.registration.model.User;
import pl.ampv.registration.repository.UserRepository;
import pl.ampv.registration.service.RoleService;
import pl.ampv.registration.service.UserService;


import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(UserData userData) {
        User user = populateUserData(userData);

        Role role = roleService.findByName("USER");
        List<Role> roleSet = new ArrayList<>();
        roleSet.add(role);

//TODO sprawdzić poprawność funkcji
//        if(user.getEmail().split("@")[1].equals("admin")){
//            role = roleService.findByName("ADMIN");
//            roleSet.add(role);
//        }
        user.setRoles(roleSet);

        return userRepository.save(user);
    }

    private User populateUserData(final UserData userData) {
        User user = new User();
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setEmail(userData.getEmail());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        return user;
    }

    @Override
    public void register(UserData userData) throws UserAlreadyExistException {

        if (checkIfUserExist(userData.getEmail())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }

        User userEntity = new User();
        BeanUtils.copyProperties(userData, userEntity);

        Role userRole = roleService.findByName("USER");
        userEntity.setRoles(Collections.singletonList(userRole));

        encodePassword(userEntity, userData);
        userRepository.save(userEntity);
    }

    @Override
    public boolean checkIfUserExist(String email) {
        User findUser = userRepository.findByEmail(email);
        return findUser != null;
    }

    @Override
    public User findUserByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void update(User user) {
        User preUpdate = userRepository.getOne(user.getId());
        user.setRoles(preUpdate.getRoles());
        user.setReview(preUpdate.getReview()); //przekazuje pola ktore nie ulegają zmianie

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    private void encodePassword(User userEntity, UserData userData) {
        userEntity.setPassword(passwordEncoder.encode(userData.getPassword()));
    }


}
