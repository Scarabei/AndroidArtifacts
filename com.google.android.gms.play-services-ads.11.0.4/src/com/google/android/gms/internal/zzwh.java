package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.google.android.gms.R.string;
import java.util.Map;

@zzzn
public final class zzwh extends zzwu {
   private final Map zzHa;
   private final Context mContext;
   private String zzNs;
   private long zzNt;
   private long zzNu;
   private String zzNv;
   private String zzNw;

   public zzwh(zzaka var1, Map var2) {
      super(var1, "createCalendarEvent");
      this.zzHa = var2;
      this.mContext = var1.zzis();
      this.zzNs = this.zzal("description");
      this.zzNv = this.zzal("summary");
      this.zzNt = this.zzam("start_ticks");
      this.zzNu = this.zzam("end_ticks");
      this.zzNw = this.zzal("location");
   }

   private final String zzal(String var1) {
      return TextUtils.isEmpty((CharSequence)this.zzHa.get(var1)) ? "" : (String)this.zzHa.get(var1);
   }

   private final long zzam(String var1) {
      String var2;
      if ((var2 = (String)this.zzHa.get(var1)) == null) {
         return -1L;
      } else {
         try {
            return Long.parseLong(var2);
         } catch (NumberFormatException var3) {
            return -1L;
         }
      }
   }

   public final void execute() {
      if (this.mContext == null) {
         this.zzan("Activity context is not available.");
      } else {
         com.google.android.gms.ads.internal.zzbs.zzbz();
         if (!zzagz.zzH(this.mContext).zzdH()) {
            this.zzan("This feature is not available on the device.");
         } else {
            com.google.android.gms.ads.internal.zzbs.zzbz();
            Builder var1 = zzagz.zzG(this.mContext);
            Resources var2 = com.google.android.gms.ads.internal.zzbs.zzbD().getResources();
            var1.setTitle(var2 != null ? var2.getString(string.s5) : "Create calendar event");
            var1.setMessage(var2 != null ? var2.getString(string.s6) : "Allow Ad to create a calendar event?");
            var1.setPositiveButton(var2 != null ? var2.getString(string.s3) : "Accept", new zzwi(this));
            var1.setNegativeButton(var2 != null ? var2.getString(string.s4) : "Decline", new zzwj(this));
            var1.create().show();
         }
      }
   }

   @TargetApi(14)
   final Intent createIntent() {
      Intent var1;
      (var1 = (new Intent("android.intent.action.EDIT")).setData(Events.CONTENT_URI)).putExtra("title", this.zzNs);
      var1.putExtra("eventLocation", this.zzNw);
      var1.putExtra("description", this.zzNv);
      if (this.zzNt > -1L) {
         var1.putExtra("beginTime", this.zzNt);
      }

      if (this.zzNu > -1L) {
         var1.putExtra("endTime", this.zzNu);
      }

      var1.setFlags(268435456);
      return var1;
   }

   // $FF: synthetic method
   static Context zza(zzwh var0) {
      return var0.mContext;
   }
}
