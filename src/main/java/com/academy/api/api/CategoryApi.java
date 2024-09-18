package com.academy.api.api;

import com.academy.api.invoker.ApiCallback;
import com.academy.api.invoker.ApiClient;
import com.academy.api.invoker.ApiException;
import com.academy.api.invoker.ApiResponse;
import com.academy.api.model.*;

import java.util.List;

public interface CategoryApi {
    ApiClient getApiClient();

    void setApiClient(ApiClient apiClient);

    int getHostIndex();

    void setHostIndex(int hostIndex);

    String getCustomBaseUrl();

    void setCustomBaseUrl(String customBaseUrl);

    okhttp3.Call addCategoryCall(CategoryProfile categoryProfile, ApiCallback _callback) throws ApiException;

    SuccessCreatedCategory addCategory(CategoryProfile categoryProfile) throws ApiException;

    ApiResponse<SuccessCreatedCategory> addCategoryWithHttpInfo(CategoryProfile categoryProfile) throws ApiException;

    okhttp3.Call addCategoryAsync(CategoryProfile categoryProfile, ApiCallback<SuccessCreatedCategory> _callback) throws ApiException;

    okhttp3.Call deleteCategoryCall(Long id, ApiCallback _callback) throws ApiException;

    CategoryResponse deleteCategory(Long id) throws ApiException;

    ApiResponse<CategoryResponse> deleteCategoryWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call deleteCategoryAsync(Long id, ApiCallback<CategoryResponse> _callback) throws ApiException;

    okhttp3.Call getAllCategoriesCall(ApiCallback _callback) throws ApiException;

    List<CategoryResponse> getAllCategories() throws ApiException;

    ApiResponse<List<CategoryResponse>> getAllCategoriesWithHttpInfo() throws ApiException;

    okhttp3.Call getAllCategoriesAsync(ApiCallback<List<CategoryResponse>> _callback) throws ApiException;

    okhttp3.Call getCategoryCall(Long id, ApiCallback _callback) throws ApiException;

    CategoryResponse getCategory(Long id) throws ApiException;

    ApiResponse<CategoryResponse> getCategoryWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call getCategoryAsync(Long id, ApiCallback<CategoryResponse> _callback) throws ApiException;

    okhttp3.Call getListOfCategoriesCall(Pageable pageable, ApiCallback _callback) throws ApiException;

    PageCategoryResponse getListOfCategories(Pageable pageable) throws ApiException;

    ApiResponse<PageCategoryResponse> getListOfCategoriesWithHttpInfo(Pageable pageable) throws ApiException;

    okhttp3.Call getListOfCategoriesAsync(Pageable pageable, ApiCallback<PageCategoryResponse> _callback) throws ApiException;

    okhttp3.Call updateCategoryCall(Long id, CategoryProfile categoryProfile, ApiCallback _callback) throws ApiException;

    CategoryProfile updateCategory(Long id, CategoryProfile categoryProfile) throws ApiException;

    ApiResponse<CategoryProfile> updateCategoryWithHttpInfo(Long id, CategoryProfile categoryProfile) throws ApiException;

    okhttp3.Call updateCategoryAsync(Long id, CategoryProfile categoryProfile, ApiCallback<CategoryProfile> _callback) throws ApiException;
}
