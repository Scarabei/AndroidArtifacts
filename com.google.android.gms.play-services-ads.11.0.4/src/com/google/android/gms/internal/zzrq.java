package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.ads.internal.overlay.zzc;
import java.net.URISyntaxException;
import java.util.Map;

@zzzn
public final class zzrq implements zzrd {
   private final zzw zzJE;
   private final zzwk zzJF;

   public zzrq(zzw var1, zzwk var2) {
      this.zzJE = var1;
      this.zzJF = var2;
   }

   public final void zza(zzaka var1, Map var2) {
      String var3 = zzaez.zzb((String)var2.get("u"), var1.getContext());
      String var4;
      if ((var4 = (String)var2.get("a")) == null) {
         zzafr.zzaT("Action missing from an open GMSG.");
      } else if (this.zzJE != null && !this.zzJE.zzaR()) {
         this.zzJE.zzt(var3);
      } else {
         zzakb var5 = var1.zziw();
         if ("expand".equalsIgnoreCase(var4)) {
            if (var1.zziA()) {
               zzafr.zzaT("Cannot expand WebView that is already expanded.");
            } else {
               this.zzj(false);
               var5.zza(zze(var2), zzf(var2));
            }
         } else if ("webapp".equalsIgnoreCase(var4)) {
            this.zzj(false);
            if (var3 != null) {
               var5.zza(zze(var2), zzf(var2), var3);
            } else {
               var5.zza(zze(var2), zzf(var2), (String)var2.get("html"), (String)var2.get("baseurl"));
            }
         } else {
            if (!"in_app_purchase".equalsIgnoreCase(var4)) {
               if ("app".equalsIgnoreCase(var4) && "true".equalsIgnoreCase((String)var2.get("system_browser"))) {
                  this.zzj(true);
                  Context var14 = var1.getContext();
                  if (TextUtils.isEmpty(var3)) {
                     zzafr.zzaT("Destination url cannot be empty.");
                     return;
                  }

                  zzakb var15 = var1.zziw();
                  Intent var16 = (new zzrr(var1)).zza(var14, var2);

                  try {
                     var15.zza(new zzc(var16));
                     return;
                  } catch (ActivityNotFoundException var17) {
                     zzafr.zzaT(var17.getMessage());
                     return;
                  }
               }

               this.zzj(true);
               String var6 = (String)var2.get("intent_url");
               Intent var7 = null;
               String var10000;
               String var10001;
               String var10002;
               if (!TextUtils.isEmpty(var6)) {
                  try {
                     var7 = Intent.parseUri(var6, 0);
                  } catch (URISyntaxException var19) {
                     var10001 = String.valueOf(var6);
                     if (var10001.length() != 0) {
                        var10000 = "Error parsing the url: ".concat(var10001);
                     } else {
                        var10002 = new String;
                        var10000 = var10002;
                        var10002.<init>("Error parsing the url: ");
                     }

                     zzafr.zzb(var10000, var19);
                  }
               }

               if (var7 != null && var7.getData() != null) {
                  Uri var8;
                  String var9;
                  if (!TextUtils.isEmpty(var9 = (var8 = var7.getData()).toString())) {
                     com.google.android.gms.ads.internal.zzbs.zzbz();
                     var9 = zzagz.zzb(var1, var9);

                     try {
                        var8 = Uri.parse(var9);
                     } catch (Exception var18) {
                        var10001 = String.valueOf(var9);
                        if (var10001.length() != 0) {
                           var10000 = "Error parsing the uri: ".concat(var10001);
                        } else {
                           var10002 = new String;
                           var10000 = var10002;
                           var10002.<init>("Error parsing the uri: ");
                        }

                        zzafr.zzb(var10000, var18);
                     }
                  }

                  var7.setData(var8);
               }

               if (var7 != null) {
                  var5.zza(new zzc(var7));
                  return;
               }

               if (!TextUtils.isEmpty(var3)) {
                  com.google.android.gms.ads.internal.zzbs.zzbz();
                  var3 = zzagz.zzb(var1, var3);
               }

               var5.zza(new zzc((String)var2.get("i"), var3, (String)var2.get("m"), (String)var2.get("p"), (String)var2.get("c"), (String)var2.get("f"), (String)var2.get("e")));
            }

         }
      }
   }

   private static boolean zze(Map var0) {
      return "1".equals(var0.get("custom_close"));
   }

   private static int zzf(Map var0) {
      String var1;
      if ((var1 = (String)var0.get("o")) != null) {
         if ("p".equalsIgnoreCase(var1)) {
            return com.google.android.gms.ads.internal.zzbs.zzbB().zzhU();
         }

         if ("l".equalsIgnoreCase(var1)) {
            return com.google.android.gms.ads.internal.zzbs.zzbB().zzhT();
         }

         if ("c".equalsIgnoreCase(var1)) {
            return com.google.android.gms.ads.internal.zzbs.zzbB().zzhV();
         }
      }

      return -1;
   }

   private final void zzj(boolean var1) {
      if (this.zzJF != null) {
         this.zzJF.zzk(var1);
      }

   }
}
