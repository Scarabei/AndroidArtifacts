package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class FragmentStatePagerAdapter extends PagerAdapter {
   private static final String TAG = "FragmentStatePagerAdapt";
   private static final boolean DEBUG = false;
   private final FragmentManager mFragmentManager;
   private FragmentTransaction mCurTransaction = null;
   private ArrayList mSavedState = new ArrayList();
   private ArrayList mFragments = new ArrayList();
   private Fragment mCurrentPrimaryItem = null;

   public FragmentStatePagerAdapter(FragmentManager fm) {
      this.mFragmentManager = fm;
   }

   public abstract Fragment getItem(int var1);

   public void startUpdate(ViewGroup container) {
      if (container.getId() == -1) {
         throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
      }
   }

   public Object instantiateItem(ViewGroup container, int position) {
      Fragment fragment;
      if (this.mFragments.size() > position) {
         fragment = (Fragment)this.mFragments.get(position);
         if (fragment != null) {
            return fragment;
         }
      }

      if (this.mCurTransaction == null) {
         this.mCurTransaction = this.mFragmentManager.beginTransaction();
      }

      fragment = this.getItem(position);
      if (this.mSavedState.size() > position) {
         Fragment.SavedState fss = (Fragment.SavedState)this.mSavedState.get(position);
         if (fss != null) {
            fragment.setInitialSavedState(fss);
         }
      }

      while(this.mFragments.size() <= position) {
         this.mFragments.add((Object)null);
      }

      fragment.setMenuVisibility(false);
      fragment.setUserVisibleHint(false);
      this.mFragments.set(position, fragment);
      this.mCurTransaction.add(container.getId(), fragment);
      return fragment;
   }

   public void destroyItem(ViewGroup container, int position, Object object) {
      Fragment fragment = (Fragment)object;
      if (this.mCurTransaction == null) {
         this.mCurTransaction = this.mFragmentManager.beginTransaction();
      }

      while(this.mSavedState.size() <= position) {
         this.mSavedState.add((Object)null);
      }

      this.mSavedState.set(position, fragment.isAdded() ? this.mFragmentManager.saveFragmentInstanceState(fragment) : null);
      this.mFragments.set(position, (Object)null);
      this.mCurTransaction.remove(fragment);
   }

   public void setPrimaryItem(ViewGroup container, int position, Object object) {
      Fragment fragment = (Fragment)object;
      if (fragment != this.mCurrentPrimaryItem) {
         if (this.mCurrentPrimaryItem != null) {
            this.mCurrentPrimaryItem.setMenuVisibility(false);
            this.mCurrentPrimaryItem.setUserVisibleHint(false);
         }

         if (fragment != null) {
            fragment.setMenuVisibility(true);
            fragment.setUserVisibleHint(true);
         }

         this.mCurrentPrimaryItem = fragment;
      }

   }

   public void finishUpdate(ViewGroup container) {
      if (this.mCurTransaction != null) {
         this.mCurTransaction.commitNowAllowingStateLoss();
         this.mCurTransaction = null;
      }

   }

   public boolean isViewFromObject(View view, Object object) {
      return ((Fragment)object).getView() == view;
   }

   public Parcelable saveState() {
      Bundle state = null;
      if (this.mSavedState.size() > 0) {
         state = new Bundle();
         Fragment.SavedState[] fss = new Fragment.SavedState[this.mSavedState.size()];
         this.mSavedState.toArray(fss);
         state.putParcelableArray("states", fss);
      }

      for(int i = 0; i < this.mFragments.size(); ++i) {
         Fragment f = (Fragment)this.mFragments.get(i);
         if (f != null && f.isAdded()) {
            if (state == null) {
               state = new Bundle();
            }

            String key = "f" + i;
            this.mFragmentManager.putFragment(state, key, f);
         }
      }

      return state;
   }

   public void restoreState(Parcelable state, ClassLoader loader) {
      if (state != null) {
         Bundle bundle = (Bundle)state;
         bundle.setClassLoader(loader);
         Parcelable[] fss = bundle.getParcelableArray("states");
         this.mSavedState.clear();
         this.mFragments.clear();
         if (fss != null) {
            for(int i = 0; i < fss.length; ++i) {
               this.mSavedState.add((Fragment.SavedState)fss[i]);
            }
         }

         Iterable keys = bundle.keySet();
         Iterator var6 = keys.iterator();

         while(true) {
            while(true) {
               String key;
               do {
                  if (!var6.hasNext()) {
                     return;
                  }

                  key = (String)var6.next();
               } while(!key.startsWith("f"));

               int index = Integer.parseInt(key.substring(1));
               Fragment f = this.mFragmentManager.getFragment(bundle, key);
               if (f != null) {
                  while(this.mFragments.size() <= index) {
                     this.mFragments.add((Object)null);
                  }

                  f.setMenuVisibility(false);
                  this.mFragments.set(index, f);
               } else {
                  Log.w("FragmentStatePagerAdapt", "Bad fragment at key " + key);
               }
            }
         }
      }
   }
}
