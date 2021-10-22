package fr.infercidium.PayMyBuddy.mapper;

import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Transform a dto into a model.
     * @param userRegistrationDto : the dto.
     * @return User model.
     */
    User dtoToModel(UserRegistrationDto userRegistrationDto);
}
