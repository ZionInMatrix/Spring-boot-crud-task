package cz.homeoffice.taskproject.convertors;

import cz.homeoffice.taskproject.entity.UserData;
import cz.homeoffice.taskproject.rest.models.UserDataDto;
import org.springframework.stereotype.Service;

@Service
public class UserConvertor {

    public UserData toEntity(UserDataDto dto) {
        return UserData.builder()
                .id(dto.getId())
                .name(dto.getName())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public UserDataDto toDto(UserData dao) {
        return UserDataDto.builder()
                .id(dao.getId())
                .username(dao.getUsername())
                .username(dao.getUsername())
                .password(dao.getPassword())
                .build();
    }
}
