package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;

public class ListFragment extends Fragment {
   static final int INTERNAL_EMPTY_ID = 16711681;
   static final int INTERNAL_PROGRESS_CONTAINER_ID = 16711682;
   static final int INTERNAL_LIST_CONTAINER_ID = 16711683;
   private final Handler mHandler = new Handler();
   private final Runnable mRequestFocus = new Runnable() {
      public void run() {
         ListFragment.this.mList.focusableViewAvailable(ListFragment.this.mList);
      }
   };
   private final OnItemClickListener mOnClickListener = new OnItemClickListener() {
      public void onItemClick(AdapterView parent, View v, int position, long id) {
         ListFragment.this.onListItemClick((ListView)parent, v, position, id);
      }
   };
   ListAdapter mAdapter;
   ListView mList;
   View mEmptyView;
   TextView mStandardEmptyView;
   View mProgressContainer;
   View mListContainer;
   CharSequence mEmptyText;
   boolean mListShown;

   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      Context context = this.getContext();
      FrameLayout root = new FrameLayout(context);
      LinearLayout pframe = new LinearLayout(context);
      pframe.setId(16711682);
      pframe.setOrientation(1);
      pframe.setVisibility(8);
      pframe.setGravity(17);
      ProgressBar progress = new ProgressBar(context, (AttributeSet)null, 16842874);
      pframe.addView(progress, new LayoutParams(-2, -2));
      root.addView(pframe, new LayoutParams(-1, -1));
      FrameLayout lframe = new FrameLayout(context);
      lframe.setId(16711683);
      TextView tv = new TextView(context);
      tv.setId(16711681);
      tv.setGravity(17);
      lframe.addView(tv, new LayoutParams(-1, -1));
      ListView lv = new ListView(context);
      lv.setId(16908298);
      lv.setDrawSelectorOnTop(false);
      lframe.addView(lv, new LayoutParams(-1, -1));
      root.addView(lframe, new LayoutParams(-1, -1));
      root.setLayoutParams(new LayoutParams(-1, -1));
      return root;
   }

   public void onViewCreated(View view, Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      this.ensureList();
   }

   public void onDestroyView() {
      this.mHandler.removeCallbacks(this.mRequestFocus);
      this.mList = null;
      this.mListShown = false;
      this.mEmptyView = this.mProgressContainer = this.mListContainer = null;
      this.mStandardEmptyView = null;
      super.onDestroyView();
   }

   public void onListItemClick(ListView l, View v, int position, long id) {
   }

   public void setListAdapter(ListAdapter adapter) {
      boolean hadAdapter = this.mAdapter != null;
      this.mAdapter = adapter;
      if (this.mList != null) {
         this.mList.setAdapter(adapter);
         if (!this.mListShown && !hadAdapter) {
            this.setListShown(true, this.getView().getWindowToken() != null);
         }
      }

   }

   public void setSelection(int position) {
      this.ensureList();
      this.mList.setSelection(position);
   }

   public int getSelectedItemPosition() {
      this.ensureList();
      return this.mList.getSelectedItemPosition();
   }

   public long getSelectedItemId() {
      this.ensureList();
      return this.mList.getSelectedItemId();
   }

   public ListView getListView() {
      this.ensureList();
      return this.mList;
   }

   public void setEmptyText(CharSequence text) {
      this.ensureList();
      if (this.mStandardEmptyView == null) {
         throw new IllegalStateException("Can't be used with a custom content view");
      } else {
         this.mStandardEmptyView.setText(text);
         if (this.mEmptyText == null) {
            this.mList.setEmptyView(this.mStandardEmptyView);
         }

         this.mEmptyText = text;
      }
   }

   public void setListShown(boolean shown) {
      this.setListShown(shown, true);
   }

   public void setListShownNoAnimation(boolean shown) {
      this.setListShown(shown, false);
   }

   private void setListShown(boolean shown, boolean animate) {
      this.ensureList();
      if (this.mProgressContainer == null) {
         throw new IllegalStateException("Can't be used with a custom content view");
      } else if (this.mListShown != shown) {
         this.mListShown = shown;
         if (shown) {
            if (animate) {
               this.mProgressContainer.startAnimation(AnimationUtils.loadAnimation(this.getContext(), 17432577));
               this.mListContainer.startAnimation(AnimationUtils.loadAnimation(this.getContext(), 17432576));
            } else {
               this.mProgressContainer.clearAnimation();
               this.mListContainer.clearAnimation();
            }

            this.mProgressContainer.setVisibility(8);
            this.mListContainer.setVisibility(0);
         } else {
            if (animate) {
               this.mProgressContainer.startAnimation(AnimationUtils.loadAnimation(this.getContext(), 17432576));
               this.mListContainer.startAnimation(AnimationUtils.loadAnimation(this.getContext(), 17432577));
            } else {
               this.mProgressContainer.clearAnimation();
               this.mListContainer.clearAnimation();
            }

            this.mProgressContainer.setVisibility(0);
            this.mListContainer.setVisibility(8);
         }

      }
   }

   public ListAdapter getListAdapter() {
      return this.mAdapter;
   }

   private void ensureList() {
      if (this.mList == null) {
         View root = this.getView();
         if (root == null) {
            throw new IllegalStateException("Content view not yet created");
         } else {
            if (root instanceof ListView) {
               this.mList = (ListView)root;
            } else {
               this.mStandardEmptyView = (TextView)root.findViewById(16711681);
               if (this.mStandardEmptyView == null) {
                  this.mEmptyView = root.findViewById(16908292);
               } else {
                  this.mStandardEmptyView.setVisibility(8);
               }

               this.mProgressContainer = root.findViewById(16711682);
               this.mListContainer = root.findViewById(16711683);
               View rawListView = root.findViewById(16908298);
               if (!(rawListView instanceof ListView)) {
                  if (rawListView == null) {
                     throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
                  }

                  throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
               }

               this.mList = (ListView)rawListView;
               if (this.mEmptyView != null) {
                  this.mList.setEmptyView(this.mEmptyView);
               } else if (this.mEmptyText != null) {
                  this.mStandardEmptyView.setText(this.mEmptyText);
                  this.mList.setEmptyView(this.mStandardEmptyView);
               }
            }

            this.mListShown = true;
            this.mList.setOnItemClickListener(this.mOnClickListener);
            if (this.mAdapter != null) {
               ListAdapter adapter = this.mAdapter;
               this.mAdapter = null;
               this.setListAdapter(adapter);
            } else if (this.mProgressContainer != null) {
               this.setListShown(false, false);
            }

            this.mHandler.post(this.mRequestFocus);
         }
      }
   }
}
