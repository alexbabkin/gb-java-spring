package com.github.alexbabkin.security.configs;

import com.github.alexbabkin.security.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    //    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwt);
            } catch (ExpiredJwtException e) {
                log.debug("The token is expired");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // //Достаем юзера из базы данных, моментальная проверка роли, но дополнительная
            // нагрузка на базу данных
            //            UsernamePasswordAuthenticationToken token = new
            // UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            //            token.setDetails(new
            // WebAuthenticationDetailsSource().buildDetails(request));
            //            SecurityContextHolder.getContext().setAuthentication(token);

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            jwtTokenUtil
                                    .getRoles(jwt)
                                    .stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList()));
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }
}
