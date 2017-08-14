package com.google.android.gms.internal;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zzzn
public final class zzahq {
   private final Context mContext;
   private String zzZB;
   private String zztV;
   private String zzwH;
   private final float zzNX;
   private float zzZC;
   private float zzZD;
   private float zzZE;
   private int mState;

   public zzahq(Context var1) {
      this.mState = 0;
      this.mContext = var1;
      this.zzNX = var1.getResources().getDisplayMetrics().density;
   }

   public zzahq(Context var1, String var2) {
      this(var1);
      this.zzZB = var2;
   }

   public final void zzf(MotionEvent var1) {
      int var2 = var1.getHistorySize();

      for(int var3 = 0; var3 < var2; ++var3) {
         this.zza(var1.getActionMasked(), var1.getHistoricalX(0, var3), var1.getHistoricalY(0, var3));
      }

      this.zza(var1.getActionMasked(), var1.getX(), var1.getY());
   }

   private final void zza(int var1, float var2, float var3) {
      if (var1 == 0) {
         this.mState = 0;
         this.zzZC = var2;
         this.zzZD = var3;
         this.zzZE = var3;
      } else if (this.mState != -1) {
         if (var1 == 2) {
            if (var3 > this.zzZD) {
               this.zzZD = var3;
            } else if (var3 < this.zzZE) {
               this.zzZE = var3;
            }

            if (this.zzZD - this.zzZE > 30.0F * this.zzNX) {
               this.mState = -1;
               return;
            }

            if (this.mState != 0 && this.mState != 2) {
               if ((this.mState == 1 || this.mState == 3) && var2 - this.zzZC <= -50.0F * this.zzNX) {
                  this.zzZC = var2;
                  ++this.mState;
               }
            } else if (var2 - this.zzZC >= 50.0F * this.zzNX) {
               this.zzZC = var2;
               ++this.mState;
            }

            if (this.mState != 1 && this.mState != 3) {
               if (this.mState == 2 && var2 < this.zzZC) {
                  this.zzZC = var2;
                  return;
               }
            } else if (var2 > this.zzZC) {
               this.zzZC = var2;
               return;
            }
         } else if (var1 == 1 && this.mState == 4) {
            this.showDialog();
         }

      }
   }

   public final void showDialog() {
      zzme var1 = zzmo.zzGs;
      if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var1)).booleanValue()) {
         var1 = zzmo.zzGr;
         if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var1)).booleanValue()) {
            this.zzhY();
            return;
         }
      }

      if (!(this.mContext instanceof Activity)) {
         zzafr.zzaS("Can not create dialog without Activity Context");
      } else {
         ArrayList var2;
         int var3 = zza(var2 = new ArrayList(), "Ad Information", true);
         zzme var7 = zzmo.zzGr;
         int var4 = zza(var2, "Creative Preview", ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var7)).booleanValue());
         var7 = zzmo.zzGs;
         int var5 = zza(var2, "Troubleshooting", ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var7)).booleanValue());
         Builder var6;
         (var6 = new Builder(this.mContext, com.google.android.gms.ads.internal.zzbs.zzbB().zzhX())).setTitle("Select a Debug Mode").setItems((CharSequence[])var2.toArray(new String[0]), new zzahr(this, var3, var4, var5));
         var6.create().show();
      }
   }

   public final void setAdUnitId(String var1) {
      this.zztV = var1;
   }

   public final void zzaO(String var1) {
      this.zzwH = var1;
   }

   public final void zzaP(String var1) {
      this.zzZB = var1;
   }

   private static int zza(List var0, String var1, boolean var2) {
      if (!var2) {
         return -1;
      } else {
         var0.add(var1);
         return var0.size() - 1;
      }
   }

   private final void zzhY() {
      if (!(this.mContext instanceof Activity)) {
         zzafr.zzaS("Can not create dialog without Activity Context");
      } else {
         String var10000;
         label24: {
            String var3 = this.zzZB;
            if (!TextUtils.isEmpty(this.zzZB)) {
               var3 = var3.replaceAll("\\+", "%20");
               Uri var4 = (new android.net.Uri.Builder()).encodedQuery(var3).build();
               StringBuilder var5 = new StringBuilder();
               com.google.android.gms.ads.internal.zzbs.zzbz();
               Map var6;
               Iterator var7 = (var6 = zzagz.zzg(var4)).keySet().iterator();

               while(var7.hasNext()) {
                  String var8 = (String)var7.next();
                  var5.append(var8).append(" = ").append((String)var6.get(var8)).append("\n\n");
               }

               String var9;
               if (!TextUtils.isEmpty(var9 = var5.toString().trim())) {
                  var10000 = var9;
                  break label24;
               }
            }

            var10000 = "No debug information";
         }

         String var1 = var10000;
         Builder var2;
         (var2 = new Builder(this.mContext)).setMessage(var1);
         var2.setTitle("Ad Information");
         var2.setPositiveButton("Share", new zzahs(this, var1));
         var2.setNegativeButton("Close", new zzaht(this));
         var2.create().show();
      }
   }

   private final void zzhZ() {
      zzafr.zzaC("Debug mode [Creative Preview] selected.");
      zzagt.zza((Runnable)(new zzahu(this)));
   }

   private final void zzia() {
      zzafr.zzaC("Debug mode [Troubleshooting] selected.");
      zzagt.zza((Runnable)(new zzahv(this)));
   }

   // $FF: synthetic method
   static void zza(zzahq var0) {
      var0.zzhY();
   }

   // $FF: synthetic method
   static void zzb(zzahq var0) {
      var0.zzhZ();
   }

   // $FF: synthetic method
   static void zzc(zzahq var0) {
      var0.zzia();
   }

   // $FF: synthetic method
   static Context zzd(zzahq var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static String zze(zzahq var0) {
      return var0.zztV;
   }

   // $FF: synthetic method
   static String zzf(zzahq var0) {
      return var0.zzwH;
   }
}
