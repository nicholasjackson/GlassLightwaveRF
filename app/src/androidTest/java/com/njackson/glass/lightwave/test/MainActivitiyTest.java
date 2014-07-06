package com.njackson.glass.lightwave.test.activities;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

import com.njackson.glass.lightwave.R;
import com.njackson.glass.lightwave.activities.MainActivity;
import com.njackson.glass.lightwave.cards.TuggableView;

import java.util.ArrayList;

/**
 * Created by server on 06/07/2014.
 */
public class MainActivitiyTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivitiyTest() {
        super(MainActivity.class);
    }

    public MainActivitiyTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testVoiceIntentSetsCardText() {
        Intent intent = new Intent();
        ArrayList<String> voiceResults = new ArrayList<String>();
        voiceResults.add("turn kitchen lights on");
        intent.putExtra(RecognizerIntent.EXTRA_RESULTS,voiceResults);
        setActivityIntent(intent);
        MainActivity activity = getActivity();
        getInstrumentation().waitForIdleSync();
        TextView view = (TextView)activity.findViewById(R.id.text_view);
        assertEquals("Kitchen Lights On",view.getText());
    }

}
