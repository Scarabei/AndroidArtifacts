package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface LifecycleDelegate {
   void onInflate(Activity var1, Bundle var2, Bundle var3);

   void onCreate(Bundle var1);

   View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3);

   void onStart();

   void onResume();

   void onPause();

   void onStop();

   void onDestroyView();

   void onDestroy();

   void onLowMemory();

   void onSaveInstanceState(Bundle var1);
}
