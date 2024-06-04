package loolu.loolu_backend.services.validation;

import loolu.loolu_backend.dto.NewUserDto;
import loolu.loolu_backend.dto.UserDto;

public interface UsersService {
    UserDto register (NewUserDto newUser);
}
