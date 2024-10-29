package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.user.ChangePasswordDTO;
import com.pjt.erpit.biz.dto.user.UserDto;
import com.pjt.erpit.biz.dto.user.UserListDto;
import com.pjt.erpit.biz.entity.Auth;
import com.pjt.erpit.biz.entity.User;
import com.pjt.erpit.biz.mapper.UserMapper;
import com.pjt.erpit.biz.repository.AuthRepository;
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

import java.util.List;

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
    private final UserMapper userMapper;
    private final AuthRepository authRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthService authService, UserMapper userMapper, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authService = authService;
        this.userMapper = userMapper;
        this.authRepository = authRepository;
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

    /**
     * 유저 목록 조회
     *
     * @param user p1
     * @return List
     */
    public List<UserListDto.Response> getUserList(String user) {
        List<UserListDto.Response> userList = userMapper.getUserList(user);
        return userList;
    }

    /**
     * 유저 정보 수정
     *
     * @param request p1
     * @param userReq p2
     * @return ResponseResult
     */
    @Transactional
    public ResponseResult<?> updateUser(HttpServletRequest request, UserDto.Request userReq) {
        if (userReq.getUsercd() == null) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "user is not exist");
        }

        if (userRepository.findByUsercd(userReq.getUsercd()) == null) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "user is not exist");
        }
        String ip = request.getRemoteAddr();
        User user = userRepository.findByUsercd(userReq.getUsercd());
        user.setUsernm(userReq.getUsernm());
        user.setUpdipaddr(ip);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResponseResult.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }


        Auth auth = authRepository.findByUsercd(userReq.getUsercd());
        auth.setAuth(userReq.getAuth());
        auth.setUpdipaddr(ip);
        try {
            authRepository.save(auth);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResponseResult.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return ResponseResult.ofSuccess("success", null);
    }
}
