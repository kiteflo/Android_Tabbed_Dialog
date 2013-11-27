Android_Tabbed_Dialog
=====================

Create a simple Tabbed Dialog as this is not as trivial as it seems to be... :-(
This step by step guide explains how to create a dialog overlay with a tab swipe component, see screenie below.

![ScreenShot](/images/screenie.png)

Instead of following our step by step guide you can simply checkout the whole project and populate the tab fragments based on your needs or simply extend the whole project…feel free, any feedback is highly appreciated:

[![Image](/images/twitter.png "Image title") ](https://twitter.com/_flomueller "Twitter")

## 1) Create tabs (=fragments)

The tab swipe component (in Android it's a core FragmentPageAdapter) enables you to put several fragments in a tab component, each tab should hold one fragment so let's start by creating the tab fragments for your overlay. We will create three fragments (Fragment_Tab_1, Fragment_Tab_2, Fragment_Tab_3) - below there is a very lightweight fragment implementation, this implementation can be applied to Fragment_Tab_1,2,…n. In our example we ant to define the UI via layout xml file so during creation make sure each fragment has a layout counterpart…

### Java adapter code

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



