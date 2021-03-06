package com.example.demo.service;

import com.example.demo.domain.model.AccountStatus;
import com.example.demo.domain.model.AccountType;
import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.exception.AccountNameExistsException;
import com.example.demo.exception.EmailExistsException;
import com.example.demo.exception.InvalidRegistrationDataException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.UserDto;
import com.example.demo.service.mappers.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(user -> userMapper.map(user)).collect(Collectors.toList());
    }

    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.map(userDto);
        user.setCreated(LocalDate.now());
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setAccountType(AccountType.STANDARD);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.map(userRepository.save(user));

    }

    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    public User updateUser(UserDto userDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setAccountName(userDto.getAccountName());
        user.setRegion(userDto.getRegion());
        user.setCity(userDto.getCity());
        user.setStreet(userDto.getStreet());
        user.setHouseNumber(userDto.getHouseNumber());
        user.setPostalCode(userDto.getPostalCode());

        return userRepository.save(user);
    }

    public UserDto findUser(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " cannot be found"));
        return userMapper.map(user);
    }

    public User findUserEntity() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User cannot be found"));
    }

    public void validateUserRegistration(UserDto userDto) throws EmailExistsException, InvalidRegistrationDataException {

        if (userDto.getEmail().isBlank()) {
            throw new InvalidRegistrationDataException("Email cannot to be empty");
        }
        if (userDto.getPassword().isBlank()) {
            throw new InvalidRegistrationDataException("Password cannot to be empty");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailExistsException("This email exists");
        }
        if (!userDto.getPassword().equals(userDto.getRepeatedPassword())) {
            throw new InvalidRegistrationDataException("Password must be the same");
        }
    }

    public void validateUserMoreDetails(UserDto userDto) throws InvalidRegistrationDataException, AccountNameExistsException {
        if (userDto.getAccountName().isBlank()) {
            throw new InvalidRegistrationDataException("Email cannot to be empty");
        }

        if (userRepository.existsByAccountName(userDto.getAccountName())) {
            throw new AccountNameExistsException("This name is taken ");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
