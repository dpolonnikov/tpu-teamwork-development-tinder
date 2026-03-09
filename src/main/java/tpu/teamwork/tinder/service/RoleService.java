package tpu.teamwork.tinder.service;

import tpu.teamwork.tinder.entity.Role;

public interface RoleService {
    Role findByName(String name);
}
