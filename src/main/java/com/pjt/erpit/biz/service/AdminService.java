package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.admin.ResetPasswordDTO;
import com.pjt.erpit.biz.dto.admin.SignupDTO;
import com.pjt.erpit.biz.entity.Auth;
import com.pjt.erpit.biz.entity.User;
import com.pjt.erpit.biz.repository.AuthRepository;
import com.pjt.erpit.biz.repository.UserRepository;
import com.pjt.erpit.core.config.ResponseResult;
import com.pjt.erpit.core.util.DateUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 어드민 관련 Service
 */
@Slf4j
@SuppressWarnings({"SpellCheckingInspection", "CallToPrintStackTrace"})
@Service
public class AdminService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final HttpServletRequest request;
    private final AuthRepository authRepository;

    @Value("${spring.init.password}")
    private String password;

    public AdminService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, HttpServletRequest request, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.request = request;
        this.authRepository = authRepository;
    }

    /**
     * 회원 가입
     *
     * @param signupDTO p1
     */
    @Transactional
    public void signup(SignupDTO.Request signupDTO) {
        String usernm = signupDTO.getUsernm();
        LocalDateTime birthdate = DateUtils.toLocalDate(signupDTO.getBirthdate());
        LocalDateTime joindate = DateUtils.toLocalDate(signupDTO.getJoindate());

        // usercd 생성
        String year = Integer.toString(LocalDate.now().getYear());
        StringBuilder usercd;
        int userIndex = 1;
        while (true) {
            usercd = new StringBuilder("ER" + year);
            if (userIndex < 10) {
                usercd.append("000");
            } else if (userIndex < 100) {
                usercd.append("00");
            } else if (userIndex < 1000) {
                usercd.append("0");
            }
            usercd.append(userIndex);

            Boolean isExist = userRepository.existsByUsercd(usercd.toString());
            if (!isExist) {
                break;
            }
            userIndex++;
        }

        String ip = request.getRemoteAddr();

        User user = new User();
        user.setUsercd(usercd.toString());
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setUsernm(usernm);
        user.setBirthdate(birthdate);
        user.setJoindate(joindate);
        user.setAddipaddr(ip);
        user.setUpdipaddr(ip);

        userRepository.save(user);

        Auth auth = new Auth();
        auth.setUsercd(user.getUsercd());
        auth.setAuth("ROLE_USER");
        auth.setAddipaddr(ip);
        auth.setUpdipaddr(ip);

        authRepository.save(auth);
    }

    /**
     * 비밀번호 초기화
     *
     * @param resetPasswordDTO p1
     * @return ResponseResult<?>
     */
    @Transactional
    public ResponseResult<?> resetPassword(ResetPasswordDTO.Request resetPasswordDTO) {
        User user = userRepository.findByUsercd(resetPasswordDTO.getUsercd());
        if (user == null) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "user is not exist");

        }
        String ip = request.getRemoteAddr();

        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setUpdipaddr(ip);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResponseResult.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return ResponseResult.ofSuccess("success", null);
    }
}
