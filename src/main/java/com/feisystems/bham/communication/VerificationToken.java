package com.feisystems.bham.communication;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.feisystems.bham.domain.provider.IndividualProvider;

@Entity
@Table(name = "rest_verification_token")
public class VerificationToken extends BaseEntity {

    private static final int DEFAULT_EXPIRY_TIME_IN_MINS = 60 * 24; //24 hours

    @Column(length=36)
    private final String token;

    @Enumerated(EnumType.STRING)
    private VerificationTokenType tokenType;

    private boolean verified;

    @ManyToOne
    @JoinColumn(name = "user_id")
    IndividualProvider user;

    public VerificationToken() {
        super();
        this.token = UUID.randomUUID().toString();
    }

    public VerificationToken(IndividualProvider user, VerificationTokenType tokenType) {
        this();
        this.user = user;
        this.tokenType = tokenType;
    }

    public VerificationTokenType getTokenType() {
        return tokenType;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public IndividualProvider getUser() {
        return user;
    }

    public void setUser(IndividualProvider user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public enum VerificationTokenType {
        lostPassword, emailVerification, emailRegistration
    }

}
