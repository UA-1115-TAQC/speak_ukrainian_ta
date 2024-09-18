package com.academy.api.api;

import com.academy.api.invoker.ApiCallback;
import com.academy.api.invoker.ApiClient;
import com.academy.api.invoker.ApiException;
import com.academy.api.invoker.ApiResponse;
import com.academy.api.model.AboutUsItemProfile;
import com.academy.api.model.AboutUsItemResponse;
import com.academy.api.model.NumberDto;

import java.util.List;

public interface AboutUsApi {
    ApiClient getApiClient();

    void setApiClient(ApiClient apiClient);

    int getHostIndex();

    void setHostIndex(int hostIndex);

    String getCustomBaseUrl();

    void setCustomBaseUrl(String customBaseUrl);

    okhttp3.Call addAboutUsItemCall(AboutUsItemProfile aboutUsItemProfile, ApiCallback _callback) throws ApiException;

    AboutUsItemResponse addAboutUsItem(AboutUsItemProfile aboutUsItemProfile) throws ApiException;

    ApiResponse<AboutUsItemResponse> addAboutUsItemWithHttpInfo(AboutUsItemProfile aboutUsItemProfile) throws ApiException;

    okhttp3.Call addAboutUsItemAsync(AboutUsItemProfile aboutUsItemProfile, ApiCallback<AboutUsItemResponse> _callback) throws ApiException;

    okhttp3.Call changeOrderCall(Long id, NumberDto numberDto, ApiCallback _callback) throws ApiException;

    String changeOrder(Long id, NumberDto numberDto) throws ApiException;

    ApiResponse<String> changeOrderWithHttpInfo(Long id, NumberDto numberDto) throws ApiException;

    okhttp3.Call changeOrderAsync(Long id, NumberDto numberDto, ApiCallback<String> _callback) throws ApiException;

    okhttp3.Call deleteAboutUsItemCall(Long id, ApiCallback _callback) throws ApiException;

    AboutUsItemResponse deleteAboutUsItem(Long id) throws ApiException;

    ApiResponse<AboutUsItemResponse> deleteAboutUsItemWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call deleteAboutUsItemAsync(Long id, ApiCallback<AboutUsItemResponse> _callback) throws ApiException;

    okhttp3.Call getAboutUsItemsCall(ApiCallback _callback) throws ApiException;

    List<AboutUsItemResponse> getAboutUsItems() throws ApiException;

    ApiResponse<List<AboutUsItemResponse>> getAboutUsItemsWithHttpInfo() throws ApiException;

    okhttp3.Call getAboutUsItemsAsync(ApiCallback<List<AboutUsItemResponse>> _callback) throws ApiException;

    okhttp3.Call getAboutUsItems1Call(Long id, ApiCallback _callback) throws ApiException;

    AboutUsItemResponse getAboutUsItems1(Long id) throws ApiException;

    ApiResponse<AboutUsItemResponse> getAboutUsItems1WithHttpInfo(Long id) throws ApiException;

    okhttp3.Call getAboutUsItems1Async(Long id, ApiCallback<AboutUsItemResponse> _callback) throws ApiException;

    okhttp3.Call updateAboutUsItemCall(Long id, AboutUsItemProfile aboutUsItemProfile, ApiCallback _callback) throws ApiException;

    AboutUsItemResponse updateAboutUsItem(Long id, AboutUsItemProfile aboutUsItemProfile) throws ApiException;

    ApiResponse<AboutUsItemResponse> updateAboutUsItemWithHttpInfo(Long id, AboutUsItemProfile aboutUsItemProfile) throws ApiException;

    okhttp3.Call updateAboutUsItemAsync(Long id, AboutUsItemProfile aboutUsItemProfile, ApiCallback<AboutUsItemResponse> _callback) throws ApiException;
}
