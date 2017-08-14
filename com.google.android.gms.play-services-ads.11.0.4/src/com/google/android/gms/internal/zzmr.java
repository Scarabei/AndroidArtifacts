package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@zzzn
public final class zzmr {
   private BlockingQueue zzGL;
   private ExecutorService zzGM;
   private LinkedHashMap zzGN = new LinkedHashMap();
   private Map zzGO = new HashMap();
   private String zzGJ;
   private Context mContext;
   private String zzwH;
   private AtomicBoolean zzGP;
   private File zzGQ;

   public zzmr(Context var1, String var2, String var3, Map var4) {
      this.mContext = var1;
      this.zzwH = var2;
      this.zzGJ = var3;
      this.zzGP = new AtomicBoolean(false);
      zzme var7 = zzmo.zzCS;
      this.zzGP.set(((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var7)).booleanValue());
      File var5;
      if (this.zzGP.get() && (var5 = Environment.getExternalStorageDirectory()) != null) {
         this.zzGQ = new File(var5, "sdk_csi_data.txt");
      }

      Iterator var8 = var4.entrySet().iterator();

      while(var8.hasNext()) {
         Entry var6 = (Entry)var8.next();
         this.zzGN.put((String)var6.getKey(), (String)var6.getValue());
      }

      this.zzGL = new ArrayBlockingQueue(30);
      this.zzGM = Executors.newSingleThreadExecutor();
      this.zzGM.execute(new zzms(this));
      this.zzGO.put("action", zzmv.zzGT);
      this.zzGO.put("ad_format", zzmv.zzGT);
      this.zzGO.put("e", zzmv.zzGU);
   }

   public final void zze(@Nullable List var1) {
      if (var1 != null && !var1.isEmpty()) {
         this.zzGN.put("e", TextUtils.join(",", var1));
      }

   }

   public final boolean zza(zznb var1) {
      return this.zzGL.offer(var1);
   }

   private final void zzdO() {
      while(true) {
         zznb var1;
         String var2;
         try {
            var2 = (var1 = (zznb)this.zzGL.take()).zzdU();
         } catch (InterruptedException var29) {
            zzafr.zzc("CsiReporter:reporter interrupted", var29);
            return;
         }

         if (!TextUtils.isEmpty(var2)) {
            Map var5 = this.zza(this.zzGN, var1.zzdV());
            Builder var10 = Uri.parse(this.zzGJ).buildUpon();
            Iterator var11 = var5.entrySet().iterator();

            while(var11.hasNext()) {
               Entry var12 = (Entry)var11.next();
               var10.appendQueryParameter((String)var12.getKey(), (String)var12.getValue());
            }

            StringBuilder var32;
            (var32 = new StringBuilder(var10.build().toString())).append("&it=").append(var2);
            String var7 = var32.toString();
            if (!this.zzGP.get()) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               zzagz.zzd(this.mContext, this.zzwH, var7);
            } else {
               String var14 = var7;
               File var13 = this.zzGQ;
               if (this.zzGQ != null) {
                  FileOutputStream var15 = null;
                  boolean var25 = false;

                  label115: {
                     try {
                        var25 = true;
                        (var15 = new FileOutputStream(var13, true)).write(var14.getBytes());
                        var15.write(10);
                        var25 = false;
                        break label115;
                     } catch (IOException var30) {
                        zzafr.zzc("CsiReporter: Cannot write to file: sdk_csi_data.txt.", var30);
                        var25 = false;
                     } finally {
                        if (var25) {
                           try {
                              if (var15 != null) {
                                 var15.close();
                              }
                           } catch (IOException var27) {
                              zzafr.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", var27);
                           }

                        }
                     }

                     try {
                        if (var15 != null) {
                           var15.close();
                        }
                     } catch (IOException var26) {
                        zzafr.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", var26);
                     }
                     continue;
                  }

                  try {
                     var15.close();
                  } catch (IOException var28) {
                     zzafr.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", var28);
                  }
               } else {
                  zzafr.zzaT("CsiReporter: File doesn't exists. Cannot write CSI data to file.");
               }
            }
         }
      }
   }

   final Map zza(Map var1, @Nullable Map var2) {
      LinkedHashMap var3 = new LinkedHashMap(var1);
      if (var2 == null) {
         return var3;
      } else {
         Iterator var4 = var2.entrySet().iterator();

         while(var4.hasNext()) {
            Entry var5;
            String var6 = (String)(var5 = (Entry)var4.next()).getKey();
            String var7 = (String)var5.getValue();
            String var8 = (String)var3.get(var6);
            var3.put(var6, this.zzM(var6).zzg(var8, var7));
         }

         return var3;
      }
   }

   public final zzmv zzM(String var1) {
      zzmv var2;
      return (var2 = (zzmv)this.zzGO.get(var1)) != null ? var2 : zzmv.zzGS;
   }

   // $FF: synthetic method
   static void zza(zzmr var0) {
      var0.zzdO();
   }
}
