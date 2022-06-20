package cz.homeoffice.taskproject.services;

import cz.homeoffice.taskproject.convertors.UserConvertor;
import cz.homeoffice.taskproject.entity.PersonalData;
import cz.homeoffice.taskproject.entity.User;
import cz.homeoffice.taskproject.repository.UserRepository;
import cz.homeoffice.taskproject.rest.models.PersonalDataDto;
import cz.homeoffice.taskproject.rest.models.UserDto;
import cz.homeoffice.taskproject.services.exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserConvertor userConvertor;

    private final PersonalDataService personalDataService;

    public UserDto addUser(UserDto userDto) {
        UUID accessToken = UUID.randomUUID();
        PersonalData data = new PersonalData();
        data.setPhoneNumber(userDto.getPersonalData().getPhoneNumber());
        data.setBirthday(userDto.getPersonalData().getBirthday());
        data.setAddress(userDto.getPersonalData().getAddress());
        userDto.setAccessToken(String.valueOf(accessToken));
        User user = userConvertor.toDao(userDto);
        user.setPersonalData(data);
        User savedUser = userRepository.save(user);
        log.info("Adding user " + user);
        return userConvertor.toDto(savedUser);
    }

    public List<UserDto> getUsers() {
        log.info("Getting users");
        return userRepository.findAll().stream().map(userConvertor::toDto).collect(Collectors.toList());
    }

    public void deleteUserById(Long id) {
        if (!userRepository.findById(id.intValue()).isPresent()) {
            throw new UserServiceException("The id number isn't found");
        }
        log.info("Deleting user");
        userRepository.deleteById(id.intValue());
    }

    public UserDto updateUser(UserDto userDto) {
        if (!userRepository.findById(userDto.getId().intValue()).isPresent()) {
            throw new UserServiceException("The id number isn't found");
        }
        User dao = userConvertor.toDao(userDto);
        User save = userRepository.save(dao);
        return userConvertor.toDto(save);
    }

    public String login(String username, String password) {
        Optional<User> user = userRepository.login(username, password);
        if (user.isPresent()) {
            String token = UUID.randomUUID().toString();
            User user1 = user.get();
            user1.setAccessToken(token);
            userRepository.save(user1);
            return token;
        }

        return StringUtils.EMPTY;
    }

    public Optional<org.springframework.security.core.userdetails.User> findByToken(String token) {
        Optional<User> user = userRepository.findByAccessToken(token);
        if (user.isPresent()) {
            User user1 = user.get();
            org.springframework.security.core.userdetails.User newUser = new org.springframework.security.core.userdetails.User(user1.getUserName(), user1.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(newUser);
        }
        return Optional.empty();
    }

    public PersonalDataDto getUserPersonalDataByAccessToken(String accessToken) {
        Optional<User> userDao = userRepository.findByAccessToken(accessToken);
        if (!userDao.isPresent()) {
            throw new UserServiceException("The id number isn't found");
        }
        return personalDataService.getPersonalDataById(userDao.get().getPersonalData().getId());
    }
}
