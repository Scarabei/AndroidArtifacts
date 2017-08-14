package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzabt {
   private static final SimpleDateFormat zzUK;

   @Nullable
   public static JSONObject zza(Context var0, zzabk var1) {
      zzaae var2 = var1.zzUj;
      Location var3 = var1.zzzV;
      zzacb var4 = var1.zzUk;
      Bundle var5 = var1.zzSG;
      JSONObject var6 = var1.zzUl;

      String var10000;
      String var10001;
      String var10002;
      try {
         HashMap var7;
         HashMap var51 = var7 = new HashMap();
         zzme var15 = zzmo.zzFl;
         var51.put("extra_caps", com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var15));
         if (var1.zzSN.size() > 0) {
            var7.put("eid", TextUtils.join(",", var1.zzSN));
         }

         if (var2.zzSy != null) {
            var7.put("ad_pos", var2.zzSy);
         }

         zzir var16 = var2.zzSz;
         String var17;
         if ((var17 = zzafo.zzhK()) != null) {
            var7.put("abf", var17);
         }

         if (var16.zzzN != -1L) {
            var7.put("cust_age", zzUK.format(new Date(var16.zzzN)));
         }

         if (var16.extras != null) {
            var7.put("extras", var16.extras);
         }

         if (var16.zzzO != -1) {
            var7.put("cust_gender", var16.zzzO);
         }

         if (var16.zzzP != null) {
            var7.put("kw", var16.zzzP);
         }

         if (var16.zzzR != -1) {
            var7.put("tag_for_child_directed_treatment", var16.zzzR);
         }

         if (var16.zzzQ) {
            var7.put("adtest", "on");
         }

         String var21;
         if (var16.versionCode >= 2) {
            if (var16.zzzS) {
               var7.put("d_imp_hdr", Integer.valueOf(1));
            }

            if (!TextUtils.isEmpty(var16.zzzT)) {
               var7.put("ppid", var16.zzzT);
            }

            if (var16.zzzU != null) {
               zzlt var19 = var16.zzzU;
               if (Color.alpha(var19.zzBw) != 0) {
                  var7.put("acolor", zzu(var19.zzBw));
               }

               if (Color.alpha(var19.backgroundColor) != 0) {
                  var7.put("bgcolor", zzu(var19.backgroundColor));
               }

               if (Color.alpha(var19.zzBx) != 0 && Color.alpha(var19.zzBy) != 0) {
                  var7.put("gradientto", zzu(var19.zzBx));
                  var7.put("gradientfrom", zzu(var19.zzBy));
               }

               if (Color.alpha(var19.zzBz) != 0) {
                  var7.put("bcolor", zzu(var19.zzBz));
               }

               var7.put("bthick", Integer.toString(var19.zzBA));
               String var20;
               switch(var19.zzBB) {
               case 0:
                  var20 = "none";
                  break;
               case 1:
                  var20 = "dashed";
                  break;
               case 2:
                  var20 = "dotted";
                  break;
               case 3:
                  var20 = "solid";
                  break;
               default:
                  var20 = null;
               }

               if (var20 != null) {
                  var7.put("btype", var20);
               }

               switch(var19.zzBC) {
               case 0:
                  var21 = "light";
                  break;
               case 1:
                  var21 = "medium";
                  break;
               case 2:
                  var21 = "dark";
                  break;
               default:
                  var21 = null;
               }

               if (var21 != null) {
                  var7.put("callbuttoncolor", var21);
               }

               if (var19.zzBD != null) {
                  var7.put("channel", var19.zzBD);
               }

               if (Color.alpha(var19.zzBE) != 0) {
                  var7.put("dcolor", zzu(var19.zzBE));
               }

               if (var19.zzBF != null) {
                  var7.put("font", var19.zzBF);
               }

               if (Color.alpha(var19.zzBG) != 0) {
                  var7.put("hcolor", zzu(var19.zzBG));
               }

               var7.put("headersize", Integer.toString(var19.zzBH));
               if (var19.zzBI != null) {
                  var7.put("q", var19.zzBI);
               }
            }
         }

         if (var16.versionCode >= 3 && var16.zzzW != null) {
            var7.put("url", var16.zzzW);
         }

         if (var16.versionCode >= 5) {
            if (var16.zzzY != null) {
               var7.put("custom_targeting", var16.zzzY);
            }

            if (var16.zzzZ != null) {
               var7.put("category_exclusions", var16.zzzZ);
            }

            if (var16.zzAa != null) {
               var7.put("request_agent", var16.zzAa);
            }
         }

         if (var16.versionCode >= 6 && var16.zzAb != null) {
            var7.put("request_pkg", var16.zzAb);
         }

         if (var16.versionCode >= 7) {
            var7.put("is_designed_for_families", var16.zzAc);
         }

         boolean var9;
         zziv[] var10;
         int var11;
         int var12;
         zziv var13;
         if (var2.zzvX.zzAu == null) {
            var7.put("format", var2.zzvX.zzAs);
            if (var2.zzvX.zzAw) {
               var7.put("fluid", "height");
            }
         } else {
            boolean var8 = false;
            var9 = false;
            var10 = var2.zzvX.zzAu;
            var11 = var2.zzvX.zzAu.length;

            for(var12 = 0; var12 < var11; ++var12) {
               if (!(var13 = var10[var12]).zzAw && !var8) {
                  var7.put("format", var13.zzAs);
                  var8 = true;
               }

               if (var13.zzAw && !var9) {
                  var7.put("fluid", "height");
                  var9 = true;
               }

               if (var8 && var9) {
                  break;
               }
            }
         }

         if (var2.zzvX.width == -1) {
            var7.put("smart_w", "full");
         }

         if (var2.zzvX.height == -2) {
            var7.put("smart_h", "auto");
         }

         if (var2.zzvX.zzAu != null) {
            StringBuilder var32 = new StringBuilder();
            var9 = false;
            var10 = var2.zzvX.zzAu;
            var11 = var2.zzvX.zzAu.length;

            for(var12 = 0; var12 < var11; ++var12) {
               if ((var13 = var10[var12]).zzAw) {
                  var9 = true;
               } else {
                  if (var32.length() != 0) {
                     var32.append("|");
                  }

                  var32.append(var13.width == -1 ? (int)((float)var13.widthPixels / var4.zzxR) : var13.width);
                  var32.append("x");
                  var32.append(var13.height == -2 ? (int)((float)var13.heightPixels / var4.zzxR) : var13.height);
               }
            }

            if (var9) {
               if (var32.length() != 0) {
                  var32.insert(0, "|");
               }

               var32.insert(0, "320x50");
            }

            var7.put("sz", var32);
         }

         if (var2.zzSF != 0) {
            var7.put("native_version", var2.zzSF);
            var7.put("native_templates", var2.zzwq);
            zzon var40 = var2.zzwj;
            switch(var2.zzwj != null ? var40.zzIo : 0) {
            case 0:
               var10002 = "any";
               break;
            case 1:
               var10002 = "portrait";
               break;
            case 2:
               var10002 = "landscape";
               break;
            default:
               var10002 = "not_set";
            }

            var7.put("native_image_orientation", var10002);
            if (!var2.zzSO.isEmpty()) {
               var7.put("native_custom_templates", var2.zzSO);
            }

            if (!TextUtils.isEmpty(var2.zzTi)) {
               try {
                  var7.put("native_advanced_settings", new JSONArray(var2.zzTi));
               } catch (JSONException var30) {
                  zzafr.zzc("Problem creating json from native advanced settings", var30);
               }
            }
         }

         if (var2.zzwn != null && var2.zzwn.size() > 0) {
            Iterator var33 = var2.zzwn.iterator();

            while(var33.hasNext()) {
               Integer var35;
               if ((var35 = (Integer)var33.next()).intValue() == 2) {
                  var7.put("iba", true);
               } else if (var35.intValue() == 1) {
                  var7.put("ina", true);
               }
            }
         }

         if (var2.zzvX.zzAx) {
            var7.put("ene", true);
         }

         if (var2.zzwl != null) {
            var7.put("is_icon_ad", true);
            var7.put("icon_ad_expansion_behavior", var2.zzwl.zzAR);
         }

         var7.put("slotname", var2.zzvR);
         var7.put("pn", var2.applicationInfo.packageName);
         if (var2.zzSA != null) {
            var7.put("vc", var2.zzSA.versionCode);
         }

         var7.put("ms", var1.zzSB);
         var7.put("seq_num", var2.zzSC);
         var7.put("session_id", var2.zzSD);
         var7.put("js", var2.zzvT.zzaP);
         Bundle var44 = var1.zzUg;
         Bundle var18 = var2.zzTa;
         zzacn var41 = var1.zzUh;
         var7.put("am", var4.zzVF);
         var7.put("cog", zzt(var4.zzVG));
         var7.put("coh", zzt(var4.zzVH));
         if (!TextUtils.isEmpty(var4.zzVI)) {
            var7.put("carrier", var4.zzVI);
         }

         var7.put("gl", var4.zzVJ);
         if (var4.zzVK) {
            var7.put("simulator", Integer.valueOf(1));
         }

         if (var4.zzVL) {
            var7.put("is_sidewinder", Integer.valueOf(1));
         }

         var7.put("ma", zzt(var4.zzVM));
         var7.put("sp", zzt(var4.zzVN));
         var7.put("hl", var4.zzVO);
         if (!TextUtils.isEmpty(var4.zzVP)) {
            var7.put("mv", var4.zzVP);
         }

         var7.put("muv", var4.zzVR);
         if (var4.zzVS != -2) {
            var7.put("cnt", var4.zzVS);
         }

         var7.put("gnt", var4.zzVT);
         var7.put("pt", var4.zzVU);
         var7.put("rm", var4.zzVV);
         var7.put("riv", var4.zzVW);
         Bundle var46;
         (var46 = new Bundle()).putString("build_build", var4.zzWb);
         var46.putString("build_device", var4.zzWc);
         Bundle var49;
         (var49 = new Bundle()).putBoolean("is_charging", var4.zzVY);
         var49.putDouble("battery_level", var4.zzVX);
         var46.putBundle("battery", var49);
         Bundle var22;
         (var22 = new Bundle()).putInt("active_network_state", var4.zzWa);
         var22.putBoolean("active_network_metered", var4.zzVZ);
         Bundle var23;
         if (var41 != null) {
            (var23 = new Bundle()).putInt("predicted_latency_micros", var41.zzWm);
            var23.putLong("predicted_down_throughput_bps", var41.zzWn);
            var23.putLong("predicted_up_throughput_bps", var41.zzWo);
            var22.putBundle("predictions", var23);
         }

         var46.putBundle("network", var22);
         (var23 = new Bundle()).putBoolean("is_browser_custom_tabs_capable", var4.zzWd);
         var46.putBundle("browser", var23);
         if (var18 != null) {
            Bundle var26;
            (var26 = new Bundle()).putString("runtime_free", Long.toString(var18.getLong("runtime_free_memory", -1L)));
            var26.putString("runtime_max", Long.toString(var18.getLong("runtime_max_memory", -1L)));
            var26.putString("runtime_total", Long.toString(var18.getLong("runtime_total_memory", -1L)));
            var26.putString("web_view_count", Integer.toString(var18.getInt("web_view_count", 0)));
            MemoryInfo var27;
            if ((var27 = (MemoryInfo)var18.getParcelable("debug_memory_info")) != null) {
               var26.putString("debug_info_dalvik_private_dirty", Integer.toString(var27.dalvikPrivateDirty));
               var26.putString("debug_info_dalvik_pss", Integer.toString(var27.dalvikPss));
               var26.putString("debug_info_dalvik_shared_dirty", Integer.toString(var27.dalvikSharedDirty));
               var26.putString("debug_info_native_private_dirty", Integer.toString(var27.nativePrivateDirty));
               var26.putString("debug_info_native_pss", Integer.toString(var27.nativePss));
               var26.putString("debug_info_native_shared_dirty", Integer.toString(var27.nativeSharedDirty));
               var26.putString("debug_info_other_private_dirty", Integer.toString(var27.otherPrivateDirty));
               var26.putString("debug_info_other_pss", Integer.toString(var27.otherPss));
               var26.putString("debug_info_other_shared_dirty", Integer.toString(var27.otherSharedDirty));
            }

            var46.putBundle("android_mem_info", var26);
         }

         Bundle var24;
         (var24 = new Bundle()).putBundle("parental_controls", var44);
         if (!TextUtils.isEmpty(var4.zzVQ)) {
            var24.putString("package_version", var4.zzVQ);
         }

         var46.putBundle("play_store", var24);
         var7.put("device", var46);
         (var18 = new Bundle()).putString("doritos", var1.zzUi);
         zzme var47 = zzmo.zzDM;
         boolean var48;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var47)).booleanValue()) {
            String var45 = null;
            var48 = false;
            if (var1.zzqi != null) {
               var45 = var1.zzqi.getId();
               var48 = var1.zzqi.isLimitAdTrackingEnabled();
            }

            if (!TextUtils.isEmpty(var45)) {
               var18.putString("rdid", var45);
               var18.putBoolean("is_lat", var48);
               var18.putString("idtype", "adid");
            } else {
               zzji.zzds();
               var21 = zzaiy.zzW(var0);
               var18.putString("pdid", var21);
               var18.putString("pdidtype", "ssaid");
            }
         }

         var7.put("pii", var18);
         var7.put("platform", Build.MANUFACTURER);
         var7.put("submodel", Build.MODEL);
         if (var3 != null) {
            zza(var7, var3);
         } else if (var2.zzSz.versionCode >= 2 && var2.zzSz.zzzV != null) {
            zza(var7, var2.zzSz.zzzV);
         }

         if (var2.versionCode >= 2) {
            var7.put("quality_signals", var2.zzSE);
         }

         if (var2.versionCode >= 4 && var2.zzSH) {
            var7.put("forceHttps", var2.zzSH);
         }

         if (var5 != null) {
            var7.put("content_info", var5);
         }

         if (var2.versionCode >= 5) {
            var7.put("u_sd", var2.zzxR);
            var7.put("sh", var2.zzSJ);
            var7.put("sw", var2.zzSI);
         } else {
            var7.put("u_sd", var4.zzxR);
            var7.put("sh", var4.zzSJ);
            var7.put("sw", var4.zzSI);
         }

         if (var2.versionCode >= 6) {
            if (!TextUtils.isEmpty(var2.zzSK)) {
               try {
                  var7.put("view_hierarchy", new JSONObject(var2.zzSK));
               } catch (JSONException var29) {
                  zzafr.zzc("Problem serializing view hierarchy to JSON", var29);
               }
            }

            var7.put("correlation_id", var2.zzSL);
         }

         if (var2.versionCode >= 7) {
            var7.put("request_id", var2.zzSM);
         }

         if (var2.versionCode >= 12 && !TextUtils.isEmpty(var2.zzSQ)) {
            var7.put("anchor", var2.zzSQ);
         }

         if (var2.versionCode >= 13) {
            var7.put("android_app_volume", var2.zzSR);
         }

         if (var2.versionCode >= 18) {
            var7.put("android_app_muted", var2.zzSX);
         }

         if (var2.versionCode >= 14 && var2.zzSS > 0) {
            var7.put("target_api", var2.zzSS);
         }

         if (var2.versionCode >= 15) {
            var7.put("scroll_index", var2.zzST == -1 ? -1 : var2.zzST);
         }

         if (var2.versionCode >= 16) {
            var7.put("_activity_context", var2.zzSU);
         }

         if (var2.versionCode >= 18) {
            if (!TextUtils.isEmpty(var2.zzSY)) {
               try {
                  var7.put("app_settings", new JSONObject(var2.zzSY));
               } catch (JSONException var28) {
                  zzafr.zzc("Problem creating json from app settings", var28);
               }
            }

            var7.put("render_in_browser", var2.zzMe);
         }

         if (var2.versionCode >= 18) {
            var7.put("android_num_video_cache_tasks", var2.zzSZ);
         }

         boolean var43 = var1.zzUm;
         zzaje var42 = var2.zzvT;
         var44 = new Bundle();
         (var46 = new Bundle()).putString("cl", "162978962");
         var46.putString("rapid_rc", "dev");
         var46.putString("rapid_rollup", "HEAD");
         var44.putBundle("build_meta", var46);
         zzme var50 = zzmo.zzFn;
         var44.putString("mf", Boolean.toString(((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var50)).booleanValue()));
         var44.putBoolean("instant_app", zzbha.zzaP(var0).zzsl());
         var44.putBoolean("lite", var42.zzaaR);
         var44.putBoolean("local_service", var43);
         var7.put("sdk_env", var44);
         var7.put("cache_state", var6);
         if (var2.versionCode >= 19) {
            var7.put("gct", var2.zzTb);
         }

         if (var2.versionCode >= 21 && var2.zzTc) {
            var7.put("de", "1");
         }

         var15 = zzmo.zzDY;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var15)).booleanValue()) {
            var17 = var2.zzvX.zzAs;
            var43 = var2.zzvX.zzAs.equals("interstitial_mb") || var17.equals("reward_mb");
            var44 = var2.zzTd;
            var48 = var2.zzTd != null;
            if (var43 && var48) {
               (var49 = new Bundle()).putBundle("interstitial_pool", var44);
               var7.put("counters", var49);
            }
         }

         if (var2.versionCode >= 22 && com.google.android.gms.ads.internal.zzbs.zzbY().zzp(var0)) {
            var7.put("gmp_app_id", var2.zzTe);
            if ("TIME_OUT".equals(var2.zzTf)) {
               var15 = zzmo.zzDB;
               var7.put("sai_timeout", com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var15));
            } else if (var2.zzTf == null) {
               var7.put("fbs_aiid", "");
            } else {
               var7.put("fbs_aiid", var2.zzTf);
            }

            var7.put("fbs_aeid", var2.zzTg);
         }

         var15 = zzmo.zzCE;
         String var34;
         if ((var34 = (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var15)) != null && !var34.isEmpty()) {
            var15 = zzmo.zzCF;
            if (VERSION.SDK_INT >= ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var15)).intValue()) {
               HashMap var37 = new HashMap();
               String[] var36;
               var11 = (var36 = var34.split(",")).length;

               for(var12 = 0; var12 < var11; ++var12) {
                  String var39;
                  List var14 = zzaiw.zzaQ(var39 = var36[var12]);
                  var37.put(var39, var14);
               }

               var7.put("video_decoders", var37);
            }
         }

         if (zzafr.zzz(2)) {
            String var38 = com.google.android.gms.ads.internal.zzbs.zzbz().zzj(var7).toString(2);
            var10001 = String.valueOf(var38);
            if (var10001.length() != 0) {
               var10000 = "Ad Request JSON: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Ad Request JSON: ");
            }

            zzafr.v(var10000);
         }

         return com.google.android.gms.ads.internal.zzbs.zzbz().zzj(var7);
      } catch (JSONException var31) {
         var10001 = String.valueOf(var31.getMessage());
         if (var10001.length() != 0) {
            var10000 = "Problem serializing ad request to JSON: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Problem serializing ad request to JSON: ");
         }

         zzafr.zzaT(var10000);
         return null;
      }
   }

   private static void zza(HashMap var0, Location var1) {
      HashMap var2 = new HashMap();
      Float var3 = var1.getAccuracy() * 1000.0F;
      Long var4 = var1.getTime() * 1000L;
      Long var5 = (long)(var1.getLatitude() * 1.0E7D);
      Long var6 = (long)(var1.getLongitude() * 1.0E7D);
      var2.put("radius", var3);
      var2.put("lat", var5);
      var2.put("long", var6);
      var2.put("time", var4);
      var0.put("uule", var2);
   }

   private static Integer zzt(boolean var0) {
      return var0 ? 1 : 0;
   }

   private static String zzu(int var0) {
      return String.format(Locale.US, "#%06x", var0 & 16777215);
   }

   public static zzaai zza(Context var0, zzaae var1, String var2) {
      try {
         JSONObject var3;
         String var4 = (var3 = new JSONObject(var2)).optString("ad_base_url", (String)null);
         String var5 = var3.optString("ad_url", (String)null);
         String var6 = var3.optString("ad_size", (String)null);
         String var7 = var3.optString("ad_slot_size", var6);
         boolean var8 = var1 != null && var1.zzSF != 0;
         String var9;
         if ((var9 = var3.optString("ad_json", (String)null)) == null) {
            var9 = var3.optString("ad_html", (String)null);
         }

         if (var9 == null) {
            var9 = var3.optString("body", (String)null);
         }

         long var10 = -1L;
         String var12 = var3.optString("debug_dialog", (String)null);
         String var13 = var3.optString("debug_signals", (String)null);
         long var14 = var3.has("interstitial_timeout") ? (long)(var3.getDouble("interstitial_timeout") * 1000.0D) : -1L;
         String var16 = var3.optString("orientation", (String)null);
         int var17 = -1;
         if ("portrait".equals(var16)) {
            var17 = com.google.android.gms.ads.internal.zzbs.zzbB().zzhU();
         } else if ("landscape".equals(var16)) {
            var17 = com.google.android.gms.ads.internal.zzbs.zzbB().zzhT();
         }

         zzaai var18 = null;
         if (TextUtils.isEmpty(var9) && !TextUtils.isEmpty(var5)) {
            var4 = (var18 = zzabm.zza(var1, var0, var1.zzvT.zzaP, var5, (String)null, (zzaca)null, (zznb)null, (zzabl)null)).zzPi;
            var9 = var18.body;
            var10 = var18.zzTs;
         }

         if (var9 == null) {
            return new zzaai(0);
         } else {
            JSONArray var19 = var3.optJSONArray("click_urls");
            List var20 = var18 == null ? null : var18.zzMa;
            if (var19 != null) {
               var20 = zza(var19, var20);
            }

            JSONArray var21 = var3.optJSONArray("impression_urls");
            List var22 = var18 == null ? null : var18.zzMb;
            if (var21 != null) {
               var22 = zza(var21, var22);
            }

            JSONArray var23 = var3.optJSONArray("manual_impression_urls");
            List var24 = var18 == null ? null : var18.zzTq;
            if (var23 != null) {
               var24 = zza(var23, var24);
            }

            if (var18 != null) {
               if (var18.orientation != -1) {
                  var17 = var18.orientation;
               }

               if (var18.zzTn > 0L) {
                  var14 = var18.zzTn;
               }
            }

            String var25 = var3.optString("active_view");
            String var26 = null;
            boolean var27;
            if (var27 = var3.optBoolean("ad_is_javascript", false)) {
               var26 = var3.optString("ad_passback_url", (String)null);
            }

            boolean var28 = var3.optBoolean("mediation", false);
            boolean var29 = var3.optBoolean("custom_render_allowed", false);
            boolean var30 = var3.optBoolean("content_url_opted_out", true);
            boolean var31 = var3.optBoolean("content_vertical_opted_out", true);
            boolean var32 = var3.optBoolean("prefetch", false);
            long var33 = var3.optLong("refresh_interval_milliseconds", -1L);
            long var35 = var3.optLong("mediation_config_cache_time_milliseconds", -1L);
            String var37 = var3.optString("gws_query_id", "");
            boolean var38 = "height".equals(var3.optString("fluid", ""));
            boolean var39 = var3.optBoolean("native_express", false);
            List var40 = zza((JSONArray)var3.optJSONArray("video_start_urls"), (List)null);
            List var41 = zza((JSONArray)var3.optJSONArray("video_complete_urls"), (List)null);
            zzaee var42 = zzaee.zza(var3.optJSONArray("rewards"));
            boolean var43 = var3.optBoolean("use_displayed_impression", false);
            zzaak var44 = zzaak.zze(var3.optJSONObject("auto_protection_configuration"));
            String var45 = var3.optString("set_cookie", "");
            List var46 = zza((JSONArray)var3.optJSONArray("remote_ping_urls"), (List)null);
            zzaeq var47 = zzaeq.zzf(var3.optJSONObject("safe_browsing"));
            boolean var48 = var3.optBoolean("render_in_browser", var1.zzMe);
            return new zzaai(var1, var4, var9, var20, var22, var14, var28, var35, var24, var33, var17, var6, var10, var12, var27, var26, var25, var29, var8, var1.zzSH, var30, var32, var37, var38, var39, var42, var40, var41, var43, var44, var1.zzSV, var45, var46, var48, var7, var47, var13, var31, var1.zzTh);
         }
      } catch (JSONException var49) {
         String var10001 = String.valueOf(var49.getMessage());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Could not parse the inline ad response: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Could not parse the inline ad response: ");
         }

         zzafr.zzaT(var10000);
         return new zzaai(0);
      }
   }

   @Nullable
   private static List zza(@Nullable JSONArray var0, @Nullable List var1) throws JSONException {
      if (var0 == null) {
         return null;
      } else {
         if (var1 == null) {
            var1 = new LinkedList();
         }

         for(int var2 = 0; var2 < var0.length(); ++var2) {
            ((List)var1).add(var0.getString(var2));
         }

         return (List)var1;
      }
   }

   public static JSONObject zzb(zzaai var0) throws JSONException {
      JSONObject var1 = new JSONObject();
      if (var0.zzPi != null) {
         var1.put("ad_base_url", var0.zzPi);
      }

      if (var0.zzTr != null) {
         var1.put("ad_size", var0.zzTr);
      }

      var1.put("native", var0.zzAv);
      if (var0.zzAv) {
         var1.put("ad_json", var0.body);
      } else {
         var1.put("ad_html", var0.body);
      }

      if (var0.zzTt != null) {
         var1.put("debug_dialog", var0.zzTt);
      }

      if (var0.zzTK != null) {
         var1.put("debug_signals", var0.zzTK);
      }

      if (var0.zzTn != -1L) {
         var1.put("interstitial_timeout", (double)var0.zzTn / 1000.0D);
      }

      if (var0.orientation == com.google.android.gms.ads.internal.zzbs.zzbB().zzhU()) {
         var1.put("orientation", "portrait");
      } else if (var0.orientation == com.google.android.gms.ads.internal.zzbs.zzbB().zzhT()) {
         var1.put("orientation", "landscape");
      }

      if (var0.zzMa != null) {
         var1.put("click_urls", zzm(var0.zzMa));
      }

      if (var0.zzMb != null) {
         var1.put("impression_urls", zzm(var0.zzMb));
      }

      if (var0.zzTq != null) {
         var1.put("manual_impression_urls", zzm(var0.zzTq));
      }

      if (var0.zzTw != null) {
         var1.put("active_view", var0.zzTw);
      }

      var1.put("ad_is_javascript", var0.zzTu);
      if (var0.zzTv != null) {
         var1.put("ad_passback_url", var0.zzTv);
      }

      var1.put("mediation", var0.zzTo);
      var1.put("custom_render_allowed", var0.zzTx);
      var1.put("content_url_opted_out", var0.zzTy);
      var1.put("content_vertical_opted_out", var0.zzTL);
      var1.put("prefetch", var0.zzTz);
      if (var0.zzMg != -1L) {
         var1.put("refresh_interval_milliseconds", var0.zzMg);
      }

      if (var0.zzTp != -1L) {
         var1.put("mediation_config_cache_time_milliseconds", var0.zzTp);
      }

      if (!TextUtils.isEmpty(var0.zzTC)) {
         var1.put("gws_query_id", var0.zzTC);
      }

      var1.put("fluid", var0.zzAw ? "height" : "");
      var1.put("native_express", var0.zzAx);
      if (var0.zzTE != null) {
         var1.put("video_start_urls", zzm(var0.zzTE));
      }

      if (var0.zzTF != null) {
         var1.put("video_complete_urls", zzm(var0.zzTF));
      }

      if (var0.zzTD != null) {
         zzaee var2 = var0.zzTD;
         JSONObject var3;
         (var3 = new JSONObject()).put("rb_type", var2.type);
         var3.put("rb_amount", var2.zzWW);
         JSONArray var4;
         (var4 = new JSONArray()).put(var3);
         var1.put("rewards", var4);
      }

      var1.put("use_displayed_impression", var0.zzTG);
      var1.put("auto_protection_configuration", var0.zzTH);
      var1.put("render_in_browser", var0.zzMe);
      return var1;
   }

   @Nullable
   private static JSONArray zzm(List var0) throws JSONException {
      JSONArray var1 = new JSONArray();
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         var1.put(var3);
      }

      return var1;
   }

   static {
      zzUK = new SimpleDateFormat("yyyyMMdd", Locale.US);
   }
}
