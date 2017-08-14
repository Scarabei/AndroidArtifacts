package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzcri;
import com.google.android.gms.plus.model.people.Person;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class PlusShare {
   public static final String EXTRA_CONTENT_URL = "com.google.android.apps.plus.CONTENT_URL";
   public static final String EXTRA_CONTENT_DEEP_LINK_ID = "com.google.android.apps.plus.CONTENT_DEEP_LINK_ID";
   public static final String EXTRA_CONTENT_DEEP_LINK_METADATA = "com.google.android.apps.plus.CONTENT_DEEP_LINK_METADATA";
   public static final String KEY_CONTENT_DEEP_LINK_METADATA_TITLE = "title";
   public static final String KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION = "description";
   public static final String KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL = "thumbnailUrl";
   public static final String EXTRA_IS_INTERACTIVE_POST = "com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST";
   public static final String EXTRA_CALL_TO_ACTION = "com.google.android.apps.plus.CALL_TO_ACTION";
   public static final String KEY_CALL_TO_ACTION_LABEL = "label";
   public static final String KEY_CALL_TO_ACTION_URL = "url";
   public static final String KEY_CALL_TO_ACTION_DEEP_LINK_ID = "deepLinkId";
   public static final String EXTRA_SENDER_ID = "com.google.android.apps.plus.SENDER_ID";
   public static final String PARAM_CONTENT_DEEP_LINK_ID = "deep_link_id";

   /** @deprecated */
   @Deprecated
   protected PlusShare() {
      throw new AssertionError();
   }

   public static String getDeepLinkId(Intent var0) {
      String var1 = null;
      if (var0 != null && var0.getData() != null) {
         var1 = var0.getData().getQueryParameter("deep_link_id");
      }

      return var1;
   }

   public static Person createPerson(String var0, String var1) {
      if (TextUtils.isEmpty(var0)) {
         throw new IllegalArgumentException("MinimalPerson ID must not be empty.");
      } else if (TextUtils.isEmpty(var1)) {
         throw new IllegalArgumentException("Display name must not be empty.");
      } else {
         return new zzcri(var1, var0, (zzcri.zzc)null, 0, (String)null);
      }
   }

   protected static boolean zzeF(String var0) {
      if (TextUtils.isEmpty(var0)) {
         Log.e("GooglePlusPlatform", "The provided deep-link ID is empty.");
         return false;
      } else if (var0.contains(" ")) {
         Log.e("GooglePlusPlatform", "Spaces are not allowed in deep-link IDs.");
         return false;
      } else {
         return true;
      }
   }

   public static class Builder {
      private final Context mContext;
      private final Intent mIntent;
      private boolean zzbAy;
      private ArrayList zzbAz;

      public Builder(Context var1) {
         this.mContext = var1;
         this.mIntent = (new Intent()).setAction("android.intent.action.SEND");
      }

      public Builder(Activity var1) {
         this.mContext = var1;
         this.mIntent = (new Intent()).setAction("android.intent.action.SEND");
         this.mIntent.addFlags(524288);
         if (var1 != null && var1.getComponentName() != null) {
            this.zzbAy = true;
         }

      }

      public PlusShare.Builder setType(String var1) {
         this.mIntent.setType(var1);
         return this;
      }

      public PlusShare.Builder setRecipients(Person var1, List var2) {
         this.mIntent.putExtra("com.google.android.apps.plus.SENDER_ID", var1 != null ? var1.getId() : "0");
         int var10000 = var2 != null ? var2.size() : 0;
         int var3 = var10000;
         if (var10000 == 0) {
            this.mIntent.removeExtra("com.google.android.apps.plus.RECIPIENT_IDS");
            this.mIntent.removeExtra("com.google.android.apps.plus.RECIPIENT_DISPLAY_NAMES");
            return this;
         } else {
            ArrayList var4 = new ArrayList(var3);
            ArrayList var5 = new ArrayList(var3);
            Iterator var6 = var2.iterator();

            while(var6.hasNext()) {
               Person var7 = (Person)var6.next();
               var4.add(var7.getId());
               var5.add(var7.getDisplayName());
            }

            this.mIntent.putStringArrayListExtra("com.google.android.apps.plus.RECIPIENT_IDS", var4);
            this.mIntent.putStringArrayListExtra("com.google.android.apps.plus.RECIPIENT_DISPLAY_NAMES", var5);
            return this;
         }
      }

      public PlusShare.Builder setText(CharSequence var1) {
         this.mIntent.putExtra("android.intent.extra.TEXT", var1);
         return this;
      }

      public PlusShare.Builder setStream(Uri var1) {
         this.zzbAz = null;
         this.mIntent.putExtra("android.intent.extra.STREAM", var1);
         return this;
      }

      public PlusShare.Builder addStream(Uri var1) {
         Uri var2;
         if ((var2 = (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM")) == null) {
            return this.setStream(var1);
         } else {
            if (this.zzbAz == null) {
               this.zzbAz = new ArrayList();
            }

            this.zzbAz.add(var2);
            this.zzbAz.add(var1);
            return this;
         }
      }

      public PlusShare.Builder setContentUrl(Uri var1) {
         String var2 = null;
         if (var1 != null) {
            var2 = var1.toString();
         }

         if (TextUtils.isEmpty(var2)) {
            this.mIntent.removeExtra("com.google.android.apps.plus.CONTENT_URL");
         } else {
            this.mIntent.putExtra("com.google.android.apps.plus.CONTENT_URL", var2);
         }

         return this;
      }

      public PlusShare.Builder setContentDeepLinkId(String var1) {
         return this.setContentDeepLinkId(var1, (String)null, (String)null, (Uri)null);
      }

      public PlusShare.Builder setContentDeepLinkId(String var1, String var2, String var3, Uri var4) {
         zzbo.zzb(this.zzbAy, "Must include the launching activity with PlusShare.Builder constructor before setting deep links");
         zzbo.zzb(!TextUtils.isEmpty(var1), "The deepLinkId parameter is required.");
         Bundle var9;
         (var9 = new Bundle()).putString("title", var2);
         var9.putString("description", var3);
         if (var4 != null) {
            var9.putString("thumbnailUrl", var4.toString());
         }

         this.mIntent.putExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID", var1);
         this.mIntent.putExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_METADATA", var9);
         this.mIntent.setType("text/plain");
         return this;
      }

      public PlusShare.Builder addCallToAction(String var1, Uri var2, String var3) {
         zzbo.zza(this.zzbAy, "Must include the launching activity with PlusShare.Builder constructor before setting call-to-action");
         zzbo.zzb(var2 != null && !TextUtils.isEmpty(var2.toString()), "Must provide a call to action URL");
         Bundle var4 = new Bundle();
         if (!TextUtils.isEmpty(var1)) {
            var4.putString("label", var1);
         }

         var4.putString("url", var2.toString());
         if (!TextUtils.isEmpty(var3)) {
            zzbo.zza(PlusShare.zzeF(var3), "The specified deep-link ID was malformed.");
            var4.putString("deepLinkId", var3);
         }

         this.mIntent.putExtra("com.google.android.apps.plus.CALL_TO_ACTION", var4);
         this.mIntent.putExtra("com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST", true);
         this.mIntent.setType("text/plain");
         return this;
      }

      public Intent getIntent() {
         boolean var1 = this.zzbAz != null && this.zzbAz.size() > 1;
         boolean var2 = "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
         boolean var3 = this.mIntent.getBooleanExtra("com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST", false);
         zzbo.zza(!var1 || !var3, "Call-to-action buttons are only available for URLs.");
         zzbo.zza(!var3 || this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_URL"), "The content URL is required for interactive posts.");
         zzbo.zza(!var3 || this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_URL") || this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID"), "Must set content URL or content deep-link ID to use a call-to-action button.");
         if (this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")) {
            zzbo.zza(PlusShare.zzeF(this.mIntent.getStringExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")), "The specified deep-link ID was malformed.");
         }

         if (!var1 && var2) {
            this.mIntent.setAction("android.intent.action.SEND");
            if (this.zzbAz != null && !this.zzbAz.isEmpty()) {
               this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)this.zzbAz.get(0));
            } else {
               this.mIntent.removeExtra("android.intent.extra.STREAM");
            }

            this.zzbAz = null;
         }

         if (var1 && !var2) {
            this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
            if (this.zzbAz != null && !this.zzbAz.isEmpty()) {
               this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.zzbAz);
            } else {
               this.mIntent.removeExtra("android.intent.extra.STREAM");
            }
         }

         if ("com.google.android.gms.plus.action.SHARE_INTERNAL_GOOGLE".equals(this.mIntent.getAction())) {
            this.mIntent.setPackage("com.google.android.gms");
            return this.mIntent;
         } else if (!this.mIntent.hasExtra("android.intent.extra.STREAM")) {
            this.mIntent.setAction("com.google.android.gms.plus.action.SHARE_GOOGLE");
            this.mIntent.setPackage("com.google.android.gms");
            return this.mIntent;
         } else {
            this.mIntent.setPackage("com.google.android.apps.plus");
            return this.mIntent;
         }
      }
   }
}
