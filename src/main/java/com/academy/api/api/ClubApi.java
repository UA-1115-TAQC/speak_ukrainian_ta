package com.academy.api.api;

import com.academy.api.invoker.ApiCallback;
import com.academy.api.invoker.ApiClient;
import com.academy.api.invoker.ApiException;
import com.academy.api.invoker.ApiResponse;
import com.academy.api.model.*;

import java.util.List;

public interface ClubApi {
    ApiClient getApiClient();

    void setApiClient(ApiClient apiClient);

    int getHostIndex();

    void setHostIndex(int hostIndex);

    String getCustomBaseUrl();

    void setCustomBaseUrl(String customBaseUrl);

    okhttp3.Call addClubCall(ClubProfile clubProfile, ApiCallback _callback) throws ApiException;

    SuccessCreatedClub addClub(ClubProfile clubProfile) throws ApiException;

    ApiResponse<SuccessCreatedClub> addClubWithHttpInfo(ClubProfile clubProfile) throws ApiException;

    okhttp3.Call addClubAsync(ClubProfile clubProfile, ApiCallback<SuccessCreatedClub> _callback) throws ApiException;

    okhttp3.Call changeClubOwnerCall(Long id, ClubOwnerProfile clubOwnerProfile, ApiCallback _callback) throws ApiException;

    ClubResponse changeClubOwner(Long id, ClubOwnerProfile clubOwnerProfile) throws ApiException;

    ApiResponse<ClubResponse> changeClubOwnerWithHttpInfo(Long id, ClubOwnerProfile clubOwnerProfile) throws ApiException;

    okhttp3.Call changeClubOwnerAsync(Long id, ClubOwnerProfile clubOwnerProfile, ApiCallback<ClubResponse> _callback) throws ApiException;

    okhttp3.Call deleteClubCall(Long id, ApiCallback _callback) throws ApiException;

    ClubResponse deleteClub(Long id) throws ApiException;

    ApiResponse<ClubResponse> deleteClubWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call deleteClubAsync(Long id, ApiCallback<ClubResponse> _callback) throws ApiException;

    okhttp3.Call getAdvancedSearchClubsCall(AdvancedSearchClubProfile advancedSearchClubProfile, Pageable pageable, ApiCallback _callback) throws ApiException;

    PageClubResponse getAdvancedSearchClubs(AdvancedSearchClubProfile advancedSearchClubProfile, Pageable pageable) throws ApiException;

    ApiResponse<PageClubResponse> getAdvancedSearchClubsWithHttpInfo(AdvancedSearchClubProfile advancedSearchClubProfile, Pageable pageable) throws ApiException;

    okhttp3.Call getAdvancedSearchClubsAsync(AdvancedSearchClubProfile advancedSearchClubProfile, Pageable pageable, ApiCallback<PageClubResponse> _callback) throws ApiException;

    okhttp3.Call getClubByIdCall(Long id, ApiCallback _callback) throws ApiException;

    ClubResponse getClubById(Long id) throws ApiException;

    ApiResponse<ClubResponse> getClubByIdWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call getClubByIdAsync(Long id, ApiCallback<ClubResponse> _callback) throws ApiException;

    okhttp3.Call getClubByNameCall(String name, ApiCallback _callback) throws ApiException;

    ClubResponse getClubByName(String name) throws ApiException;

    ApiResponse<ClubResponse> getClubByNameWithHttpInfo(String name) throws ApiException;

    okhttp3.Call getClubByNameAsync(String name, ApiCallback<ClubResponse> _callback) throws ApiException;

    okhttp3.Call getClubsCall(ApiCallback _callback) throws ApiException;

    List<ClubResponse> getClubs() throws ApiException;

    ApiResponse<List<ClubResponse>> getClubsWithHttpInfo() throws ApiException;

    okhttp3.Call getClubsAsync(ApiCallback<List<ClubResponse>> _callback) throws ApiException;

    okhttp3.Call getClubsByCategoryAndCityCall(SearchClubProfile searchClubProfile, ApiCallback _callback) throws ApiException;

    List<ClubResponse> getClubsByCategoryAndCity(SearchClubProfile searchClubProfile) throws ApiException;

    ApiResponse<List<ClubResponse>> getClubsByCategoryAndCityWithHttpInfo(SearchClubProfile searchClubProfile) throws ApiException;

    okhttp3.Call getClubsByCategoryAndCityAsync(SearchClubProfile searchClubProfile, ApiCallback<List<ClubResponse>> _callback) throws ApiException;

    okhttp3.Call getClubsByCenterIdCall(Long id, ApiCallback _callback) throws ApiException;

    List<ClubResponse> getClubsByCenterId(Long id) throws ApiException;

    ApiResponse<List<ClubResponse>> getClubsByCenterIdWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call getClubsByCenterIdAsync(Long id, ApiCallback<List<ClubResponse>> _callback) throws ApiException;

    okhttp3.Call getClubsByUserIdCall(Long id, Pageable pageable, ApiCallback _callback) throws ApiException;

    PageClubResponse getClubsByUserId(Long id, Pageable pageable) throws ApiException;

    ApiResponse<PageClubResponse> getClubsByUserIdWithHttpInfo(Long id, Pageable pageable) throws ApiException;

    okhttp3.Call getClubsByUserIdAsync(Long id, Pageable pageable, ApiCallback<PageClubResponse> _callback) throws ApiException;

    okhttp3.Call getClubsListOfClubsCall(SearchClubProfile searchClubProfile, Pageable pageable, Long userId, ApiCallback _callback) throws ApiException;

    PageClubResponse getClubsListOfClubs(SearchClubProfile searchClubProfile, Pageable pageable, Long userId) throws ApiException;

    ApiResponse<PageClubResponse> getClubsListOfClubsWithHttpInfo(SearchClubProfile searchClubProfile, Pageable pageable, Long userId) throws ApiException;

    okhttp3.Call getClubsListOfClubsAsync(SearchClubProfile searchClubProfile, Pageable pageable, Long userId, ApiCallback<PageClubResponse> _callback) throws ApiException;

    okhttp3.Call getClubsWithoutCategoriesCall(Pageable pageable, ApiCallback _callback) throws ApiException;

    PageClubResponse getClubsWithoutCategories(Pageable pageable) throws ApiException;

    ApiResponse<PageClubResponse> getClubsWithoutCategoriesWithHttpInfo(Pageable pageable) throws ApiException;

    okhttp3.Call getClubsWithoutCategoriesAsync(Pageable pageable, ApiCallback<PageClubResponse> _callback) throws ApiException;

    okhttp3.Call getListClubsByUserIdCall(Long id, ApiCallback _callback) throws ApiException;

    List<ClubResponse> getListClubsByUserId(Long id) throws ApiException;

    ApiResponse<List<ClubResponse>> getListClubsByUserIdWithHttpInfo(Long id) throws ApiException;

    okhttp3.Call getListClubsByUserIdAsync(Long id, ApiCallback<List<ClubResponse>> _callback) throws ApiException;

    okhttp3.Call getSimilarClubsCall(SimilarClubProfile similarClubProfile, ApiCallback _callback) throws ApiException;

    List<ClubResponse> getSimilarClubs(SimilarClubProfile similarClubProfile) throws ApiException;

    ApiResponse<List<ClubResponse>> getSimilarClubsWithHttpInfo(SimilarClubProfile similarClubProfile) throws ApiException;

    okhttp3.Call getSimilarClubsAsync(SimilarClubProfile similarClubProfile, ApiCallback<List<ClubResponse>> _callback) throws ApiException;

    okhttp3.Call getTopClubsByCityCall(TopClubProfile topClubProfile, ApiCallback _callback) throws ApiException;

    List<ClubResponse> getTopClubsByCity(TopClubProfile topClubProfile) throws ApiException;

    ApiResponse<List<ClubResponse>> getTopClubsByCityWithHttpInfo(TopClubProfile topClubProfile) throws ApiException;

    okhttp3.Call getTopClubsByCityAsync(TopClubProfile topClubProfile, ApiCallback<List<ClubResponse>> _callback) throws ApiException;

    okhttp3.Call patchClubCall(Long id, ClubResponse clubResponse, ApiCallback _callback) throws ApiException;

    SuccessUpdatedClub patchClub(Long id, ClubResponse clubResponse) throws ApiException;

    ApiResponse<SuccessUpdatedClub> patchClubWithHttpInfo(Long id, ClubResponse clubResponse) throws ApiException;

    okhttp3.Call patchClubAsync(Long id, ClubResponse clubResponse, ApiCallback<SuccessUpdatedClub> _callback) throws ApiException;

    okhttp3.Call updateClubCall(Long id, ClubResponse clubResponse, ApiCallback _callback) throws ApiException;

    SuccessUpdatedClub updateClub(Long id, ClubResponse clubResponse) throws ApiException;

    ApiResponse<SuccessUpdatedClub> updateClubWithHttpInfo(Long id, ClubResponse clubResponse) throws ApiException;

    okhttp3.Call updateClubAsync(Long id, ClubResponse clubResponse, ApiCallback<SuccessUpdatedClub> _callback) throws ApiException;

    okhttp3.Call updateClubsRatingCall(ApiCallback _callback) throws ApiException;

    List<ClubResponse> updateClubsRating() throws ApiException;

    ApiResponse<List<ClubResponse>> updateClubsRatingWithHttpInfo() throws ApiException;

    okhttp3.Call updateClubsRatingAsync(ApiCallback<List<ClubResponse>> _callback) throws ApiException;

    okhttp3.Call updateContactsCall(ApiCallback _callback) throws ApiException;

    void updateContacts() throws ApiException;

    ApiResponse<Void> updateContactsWithHttpInfo() throws ApiException;

    okhttp3.Call updateContactsAsync(ApiCallback<Void> _callback) throws ApiException;
}
