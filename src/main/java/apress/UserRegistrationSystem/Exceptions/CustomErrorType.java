package main.java.apress.UserRegistrationSystem.Exceptions;

import apress.UserRegistrationSystem.dto.UserDTO;

public class CustomErrorType extends UserDTO {
    private String errorMessage;

    public CustomErrorType(final String errorMessage){
        this.errorMessage=errorMessage;
    }


    public String getErrorMessage(){
        return errorMessage;
    }
}
