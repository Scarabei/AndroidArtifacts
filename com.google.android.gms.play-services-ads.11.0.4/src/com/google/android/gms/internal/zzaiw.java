package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.VideoCapabilities;
import android.os.Build.VERSION;
import android.util.Range;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zzzn
public final class zzaiw {
   private static Map zzaaz = new HashMap();
   private static List zzaaA;
   private static final Object zzaaB = new Object();

   @TargetApi(16)
   public static List zzaQ(String var0) {
      Object var1 = zzaaB;
      synchronized(zzaaB) {
         if (zzaaz.containsKey(var0)) {
            return (List)zzaaz.get(var0);
         } else {
            try {
               Object var13 = zzaaB;
               synchronized(zzaaB) {
                  if (zzaaA == null) {
                     if (VERSION.SDK_INT >= 21) {
                        zzaaA = Arrays.asList((new MediaCodecList(0)).getCodecInfos());
                     } else if (VERSION.SDK_INT >= 16) {
                        int var14 = MediaCodecList.getCodecCount();
                        zzaaA = new ArrayList(var14);

                        for(int var15 = 0; var15 < var14; ++var15) {
                           MediaCodecInfo var16 = MediaCodecList.getCodecInfoAt(var15);
                           zzaaA.add(var16);
                        }
                     } else {
                        zzaaA = Collections.emptyList();
                     }
                  }
               }

               ArrayList var2 = new ArrayList();
               Iterator var21 = zzaaA.iterator();

               while(true) {
                  MediaCodecInfo var22;
                  do {
                     do {
                        if (!var21.hasNext()) {
                           zzaaz.put(var0, var2);
                           ArrayList var10000 = var2;
                           return var10000;
                        }
                     } while((var22 = (MediaCodecInfo)var21.next()).isEncoder());
                  } while(!Arrays.asList(var22.getSupportedTypes()).contains(var0));

                  HashMap var5;
                  (var5 = new HashMap()).put("codecName", var22.getName());
                  CodecCapabilities var6 = var22.getCapabilitiesForType(var0);
                  ArrayList var7 = new ArrayList();
                  CodecProfileLevel[] var8 = var6.profileLevels;
                  int var9 = var6.profileLevels.length;

                  for(int var10 = 0; var10 < var9; ++var10) {
                     CodecProfileLevel var11 = var8[var10];
                     var7.add(new Integer[]{var11.profile, var11.level});
                  }

                  var5.put("profileLevels", var7);
                  if (VERSION.SDK_INT >= 21) {
                     VideoCapabilities var23 = var6.getVideoCapabilities();
                     var5.put("bitRatesBps", zza(var23.getBitrateRange()));
                     var5.put("widthAlignment", var23.getWidthAlignment());
                     var5.put("heightAlignment", var23.getHeightAlignment());
                     var5.put("frameRates", zza(var23.getSupportedFrameRates()));
                     var5.put("widths", zza(var23.getSupportedWidths()));
                     var5.put("heights", zza(var23.getSupportedHeights()));
                  }

                  if (VERSION.SDK_INT >= 23) {
                     var5.put("instancesLimit", var6.getMaxSupportedInstances());
                  }

                  var2.add(var5);
               }
            } catch (LinkageError | RuntimeException var19) {
               HashMap var3;
               (var3 = new HashMap()).put("error", var19.getClass().getSimpleName());
               ArrayList var4;
               (var4 = new ArrayList()).add(var3);
               zzaaz.put(var0, var4);
               return var4;
            }
         }
      }
   }

   @TargetApi(21)
   private static Integer[] zza(Range var0) {
      return new Integer[]{(Integer)var0.getLower(), (Integer)var0.getUpper()};
   }
}
