package com.academy.proxy;

import com.academy.ApiCallback;
import com.academy.ApiClient;
import com.academy.ApiException;
import com.academy.ApiResponse;
import com.academy.api.*;
import okhttp3.Call;

import java.util.logging.Logger;

public class LoginProxy implements Login {
    private static final Logger LOGGER = Logger.getLogger(LoginProxy.class.getName());
    private static Login login;

    public LoginProxy(Login login) {
        this.login = login;
    }


    @Override
    public ApiClient getApiClient() {
            LOGGER.info("getApiClient method called");
            return login.getApiClient();
    }

    @Override
    public void setApiClient(ApiClient apiClient) {
        LOGGER.info("setApiClient method called with apiClient: " + apiClient);
        login.setApiClient(apiClient);

    }

    @Override
    public int getHostIndex() {
        LOGGER.info("getHostIndex method called");
        return login.getHostIndex();
    }

    @Override
    public void setHostIndex(int hostIndex) {

    }

    @Override
    public String getCustomBaseUrl() {
        return null;
    }

    @Override
    public void setCustomBaseUrl(String customBaseUrl) {

    }

    @Override
    public Call refreshAccessTokenCall(RefreshTokenRequest refreshTokenRequest, ApiCallback _callback) throws ApiException {
        return null;
    }

    @Override
    public RefreshTokenResponse refreshAccessToken(RefreshTokenRequest refreshTokenRequest) throws ApiException {
        return null;
    }

    @Override
    public ApiResponse<RefreshTokenResponse> refreshAccessTokenWithHttpInfo(RefreshTokenRequest refreshTokenRequest) throws ApiException {
        return null;
    }

    @Override
    public Call refreshAccessTokenAsync(RefreshTokenRequest refreshTokenRequest, ApiCallback<RefreshTokenResponse> _callback) throws ApiException {
        return null;
    }

    @Override
    public Call revokeRefreshTokenCall(RefreshTokenRequest refreshTokenRequest, ApiCallback _callback) throws ApiException {
        return null;
    }

    @Override
    public void revokeRefreshToken(RefreshTokenRequest refreshTokenRequest) throws ApiException {

    }

    @Override
    public ApiResponse<Void> revokeRefreshTokenWithHttpInfo(RefreshTokenRequest refreshTokenRequest) throws ApiException {
        return null;
    }

    @Override
    public Call revokeRefreshTokenAsync(RefreshTokenRequest refreshTokenRequest, ApiCallback<Void> _callback) throws ApiException {
        return null;
    }

    @Override
    public Call signInCall(UserLogin userLogin, ApiCallback _callback) throws ApiException {
        return null;
    }

    @Override
    public SuccessLogin signIn(UserLogin userLogin) throws ApiException {
        return null;
    }

    @Override
    public ApiResponse<SuccessLogin> signInWithHttpInfo(UserLogin userLogin) throws ApiException {
        return null;
    }

    @Override
    public Call signInAsync(UserLogin userLogin, ApiCallback<SuccessLogin> _callback) throws ApiException {
        return null;
    }
}
