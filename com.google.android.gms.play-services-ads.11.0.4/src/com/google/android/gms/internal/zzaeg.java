package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzq;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzaeg implements zzaet {
   private final adt zzWX;
   private final LinkedHashMap zzWY;
   private final Context mContext;
   private final zzael zzWZ;
   @VisibleForTesting
   boolean zzXa;
   private final zzaeq zzVu;
   private final Object mLock;
   private HashSet zzXb;
   private boolean zzXc;
   private boolean zzXd;
   private boolean zzXe;

   public zzaeg(Context var1, zzaje var2, zzaai var3) {
      this(var1, var2, var3, new zzael());
   }

   @VisibleForTesting
   private zzaeg(Context var1, zzaje var2, zzaai var3, zzael var4) {
      this.mLock = new Object();
      this.zzXb = new HashSet();
      this.zzXc = false;
      this.zzXd = false;
      this.zzXe = false;
      zzbo.zzb(var3.zzTJ, "SafeBrowsing config is not present.");
      this.mContext = var1.getApplicationContext() != null ? var1.getApplicationContext() : var1;
      this.zzWY = new LinkedHashMap();
      this.zzWZ = var4;
      this.zzVu = var3.zzTJ;
      Iterator var5 = this.zzVu.zzXv.iterator();

      while(var5.hasNext()) {
         String var6 = (String)var5.next();
         this.zzXb.add(var6.toLowerCase(Locale.ENGLISH));
      }

      this.zzXb.remove("cookie".toLowerCase(Locale.ENGLISH));
      adt var10;
      (var10 = new adt()).zzcsJ = Integer.valueOf(8);
      var10.url = var3.zzPi;
      var10.zzcsL = var3.zzPi;
      var10.zzcsN = new adu();
      var10.zzcsN.zzXr = this.zzVu.zzXr;
      aec var11;
      (var11 = new aec()).zzctu = var2.zzaP;
      zze.zzoW();
      long var12;
      if ((var12 = (long)zze.zzau(this.mContext)) > 0L) {
         var11.zzctv = var12;
      }

      var10.zzcsX = var11;
      this.zzWX = var10;
   }

   public final zzaeq zzgY() {
      return this.zzVu;
   }

   public final void zzaA(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzWX.zzcsP = var1;
      }
   }

   public final boolean zzgZ() {
      return zzq.zzsc() && this.zzVu.zzXt && !this.zzXd;
   }

   public final void zzk(View var1) {
      if (this.zzVu.zzXt) {
         if (!this.zzXd) {
            com.google.android.gms.ads.internal.zzbs.zzbz();
            Bitmap var2;
            if ((var2 = zzagz.zzm(var1)) == null) {
               zzaes.zzaC("Failed to capture the webview bitmap.");
            } else {
               this.zzXd = true;
               zzagz.zzb(new zzaeh(this, var2));
            }
         }
      }
   }

   public final void zza(String var1, Map var2, int var3) {
      Object var4 = this.mLock;
      synchronized(this.mLock) {
         if (var3 == 3) {
            this.zzXe = true;
         }

         if (this.zzWY.containsKey(var1)) {
            if (var3 == 3) {
               ((aeb)this.zzWY.get(var1)).zzcts = var3;
            }

         } else {
            aeb var5;
            (var5 = new aeb()).zzcts = var3;
            var5.zzbuM = this.zzWY.size();
            var5.url = var1;
            var5.zzctn = new adw();
            if (this.zzXb.size() > 0 && var2 != null) {
               LinkedList var6 = new LinkedList();
               Iterator var7 = var2.entrySet().iterator();

               while(var7.hasNext()) {
                  Entry var8 = (Entry)var7.next();

                  try {
                     String var9 = var8.getKey() != null ? (String)var8.getKey() : "";
                     String var10 = var8.getValue() != null ? (String)var8.getValue() : "";
                     String var11 = var9.toLowerCase(Locale.ENGLISH);
                     if (this.zzXb.contains(var11)) {
                        adv var12;
                        (var12 = new adv()).zzcsZ = var9.getBytes("UTF-8");
                        var12.zzcnR = var10.getBytes("UTF-8");
                        var6.add(var12);
                     }
                  } catch (UnsupportedEncodingException var14) {
                     zzaes.zzaC("Cannot convert string to bytes, skip header.");
                  }
               }

               adv[] var16 = new adv[var6.size()];
               var6.toArray(var16);
               var5.zzctn.zzctb = var16;
            }

            this.zzWY.put(var1, var5);
         }
      }
   }

   public final void zzha() {
      this.zzXc = true;
   }

   @Nullable
   private final aeb zzaB(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         return (aeb)this.zzWY.get(var1);
      }
   }

   public final void zzhb() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzajm var2;
         (var2 = this.zzWZ.zza(this.mContext, this.zzWY.keySet())).zzc(new zzaei(this, var2));
      }
   }

   @VisibleForTesting
   final void zzi(@Nullable Map var1) throws JSONException {
      if (var1 != null) {
         Iterator var2 = var1.keySet().iterator();

         while(true) {
            String var3;
            String var4;
            JSONArray var5;
            do {
               if (!var2.hasNext()) {
                  return;
               }

               var3 = (String)var2.next();
               var4 = (String)var1.get(var3);
            } while((var5 = (new JSONObject(var4)).optJSONArray("matches")) == null);

            Object var6 = this.mLock;
            synchronized(this.mLock) {
               int var7 = var5.length();
               aeb var8;
               if ((var8 = this.zzaB(var3)) == null) {
                  String var10001 = String.valueOf(var3);
                  String var10000;
                  if (var10001.length() != 0) {
                     var10000 = "Cannot find the corresponding resource object for ".concat(var10001);
                  } else {
                     String var10002 = new String;
                     var10000 = var10002;
                     var10002.<init>("Cannot find the corresponding resource object for ");
                  }

                  zzaes.zzaC(var10000);
               } else {
                  var8.zzctt = new String[var7];

                  for(int var9 = 0; var9 < var7; ++var9) {
                     var8.zzctt[var9] = var5.getJSONObject(var9).getString("threat_type");
                  }

                  this.zzXa |= var7 > 0;
               }
            }
         }
      }
   }

   @VisibleForTesting
   final void send() {
      if (this.zzXa && this.zzVu.zzXx || this.zzXe && this.zzVu.zzXw || !this.zzXa && this.zzVu.zzXu) {
         Object var1 = this.mLock;
         synchronized(this.mLock) {
            this.zzWX.zzcsO = new aeb[this.zzWY.size()];
            this.zzWY.values().toArray(this.zzWX.zzcsO);
            if (zzaes.isEnabled()) {
               String var3 = String.valueOf(this.zzWX.url);
               String var4 = String.valueOf(this.zzWX.zzcsP);
               StringBuilder var2 = new StringBuilder((new StringBuilder(53 + String.valueOf(var3).length() + String.valueOf(var4).length())).append("Sending SB report\n  url: ").append(var3).append("\n  clickUrl: ").append(var4).append("\n  resources: \n").toString());
               aeb[] var13 = this.zzWX.zzcsO;
               int var14 = this.zzWX.zzcsO.length;

               for(int var5 = 0; var5 < var14; ++var5) {
                  aeb var6 = var13[var5];
                  var2.append("    [");
                  var2.append(var6.zzctt.length);
                  var2.append("] ");
                  var2.append(var6.url);
               }

               zzaes.zzaC(var2.toString());
            }

            byte[] var10001 = adp.zzc(this.zzWX);
            String var10 = this.zzVu.zzXs;
            byte[] var9 = var10001;
            zzajm var11 = (new zzaie(this.mContext)).zza(1, var10, (Map)null, var9);
            if (zzaes.isEnabled()) {
               var11.zzc(new zzaej(this));
            }

         }
      }
   }

   // $FF: synthetic method
   static Object zza(zzaeg var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static adt zzb(zzaeg var0) {
      return var0.zzWX;
   }
}
