package tpu.teamwork.tinder.mapper;

import org.mapstruct.Mapper;
import tpu.teamwork.tinder.dto.UserResponseDTO;
import tpu.teamwork.tinder.entity.User;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserResponseDTO toUserResponseDTO(User user);
}
