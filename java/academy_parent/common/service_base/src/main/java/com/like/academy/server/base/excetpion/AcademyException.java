package com.like.academy.server.base.excetpion;

import com.lk.academy.common.base.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author likeLove
 * @time 2020-08-26  20:58
 */
@Data
public class AcademyException extends RuntimeException {
    private Integer code;
    public AcademyException(String message,Integer code) {
        super(message);
        this.code = code;
    }
    public AcademyException(ResultCodeEnum result) {
        super(result.getMessage());
        this.code = result.getCode();
    }
    @Override
    public String toString() {
        return "AcademyException{" + "message='" + this.getMessage() + '\'' + ", code=" + code + '}';
    }
}
