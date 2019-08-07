package com.jrmarcco.common.config.global;

import com.jrmarcco.common.base.BaseResult;
import com.jrmarcco.common.exception.ServiceException;
import com.jrmarcco.common.exception.sys.SysError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理。
 *
 * @author hongjc
 * @version 1.0  2019/1/16
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public BaseResult<Void> handleServiceException(ServiceException e) {
        log.error("### [{}] {} ###", e.getExceptionCode(), e.getExceptionMessage());
        return new BaseResult<Void>().error(e);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public BaseResult<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("### {} ###", e.getMessage(), e);

        return new BaseResult<Void>().error(SysError.Default.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResult<Void> handleException(Exception e) {
        log.error("### {} ###", e.getMessage(), e);
        return new BaseResult<Void>().error(SysError.Default);
    }
}
