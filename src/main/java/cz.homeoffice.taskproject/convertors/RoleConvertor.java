package cz.homeoffice.taskproject.convertors;

import cz.homeoffice.taskproject.entity.Role;
import cz.homeoffice.taskproject.rest.models.RoleDto;
import org.springframework.stereotype.Service;

@Service
public class RoleConvertor {

    public RoleDto toDto(Role roleDto) {
        return RoleDto.builder()
                .name(roleDto.getName())
                .build();
    }
}
