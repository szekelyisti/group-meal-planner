package org.ppke.itk.groupmealplanner.security;

import lombok. RequiredArgsConstructor ;
import lombok.extern.slf4j. Slf4j ;
import org.ppke.itk.groupmealplanner.domain.User;
import org.ppke.itk.groupmealplanner.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype. Service ;
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        User dbUser = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException(String.format("User with username %s", username)));
        return org.springframework.security.core.userdetails.User. builder ()
                .username(username)
                .password(dbUser.getPassword())
                .authorities(dbUser.getRole())
                .build();
    }
}
