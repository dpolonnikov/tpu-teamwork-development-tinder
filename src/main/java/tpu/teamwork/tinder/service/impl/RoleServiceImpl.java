package tpu.teamwork.tinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tpu.teamwork.tinder.entity.Role;
import tpu.teamwork.tinder.exception.RoleNotFoundException;
import tpu.teamwork.tinder.repository.RoleRepository;
import tpu.teamwork.tinder.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(()-> new RoleNotFoundException(
                String.format("Роль `%s` не существует в бд", name)
        ));
    }
}
