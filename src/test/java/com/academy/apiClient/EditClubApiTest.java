package com.academy.apiClient;

import com.academy.api.ApiClientProxy;
import com.academy.api.api.ClubApi;
import com.academy.api.api.impl.ContactApiImpl;
import com.academy.api.api.impl.ClubApiImpl;
import com.academy.api.invoker.ApiException;
import com.academy.api.model.ClubResponse;
import com.academy.api.model.ContactDataResponse;
import com.academy.apiClient.runners.LoginWithManagerAPIClientTestRunner;
import java.util.Set;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditClubApiTest extends LoginWithManagerAPIClientTestRunner {
    private SoftAssert softAssert;
    //This is ClubApi Proxy object
    private ClubApi clubApi;

    @BeforeClass
    public void beforeClass() {
        //proxy initialization
        ClubApi club = new ClubApiImpl(apiClient);
        clubApi = (ClubApi) ApiClientProxy.getProxy(club);
    }

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
    }

    @Test
    @Description("Vefiry that user cannot save 'Контакти' fields with invalid data (for club that is not in the center)")
    @Issue("TUA-977")
    public void edit_Club_Contacts_With_Incorrect_data() throws ApiException {
        final String incorrectPhone = "05045636583653";
        final String incorrectEmail = "fjhujkdj@@ha.com";

        ContactApiImpl contactApi = new ContactApiImpl(apiClient);
        var contactPhone = contactApi.getContactType(1L);
        var contactEmail = contactApi.getContactType(4L);

        var incorrectContactData = Set.of(
                new ContactDataResponse()
                        .contactType(contactPhone)
                        .contactData(incorrectPhone),
                new ContactDataResponse()
                        .contactType(contactEmail)
                        .contactData(incorrectEmail)
        );

        ClubResponse club = clubApi.getListClubsByUserId(userId).getFirst();
        Set<ContactDataResponse> initialContacts = club.getContacts();

        club = club.contacts(incorrectContactData);
        softAssert.assertEquals(clubApi.updateClubWithHttpInfo(club.getId(), club)
                        .getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Response status code should be %s"
                        .formatted(HttpStatus.SC_BAD_REQUEST));

        club = clubApi.getClubById(club.getId());
        softAssert.assertFalse(club.getContacts()
                .containsAll(incorrectContactData), """
                Club should not contain phone '%s' and/or email '%s'"""
                .formatted(incorrectPhone, incorrectEmail));

        club = club.contacts(initialContacts);
        clubApi.updateClub(club.getId(), club);
        softAssert.assertAll();
    }
}
