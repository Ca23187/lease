package com.lease.common.exception;

import com.lease.common.result.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class LeaseException extends RuntimeException {

    private Integer code;

    public LeaseException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

}
