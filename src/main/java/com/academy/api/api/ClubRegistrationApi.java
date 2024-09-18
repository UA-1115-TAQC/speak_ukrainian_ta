package com.academy.api.api;

import com.academy.api.invoker.ApiCallback;
import com.academy.api.invoker.ApiClient;
import com.academy.api.invoker.ApiException;
import com.academy.api.invoker.ApiResponse;
import com.academy.api.model.*;

import java.util.List;

public interface ClubRegistrationApi {
    ApiClient getApiClient();

    void setApiClient(ApiClient apiClient);

    int getHostIndex();

    void setHostIndex(int hostIndex);

    String getCustomBaseUrl();

    void setCustomBaseUrl(String customBaseUrl);

    okhttp3.Call addClubRegistrationCall(ClubRegistrationRequest clubRegistrationRequest, ApiCallback _callback) throws ApiException;

    List<ClubRegistrationResponse> addClubRegistration(ClubRegistrationRequest clubRegistrationRequest) throws ApiException;

    ApiResponse<List<ClubRegistrationResponse>> addClubRegistrationWithHttpInfo(ClubRegistrationRequest clubRegistrationRequest) throws ApiException;

    okhttp3.Call addClubRegistrationAsync(ClubRegistrationRequest clubRegistrationRequest, ApiCallback<List<ClubRegistrationResponse>> _callback) throws ApiException;

    okhttp3.Call addClubRegistrationForUserCall(UserClubRegistrationRequest userClubRegistrationRequest, ApiCallback _callback) throws ApiException;

    UserClubRegistrationResponse addClubRegistrationForUser(UserClubRegistrationRequest userClubRegistrationRequest) throws ApiException;

    ApiResponse<UserClubRegistrationResponse> addClubRegistrationForUserWithHttpInfo(UserClubRegistrationRequest userClubRegistrationRequest) throws ApiException;

    okhttp3.Call addClubRegistrationForUserAsync(UserClubRegistrationRequest userClubRegistrationRequest, ApiCallback<UserClubRegistrationResponse> _callback) throws ApiException;

    okhttp3.Call approveRegistrationCall(Long clubRegistrationId, ApiCallback _callback) throws ApiException;

    RegistrationApprovedSuccess approveRegistration(Long clubRegistrationId) throws ApiException;

    ApiResponse<RegistrationApprovedSuccess> approveRegistrationWithHttpInfo(Long clubRegistrationId) throws ApiException;

    okhttp3.Call approveRegistrationAsync(Long clubRegistrationId, ApiCallback<RegistrationApprovedSuccess> _callback) throws ApiException;

    okhttp3.Call cancelRegistrationCall(Long clubRegistrationId, ApiCallback _callback) throws ApiException;

    RegistrationCanceledSuccess cancelRegistration(Long clubRegistrationId) throws ApiException;

    ApiResponse<RegistrationCanceledSuccess> cancelRegistrationWithHttpInfo(Long clubRegistrationId) throws ApiException;

    okhttp3.Call cancelRegistrationAsync(Long clubRegistrationId, ApiCallback<RegistrationCanceledSuccess> _callback) throws ApiException;

    okhttp3.Call getChildrenByParentIdCall(Long clubId, ApiCallback _callback) throws ApiException;

    List<ChildResponse> getChildrenByParentId(Long clubId) throws ApiException;

    ApiResponse<List<ChildResponse>> getChildrenByParentIdWithHttpInfo(Long clubId) throws ApiException;

    okhttp3.Call getChildrenByParentIdAsync(Long clubId, ApiCallback<List<ChildResponse>> _callback) throws ApiException;

    okhttp3.Call getClubRegistrationsByManagerIdCall(Long managerId, ApiCallback _callback) throws ApiException;

    List<FullClubRegistration> getClubRegistrationsByManagerId(Long managerId) throws ApiException;

    ApiResponse<List<FullClubRegistration>> getClubRegistrationsByManagerIdWithHttpInfo(Long managerId) throws ApiException;

    okhttp3.Call getClubRegistrationsByManagerIdAsync(Long managerId, ApiCallback<List<FullClubRegistration>> _callback) throws ApiException;

    okhttp3.Call getUnapprovedClubRegistrationsByManagerIdCall(Long managerId, ApiCallback _callback) throws ApiException;

    List<UnapprovedClubRegistration> getUnapprovedClubRegistrationsByManagerId(Long managerId) throws ApiException;

    ApiResponse<List<UnapprovedClubRegistration>> getUnapprovedClubRegistrationsByManagerIdWithHttpInfo(Long managerId) throws ApiException;

    okhttp3.Call getUnapprovedClubRegistrationsByManagerIdAsync(Long managerId, ApiCallback<List<UnapprovedClubRegistration>> _callback) throws ApiException;

    okhttp3.Call getUserApplicationsCall(Long userId, ApiCallback _callback) throws ApiException;

    List<FullClubRegistration> getUserApplications(Long userId) throws ApiException;

    ApiResponse<List<FullClubRegistration>> getUserApplicationsWithHttpInfo(Long userId) throws ApiException;

    okhttp3.Call getUserApplicationsAsync(Long userId, ApiCallback<List<FullClubRegistration>> _callback) throws ApiException;

    okhttp3.Call isUserAlreadyRegisteredCall(Long clubId, Long userId, ApiCallback _callback) throws ApiException;

    Boolean isUserAlreadyRegistered(Long clubId, Long userId) throws ApiException;

    ApiResponse<Boolean> isUserAlreadyRegisteredWithHttpInfo(Long clubId, Long userId) throws ApiException;

    okhttp3.Call isUserAlreadyRegisteredAsync(Long clubId, Long userId, ApiCallback<Boolean> _callback) throws ApiException;
}
