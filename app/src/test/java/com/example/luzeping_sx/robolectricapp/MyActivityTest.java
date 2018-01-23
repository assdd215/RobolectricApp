package com.example.luzeping_sx.robolectricapp;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.luzeping_sx.robolectricapp.robolectric.MyRoboRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by aria on 2018/1/4.
 */

@RunWith(MyRoboRunner.class)
@Config(manifest = Config.NONE)
public class MyActivityTest {

    MyActivity mMyctivity;
    TextView mTextView;
    Button mButton;
    ActivityController<MyActivity> mActivityController;


    @Before
    public void init(){
        mActivityController = Robolectric.buildActivity(MyActivity.class).create();
        mMyctivity = mActivityController.get();
        mTextView = mMyctivity.findViewById(R.id.textView);
        mButton = mMyctivity.findViewById(R.id.login);

    }

    @Test
    public void TestLifeCycle(){

        assertEquals("onCreate",mTextView.getText());
        mActivityController.start();
        assertEquals("onStart",mTextView.getText());
        mActivityController.resume();
        assertEquals("onResume",mTextView.getText());
        mActivityController.visible();

    }

    @Test
    public void TestUI(){

        Button mInverseBtn = mMyctivity.findViewById(R.id.inverseBtn);
        assertTrue(mInverseBtn.isEnabled());

        CheckBox mCheckBox = mMyctivity.findViewById(R.id.checkbox);
        mCheckBox.setChecked(true);
        mInverseBtn.performClick();
        assertTrue(!mCheckBox.isChecked());
        mInverseBtn.performClick();
        assertTrue(mCheckBox.isChecked());
    }

    @Test
    public void TestToast(){

        mMyctivity.findViewById(R.id.toastBtn).performClick();
        assertEquals(ShadowToast.getTextOfLatestToast(),"test toast");
    }

    @Test
    public void TestDialog(){
        //与预测结果相反，待定
        mActivityController.start().resume().visible();
        Button mDialogBtn = mMyctivity.findViewById(R.id.dialogBtn);
        assertTrue(mDialogBtn.isEnabled());
        mDialogBtn.performClick();
        AlertDialog lastAlertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(lastAlertDialog == null);
    }

    @Test
    public void TestIntent(){

        mButton.performClick();
        Intent expectedIntent = new Intent(mMyctivity,MainActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(),actual.getComponent());
    }

    @Test
    public void TestResource(){
        Application application = RuntimeEnvironment.application;
        String appName = application.getString(R.string.app_name);
        assertEquals("RobolectricApp",appName);
    }
}
