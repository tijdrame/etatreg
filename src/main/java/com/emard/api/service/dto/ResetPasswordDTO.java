package com.emard.api.service.dto;

public class ResetPasswordDTO {

    private String newPassword, login;

    public ResetPasswordDTO() {
    }

    public ResetPasswordDTO(String newPassword, String login) {
        this.newPassword = newPassword;
        this.login = login;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ResetPasswordDTO newPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public ResetPasswordDTO login(String login) {
        this.login = login;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " newPassword='" + getNewPassword() + "'" +
            ", login='" + getLogin() + "'" +
            "}";
    }
    
}
