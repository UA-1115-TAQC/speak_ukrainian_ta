package com.academy.api.api;

import com.academy.api.invoker.ApiCallback;
import com.academy.api.invoker.ApiClient;
import com.academy.api.invoker.ApiException;
import com.academy.api.invoker.ApiResponse;
import com.academy.api.model.ContactType;
import com.academy.api.model.ContactTypeProfile;
import com.academy.api.model.ContactTypeResponse;
import com.academy.api.model.SuccessCreatedContactType;

import java.util.List;

public interface ContactApi {
    ApiClient getApiClient();

    void setApiClient(ApiClient apiClient);

    int getHostIndex();

    void setHostIndex(int hostIndex);

    String getCustomBaseUrl();

    void setCustomBaseUrl(String customBaseUrl);

    okhttp3.Call addContactTypeCall(ContactTypeProfile contactTypeProfile, ApiCallback _callback) throws ApiException;

    SuccessCreatedContactType addContactType(ContactTypeProfile contactTypeProfile) throws ApiException;

    ApiResponse<SuccessCreatedContactType> addContactTypeWithHttpInfo(ContactTypeProfile contactTypeProfile) throws ApiException;

    okhttp3.Call addContactTypeAsync(ContactTypeProfile contactTypeProfile, ApiCallback<SuccessCreatedContactType> _callback) throws ApiException;

    okhttp3.Call deleteContactTypeCall(Long id, ApiCallback _callback) throws ApiException;

    ContactTypeResponse deleteContactType(Long id) throws ApiException;

    ApiResponse<ContactTypeResponse> deleteContactTypeWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call deleteContactTypeAsync(Long id, ApiCallback<ContactTypeResponse> _callback) throws ApiException;

    okhttp3.Call getContactTypeCall(Long id, ApiCallback _callback) throws ApiException;

    ContactType getContactType(Long id) throws ApiException;

    ApiResponse<ContactType> getContactTypeWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call getContactTypeAsync(Long id, ApiCallback<ContactType> _callback) throws ApiException;

    okhttp3.Call getContactTypesCall(ApiCallback _callback) throws ApiException;

    List<ContactTypeResponse> getContactTypes() throws ApiException;

    ApiResponse<List<ContactTypeResponse>> getContactTypesWithHttpInfo() throws ApiException;

    okhttp3.Call getContactTypesAsync(ApiCallback<List<ContactTypeResponse>> _callback) throws ApiException;

    okhttp3.Call updateContactTypeCall(Long id, ContactTypeProfile contactTypeProfile, ApiCallback _callback) throws ApiException;

    ContactTypeProfile updateContactType(Long id, ContactTypeProfile contactTypeProfile) throws ApiException;

    ApiResponse<ContactTypeProfile> updateContactTypeWithHttpInfo(Long id, ContactTypeProfile contactTypeProfile) throws ApiException;

    okhttp3.Call updateContactTypeAsync(Long id, ContactTypeProfile contactTypeProfile, ApiCallback<ContactTypeProfile> _callback) throws ApiException;
}
