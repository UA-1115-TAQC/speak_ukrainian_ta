package com.academy.ui.clubsPage;

import com.academy.ui.components.SwitchPaginationComponent;
import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortTest extends BaseTestRunner {
    private ClubsPage clubsPage;

    @BeforeMethod(description = "Precondition: open clubsPage")
    public void openClubsPageWithAdvancedSearch() {
        clubsPage = homePage.getAdvancedSearchHeaderComponent().clickAdvancedSearchIcon();
    }

    @Test
    @Description("Verify that clubs can be sorted alphabetically (ascending)")
    @Issue("TUA-239")
    public void ascendingAlphabetSortTest(){
        List<String> names = getAllClubsNames();
        List<String> sorted = names.stream().sorted().collect(Collectors.toList());
        assertEquals(names, sorted);
    }

    @Test
    @Description("Verify that clubs can be sorted alphabetically (descending)")
    @Issue("TUA-239")
    public void descendingAlphabetSortTest(){
        clubsPage.getListControl().clickArrowUp();
        List<String> names = getAllClubsNames();
        List<String> sorted = names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertEquals(names, sorted);
    }


    @Step("Get the list of all clubs on the clubsPage")
    private List<String> getAllClubsNames(){
        List<ClubCardComponent> cards = null;
        List<String> clubNames = new ArrayList<>();
        while(true){
            cards = clubsPage.getClubCards();
            for(ClubCardComponent c : cards){
                clubNames.add(c.getClubName());
            }

            SwitchPaginationComponent pagination = clubsPage.getSwitchPagination();
            if(pagination.isNextDisabled()){
                break;
            }
            clubsPage = pagination.clickNext();
        }
        return clubNames;
    }
    
}
