package cz.homeoffice.taskproject.convertors;

import cz.homeoffice.taskproject.entity.User;
import cz.homeoffice.taskproject.rest.models.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserConvertor {

    public User toDao(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .accessToken(dto.getAccessToken())
                .build();
    }

    public UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .accessToken(entity.getAccessToken())
                .build();
    }
}
