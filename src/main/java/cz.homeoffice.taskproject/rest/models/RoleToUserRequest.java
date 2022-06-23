package cz.homeoffice.taskproject.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleToUserRequest {

    String username;
    String roleName;
}
