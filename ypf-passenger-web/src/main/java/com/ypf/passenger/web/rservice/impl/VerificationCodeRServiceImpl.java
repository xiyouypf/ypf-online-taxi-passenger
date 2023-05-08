package com.ypf.passenger.web.rservice.impl;

import com.ypf.common.enums.ExceptionCodeEnum;
import com.ypf.common.enums.UserTypeEnum;
import com.ypf.common.response.ServiceResponse;
import com.ypf.common.service.VerificationCodeService;
import com.ypf.passenger.api.exception.PassengerException;
import com.ypf.passenger.web.param.BaseVerificationCodeParam;
import com.ypf.passenger.web.param.VerificationCodeCheckParam;
import com.ypf.passenger.web.param.VerificationCodeGenerateParam;
import com.ypf.passenger.web.rservice.VerificationCodeRService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author ypf
 * @date 2023/05/05 00:02
 */
@RestController
@Slf4j
public class VerificationCodeRServiceImpl implements VerificationCodeRService {
    @Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping("/driver/verificationCode/get")
    @Override
    public ServiceResponse<Boolean> verificationCodeGet(VerificationCodeGenerateParam generateParam) {
        try {
            checkParam(generateParam);
            return verificationCodeService.generateVerificationCode(generateParam.getPhone(), UserTypeEnum.DRIVER_IDENTITY.getIdentityType());
        } catch (PassengerException e) {
            log.info("获取验证码程序异常generateParam = {}", generateParam);
            return ServiceResponse.buildErrorResponse(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.info("获取验证码系统异常", e);
            return ServiceResponse.buildErrorResponse(ExceptionCodeEnum.ERROR.getCode(), ExceptionCodeEnum.VERIFICATION_CODE_ERROR.getMsg());
        }
    }

    @PostMapping("/driver/verificationCode/check")
    @Override
    public ServiceResponse<Boolean> checkVerificationCode(VerificationCodeCheckParam param) {
        try {
            checkParam(param);
            return verificationCodeService.checkCode(param.getPhone(), param.getIdentityType(), param.getVerificationCode());
        } catch (PassengerException e) {
            log.info("校验验证码程序异常generateParam = {}", param);
            return ServiceResponse.buildErrorResponse(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.info("校验验证码系统异常", e);
            return ServiceResponse.buildErrorResponse(ExceptionCodeEnum.ERROR.getCode(), ExceptionCodeEnum.VERIFICATION_CODE_ERROR.getMsg());
        }
    }

    private void checkParam(BaseVerificationCodeParam param) {
        if (!Objects.equals(param.getIdentityType(), UserTypeEnum.DRIVER_IDENTITY.getIdentityType())) {
            throw new PassengerException(ExceptionCodeEnum.VERIFICATION_CODE_ERROR.getCode(), "参数错误");
        }
    }
}
