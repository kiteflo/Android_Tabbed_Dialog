Android_Tabbed_Dialog
=====================

Create a simple Tabbed Dialog as this is not as trivial as it seems to be... :-(
This step by step guide explains how to create a dialog overlay with a tab swipe component, see screenie below.

![ScreenShot](/images/screenie.png)

Instead of following our step by step guide you can simply checkout the whole project and populate the tab fragments based on your needs or simply extend the whole project…feel free, any feedback is highly appreciated:

[![Image](/images/twitter.png "Image title") ](https://twitter.com/_flomueller "Twitter")

## 1 Create tabs (=fragments)

The tab swipe component (in Android it's a core FragmentPageAdapter) enables you to put several fragments in a tab component, each tab should hold one fragment so let's start by creating the tab fragments for your overlay. We will create three fragments (Fragment_Tab_1, Fragment_Tab_2, Fragment_Tab_3) - below there is a very lightweight fragment implementation, this implementation can be applied to Fragment_Tab_1,2,…n. In our example we ant to define the UI via layout xml file so during creation make sure each fragment has a layout counterpart…

### Fragment adapter code
**(Fragment_Tab_1.java)**

```java
package com.jooik.tabbeddialog.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jooik.tabbeddialog.R;

/**
 * A simple counterpart for tab1 layout...
 */
public class Fragment_Tab_1 extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tab_1, container, false);
    }
}
```

### XML layout definition
**(fragment_tab_1.xml)**

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:orientation="vertical"
              android:layout_width="match_parent"
              android:layout_height="match_parent">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Tab 1"/>
</LinearLayout>
```

## 2 Create Dialog Fragment (= "Tab Host")
Next the fragments need to be placed/grouped somehow, therefor you have to create a new fragment…within this fragment (we also need a corresponding layout file) certain magic ties in the previously created tab fragments so let's get started with our lesson in magic ;-)

There are several steps you need to apply in order to enable your fragment grouping the tabs, let's start with the layout extensions. The layout needs to contain a tab host which easily can be achieved by adding a ViewPager component, within this ViewPager we have to add a PagerTitleStrip

### 2.1 Add tab host to layout…
**(fragment_dialog.xml)**

Simply copy/past the contents below to your fragment XML, the whole file should look like this:

```xml
<android.support.v4.view.ViewPager xmlns:android="http://schemas.android.com/apk/res/android"
                                   xmlns:tools="http://schemas.android.com/tools"
                                   android:id="@+id/pager"
                                   android:layout_width="match_parent"
                                   android:layout_height="match_parent">
    <!-- Current + adjacent page titles...-->
    <android.support.v4.view.PagerTitleStrip
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="top"
        android:background="#33b5e5"
        android:paddingBottom="4dp"
        android:paddingTop="4dp"
        android:textColor="#fff" />
</android.support.v4.view.ViewPager>

```

### 2.2 Initialize tab host in Java Adapter
**(FragmentDialog.java)**

*1) You need to add a inner class to your fragment java code extending FragmentPageAdapter. This class will handle instantiation etc. of your fragments while the user swipes over the tab host…ass the code below to the java part of your fragment:*

```java
// ------------------------------------------------------------------------
// inner classes
// ------------------------------------------------------------------------

/**
 * Used for tab paging...
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter
{

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
        {
            // find first fragment...
            Fragment_Tab_1 ft1 = new Fragment_Tab_1();
            return ft1;
        }
        if (position == 1)
        {
            // find first fragment...
            Fragment_Tab_2 ft2 = new Fragment_Tab_2();
            return ft2;
        }
        else if (position == 2)
        {
            // find first fragment...
            Fragment_Tab_3 ft3 = new Fragment_Tab_3();
            return ft3;
        }

        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "First Tab";
            case 1:
                return "Second Tab";
            case 2:
                return "Third Tab";
        }
        return null;
    }
}
```

*2) Make your fragment class extending **DialogFragment** instead of Fragment (in case you dont wanna display the tab host as a dialog skip this step…)*

```java
public class FragmentDialog extends DialogFragment
```

*3) Finally implement usage and initialisation of FragmentPageAdapter. We are overriding **onCreateDialog** AND **onCreateView**, this enables us to control dialog look & feel (Titlebar, buttons, colors) via onCreateDialog, anything view related goes into onCreateView…add the following java snippet to your adapter:*

**ATTENTION: when instantiating a FragmentPageAdapter from a fragment you can not use *getSupportFragmentManager()/getFragmentManager()*. Instead of this you have to use *getChildFragmentManager*. So replace the child fragment manager stuff in case you wanna display a tab host within the context of an activity directly…***

```java
// ------------------------------------------------------------------------
// members
// ------------------------------------------------------------------------

private SectionsPagerAdapter sectionsPagerAdapter;
private ViewPager viewPager;

// ------------------------------------------------------------------------
// public usage
// ------------------------------------------------------------------------

@Override
public Dialog onCreateDialog(final Bundle savedInstanceState)
{
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    return dialog;
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_dialog, container);

    // tab slider
    sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

    // Set up the ViewPager with the sections adapter.
    viewPager = (ViewPager)view.findViewById(R.id.pager);
    viewPager.setAdapter(sectionsPagerAdapter);

    return view;
}
```

## 3 Invoke/Open dialog

Nearly finished! Finally add a button to your main activity (programmatically or XML based does not matter), add the following implementation as button click handler..:

```java
/**
 * Fire up popup dialog...
 * @param view
 */
public void onOpenDialog(View view)
{
    FragmentManager fm = getSupportFragmentManager();
    FragmentDialog overlay = new FragmentDialog();
    overlay.show(fm, "FragmentDialog");
}
```

## 4 DONE! Enjoy your tab host popover window… :-)

![ScreenShot1](/images/screenie01.png)
![ScreenShot2](/images/screenie02.png)