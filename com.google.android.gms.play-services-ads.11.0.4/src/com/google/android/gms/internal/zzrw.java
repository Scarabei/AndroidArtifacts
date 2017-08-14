package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.overlay.zzaa;
import com.google.android.gms.ads.internal.overlay.zzaq;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@zzzn
public final class zzrw implements zzrd {
   private boolean zzJK;

   private static int zza(Context var0, Map var1, String var2, int var3) {
      String var4 = (String)var1.get(var2);
      int var5 = var3;
      if (var4 != null) {
         try {
            zzji.zzds();
            var5 = zzaiy.zzc(var0, Integer.parseInt(var4));
         } catch (NumberFormatException var6) {
            zzafr.zzaT((new StringBuilder(34 + String.valueOf(var2).length() + String.valueOf(var4).length())).append("Could not parse ").append(var2).append(" in a video GMSG: ").append(var4).toString());
         }
      }

      return var5;
   }

   public final void zza(zzaka var1, Map var2) {
      String var3;
      if ((var3 = (String)var2.get("action")) == null) {
         zzafr.zzaT("Action missing from video GMSG.");
      } else {
         if (zzafr.zzz(3)) {
            JSONObject var4;
            (var4 = new JSONObject(var2)).remove("google.afma.Notify_dt");
            String var5 = String.valueOf(var4.toString());
            zzafr.zzaC((new StringBuilder(13 + String.valueOf(var3).length() + String.valueOf(var5).length())).append("Video GMSG: ").append(var3).append(" ").append(var5).toString());
         }

         String var20;
         if ("background".equals(var3)) {
            if (TextUtils.isEmpty(var20 = (String)var2.get("color"))) {
               zzafr.zzaT("Color parameter missing from color video GMSG.");
            } else {
               try {
                  int var24 = Color.parseColor(var20);
                  var1.setBackgroundColor(var24);
               } catch (IllegalArgumentException var16) {
                  zzafr.zzaT("Invalid color parameter in video GMSG.");
               }
            }
         } else {
            int var8;
            if (!"decoderProps".equals(var3)) {
               zzajz var21;
               if ((var21 = var1.zziE()) == null) {
                  zzafr.zzaT("Could not get underlay container for a video GMSG.");
               } else {
                  boolean var23 = "new".equals(var3);
                  boolean var25 = "position".equals(var3);
                  int var10;
                  int var29;
                  if (!var23 && !var25) {
                     zzaa var27;
                     if ((var27 = var21.zzip()) == null) {
                        zzaa.zzh(var1);
                     } else {
                        Context var30;
                        if ("click".equals(var3)) {
                           var29 = zza(var30 = var1.getContext(), var2, "x", 0);
                           var10 = zza(var30, var2, "y", 0);
                           long var33 = SystemClock.uptimeMillis();
                           MotionEvent var32 = MotionEvent.obtain(var33, var33, 0, (float)var29, (float)var10, 0);
                           var27.zze(var32);
                           var32.recycle();
                        } else {
                           String var10000;
                           String var10001;
                           String var10002;
                           String var28;
                           if ("currentTime".equals(var3)) {
                              if ((var28 = (String)var2.get("time")) == null) {
                                 zzafr.zzaT("Time parameter missing from currentTime video GMSG.");
                              } else {
                                 try {
                                    var10 = (int)(Float.parseFloat(var28) * 1000.0F);
                                    var27.seekTo(var10);
                                 } catch (NumberFormatException var18) {
                                    var10001 = String.valueOf(var28);
                                    if (var10001.length() != 0) {
                                       var10000 = "Could not parse time parameter from currentTime video GMSG: ".concat(var10001);
                                    } else {
                                       var10002 = new String;
                                       var10000 = var10002;
                                       var10002.<init>("Could not parse time parameter from currentTime video GMSG: ");
                                    }

                                    zzafr.zzaT(var10000);
                                 }
                              }
                           } else if ("hide".equals(var3)) {
                              var27.setVisibility(4);
                           } else if ("load".equals(var3)) {
                              var27.zzfY();
                           } else if ("muted".equals(var3)) {
                              if (Boolean.parseBoolean((String)var2.get("muted"))) {
                                 var27.zzfZ();
                              } else {
                                 var27.zzga();
                              }
                           } else if ("pause".equals(var3)) {
                              var27.pause();
                           } else if ("play".equals(var3)) {
                              var27.play();
                           } else if ("show".equals(var3)) {
                              var27.setVisibility(0);
                           } else if ("src".equals(var3)) {
                              var27.zzaq((String)var2.get("src"));
                           } else if ("touchMove".equals(var3)) {
                              var29 = zza(var30 = var1.getContext(), var2, "dx", 0);
                              var10 = zza(var30, var2, "dy", 0);
                              var27.zza((float)var29, (float)var10);
                              if (!this.zzJK) {
                                 var1.zziu().zzfQ();
                                 this.zzJK = true;
                              }

                           } else if (!"volume".equals(var3)) {
                              if ("watermark".equals(var3)) {
                                 var27.zzgb();
                              } else {
                                 var10001 = String.valueOf(var3);
                                 if (var10001.length() != 0) {
                                    var10000 = "Unknown video action: ".concat(var10001);
                                 } else {
                                    var10002 = new String;
                                    var10000 = var10002;
                                    var10002.<init>("Unknown video action: ");
                                 }

                                 zzafr.zzaT(var10000);
                              }
                           } else if ((var28 = (String)var2.get("volume")) == null) {
                              zzafr.zzaT("Level parameter missing from volume video GMSG.");
                           } else {
                              try {
                                 float var31 = Float.parseFloat(var28);
                                 var27.zzb(var31);
                              } catch (NumberFormatException var19) {
                                 var10001 = String.valueOf(var28);
                                 if (var10001.length() != 0) {
                                    var10000 = "Could not parse volume parameter from volume video GMSG: ".concat(var10001);
                                 } else {
                                    var10002 = new String;
                                    var10000 = var10002;
                                    var10002.<init>("Could not parse volume parameter from volume video GMSG: ");
                                 }

                                 zzafr.zzaT(var10000);
                              }
                           }
                        }
                     }
                  } else {
                     Context var26;
                     var8 = zza(var26 = var1.getContext(), var2, "x", 0);
                     var29 = zza(var26, var2, "y", 0);
                     var10 = zza(var26, var2, "w", -1);
                     int var11 = zza(var26, var2, "h", -1);
                     zzme var15 = zzmo.zzFB;
                     if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var15)).booleanValue()) {
                        var10 = Math.min(var10, var1.getMeasuredWidth() - var8);
                        var11 = Math.min(var11, var1.getMeasuredHeight() - var29);
                     }

                     int var12;
                     try {
                        var12 = Integer.parseInt((String)var2.get("player"));
                     } catch (NumberFormatException var17) {
                        var12 = 0;
                     }

                     boolean var13 = Boolean.parseBoolean((String)var2.get("spherical"));
                     if (var23 && var21.zzip() == null) {
                        zzaq var14 = new zzaq((String)var2.get("flags"));
                        var21.zza(var8, var29, var10, var11, var12, var13, var14);
                     } else {
                        var21.zze(var8, var29, var10, var11);
                     }
                  }
               }
            } else if ((var20 = (String)var2.get("mimeTypes")) == null) {
               zzafr.zzaT("No MIME types specified for decoder properties inspection.");
               zzaa.zza(var1, "missingMimeTypes");
            } else if (VERSION.SDK_INT < 16) {
               zzafr.zzaT("Video decoder properties available on API versions >= 16.");
               zzaa.zza(var1, "deficientApiVersion");
            } else {
               HashMap var22 = new HashMap();
               String[] var6;
               int var7 = (var6 = var20.split(",")).length;

               for(var8 = 0; var8 < var7; ++var8) {
                  String var9 = var6[var8];
                  var22.put(var9, zzaiw.zzaQ(var9.trim()));
               }

               zzaa.zzc(var1, var22);
            }
         }
      }
   }
}
