package com.chang.translator;

import com.chang.exception.CustomOauthException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * 自定义oauth2异常转换实现类
 * @author Mr.Chang
 **/
@Slf4j
@Component
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e){
        if(e instanceof InvalidTokenException){
            InvalidTokenException invalidTokenException = (InvalidTokenException) e;
            return ResponseEntity.status(invalidTokenException.getHttpErrorCode()).body(new CustomOauthException("非法的token"));
        }
        if(e instanceof OAuth2Exception){
            OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
            return ResponseEntity.status(oAuth2Exception.getHttpErrorCode()).body(new CustomOauthException(oAuth2Exception.getMessage()));
        }else if(e instanceof DisabledException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new CustomOauthException(e.getMessage()));
        }else{
            return ResponseEntity.status(500).body(new CustomOauthException(e.getMessage()));
        }
    }
}
