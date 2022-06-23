package cz.homeoffice.taskproject.convertors;

import cz.homeoffice.taskproject.entity.UserData;
import cz.homeoffice.taskproject.rest.models.UserDataDto;
import org.springframework.stereotype.Service;

@Service
public class UserConvertor {

    public UserData toDao(UserDataDto dto) {
        return UserData.builder()
                .id(dto.getId())
                .name(dto.getName())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public UserDataDto toDto(UserData entity) {
        return UserDataDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }
}
