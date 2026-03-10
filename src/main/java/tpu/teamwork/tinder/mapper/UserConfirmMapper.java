package tpu.teamwork.tinder.mapper;

import org.mapstruct.Mapper;
import tpu.teamwork.tinder.dto.UserConfirmRequestDTO;
import tpu.teamwork.tinder.entity.User;

@Mapper(componentModel = "spring")
public interface UserConfirmMapper {
    User toUserEntity(UserConfirmRequestDTO userConfirmRequestDTO);
}
