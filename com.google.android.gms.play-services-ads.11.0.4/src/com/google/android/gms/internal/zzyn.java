package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzyn implements Callable {
   private static long zzRj;
   private final Context mContext;
   private final zzaie zzRv;
   private final zzbb zzRw;
   private final zzcu zzIc;
   private zzyh zzuP;
   private final Object mLock = new Object();
   private final zzafg zzQQ;
   private final zznb zzsK;
   private boolean zzRx;
   private int mErrorCode;
   private List zzRy;
   private JSONObject zzRz;
   private String zzRA;
   private boolean zzRB;

   public zzyn(Context var1, zzbb var2, zzaie var3, zzcu var4, zzafg var5, zznb var6) {
      this.mContext = var1;
      this.zzRw = var2;
      this.zzRv = var3;
      this.zzQQ = var5;
      this.zzIc = var4;
      this.zzsK = var6;
      zzme var7 = zzmo.zzFu;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var7)).booleanValue()) {
         this.zzuP = var2.zzbi();
      }

      if (this.zzuP == null) {
         this.zzuP = new zzyh(var1, var5, var2, var4);
         this.zzuP.zzgs();
         this.zzRB = true;
      }

      this.zzRx = false;
      this.mErrorCode = -2;
      this.zzRy = null;
      this.zzRA = null;
   }

   private final zzaff zzgw() {
      try {
         if (this.zzRB) {
            this.zzuP.zzgt();
         }

         String var1 = UUID.randomUUID().toString();
         zzyw var8;
         JSONObject var10000;
         if (this.zzgx()) {
            var10000 = null;
         } else {
            zzajg var7 = new zzajg();
            var8 = new zzyw(this);
            zzyo var9 = new zzyo(this, var1, var8, var7);
            this.zzuP.zza((zzym)var9);
            var10000 = (JSONObject)var7.get(zzRj, TimeUnit.MILLISECONDS);
         }

         JSONObject var2 = var10000;
         Object var26;
         if (!this.zzgx() && var2 != null) {
            String var18 = var2.getString("template_id");
            boolean var20 = this.zzQQ.zzUj.zzwj != null ? this.zzQQ.zzUj.zzwj.zzIn : false;
            boolean var21 = this.zzQQ.zzUj.zzwj != null ? this.zzQQ.zzUj.zzwj.zzIp : false;
            if ("2".equals(var18)) {
               var26 = new zzzf(var20, var21);
            } else if ("1".equals(var18)) {
               var26 = new zzzg(var20, var21);
            } else {
               label105: {
                  if ("3".equals(var18)) {
                     String var10 = var2.getString("custom_template_id");
                     zzajg var11 = new zzajg();
                     zzagz.zzZr.post(new zzyq(this, var11, var10));
                     if (var11.get(zzRj, TimeUnit.MILLISECONDS) != null) {
                        var26 = new zzzh(var20);
                        break label105;
                     }

                     String var10001 = String.valueOf(var2.getString("custom_template_id"));
                     String var27;
                     if (var10001.length() != 0) {
                        var27 = "No handler for custom template: ".concat(var10001);
                     } else {
                        String var10002 = new String;
                        var27 = var10002;
                        var10002.<init>("No handler for custom template: ");
                     }

                     zzafr.e(var27);
                  } else {
                     this.zzt(0);
                  }

                  var26 = null;
               }
            }
         } else {
            var26 = null;
         }

         Object var3 = var26;
         zzoa var28;
         if (!this.zzgx() && var3 != null && var2 != null) {
            JSONObject var22;
            String[] var24 = zzd(var22 = var2.getJSONObject("tracking_urls_and_actions"), "impression_tracking_urls");
            this.zzRy = var24 == null ? null : Arrays.asList(var24);
            this.zzRz = var22.optJSONObject("active_view");
            this.zzRA = var2.optString("debug_signals");
            zzoa var25;
            (var25 = ((zzyv)var3).zza(this, var2)).zzb(new zzoc(this.mContext, this.zzRw, this.zzuP, this.zzIc, var2, var25, this.zzQQ.zzUj.zzvT, var1));
            var28 = var25;
         } else {
            var28 = null;
         }

         zzoa var4 = var28;
         if (var4 instanceof zznu) {
            zznu var19 = (zznu)var4;
            var8 = new zzyw(this);
            zzyr var23 = new zzyr(this, var19);
            var8.zzRW = var23;
            this.zzuP.zza((zzym)(new zzys(this, var23)));
         }

         return this.zza(var4);
      } catch (CancellationException var12) {
         ;
      } catch (ExecutionException var13) {
         ;
      } catch (InterruptedException var14) {
         ;
      } catch (JSONException var15) {
         zzafr.zzc("Malformed native JSON response.", var15);
      } catch (TimeoutException var16) {
         zzafr.zzc("Timeout when loading native ad.", var16);
      } catch (Exception var17) {
         zzafr.zzc("Error occured while doing native ads initialization.", var17);
      }

      if (!this.zzRx) {
         this.zzt(0);
      }

      return this.zza((zzoa)null);
   }

   private final void zzc(zzpj var1, String var2) {
      try {
         zzpt var3;
         if ((var3 = this.zzRw.zzs(var1.getCustomTemplateId())) != null) {
            var3.zzb(var1, var2);
         }

      } catch (RemoteException var4) {
         zzafr.zzc((new StringBuilder(40 + String.valueOf(var2).length())).append("Failed to call onCustomClick for asset ").append(var2).append(".").toString(), var4);
      }
   }

   private final zzaff zza(zzoa var1) {
      Object var3 = this.mLock;
      int var2;
      synchronized(this.mLock) {
         var2 = this.mErrorCode;
         if (var1 == null && this.mErrorCode == -2) {
            var2 = 0;
         }
      }

      if (var2 != -2) {
         var1 = null;
      }

      return new zzaff(this.zzQQ.zzUj.zzSz, (zzaka)null, this.zzQQ.zzXY.zzMa, var2, this.zzQQ.zzXY.zzMb, this.zzRy, this.zzQQ.zzXY.orientation, this.zzQQ.zzXY.zzMg, this.zzQQ.zzUj.zzSC, false, (zzua)null, (zzut)null, (String)null, (zzub)null, (zzud)null, 0L, this.zzQQ.zzvX, this.zzQQ.zzXY.zzTn, this.zzQQ.zzXR, this.zzQQ.zzXS, this.zzQQ.zzXY.zzTt, this.zzRz, var1, (zzaee)null, (List)null, (List)null, this.zzQQ.zzXY.zzTG, this.zzQQ.zzXY.zzTH, (String)null, this.zzQQ.zzXY.zzMd, this.zzRA, this.zzQQ.zzXX);
   }

   public final zzajm zzd(JSONObject var1) throws JSONException {
      JSONObject var2;
      if ((var2 = var1.optJSONObject("attribution")) == null) {
         return new zzajh((Object)null);
      } else {
         String var3 = var2.optString("text");
         int var4 = var2.optInt("text_size", -1);
         Integer var5 = zzb(var2, "text_color");
         Integer var6 = zzb(var2, "bg_color");
         int var7 = var2.optInt("animation_ms", 1000);
         int var8 = var2.optInt("presentation_ms", 4000);
         int var9 = this.zzQQ.zzUj.zzwj != null && this.zzQQ.zzUj.zzwj.versionCode >= 2 ? this.zzQQ.zzUj.zzwj.zzIq : 1;
         boolean var10 = var2.optBoolean("allow_pub_rendering");
         Object var11 = new ArrayList();
         if (var2.optJSONArray("images") != null) {
            var11 = this.zza(var2, "images", false, false, true);
         } else {
            ((List)var11).add(this.zza(var2, "image", false, false));
         }

         return zzaji.zza((zzajm)zzaji.zzp((List)var11), (zzajl)(new zzyt(this, var3, var6, var5, var4, var8, var7, var9, var10)));
      }
   }

   private static Integer zzb(JSONObject var0, String var1) {
      try {
         JSONObject var2;
         int var3 = (var2 = var0.getJSONObject(var1)).getInt("r");
         int var4 = var2.getInt("g");
         int var5 = var2.getInt("b");
         return Color.rgb(var3, var4, var5);
      } catch (JSONException var6) {
         return null;
      }
   }

   public final Future zza(JSONObject var1, String var2, boolean var3) throws JSONException {
      JSONObject var4;
      boolean var5 = (var4 = var1.getJSONObject(var2)).optBoolean("require", true);
      var4 = var4 == null ? new JSONObject() : var4;
      return this.zza(var4, var5, var3);
   }

   public final zzajm zza(JSONObject var1, String var2, boolean var3, boolean var4) throws JSONException {
      JSONObject var5 = (var5 = var3 ? var1.getJSONObject(var2) : var1.optJSONObject(var2)) == null ? new JSONObject() : var5;
      return this.zza(var5, var3, var4);
   }

   public final List zza(JSONObject var1, String var2, boolean var3, boolean var4, boolean var5) throws JSONException {
      JSONArray var6 = var1.optJSONArray(var2);
      ArrayList var7 = new ArrayList();
      if (var6 != null && var6.length() != 0) {
         int var8 = var5 ? var6.length() : 1;

         for(int var9 = 0; var9 < var8; ++var9) {
            JSONObject var10 = (var10 = var6.getJSONObject(var9)) == null ? new JSONObject() : var10;
            var7.add(this.zza(var10, false, var4));
         }

         return var7;
      } else {
         this.zzc(0, false);
         return var7;
      }
   }

   private final zzajm zza(JSONObject var1, boolean var2, boolean var3) throws JSONException {
      String var4 = var2 ? var1.getString("url") : var1.optString("url");
      double var5 = var1.optDouble("scale", 1.0D);
      boolean var7 = var1.optBoolean("is_transparent", true);
      if (TextUtils.isEmpty(var4)) {
         this.zzc(0, var2);
         return new zzajh((Object)null);
      } else {
         return (zzajm)(var3 ? new zzajh(new zznp((Drawable)null, Uri.parse(var4), var5)) : this.zzRv.zza(var4, new zzyu(this, var2, var5, var7, var4)));
      }
   }

   public final zzajm zzc(JSONObject var1, String var2) throws JSONException {
      JSONObject var3;
      if ((var3 = var1.optJSONObject(var2)) == null) {
         return new zzajh((Object)null);
      } else if (TextUtils.isEmpty(var3.optString("vast_xml"))) {
         zzafr.zzaT("Required field 'vast_xml' is missing");
         return new zzajh((Object)null);
      } else {
         zzbb var8 = this.zzRw;
         zznb var7 = this.zzsK;
         zzafg var6 = this.zzQQ;
         zzcu var5 = this.zzIc;
         Context var4 = this.mContext;
         zzyx var10 = new zzyx(var4, var5, var6, var7, var8);
         zzajg var9 = new zzajg();
         com.google.android.gms.ads.internal.zzbs.zzbz();
         zzagz.runOnUiThread(new zzyy(var10, var3, var9));
         return var9;
      }
   }

   public final boolean zzgx() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzRx;
      }
   }

   public final void zzt(int var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzRx = true;
         this.mErrorCode = var1;
      }
   }

   public final void zzc(int var1, boolean var2) {
      if (var2) {
         this.zzt(var1);
      }

   }

   static zzaka zzb(zzajm var0) {
      try {
         zzme var2 = zzmo.zzFz;
         return (zzaka)var0.get((long)((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).intValue(), TimeUnit.SECONDS);
      } catch (InterruptedException var3) {
         zzafr.zzc("InterruptedException occurred while waiting for video to load", var3);
         Thread.currentThread().interrupt();
      } catch (TimeoutException | CancellationException | ExecutionException var4) {
         zzafr.zzc("Exception occurred while waiting for video to load", var4);
      }

      return null;
   }

   private static String[] zzd(JSONObject var0, String var1) throws JSONException {
      JSONArray var2;
      if ((var2 = var0.optJSONArray(var1)) == null) {
         return null;
      } else {
         String[] var3 = new String[var2.length()];

         for(int var4 = 0; var4 < var2.length(); ++var4) {
            var3[var4] = var2.getString(var4);
         }

         return var3;
      }
   }

   private static List zzj(List var0) throws RemoteException {
      ArrayList var1 = new ArrayList();
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         zznp var3 = (zznp)var2.next();
         var1.add((Drawable)zzn.zzE(var3.zzeg()));
      }

      return var1;
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      return this.zzgw();
   }

   // $FF: synthetic method
   static zzafg zza(zzyn var0) {
      return var0.zzQQ;
   }

   // $FF: synthetic method
   static zzbb zzb(zzyn var0) {
      return var0.zzRw;
   }

   // $FF: synthetic method
   static void zza(zzyn var0, zzpj var1, String var2) {
      var0.zzc(var1, var2);
   }

   // $FF: synthetic method
   static List zzk(List var0) throws RemoteException {
      return zzj(var0);
   }

   static {
      zzRj = TimeUnit.SECONDS.toMillis(60L);
   }
}
