package com.sparta.myblog.controller;

import com.sparta.myblog.dto.AddUserRequest;
import com.sparta.myblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest addUserRequest){
        userService.save(addUserRequest); //회원 가입 메서드 추출
        return "redirect:/login"; // 회원 가입이 완료된 이후에 로그인 페이지로 이동
    }

}
