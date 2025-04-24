package com.manudev.Trading.userService.service.impl;

import com.manudev.Trading.userService.dto.AuthCreateUserDTO;
import com.manudev.Trading.userService.dto.AuthLoginRequestDTO;
import com.manudev.Trading.userService.dto.AuthResponseDTO;
import com.manudev.Trading.userService.model.RoleEntity;
import com.manudev.Trading.userService.model.UserEntity;
import com.manudev.Trading.userService.repository.RoleRepository;
import com.manudev.Trading.userService.repository.UserRepository;
import com.manudev.Trading.userService.config.filter.JwtUtil;
import com.manudev.Trading.userService.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario: " + username + " no existe"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        // Obtener roles del usuario
        userEntity.getRoles().forEach(role ->
                authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getUserRole().name())))
        );

        // Obtener permisos del usuario
        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission ->
                        authorityList.add(new SimpleGrantedAuthority(permission.getName()))
                );

        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isAccountNoLocked(),
                userEntity.isCredentialNoExpired(),
                authorityList
        );
    }

    public AuthResponseDTO loginUser(AuthLoginRequestDTO authLoginRequestDTO) {
        String username = authLoginRequestDTO.username();
        String password = authLoginRequestDTO.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtil.createToken(authentication);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(username, "User Loged succesfully", accessToken, true);
        return authResponseDTO;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);

        if (userDetails == null){
            throw new BadCredentialsException("Invalid Username of Password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Username of Password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponseDTO createUser(AuthCreateUserDTO authCreateUserDTO){
        String username = authCreateUserDTO.username();
        String password = authCreateUserDTO.password();
        List<String> roleRequest = authCreateUserDTO.roleRequest().roleListName();
        Set<RoleEntity> roleEntitySet = roleRepository.findRoleByRoleEnumIn(roleRequest).stream().collect(Collectors.toSet());

        if(roleEntitySet.isEmpty()){
            try {
                throw new IllegalAccessException("Los roles especificados no existen");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(roleEntitySet)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        UserEntity userCreated = userRepository.save(userEntity);
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoles().forEach(userRole -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(userRole.getUserRole().name()))));
        userCreated.getRoles()
                .stream()
                .flatMap(userRole -> userRole.getPermissionList().stream())
                .forEach(permissionEntity -> authorityList.add(new SimpleGrantedAuthority(permissionEntity.getName())));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getUsername(), userCreated.getPassword(), authorityList);

        String accessToken = jwtUtil.createToken(authentication);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(userCreated.getUsername(), "User created Succesfully", accessToken, true);
        return authResponseDTO;
    }
}