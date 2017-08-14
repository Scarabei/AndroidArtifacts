package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.RestrictTo.Scope;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public abstract class FragmentManager {
   public static final int POP_BACK_STACK_INCLUSIVE = 1;

   public abstract FragmentTransaction beginTransaction();

   /** @deprecated */
   @Deprecated
   @RestrictTo({Scope.LIBRARY_GROUP})
   public FragmentTransaction openTransaction() {
      return this.beginTransaction();
   }

   public abstract boolean executePendingTransactions();

   public abstract Fragment findFragmentById(@IdRes int var1);

   public abstract Fragment findFragmentByTag(String var1);

   public abstract void popBackStack();

   public abstract boolean popBackStackImmediate();

   public abstract void popBackStack(String var1, int var2);

   public abstract boolean popBackStackImmediate(String var1, int var2);

   public abstract void popBackStack(int var1, int var2);

   public abstract boolean popBackStackImmediate(int var1, int var2);

   public abstract int getBackStackEntryCount();

   public abstract FragmentManager.BackStackEntry getBackStackEntryAt(int var1);

   public abstract void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener var1);

   public abstract void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener var1);

   public abstract void putFragment(Bundle var1, String var2, Fragment var3);

   public abstract Fragment getFragment(Bundle var1, String var2);

   public abstract List getFragments();

   public abstract Fragment.SavedState saveFragmentInstanceState(Fragment var1);

   public abstract boolean isDestroyed();

   public abstract void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks var1, boolean var2);

   public abstract void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks var1);

   public abstract Fragment getPrimaryNavigationFragment();

   public abstract void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4);

   public static void enableDebugLogging(boolean enabled) {
      FragmentManagerImpl.DEBUG = enabled;
   }

   public abstract boolean isStateSaved();

   public abstract static class FragmentLifecycleCallbacks {
      public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
      }

      public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
      }

      public void onFragmentPreCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
      }

      public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
      }

      public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
      }

      public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
      }

      public void onFragmentStarted(FragmentManager fm, Fragment f) {
      }

      public void onFragmentResumed(FragmentManager fm, Fragment f) {
      }

      public void onFragmentPaused(FragmentManager fm, Fragment f) {
      }

      public void onFragmentStopped(FragmentManager fm, Fragment f) {
      }

      public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
      }

      public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
      }

      public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
      }

      public void onFragmentDetached(FragmentManager fm, Fragment f) {
      }
   }

   public interface OnBackStackChangedListener {
      void onBackStackChanged();
   }

   public interface BackStackEntry {
      int getId();

      String getName();

      @StringRes
      int getBreadCrumbTitleRes();

      @StringRes
      int getBreadCrumbShortTitleRes();

      CharSequence getBreadCrumbTitle();

      CharSequence getBreadCrumbShortTitle();
   }
}
