package tpu.teamwork.tinder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tpu.teamwork.tinder.dto.SignUpRequestDTO;
import tpu.teamwork.tinder.dto.UserResponseDTO;
import tpu.teamwork.tinder.entity.User;
import tpu.teamwork.tinder.mapper.UserResponseMapper;
import tpu.teamwork.tinder.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserResponseMapper userResponseMapper;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Пользователь `%s` не найден", username))
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    @Transactional
    public UserResponseDTO createNewUser(SignUpRequestDTO signUpRequestDTO) {
        User user = new User();
        user.setUsername(signUpRequestDTO.username());
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.password()));
        user.setEmail(signUpRequestDTO.email());
        user.setRoles(List.of(roleService.findByName("ROLE_USER")));
        User savedUser = userRepository.save(user);
        log.info("Сохранен пользователь с id `{}` ", savedUser.getId());
        return userResponseMapper.toUserResponseDTO(savedUser);
    }
}
