package com.mylibrary.swslibrary;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.mylibrary.swslibrary", appContext.getPackageName());

    }

    @Test
    public void asyncTask(){
        AsyncTask asyncTask = new AsyncTask(Looper.getMainLooper()) {
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }
        };
        asyncTask.execute();
    }

}