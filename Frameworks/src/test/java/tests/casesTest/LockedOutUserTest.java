package tests.casesTest;

import constants.Constant;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static constants.Constant.urls.TEST_CASES_HOME_PAGE;

public class LockedOutUserTest extends BaseTest {

    @Test
    public void checkIsRedirectToListing () {
        basePage.open(TEST_CASES_HOME_PAGE);
        cases_home_page.enterUserData(Constant.userData.LOGIN2,
                Constant.userData.PASS);

        casesListingPage.doAllActions();
    }
}
