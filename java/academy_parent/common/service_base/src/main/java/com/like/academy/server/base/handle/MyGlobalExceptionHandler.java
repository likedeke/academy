package com.like.academy.server.base.handle;

import com.like.academy.server.base.excetpion.AcademyException;
import com.lk.academy.common.base.result.Result;
import com.lk.academy.common.base.result.ResultCodeEnum;
import com.lk.academy.common.base.util.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 * @author likeLove
 * @time 2020-08-21  13:28
 */
@ControllerAdvice
@Slf4j
public class MyGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error(ExceptionUtils.getMessage(e));
        return Result.error();
    }

    @ExceptionHandler (BadSqlGrammarException.class)
    @ResponseBody
    public Result error(BadSqlGrammarException e) {
        log.error(ExceptionUtils.getMessage(e));
        return Result.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler (HttpMessageNotReadableException.class)
    @ResponseBody
    public Result error(HttpMessageNotReadableException e) {
        log.error(ExceptionUtils.getMessage(e));
        return Result.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    //捕获自定义异常
    @ExceptionHandler (AcademyException.class)
    @ResponseBody
    public Result error(AcademyException e) {
        log.error(ExceptionUtils.getMessage(e));
        return Result.error().message(e.getMessage()).code(e.getCode());
    }

}
