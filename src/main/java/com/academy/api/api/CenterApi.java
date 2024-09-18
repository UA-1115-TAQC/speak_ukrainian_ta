package com.academy.api.api;

import com.academy.api.invoker.ApiCallback;
import com.academy.api.invoker.ApiClient;
import com.academy.api.invoker.ApiException;
import com.academy.api.invoker.ApiResponse;
import com.academy.api.model.*;

import java.util.List;

public interface CenterApi {
    ApiClient getApiClient();

    void setApiClient(ApiClient apiClient);

    int getHostIndex();

    void setHostIndex(int hostIndex);

    String getCustomBaseUrl();

    void setCustomBaseUrl(String customBaseUrl);

    okhttp3.Call addCenterCall(CenterProfile centerProfile, ApiCallback _callback) throws ApiException;

    SuccessCreatedCenter addCenter(CenterProfile centerProfile) throws ApiException;

    ApiResponse<SuccessCreatedCenter> addCenterWithHttpInfo(CenterProfile centerProfile) throws ApiException;

    okhttp3.Call addCenterAsync(CenterProfile centerProfile, ApiCallback<SuccessCreatedCenter> _callback) throws ApiException;

    okhttp3.Call deleteCenterCall(Long id, ApiCallback _callback) throws ApiException;

    CenterResponse deleteCenter(Long id) throws ApiException;

    ApiResponse<CenterResponse> deleteCenterWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call deleteCenterAsync(Long id, ApiCallback<CenterResponse> _callback) throws ApiException;

    okhttp3.Call getAdvancedSearchClubs1Call(AdvancedSearchCenterProfile advancedSearchCenterProfile, Pageable pageable, ApiCallback _callback) throws ApiException;

    PageCenterResponse getAdvancedSearchClubs1(AdvancedSearchCenterProfile advancedSearchCenterProfile, Pageable pageable) throws ApiException;

    ApiResponse<PageCenterResponse> getAdvancedSearchClubs1WithHttpInfo(AdvancedSearchCenterProfile advancedSearchCenterProfile, Pageable pageable) throws ApiException;

    okhttp3.Call getAdvancedSearchClubs1Async(AdvancedSearchCenterProfile advancedSearchCenterProfile, Pageable pageable, ApiCallback<PageCenterResponse> _callback) throws ApiException;

    okhttp3.Call getCenterCall(Long id, ApiCallback _callback) throws ApiException;

    CenterResponse getCenter(Long id) throws ApiException;

    ApiResponse<CenterResponse> getCenterWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call getCenterAsync(Long id, ApiCallback<CenterResponse> _callback) throws ApiException;

    okhttp3.Call getCenterClubsByCenterIdCall(Long id, Integer size, Pageable pageable, ApiCallback _callback) throws ApiException;

    PageClubResponse getCenterClubsByCenterId(Long id, Integer size, Pageable pageable) throws ApiException;

    ApiResponse<PageClubResponse> getCenterClubsByCenterIdWithHttpInfo(Long id, Integer size, Pageable pageable) throws ApiException;

    okhttp3.Call getCenterClubsByCenterIdAsync(Long id, Integer size, Pageable pageable, ApiCallback<PageClubResponse> _callback) throws ApiException;

    okhttp3.Call getCentersCall(ApiCallback _callback) throws ApiException;

    List<CenterResponse> getCenters() throws ApiException;

    ApiResponse<List<CenterResponse>> getCentersWithHttpInfo() throws ApiException;

    okhttp3.Call getCentersAsync(ApiCallback<List<CenterResponse>> _callback) throws ApiException;

    okhttp3.Call getCentersByUserIdCall(Long id, Pageable pageable, ApiCallback _callback) throws ApiException;

    PageCenterResponse getCentersByUserId(Long id, Pageable pageable) throws ApiException;

    ApiResponse<PageCenterResponse> getCentersByUserIdWithHttpInfo(Long id, Pageable pageable) throws ApiException;

    okhttp3.Call getCentersByUserIdAsync(Long id, Pageable pageable, ApiCallback<PageCenterResponse> _callback) throws ApiException;

    okhttp3.Call updateCenterCall(Long id, CenterProfile centerProfile, ApiCallback _callback) throws ApiException;

    CenterProfile updateCenter(Long id, CenterProfile centerProfile) throws ApiException;

    ApiResponse<CenterProfile> updateCenterWithHttpInfo(Long id, CenterProfile centerProfile) throws ApiException;

    okhttp3.Call updateCenterAsync(Long id, CenterProfile centerProfile, ApiCallback<CenterProfile> _callback) throws ApiException;

    okhttp3.Call updateCentersRatingCall(ApiCallback _callback) throws ApiException;

    List<CenterResponse> updateCentersRating() throws ApiException;

    ApiResponse<List<CenterResponse>> updateCentersRatingWithHttpInfo() throws ApiException;

    okhttp3.Call updateCentersRatingAsync(ApiCallback<List<CenterResponse>> _callback) throws ApiException;
}
