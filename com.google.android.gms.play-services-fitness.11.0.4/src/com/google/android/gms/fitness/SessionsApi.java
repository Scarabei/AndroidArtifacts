package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zze;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;

public interface SessionsApi {
   PendingResult startSession(GoogleApiClient var1, Session var2);

   PendingResult stopSession(GoogleApiClient var1, String var2);

   PendingResult insertSession(GoogleApiClient var1, SessionInsertRequest var2);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"},
      conditional = true
   )
   PendingResult readSession(GoogleApiClient var1, SessionReadRequest var2);

   PendingResult registerForSessions(GoogleApiClient var1, PendingIntent var2);

   PendingResult unregisterForSessions(GoogleApiClient var1, PendingIntent var2);

   public static class ViewIntentBuilder {
      private final Context mContext;
      private Session zzaTe;
      private String zzaTf;
      private boolean zzaTg = false;

      public ViewIntentBuilder(Context var1) {
         this.mContext = var1;
      }

      public SessionsApi.ViewIntentBuilder setSession(Session var1) {
         this.zzaTe = var1;
         return this;
      }

      public SessionsApi.ViewIntentBuilder setPreferredApplication(String var1) {
         this.zzaTf = var1;
         this.zzaTg = true;
         return this;
      }

      public Intent build() {
         zzbo.zza(this.zzaTe != null, "Session must be set");
         Intent var1;
         (var1 = new Intent("vnd.google.fitness.VIEW")).setType(Session.getMimeType(this.zzaTe.getActivity()));
         zze.zza(this.zzaTe, var1, "vnd.google.fitness.session");
         if (!this.zzaTg) {
            this.zzaTf = this.zzaTe.getAppPackageName();
         }

         if (this.zzaTf != null) {
            Intent var4 = (new Intent(var1)).setPackage(this.zzaTf);
            ResolveInfo var5;
            if ((var5 = this.mContext.getPackageManager().resolveActivity(var4, 0)) != null) {
               String var6 = var5.activityInfo.name;
               var4.setComponent(new ComponentName(this.zzaTf, var6));
               return var4;
            }
         }

         return var1;
      }
   }
}
