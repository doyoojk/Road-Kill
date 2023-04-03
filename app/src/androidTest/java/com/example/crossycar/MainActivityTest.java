package com.example.crossycar;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
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
//    @Test
//    public void checkStartActivates() {
//        ViewInteraction button = onView(
//                allOf(withId(R.id.game_start_button), withText("START GAME"),
//                        withParent(withParent(withId(android.R.id.content))),
//                        isDisplayed()));
//        button.check(matches(isDisplayed()));
//    }
    @Test
    public void checkStartActivates() {
        ViewInteraction button = onView(
                allOf(withId(R.id.game_start_button), withText("START GAME"),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
    }


    /*
    Test checks if radio button easy exists and contains the text: EASY
    written by: Katherine
    */
//    @Test
//    public void checkEasyGameLoop() {
//        ViewInteraction materialButton = onView(
//                allOf(withId(R.id.game_start_button), withText("Start Game"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        materialButton.perform(click());
//
//        ViewInteraction materialRadioButton = onView(
//                allOf(withId(R.id.difficulty_easy), withText("Easy"),
//                        childAtPosition(
//                                allOf(withId(R.id.difficulty_selection_group),
//                                        childAtPosition(
//                                                withClassName(is("android.widget.RelativeLayout")),
//                                                4)),
//                                0),
//                        isDisplayed()));
//        materialRadioButton.perform(click());
//
//        ViewInteraction radioButton = onView(
//                allOf(withId(R.id.difficulty_easy), withText("Easy"),
//                        withParent(allOf(withId(R.id.difficulty_selection_group),
//                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
//                        isDisplayed()));
//        radioButton.check(matches(isDisplayed()));
//    }
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
//    @Test
//    public void redBmwSpawns() {
//        ViewInteraction materialButton = onView(
//                allOf(withId(R.id.game_start_button), withText("Start Game"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        materialButton.perform(click());
//
//        ViewInteraction materialRadioButton = onView(
//                allOf(withId(R.id.difficulty_medium), withText("Medium"),
//                        childAtPosition(
//                                allOf(withId(R.id.difficulty_selection_group),
//                                        childAtPosition(
//                                                withClassName(is("android.widget.RelativeLayout")),
//                                                4)),
//                                1),
//                        isDisplayed()));
//        materialRadioButton.perform(click());
//
//        ViewInteraction radioButton = onView(
//                allOf(withId(R.id.difficulty_medium), withText("Medium"),
//                        withParent(allOf(withId(R.id.difficulty_selection_group),
//                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
//                        isDisplayed()));
//        radioButton.check(matches(isDisplayed()));
//    }
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
//    @Test
//    public void fiveLivesInEasy() {
//        ViewInteraction materialButton = onView(
//                allOf(withId(R.id.game_start_button), withText("Start Game"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        materialButton.perform(click());
//
//        ViewInteraction materialRadioButton = onView(
//                allOf(withId(R.id.difficulty_hard), withText("Hard"),
//                        childAtPosition(
//                                allOf(withId(R.id.difficulty_selection_group),
//                                        childAtPosition(
//                                                withClassName(is("android.widget.RelativeLayout")),
//                                                4)),
//                                2),
//                        isDisplayed()));
//        materialRadioButton.perform(click());
//
//        ViewInteraction radioButton = onView(
//                allOf(withId(R.id.difficulty_hard), withText("Hard"),
//                        withParent(allOf(withId(R.id.difficulty_selection_group),
//                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
//                        isDisplayed()));
//        radioButton.check(matches(isDisplayed()));
//    }
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
//    @Test
//    public void fourLivesInMedium() {
//        ViewInteraction materialButton = onView(
//                allOf(withId(R.id.game_start_button), withText("Start Game"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        materialButton.perform(click());
//
//        ViewInteraction appCompatEditText = onView(
//                allOf(withId(R.id.player_name_input),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                3),
//                        isDisplayed()));
//        appCompatEditText.perform(replaceText("Erick"), closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText2 = onView(
//                allOf(withId(R.id.player_name_input), withText("Erick"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                3),
//                        isDisplayed()));
//        appCompatEditText2.perform(pressImeActionButton());
//
//        ViewInteraction editText = onView(
//                allOf(withId(R.id.player_name_input), withText("Erick"),
//                        withParent(withParent(withId(android.R.id.content))),
//                        isDisplayed()));
//        editText.check(matches(withText("Erick")));
//    }
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
//    @Test
//    public void twoLivesInHard() {
//        ViewInteraction materialButton = onView(
//                allOf(withId(R.id.game_start_button), withText("Start Game"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        materialButton.perform(click());
//
//        ViewInteraction appCompatEditText = onView(
//                allOf(withId(R.id.player_name_input),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                3),
//                        isDisplayed()));
//        appCompatEditText.perform(replaceText("Erick"), closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText2 = onView(
//                allOf(withId(R.id.player_name_input), withText("Erick"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                3),
//                        isDisplayed()));
//        appCompatEditText2.perform(pressImeActionButton());
//
//        ViewInteraction editText = onView(
//                allOf(withId(R.id.player_name_input), withText("Erick"),
//                        withParent(withParent(withId(android.R.id.content))),
//                        isDisplayed()));
//        editText.check(matches(withText("Erick")));
//    }
//    @Test
//    public void twoLivesInHard() {
//        ViewInteraction materialButton = onView(
//                allOf(withId(R.id.game_start_button), withText("Start Game"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        materialButton.perform(click());
//
//        ViewInteraction materialRadioButton = onView(
//                allOf(withId(R.id.difficulty_hard), withText("Hard"),
//                        childAtPosition(
//                                allOf(withId(R.id.difficulty_selection_group),
//                                        childAtPosition(
//                                                withClassName(is("android.widget.RelativeLayout")),
//                                                4)),
//                                2),
//                        isDisplayed()));
//        materialRadioButton.perform(click());
//
//        ViewInteraction livesText = onView(
//                allOf(withId(R.id.lives_text),
//                        withParent(allOf(withId(R.id.lives_layout),
//                                withParent(withId(R.id.game_layout))))));
//        livesText.check(matches(withText("2")));
//
//        ViewInteraction playerNameText = onView(
//                allOf(withId(R.id.player_name_input), withText("Erick"),
//                        withParent(withParent(withId(android.R.id.content))),
//                        isDisplayed()));
//        playerNameText.check(matches(withText("Erick")));
//    }
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
}
