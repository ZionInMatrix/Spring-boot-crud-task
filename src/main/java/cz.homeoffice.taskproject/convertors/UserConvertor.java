package cz.homeoffice.taskproject.convertors;

import cz.homeoffice.taskproject.entity.User;
import cz.homeoffice.taskproject.rest.models.UserDto;

public class UserConvertor {

    public User toDao(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .accessToken(dto.getAccessToken())
                .build();
    }

    public UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .accessToken(entity.getAccessToken())
                .build();
    }
}
