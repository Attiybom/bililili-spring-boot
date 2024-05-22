package com.attiy.bilibili.controller;

import com.attiy.bilibili.controller.support.UserSupport;
import com.attiy.bilibili.domain.JsonResponse;
import com.attiy.bilibili.domain.User;
import com.attiy.bilibili.service.UserService;
import com.attiy.bilibili.service.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSupport userSupport;

    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKey() {
        String pk = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(pk);
    }

    @GetMapping("/users")
    public JsonResponse<User> getUserInfo() {
        Long userId = userSupport.getCurrentUserId();
        User user = userService.getUserInfo(userId);
        return new JsonResponse<>(user);
    }

    @PostMapping("/users")
    public JsonResponse<String> createUser(@RequestBody User user) {

        userService.createUser(user);

        return JsonResponse.success();
    }

    @PostMapping("/user-tokens")
    public JsonResponse<String> login(@RequestBody  User user) throws Exception {
        String token = userService.login(user);
        return new JsonResponse<>(token);
    }


}
