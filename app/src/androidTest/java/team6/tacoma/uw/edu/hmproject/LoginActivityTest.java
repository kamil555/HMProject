package team6.tacoma.uw.edu.hmproject;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by Mark on 5/30/2016.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Solo solo;

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testLoginFragmentLoads() {
        boolean fragmentLoaded = solo.searchText("user name");
        assertTrue("Login fragment loaded", fragmentLoaded);
    }

    public void testLoginWorks() {
        solo.enterText(0, "test@.test");
        solo.enterText(1, "fortest");
        solo.clickOnButton("Log In");
        boolean worked = solo.searchText("View My Book");
        assertTrue("Login worked", worked);
    }
}
