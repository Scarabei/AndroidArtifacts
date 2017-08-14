package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ActionProvider;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.string;
import android.support.v7.content.res.AppCompatResources;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;

public class ShareActionProvider extends ActionProvider {
   private static final int DEFAULT_INITIAL_ACTIVITY_COUNT = 4;
   private int mMaxShownActivityCount = 4;
   private final ShareActionProvider.ShareMenuItemOnMenuItemClickListener mOnMenuItemClickListener = new ShareActionProvider.ShareMenuItemOnMenuItemClickListener();
   public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
   final Context mContext;
   String mShareHistoryFileName = "share_history.xml";
   ShareActionProvider.OnShareTargetSelectedListener mOnShareTargetSelectedListener;
   private ActivityChooserModel.OnChooseActivityListener mOnChooseActivityListener;

   public ShareActionProvider(Context context) {
      super(context);
      this.mContext = context;
   }

   public void setOnShareTargetSelectedListener(ShareActionProvider.OnShareTargetSelectedListener listener) {
      this.mOnShareTargetSelectedListener = listener;
      this.setActivityChooserPolicyIfNeeded();
   }

   public View onCreateActionView() {
      ActivityChooserView activityChooserView = new ActivityChooserView(this.mContext);
      if (!activityChooserView.isInEditMode()) {
         ActivityChooserModel dataModel = ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName);
         activityChooserView.setActivityChooserModel(dataModel);
      }

      TypedValue outTypedValue = new TypedValue();
      this.mContext.getTheme().resolveAttribute(attr.actionModeShareDrawable, outTypedValue, true);
      Drawable drawable = AppCompatResources.getDrawable(this.mContext, outTypedValue.resourceId);
      activityChooserView.setExpandActivityOverflowButtonDrawable(drawable);
      activityChooserView.setProvider(this);
      activityChooserView.setDefaultActionButtonContentDescription(string.abc_shareactionprovider_share_with_application);
      activityChooserView.setExpandActivityOverflowButtonContentDescription(string.abc_shareactionprovider_share_with);
      return activityChooserView;
   }

   public boolean hasSubMenu() {
      return true;
   }

   public void onPrepareSubMenu(SubMenu subMenu) {
      subMenu.clear();
      ActivityChooserModel dataModel = ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName);
      PackageManager packageManager = this.mContext.getPackageManager();
      int expandedActivityCount = dataModel.getActivityCount();
      int collapsedActivityCount = Math.min(expandedActivityCount, this.mMaxShownActivityCount);

      for(int i = 0; i < collapsedActivityCount; ++i) {
         ResolveInfo activity = dataModel.getActivity(i);
         subMenu.add(0, i, i, activity.loadLabel(packageManager)).setIcon(activity.loadIcon(packageManager)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
      }

      if (collapsedActivityCount < expandedActivityCount) {
         SubMenu expandedSubMenu = subMenu.addSubMenu(0, collapsedActivityCount, collapsedActivityCount, this.mContext.getString(string.abc_activity_chooser_view_see_all));

         for(int i = 0; i < expandedActivityCount; ++i) {
            ResolveInfo activity = dataModel.getActivity(i);
            expandedSubMenu.add(0, i, i, activity.loadLabel(packageManager)).setIcon(activity.loadIcon(packageManager)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
         }
      }

   }

   public void setShareHistoryFileName(String shareHistoryFile) {
      this.mShareHistoryFileName = shareHistoryFile;
      this.setActivityChooserPolicyIfNeeded();
   }

   public void setShareIntent(Intent shareIntent) {
      if (shareIntent != null) {
         String action = shareIntent.getAction();
         if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
            this.updateIntent(shareIntent);
         }
      }

      ActivityChooserModel dataModel = ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName);
      dataModel.setIntent(shareIntent);
   }

   private void setActivityChooserPolicyIfNeeded() {
      if (this.mOnShareTargetSelectedListener != null) {
         if (this.mOnChooseActivityListener == null) {
            this.mOnChooseActivityListener = new ShareActionProvider.ShareActivityChooserModelPolicy();
         }

         ActivityChooserModel dataModel = ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName);
         dataModel.setOnChooseActivityListener(this.mOnChooseActivityListener);
      }
   }

   void updateIntent(Intent intent) {
      if (VERSION.SDK_INT >= 21) {
         intent.addFlags(134742016);
      } else {
         intent.addFlags(524288);
      }

   }

   private class ShareActivityChooserModelPolicy implements ActivityChooserModel.OnChooseActivityListener {
      public boolean onChooseActivity(ActivityChooserModel host, Intent intent) {
         if (ShareActionProvider.this.mOnShareTargetSelectedListener != null) {
            ShareActionProvider.this.mOnShareTargetSelectedListener.onShareTargetSelected(ShareActionProvider.this, intent);
         }

         return false;
      }
   }

   private class ShareMenuItemOnMenuItemClickListener implements OnMenuItemClickListener {
      public boolean onMenuItemClick(MenuItem item) {
         ActivityChooserModel dataModel = ActivityChooserModel.get(ShareActionProvider.this.mContext, ShareActionProvider.this.mShareHistoryFileName);
         int itemId = item.getItemId();
         Intent launchIntent = dataModel.chooseActivity(itemId);
         if (launchIntent != null) {
            String action = launchIntent.getAction();
            if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
               ShareActionProvider.this.updateIntent(launchIntent);
            }

            ShareActionProvider.this.mContext.startActivity(launchIntent);
         }

         return true;
      }
   }

   public interface OnShareTargetSelectedListener {
      boolean onShareTargetSelected(ShareActionProvider var1, Intent var2);
   }
}
