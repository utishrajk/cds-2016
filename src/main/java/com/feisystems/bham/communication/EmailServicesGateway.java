package com.feisystems.bham.communication;


public interface EmailServicesGateway {

    public void sendVerificationToken(EmailServiceTokenModel model);
}
