package com.academy.api.api;

import com.academy.api.invoker.ApiCallback;
import com.academy.api.invoker.ApiClient;
import com.academy.api.invoker.ApiException;
import com.academy.api.invoker.ApiResponse;
import com.academy.api.model.CityProfile;
import com.academy.api.model.CityResponse;
import com.academy.api.model.SuccessCreatedCity;

import java.util.List;

public interface CityApi {
    ApiClient getApiClient();

    void setApiClient(ApiClient apiClient);

    int getHostIndex();

    void setHostIndex(int hostIndex);

    String getCustomBaseUrl();

    void setCustomBaseUrl(String customBaseUrl);

    okhttp3.Call addCityCall(CityProfile cityProfile, ApiCallback _callback) throws ApiException;

    SuccessCreatedCity addCity(CityProfile cityProfile) throws ApiException;

    ApiResponse<SuccessCreatedCity> addCityWithHttpInfo(CityProfile cityProfile) throws ApiException;

    okhttp3.Call addCityAsync(CityProfile cityProfile, ApiCallback<SuccessCreatedCity> _callback) throws ApiException;

    okhttp3.Call deleteCityCall(Long id, ApiCallback _callback) throws ApiException;

    CityResponse deleteCity(Long id) throws ApiException;

    ApiResponse<CityResponse> deleteCityWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call deleteCityAsync(Long id, ApiCallback<CityResponse> _callback) throws ApiException;

    okhttp3.Call getCitiesCall(ApiCallback _callback) throws ApiException;

    List<CityResponse> getCities() throws ApiException;

    ApiResponse<List<CityResponse>> getCitiesWithHttpInfo() throws ApiException;

    okhttp3.Call getCitiesAsync(ApiCallback<List<CityResponse>> _callback) throws ApiException;

    okhttp3.Call getCityCall(Long id, ApiCallback _callback) throws ApiException;

    CityResponse getCity(Long id) throws ApiException;

    ApiResponse<CityResponse> getCityWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call getCityAsync(Long id, ApiCallback<CityResponse> _callback) throws ApiException;

    okhttp3.Call updateCityCall(Long id, CityProfile cityProfile, ApiCallback _callback) throws ApiException;

    CityProfile updateCity(Long id, CityProfile cityProfile) throws ApiException;

    ApiResponse<CityProfile> updateCityWithHttpInfo(Long id, CityProfile cityProfile) throws ApiException;

    okhttp3.Call updateCityAsync(Long id, CityProfile cityProfile, ApiCallback<CityProfile> _callback) throws ApiException;
}
