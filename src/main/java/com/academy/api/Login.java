package com.academy.api;

import com.academy.ApiCallback;
import com.academy.ApiClient;
import com.academy.ApiException;
import com.academy.ApiResponse;

public interface Login {
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
