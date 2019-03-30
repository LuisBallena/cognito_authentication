package com.scrap.cognito.facade;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpRequest;
import com.amazonaws.services.cognitoidp.model.DescribeUserPoolRequest;
import com.amazonaws.services.cognitoidp.model.DescribeUserPoolResult;
import com.amazonaws.services.cognitoidp.model.PasswordPolicyType;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.scrap.cognito.api.dto.ConfirmDTO;
import com.scrap.cognito.api.dto.UserDTO;
import com.scrap.cognito.config.AWSProperties;
import com.scrap.microservice.util.PasswordPolicyEnum;
import com.scrap.microservice.util.PasswordUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * CognitoFacade.
 *
 * @author Luis Alonso Ballena Garcia
 */
@Slf4j
@Component
public class CognitoFacade {

    private static String COGNITO_PARAM_EMAIL = "email";

    private AWSCognitoIdentityProvider awsCognitoIdentityProvider;
    private AWSProperties awsProperties;
    private MessageSource messageSource;

    public CognitoFacade(AWSCognitoIdentityProvider awsCognitoIdentityProvider, AWSProperties awsProperties,
                         MessageSource messageSource) {
        this.awsCognitoIdentityProvider = awsCognitoIdentityProvider;
        this.awsProperties = awsProperties;
        this.messageSource = messageSource;
    }

    public void signUp(UserDTO userDTO) {
        validatePassword(userDTO.getPassword());
        List<AttributeType> list = new ArrayList();
        list.add(getAttributeType(COGNITO_PARAM_EMAIL, userDTO.getEmail()));
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setClientId(awsProperties.getCognito().getCliendId());
        signUpRequest.setUsername(userDTO.getEmail());
        signUpRequest.setPassword(userDTO.getPassword());
        signUpRequest.setUserAttributes(list);
        try {
            awsCognitoIdentityProvider.signUp(signUpRequest);
        } catch (Exception e) {
            log.error("Ocurrio un error al registrar al usuario en cognito : {}", e.getMessage(), e);
            throw new RuntimeException("Ocurrio un error al registrar el usuario en cognito");
        }
    }

    public void confirmCode(ConfirmDTO confirmDTO) {
        ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest();
        confirmSignUpRequest.setUsername(confirmDTO.getEmail());
        confirmSignUpRequest.setConfirmationCode(confirmDTO.getCode());
        confirmSignUpRequest.setClientId(awsProperties.getCognito().getCliendId());
        try {
            awsCognitoIdentityProvider.confirmSignUp(confirmSignUpRequest);
        } catch (Exception e) {
            log.error("Ocurrio un error al confirmar el usuario en cognito : {}", e.getMessage(), e);
            throw new RuntimeException("Ocurrio un error al confirmar el usuario en cognito");
        }
    }


    private void validatePassword(String password) {
        PasswordPolicyType passwordPolicyType = getPasswordPolicy();
        List<PasswordPolicyEnum> passwordPolicyEnums = new ArrayList<>();
        addPolicy(passwordPolicyEnums, passwordPolicyType.getRequireLowercase(),
                PasswordPolicyEnum.MIN_ONE_LOWERCASE_LETTER);
        addPolicy(passwordPolicyEnums, passwordPolicyType.getRequireUppercase(),
                PasswordPolicyEnum.MIN_ONE_UPPERCASE_LETTER);
        addPolicy(passwordPolicyEnums, passwordPolicyType.getRequireNumbers(),
                PasswordPolicyEnum.MIN_ONE_NUMBER);
        addPolicy(passwordPolicyEnums, passwordPolicyType.getRequireSymbols(),
                PasswordPolicyEnum.MIN_ONE_SPECIAL_CHARACTER);
        boolean result = PasswordUtil.validatePassword(password, passwordPolicyType.getMinimumLength()
                , passwordPolicyEnums);
        if (!result) {
            throw new RuntimeException(messageSource.getMessage("com.scrap.password.not.valid", null,
                    Locale.getDefault()));
        }
    }

    private PasswordPolicyType getPasswordPolicy() {
        PasswordPolicyType passwordPolicyType = null;
        DescribeUserPoolRequest describeUserPoolRequest = new DescribeUserPoolRequest();
        describeUserPoolRequest.setUserPoolId(awsProperties.getCognito().getUserPoolId());
        try {
            DescribeUserPoolResult describeUserPoolResult =
                    awsCognitoIdentityProvider.describeUserPool(describeUserPoolRequest);
            passwordPolicyType = describeUserPoolResult.getUserPool().getPolicies()
                    .getPasswordPolicy();
        } catch (Exception e) {
            log.error("Ocurrio un error al obtener información del UserPool : {}", e.getMessage(), e);
            throw new RuntimeException("Ocurrio un error al obtener información del UserPool");
        }
        return passwordPolicyType;
    }

    private AttributeType getAttributeType(String name, String value) {
        AttributeType attributeType = new AttributeType();
        attributeType.setName(name);
        attributeType.setValue(value);
        return attributeType;
    }


    private List<PasswordPolicyEnum> addPolicy(List<PasswordPolicyEnum> passwordPolicyEnums, Boolean value,
                                               PasswordPolicyEnum passwordPolicyEnum) {
        if (value) {
            passwordPolicyEnums.add(passwordPolicyEnum);
        }
        return passwordPolicyEnums;
    }

}
