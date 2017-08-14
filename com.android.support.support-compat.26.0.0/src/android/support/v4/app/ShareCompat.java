package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.support.annotation.StringRes;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import java.util.ArrayList;

public final class ShareCompat {
   public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
   public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
   private static final String HISTORY_FILENAME_PREFIX = ".sharecompat_";

   public static String getCallingPackage(Activity calledActivity) {
      String result = calledActivity.getCallingPackage();
      if (result == null) {
         result = calledActivity.getIntent().getStringExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE");
      }

      return result;
   }

   public static ComponentName getCallingActivity(Activity calledActivity) {
      ComponentName result = calledActivity.getCallingActivity();
      if (result == null) {
         result = (ComponentName)calledActivity.getIntent().getParcelableExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY");
      }

      return result;
   }

   public static void configureMenuItem(MenuItem item, ShareCompat.IntentBuilder shareIntent) {
      ActionProvider itemProvider = item.getActionProvider();
      ShareActionProvider provider;
      if (!(itemProvider instanceof ShareActionProvider)) {
         provider = new ShareActionProvider(shareIntent.getActivity());
      } else {
         provider = (ShareActionProvider)itemProvider;
      }

      provider.setShareHistoryFileName(".sharecompat_" + shareIntent.getActivity().getClass().getName());
      provider.setShareIntent(shareIntent.getIntent());
      item.setActionProvider(provider);
      if (VERSION.SDK_INT < 16 && !item.hasSubMenu()) {
         item.setIntent(shareIntent.createChooserIntent());
      }

   }

   public static void configureMenuItem(Menu menu, int menuItemId, ShareCompat.IntentBuilder shareIntent) {
      MenuItem item = menu.findItem(menuItemId);
      if (item == null) {
         throw new IllegalArgumentException("Could not find menu item with id " + menuItemId + " in the supplied menu");
      } else {
         configureMenuItem(item, shareIntent);
      }
   }

   public static class IntentReader {
      private static final String TAG = "IntentReader";
      private Activity mActivity;
      private Intent mIntent;
      private String mCallingPackage;
      private ComponentName mCallingActivity;
      private ArrayList mStreams;

      public static ShareCompat.IntentReader from(Activity activity) {
         return new ShareCompat.IntentReader(activity);
      }

      private IntentReader(Activity activity) {
         this.mActivity = activity;
         this.mIntent = activity.getIntent();
         this.mCallingPackage = ShareCompat.getCallingPackage(activity);
         this.mCallingActivity = ShareCompat.getCallingActivity(activity);
      }

      public boolean isShareIntent() {
         String action = this.mIntent.getAction();
         return "android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action);
      }

      public boolean isSingleShare() {
         return "android.intent.action.SEND".equals(this.mIntent.getAction());
      }

      public boolean isMultipleShare() {
         return "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
      }

      public String getType() {
         return this.mIntent.getType();
      }

      public CharSequence getText() {
         return this.mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
      }

      public String getHtmlText() {
         String result = this.mIntent.getStringExtra("android.intent.extra.HTML_TEXT");
         if (result == null) {
            CharSequence text = this.getText();
            if (text instanceof Spanned) {
               result = Html.toHtml((Spanned)text);
            } else if (text != null) {
               if (VERSION.SDK_INT >= 16) {
                  result = Html.escapeHtml(text);
               } else {
                  StringBuilder out = new StringBuilder();
                  withinStyle(out, text, 0, text.length());
                  result = out.toString();
               }
            }
         }

         return result;
      }

      private static void withinStyle(StringBuilder out, CharSequence text, int start, int end) {
         for(int i = start; i < end; ++i) {
            char c = text.charAt(i);
            if (c == '<') {
               out.append("&lt;");
            } else if (c == '>') {
               out.append("&gt;");
            } else if (c == '&') {
               out.append("&amp;");
            } else if (c <= '~' && c >= ' ') {
               if (c != ' ') {
                  out.append(c);
               } else {
                  while(i + 1 < end && text.charAt(i + 1) == ' ') {
                     out.append("&nbsp;");
                     ++i;
                  }

                  out.append(' ');
               }
            } else {
               out.append("&#" + c + ";");
            }
         }

      }

      public Uri getStream() {
         return (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
      }

      public Uri getStream(int index) {
         if (this.mStreams == null && this.isMultipleShare()) {
            this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
         }

         if (this.mStreams != null) {
            return (Uri)this.mStreams.get(index);
         } else if (index == 0) {
            return (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
         } else {
            throw new IndexOutOfBoundsException("Stream items available: " + this.getStreamCount() + " index requested: " + index);
         }
      }

      public int getStreamCount() {
         if (this.mStreams == null && this.isMultipleShare()) {
            this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
         }

         if (this.mStreams != null) {
            return this.mStreams.size();
         } else {
            return this.mIntent.hasExtra("android.intent.extra.STREAM") ? 1 : 0;
         }
      }

      public String[] getEmailTo() {
         return this.mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
      }

      public String[] getEmailCc() {
         return this.mIntent.getStringArrayExtra("android.intent.extra.CC");
      }

      public String[] getEmailBcc() {
         return this.mIntent.getStringArrayExtra("android.intent.extra.BCC");
      }

      public String getSubject() {
         return this.mIntent.getStringExtra("android.intent.extra.SUBJECT");
      }

      public String getCallingPackage() {
         return this.mCallingPackage;
      }

      public ComponentName getCallingActivity() {
         return this.mCallingActivity;
      }

      public Drawable getCallingActivityIcon() {
         if (this.mCallingActivity == null) {
            return null;
         } else {
            PackageManager pm = this.mActivity.getPackageManager();

            try {
               return pm.getActivityIcon(this.mCallingActivity);
            } catch (NameNotFoundException var3) {
               Log.e("IntentReader", "Could not retrieve icon for calling activity", var3);
               return null;
            }
         }
      }

      public Drawable getCallingApplicationIcon() {
         if (this.mCallingPackage == null) {
            return null;
         } else {
            PackageManager pm = this.mActivity.getPackageManager();

            try {
               return pm.getApplicationIcon(this.mCallingPackage);
            } catch (NameNotFoundException var3) {
               Log.e("IntentReader", "Could not retrieve icon for calling application", var3);
               return null;
            }
         }
      }

      public CharSequence getCallingApplicationLabel() {
         if (this.mCallingPackage == null) {
            return null;
         } else {
            PackageManager pm = this.mActivity.getPackageManager();

            try {
               return pm.getApplicationLabel(pm.getApplicationInfo(this.mCallingPackage, 0));
            } catch (NameNotFoundException var3) {
               Log.e("IntentReader", "Could not retrieve label for calling application", var3);
               return null;
            }
         }
      }
   }

   public static class IntentBuilder {
      private Activity mActivity;
      private Intent mIntent;
      private CharSequence mChooserTitle;
      private ArrayList mToAddresses;
      private ArrayList mCcAddresses;
      private ArrayList mBccAddresses;
      private ArrayList mStreams;

      public static ShareCompat.IntentBuilder from(Activity launchingActivity) {
         return new ShareCompat.IntentBuilder(launchingActivity);
      }

      private IntentBuilder(Activity launchingActivity) {
         this.mActivity = launchingActivity;
         this.mIntent = (new Intent()).setAction("android.intent.action.SEND");
         this.mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", launchingActivity.getPackageName());
         this.mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", launchingActivity.getComponentName());
         this.mIntent.addFlags(524288);
      }

      public Intent getIntent() {
         if (this.mToAddresses != null) {
            this.combineArrayExtra("android.intent.extra.EMAIL", this.mToAddresses);
            this.mToAddresses = null;
         }

         if (this.mCcAddresses != null) {
            this.combineArrayExtra("android.intent.extra.CC", this.mCcAddresses);
            this.mCcAddresses = null;
         }

         if (this.mBccAddresses != null) {
            this.combineArrayExtra("android.intent.extra.BCC", this.mBccAddresses);
            this.mBccAddresses = null;
         }

         boolean needsSendMultiple = this.mStreams != null && this.mStreams.size() > 1;
         boolean isSendMultiple = this.mIntent.getAction().equals("android.intent.action.SEND_MULTIPLE");
         if (!needsSendMultiple && isSendMultiple) {
            this.mIntent.setAction("android.intent.action.SEND");
            if (this.mStreams != null && !this.mStreams.isEmpty()) {
               this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)this.mStreams.get(0));
            } else {
               this.mIntent.removeExtra("android.intent.extra.STREAM");
            }

            this.mStreams = null;
         }

         if (needsSendMultiple && !isSendMultiple) {
            this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
            if (this.mStreams != null && !this.mStreams.isEmpty()) {
               this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.mStreams);
            } else {
               this.mIntent.removeExtra("android.intent.extra.STREAM");
            }
         }

         return this.mIntent;
      }

      Activity getActivity() {
         return this.mActivity;
      }

      private void combineArrayExtra(String extra, ArrayList add) {
         String[] currentAddresses = this.mIntent.getStringArrayExtra(extra);
         int currentLength = currentAddresses != null ? currentAddresses.length : 0;
         String[] finalAddresses = new String[currentLength + add.size()];
         add.toArray(finalAddresses);
         if (currentAddresses != null) {
            System.arraycopy(currentAddresses, 0, finalAddresses, add.size(), currentLength);
         }

         this.mIntent.putExtra(extra, finalAddresses);
      }

      private void combineArrayExtra(String extra, String[] add) {
         Intent intent = this.getIntent();
         String[] old = intent.getStringArrayExtra(extra);
         int oldLength = old != null ? old.length : 0;
         String[] result = new String[oldLength + add.length];
         if (old != null) {
            System.arraycopy(old, 0, result, 0, oldLength);
         }

         System.arraycopy(add, 0, result, oldLength, add.length);
         intent.putExtra(extra, result);
      }

      public Intent createChooserIntent() {
         return Intent.createChooser(this.getIntent(), this.mChooserTitle);
      }

      public void startChooser() {
         this.mActivity.startActivity(this.createChooserIntent());
      }

      public ShareCompat.IntentBuilder setChooserTitle(CharSequence title) {
         this.mChooserTitle = title;
         return this;
      }

      public ShareCompat.IntentBuilder setChooserTitle(@StringRes int resId) {
         return this.setChooserTitle(this.mActivity.getText(resId));
      }

      public ShareCompat.IntentBuilder setType(String mimeType) {
         this.mIntent.setType(mimeType);
         return this;
      }

      public ShareCompat.IntentBuilder setText(CharSequence text) {
         this.mIntent.putExtra("android.intent.extra.TEXT", text);
         return this;
      }

      public ShareCompat.IntentBuilder setHtmlText(String htmlText) {
         this.mIntent.putExtra("android.intent.extra.HTML_TEXT", htmlText);
         if (!this.mIntent.hasExtra("android.intent.extra.TEXT")) {
            this.setText(Html.fromHtml(htmlText));
         }

         return this;
      }

      public ShareCompat.IntentBuilder setStream(Uri streamUri) {
         if (!this.mIntent.getAction().equals("android.intent.action.SEND")) {
            this.mIntent.setAction("android.intent.action.SEND");
         }

         this.mStreams = null;
         this.mIntent.putExtra("android.intent.extra.STREAM", streamUri);
         return this;
      }

      public ShareCompat.IntentBuilder addStream(Uri streamUri) {
         Uri currentStream = (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
         if (this.mStreams == null && currentStream == null) {
            return this.setStream(streamUri);
         } else {
            if (this.mStreams == null) {
               this.mStreams = new ArrayList();
            }

            if (currentStream != null) {
               this.mIntent.removeExtra("android.intent.extra.STREAM");
               this.mStreams.add(currentStream);
            }

            this.mStreams.add(streamUri);
            return this;
         }
      }

      public ShareCompat.IntentBuilder setEmailTo(String[] addresses) {
         if (this.mToAddresses != null) {
            this.mToAddresses = null;
         }

         this.mIntent.putExtra("android.intent.extra.EMAIL", addresses);
         return this;
      }

      public ShareCompat.IntentBuilder addEmailTo(String address) {
         if (this.mToAddresses == null) {
            this.mToAddresses = new ArrayList();
         }

         this.mToAddresses.add(address);
         return this;
      }

      public ShareCompat.IntentBuilder addEmailTo(String[] addresses) {
         this.combineArrayExtra("android.intent.extra.EMAIL", addresses);
         return this;
      }

      public ShareCompat.IntentBuilder setEmailCc(String[] addresses) {
         this.mIntent.putExtra("android.intent.extra.CC", addresses);
         return this;
      }

      public ShareCompat.IntentBuilder addEmailCc(String address) {
         if (this.mCcAddresses == null) {
            this.mCcAddresses = new ArrayList();
         }

         this.mCcAddresses.add(address);
         return this;
      }

      public ShareCompat.IntentBuilder addEmailCc(String[] addresses) {
         this.combineArrayExtra("android.intent.extra.CC", addresses);
         return this;
      }

      public ShareCompat.IntentBuilder setEmailBcc(String[] addresses) {
         this.mIntent.putExtra("android.intent.extra.BCC", addresses);
         return this;
      }

      public ShareCompat.IntentBuilder addEmailBcc(String address) {
         if (this.mBccAddresses == null) {
            this.mBccAddresses = new ArrayList();
         }

         this.mBccAddresses.add(address);
         return this;
      }

      public ShareCompat.IntentBuilder addEmailBcc(String[] addresses) {
         this.combineArrayExtra("android.intent.extra.BCC", addresses);
         return this;
      }

      public ShareCompat.IntentBuilder setSubject(String subject) {
         this.mIntent.putExtra("android.intent.extra.SUBJECT", subject);
         return this;
      }
   }
}
