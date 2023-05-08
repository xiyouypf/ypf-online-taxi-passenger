package com.ypf.passenger.web.config;

import com.ypf.common.service.VerificationCodeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ypf
 * @date 2023/05/05 11:07
 */
@Configuration
public class VerificationCodeConfig {
    @Bean
    public VerificationCodeService verificationCodeService() {
        return new VerificationCodeService();
    }
}
