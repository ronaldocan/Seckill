package org.validator;

import org.seckill.dto.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Administrator on 2017/6/27.
 */
public class UserValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (user.getUsername().length() < 8){
            errors.rejectValue("username","valid.userNamelin",new Object[]{"minLength",8},"用户名不能小{1}位");
        }
    }
}
