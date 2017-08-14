package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbo;

@zzzn
public final class zzlz {
   private final Context mContext;

   public zzlz(Context var1) {
      zzbo.zzb(var1, "Context can not be null");
      this.mContext = var1;
   }

   public final boolean zzdE() {
      Intent var1;
      (var1 = new Intent("android.intent.action.DIAL")).setData(Uri.parse("tel:"));
      return this.zza(var1);
   }

   public final boolean zzdF() {
      Intent var1;
      (var1 = new Intent("android.intent.action.VIEW")).setData(Uri.parse("sms:"));
      return this.zza(var1);
   }

   public final boolean zzdG() {
      return ((Boolean)zzait.zzb(new zzma())).booleanValue() && zzbha.zzaP(this.mContext).checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
   }

   @TargetApi(14)
   public final boolean zzdH() {
      Intent var1 = (new Intent("android.intent.action.INSERT")).setType("vnd.android.cursor.dir/event");
      return this.zza(var1);
   }

   private final boolean zza(Intent var1) {
      zzbo.zzb(var1, "Intent can not be null");
      return !this.mContext.getPackageManager().queryIntentActivities(var1, 0).isEmpty();
   }
}
