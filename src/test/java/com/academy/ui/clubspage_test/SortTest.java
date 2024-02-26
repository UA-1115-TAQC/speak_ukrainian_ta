package com.academy.ui.clubspage_test;

import com.academy.ui.components.SwitchPaginationComponent;
import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortTest extends BaseTestRunner {
    private ClubsPage clubsPage;

    @BeforeMethod
    public void openClubsPageWithAdvancedSearch() {
        clubsPage = homePage.getAdvancedSearchHeaderComponent().clickAdvancedSearchIcon();
    }

    @Test
    public void ascendingAlphabetSortTest(){
        List<String> names = getAllClubsNames();
        List<String> sorted = names.stream().sorted().collect(Collectors.toList());
        assertEquals(names, sorted);
    }

    @Test
    public void descendingAlphabetSortTest(){
        clubsPage.getListControl().clickArrowUp();
        List<String> names = getAllClubsNames();
        List<String> sorted = names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertEquals(names, sorted);
    }

    private List<String> getAllClubsNames(){
        List<ClubCardComponent> cards = null;
        List<String> clubNames = new ArrayList<>();
        while(true){
            cards = clubsPage.getClubCards();
            for(ClubCardComponent c : cards){
                String name = c.getClubName();
//                System.out.println(name);
                clubNames.add(name);
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
