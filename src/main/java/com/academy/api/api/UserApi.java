package com.academy.api.api;

import com.academy.api.invoker.ApiCallback;
import com.academy.api.invoker.ApiClient;
import com.academy.api.invoker.ApiException;
import com.academy.api.invoker.ApiResponse;
import com.academy.api.model.SuccessUpdatedUser;
import com.academy.api.model.UserPasswordUpdate;
import com.academy.api.model.UserResponse;
import com.academy.api.model.UserUpdateProfile;

import java.util.List;

public interface UserApi {
    ApiClient getApiClient();

    void setApiClient(ApiClient apiClient);

    int getHostIndex();

    void setHostIndex(int hostIndex);

    String getCustomBaseUrl();

    void setCustomBaseUrl(String customBaseUrl);

    okhttp3.Call changePassword1Call(Long id, UserPasswordUpdate userPasswordUpdate, ApiCallback _callback) throws ApiException;

    void changePassword1(Long id, UserPasswordUpdate userPasswordUpdate) throws ApiException;

    ApiResponse<Void> changePassword1WithHttpInfo(Long id, UserPasswordUpdate userPasswordUpdate) throws ApiException;

    okhttp3.Call changePassword1Async(Long id, UserPasswordUpdate userPasswordUpdate, ApiCallback<Void> _callback) throws ApiException;

    okhttp3.Call deleteUserCall(Long id, ApiCallback _callback) throws ApiException;

    UserResponse deleteUser(Long id) throws ApiException;

    ApiResponse<UserResponse> deleteUserWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call deleteUserAsync(Long id, ApiCallback<UserResponse> _callback) throws ApiException;

    okhttp3.Call findAllUsersCall(ApiCallback _callback) throws ApiException;

    List<UserResponse> findAllUsers() throws ApiException;

    ApiResponse<List<UserResponse>> findAllUsersWithHttpInfo() throws ApiException;

    okhttp3.Call findAllUsersAsync(ApiCallback<List<UserResponse>> _callback) throws ApiException;

    okhttp3.Call getUserByIdCall(Long id, ApiCallback _callback) throws ApiException;

    UserResponse getUserById(Long id) throws ApiException;

    ApiResponse<UserResponse> getUserByIdWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call getUserByIdAsync(Long id, ApiCallback<UserResponse> _callback) throws ApiException;

    okhttp3.Call getUsersByRoleCall(String role, ApiCallback _callback) throws ApiException;

    List<UserResponse> getUsersByRole(String role) throws ApiException;

    ApiResponse<List<UserResponse>> getUsersByRoleWithHttpInfo(String role) throws ApiException;

    okhttp3.Call getUsersByRoleAsync(String role, ApiCallback<List<UserResponse>> _callback) throws ApiException;

    okhttp3.Call updateUserCall(Long id, UserUpdateProfile userUpdateProfile, ApiCallback _callback) throws ApiException;

    SuccessUpdatedUser updateUser(Long id, UserUpdateProfile userUpdateProfile) throws ApiException;

    ApiResponse<SuccessUpdatedUser> updateUserWithHttpInfo(Long id, UserUpdateProfile userUpdateProfile) throws ApiException;

    okhttp3.Call updateUserAsync(Long id, UserUpdateProfile userUpdateProfile, ApiCallback<SuccessUpdatedUser> _callback) throws ApiException;
}
