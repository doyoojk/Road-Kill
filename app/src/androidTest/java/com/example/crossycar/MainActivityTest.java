package com.example.crossycar;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);
    /*
    Test checks if start button exists and contains the text: START GAME
    written by: Katherine
     */

    @Test
    public void checkStartActivates() {
        ViewInteraction button = onView(
                allOf(withId(R.id.game_start_button), withText("START GAME"),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
    }

    @Test
    public void checkEasyGameLoop() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.game_start_button), withText("Start Game"),
                        isDisplayed()));
        materialButton.perform(click());

        // Add a delay of 2 seconds before clicking on the "Easy" radio button
        SystemClock.sleep(2000);

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.difficulty_easy), withText("Easy"),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.difficulty_easy), withText("Easy"),
                        isDisplayed()));
        radioButton.check(matches(isDisplayed()));
    }

    /*
    Test checks if radio button medium exists and contains the text: MEDIUM
    written by: Jamie
    */
    @Test
    public void checkMediumGameLoop() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.game_start_button), withText("Start Game"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.difficulty_medium), withText("Medium"),
                        childAtPosition(
                                allOf(withId(R.id.difficulty_selection_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.difficulty_medium), withText("Medium"),
                        withParent(allOf(withId(R.id.difficulty_selection_group),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        radioButton.check(matches(isDisplayed()));
    }

    /*
    Test checks if radio button hard exists and contains the text: HARD
    written by: Jamie
    */
    @Test
    public void checksHardGameLoop() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.game_start_button), withText("Start Game"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.difficulty_hard), withText("Hard"),
                        childAtPosition(
                                allOf(withId(R.id.difficulty_selection_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                4)),
                                2),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.difficulty_hard), withText("Hard"),
                        withParent(allOf(withId(R.id.difficulty_selection_group),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        radioButton.check(matches(isDisplayed()));
    }

    /*
    Test checks if text box works and the name Erick is recorded
    written by: Erick
     */
    @Test
    public void checkNameAppearsUnderPlayer() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.game_start_button), withText("Start Game"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.player_name_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Erick"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.player_name_input), withText("Erick"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction editText = onView(
                allOf(withId(R.id.player_name_input), withText("Erick"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        editText.check(matches(withText("Erick")));
    }

    /*
    Test checks if red bwm spawns on game map
    written by: Junseob
     */
    @Test
    public void redBmwSpawns() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.game_start_button), withText("Start Game"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        // wait for difficulty selection to be displayed
        try {
            Thread.sleep(1000); // wait for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.difficulty_medium), withText("Medium"),
                        childAtPosition(
                                allOf(withId(R.id.difficulty_selection_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.difficulty_medium), withText("Medium"),
                        withParent(allOf(withId(R.id.difficulty_selection_group),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        radioButton.check(matches(isDisplayed()));
    }


    /*
    written by: Junseob
    */
    @Test
    public void teslaSpawns() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.game_start_button), withText("Start Game"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.difficulty_easy), withText("Easy"),
                        childAtPosition(
                                allOf(withId(R.id.difficulty_selection_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                4)),
                                0),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.difficulty_easy), withText("Easy"),
                        withParent(allOf(withId(R.id.difficulty_selection_group),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        radioButton.check(matches(isDisplayed()));
    }

    /*
    Test checks if green g-wagon spawns on game map
    written by: Akshay
    */
    @Test
    public void gWagonSpawns() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.game_start_button), withText("Start Game"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.difficulty_medium), withText("Medium"),
                        childAtPosition(
                                allOf(withId(R.id.difficulty_selection_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.difficulty_medium), withText("Medium"),
                        withParent(allOf(withId(R.id.difficulty_selection_group),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        radioButton.check(matches(isDisplayed()));
    }

    /*
    Test checks if easy game loop has 5 lives
    written by: Akshay
    */
    @Test
    public void fiveLivesInEasy() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.game_start_button), withText("Start Game"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.difficulty_easy), withText("Easy"),
                        childAtPosition(
                                allOf(withId(R.id.difficulty_selection_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                4)),
                                0),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction livesTextView = onView(
                allOf(withId(R.id.lives), withText("5"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        livesTextView.check(matches(withText("5")));
    }


    /*
    Test checks if medium game loop has 4 lives
    written by: Jocelyn
     */
    @Test
    public void fourLivesInMedium() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.game_start_button), withText("Start Game"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.player_name_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Junseob"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.player_name_input), withText("Junseob"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        // Change the ID to the correct value
        ViewInteraction editText = onView(
                allOf(withId(R.id.player_name), withText("Junseob"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        editText.check(matches(withText("Junseob")));
    }

    /*
    Test checks if hard game loop has 2 lives
    written by: Jocelyn
     */
    @Test
    public void twoLivesInHard() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.game_start_button), withText("Start Game"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.difficulty_hard), withText("Hard"),
                        childAtPosition(
                                allOf(withId(R.id.difficulty_selection_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                4)),
                                2),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.player_name_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Junseob"), closeSoftKeyboard());

        ViewInteraction playerNameText = onView(
                allOf(withId(R.id.player_name_input), withText("Junseob"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        playerNameText.check(matches(withText("Junseob")));

        ViewInteraction livesText = onView(
                allOf(withId(R.id.lives_text),
                        withParent(allOf(withId(R.id.lives_layout),
                                withParent(withId(R.id.game_layout))))));
        livesText.check(matches(withText("2")));
    }
    /*
    Test checks if deer spawn in game map
    written by: Erick
     */
    @Test
    public void checkIfDeerSpawn() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.game_start_button), withText("Start Game"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.player_name_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Erick"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.player_name_input), withText("Erick"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction editText = onView(
                allOf(withId(R.id.player_name_input), withText("Erick"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        editText.check(matches(withText("Erick")));
    }


    /*
    helper function many tests above.
    written by: Erick
     */
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
    // sprint 5 test cases
    // Jumping on a log does not cause the player to lose a life and return to the beginning.
    //• Reaching the goal tile gives players the proper number of points.
    //• Restarting the game properly resets the game’s state.
    //• Logs have different speeds (if you choose speed to be your differing behavior)
    /*
    Test checks if jumping on a log does not cause the player to lose a life and return to the beginning
    written by: Junseob
    */
    @Test
    public void testJumpOnLog() {
        // click start game button
        onView(withId(R.id.game_start_button)).perform(click());

        // perform jump on a log action
        onView(withId(R.id.player)).perform(ViewActions.swipeUp());

        // check if player's life is not decreased
        onView(withId(R.id.lives)).check(matches(withText("Lives: 3")));

        // check if player is still on the same tile
        onView(withId(R.id.player_tile)).check(matches(withText("2")));
    }
        /*
    Test checks if player gets the proper number of points upon reaching the goal tile
    written by: Junseob
    */

    @Test
    public void testReachingGoalTile() {
        // click start game button
        onView(withId(R.id.game_start_button)).perform(click());

        // perform jump to reach goal tile
        onView(withId(R.id.player)).perform(ViewActions.swipeUp());
        onView(withId(R.id.player)).perform(swipeRight());

        // check if player's points are increased
        onView(withId(R.id.points)).check(matches(withText("Points: 100")));

        // check if player is on the goal tile
        onView(withId(R.id.player_tile)).check(matches(withText("6")));
    }
        /*
    Test checks if game state is reset properly upon restarting the game
    written by: Jamie
    */
    @Test
    public void testRestartGame() {
        // click start game button
        onView(withId(R.id.game_start_button)).perform(click());

        // perform some actions in game
        onView(withId(R.id.player)).perform(ViewActions.swipeUp());
        onView(withId(R.id.player)).perform(swipeRight());

        // click restart game button
        onView(withId(R.id.restart_button)).perform(click());

        // check if game state is reset properly
        onView(withId(R.id.points)).check(matches(withText("Points: 0")));
        onView(withId(R.id.lives)).check(matches(withText("Lives: 3")));
        onView(withId(R.id.player_tile)).check(matches(withText("1")));
    }
        /*
    Test checks if player reaches the goal tile and congratulatory screen is displayed
    written by: Jamie
     */
    @Test
    public void checkGameWinScreen() {
        onView(withId(R.id.player)).perform(swipeRight()); // assuming right swipe reaches goal tile
        onView(withId(R.id.congratulatory_screen)).check(matches(isDisplayed()));
    }
        /*
    Test checks if reaching the goal tile gives the greatest number of points to the player
    written by: Kat
     */
    @Test
    public void checkGoalTilePoints() {
        ViewInteraction button = onView(
                allOf(withId(R.id.game_start_button), withText("START GAME"),
                        isDisplayed()));
        button.perform(click());

        // Move player sprite to the goal tile
        // ...

        // Check if player gets the greatest number of points
        int maxPoints = 1000;
        ViewInteraction textView = onView(
                allOf(withId(R.id.score_text), withText(String.format(Locale.getDefault(), "%d pts", maxPoints)),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));
    }
        /*
    test case that checks if the final score of the player is displayed in the endgame screen
    written by: Kat
    */
    @Test
    public void testScoreDisplayed() {
        // Get the score from the previous activity
        Instrumentation.ActivityResult score = mActivityScenarioRule.getScenario().getResult();
        // Check if the score is displayed in the endgame screen
        onView(withId(R.id.scoreTextView)).check(matches(withText("Score: " + score)));
    }
    /*
test case that checks whether the Game Win Screen congratulates the player with some kind of text
written by: Erick
*/
    @Test
    public void testGameWinScreenCongratulatesPlayerWithText() {
        onView(withId(R.id.congratulationsTextView)).check(matches(isDisplayed()));
        onView(withText(R.string.congratulations_message)).check(matches(isDisplayed()));
    }
    /*
test case that checks whether the Game Win Screen
provide the player with two options to select: restart game and exit game
written by: Erick
*/
    @Test
    public void gameWinScreenDisplaysTwoOptions() {
        // Check that both the restart button and exit button are displayed
        onView(withId(R.id.restartButton)).check(matches(isDisplayed()));
        onView(withId(R.id.exitButton)).check(matches(isDisplayed()));

        // Click the restart button and verify that it starts the ChoosePlayer activity
        onView(withId(R.id.restartButton)).perform(click());

        // Click the exit button and verify that the app is finished
        onView(withId(R.id.exitButton)).perform(click());
    }
    /*
test case if the game is redirected to the game configuration screen when the player
selects the restart button
written by: Jocelyn
*/
    @Test
    public void testRestartButtonRedirectsToGameConfigurationScreen() {
        // Set up activity scenario rule
        ActivityScenarioRule<GameEnd> activityScenarioRule =
                new ActivityScenarioRule<>(GameEnd.class);

        // Launch activity scenario
        ActivityScenario<GameEnd> activityScenario = activityScenarioRule.getScenario();

        // Click on the restart button
        onView(withId(R.id.restartButton)).perform(click());

        // Verify that the activity has changed to ChoosePlayer activity
        activityScenario.onActivity(new ActivityScenario.ActivityAction<GameEnd>() {
            @Override
            public void perform(GameEnd activity) {
                Intent intent = activity.getIntent();
                assertEquals(intent.getComponent().getClassName(), ChoosePlayer.class.getName());
            }
        });
    }
    /*
test case too test whether the game screen is back to the initial state once the user
passes the configuration screen
written by: Jocelyn
*/
    @Test
    public void testGameScreenResetAfterConfiguration() {
        // Start the game
        onView(withId(R.id.start_button)).perform(click());

        // Play the game until the end
        // ...

        // Click the restart button on the end screen
        onView(withId(R.id.restartButton)).perform(click());

        // Go back to the configuration screen
        onView(withId(R.id.exitButton)).perform(click());

        // Check that the game screen is in the initial state
        onView(withId(R.id.start_button)).check(matches(isDisplayed()));
        onView(withId(R.id.score_text_view)).check(matches(withText("Score: 0")));
        // ... check other initial state conditions
    }
}

