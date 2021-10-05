package fr.infercidium.PayMyBuddy.mapper;

import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToModel(UserRegistrationDto userRegistrationDto);
    UserRegistrationDto modelToDto(User user);
}
