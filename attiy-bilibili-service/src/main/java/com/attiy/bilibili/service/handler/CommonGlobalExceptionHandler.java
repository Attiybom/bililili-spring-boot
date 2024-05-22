package com.attiy.bilibili.service.handler;

import com.attiy.bilibili.domain.JsonResponse;
import com.attiy.bilibili.domain.exception.ConditionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonGlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse<String> commonExcetionHandler(HttpServletRequest request, Exception err) {
        String errorMessage = err.getMessage();
        if (err instanceof ConditionException) {
            String errorCode = ((ConditionException)err).getCode();
            return new JsonResponse<>(errorCode, errorMessage);
        } else {
            return new  JsonResponse<>("500", errorMessage);
        }
    }
}
