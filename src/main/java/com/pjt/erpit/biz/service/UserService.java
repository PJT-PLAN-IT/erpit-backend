package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.user.ChangePasswordDTO;
import com.pjt.erpit.biz.entity.User;
import com.pjt.erpit.biz.repository.UserRepository;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 유저 관련 Service
 */
@SuppressWarnings("CallToPrintStackTrace")
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthService authService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthService authService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authService = authService;
    }

    /**
     * 비밀번호 재설정
     *
     * @param changePasswordDTO p1
     * @return ResponseResult<?>
     */
    @Transactional
    public ResponseResult<?> changePassword(HttpServletRequest request, HttpServletResponse response, ChangePasswordDTO.Request changePasswordDTO) {
        String usercd = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsercd(usercd);
        if (user == null) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "user is not exist");
        }

        String ip = request.getRemoteAddr();

        user.setPassword(bCryptPasswordEncoder.encode(changePasswordDTO.getPassword()));
        user.setUpdipaddr(ip);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResponseResult.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        authService.logout(request, response, usercd);

        return ResponseResult.ofSuccess("success", null);
    }
}
