package com.academy.api.api;

import com.academy.api.invoker.ApiCallback;
import com.academy.api.invoker.ApiClient;
import com.academy.api.invoker.ApiException;
import com.academy.api.invoker.ApiResponse;
import com.academy.api.model.RefreshTokenRequest;
import com.academy.api.model.RefreshTokenResponse;
import com.academy.api.model.SuccessLogin;
import com.academy.api.model.UserLogin;

public interface LoginApi {
    ApiClient getApiClient();

    void setApiClient(ApiClient apiClient);

    int getHostIndex();

    void setHostIndex(int hostIndex);

    String getCustomBaseUrl();

    void setCustomBaseUrl(String customBaseUrl);

    okhttp3.Call refreshAccessTokenCall(RefreshTokenRequest refreshTokenRequest, ApiCallback _callback) throws ApiException;

    RefreshTokenResponse refreshAccessToken(RefreshTokenRequest refreshTokenRequest) throws ApiException;

    ApiResponse<RefreshTokenResponse> refreshAccessTokenWithHttpInfo(RefreshTokenRequest refreshTokenRequest) throws ApiException;

    okhttp3.Call refreshAccessTokenAsync(RefreshTokenRequest refreshTokenRequest, ApiCallback<RefreshTokenResponse> _callback) throws ApiException;

    okhttp3.Call revokeRefreshTokenCall(RefreshTokenRequest refreshTokenRequest, ApiCallback _callback) throws ApiException;

    void revokeRefreshToken(RefreshTokenRequest refreshTokenRequest) throws ApiException;

    ApiResponse<Void> revokeRefreshTokenWithHttpInfo(RefreshTokenRequest refreshTokenRequest) throws ApiException;

    okhttp3.Call revokeRefreshTokenAsync(RefreshTokenRequest refreshTokenRequest, ApiCallback<Void> _callback) throws ApiException;

    okhttp3.Call signInCall(UserLogin userLogin, ApiCallback _callback) throws ApiException;

    SuccessLogin signIn(UserLogin userLogin) throws ApiException;

    ApiResponse<SuccessLogin> signInWithHttpInfo(UserLogin userLogin) throws ApiException;

    okhttp3.Call signInAsync(UserLogin userLogin, ApiCallback<SuccessLogin> _callback) throws ApiException;
}
