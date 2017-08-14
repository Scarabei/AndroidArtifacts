package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzakf;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zznn;
import com.google.android.gms.internal.zznq;
import com.google.android.gms.internal.zzns;
import com.google.android.gms.internal.zzos;
import com.google.android.gms.internal.zzot;
import com.google.android.gms.internal.zzrd;
import com.google.android.gms.internal.zzuh;
import com.google.android.gms.internal.zzvc;
import com.google.android.gms.internal.zzvf;
import com.google.android.gms.internal.zzzn;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzar {
   @Nullable
   public static View zzd(@Nullable zzaff var0) {
      if (var0 == null) {
         zzafr.e("AdState is null");
         return null;
      } else if (zze(var0) && var0.zzPg != null) {
         return var0.zzPg.getView();
      } else {
         try {
            IObjectWrapper var10000 = var0.zzMH != null ? var0.zzMH.getView() : null;
            IObjectWrapper var1 = var10000;
            if (var10000 == null) {
               zzafr.zzaT("View in mediation adapter is null.");
               return null;
            } else {
               return (View)com.google.android.gms.dynamic.zzn.zzE(var1);
            }
         } catch (RemoteException var2) {
            zzafr.zzc("Could not get View from mediation adapter.", var2);
            return null;
         }
      }
   }

   public static boolean zza(zzaka var0, zzuh var1, CountDownLatch var2) {
      boolean var3 = false;

      try {
         View var8;
         boolean var10000;
         if ((var8 = var0.getView()) == null) {
            zzafr.zzaT("AdWebView is null");
            var10000 = false;
         } else {
            var8.setVisibility(4);
            List var9 = var1.zzMG.zzLV;
            if (var1.zzMG.zzLV != null && !var9.isEmpty()) {
               label61: {
                  var0.zziw().zza((String)"/nativeExpressAssetsLoaded", (zzrd)(new zzau(var2)));
                  var0.zziw().zza((String)"/nativeExpressAssetsLoadingFailed", (zzrd)(new zzav(var2)));
                  zzvc var10 = var1.zzMH.zzfq();
                  zzvf var11 = var1.zzMH.zzfr();
                  String var16;
                  if (var9.contains("2") && var10 != null) {
                     zznq var19 = new zznq(var10.getHeadline(), var10.getImages(), var10.getBody(), var10.zzeh(), var10.getCallToAction(), var10.getStarRating(), var10.getStore(), var10.getPrice(), (zznn)null, var10.getExtras(), (zzks)null, (View)null);
                     var16 = var1.zzMG.zzLU;
                     var0.zziw().zza((zzakf)(new zzas(var19, var16, var0)));
                  } else {
                     if (!var9.contains("1") || var11 == null) {
                        zzafr.zzaT("No matching template id and mapper");
                        var10000 = false;
                        break label61;
                     }

                     zzns var12 = new zzns(var11.getHeadline(), var11.getImages(), var11.getBody(), var11.zzem(), var11.getCallToAction(), var11.getAdvertiser(), (zznn)null, var11.getExtras(), (zzks)null, (View)null);
                     var16 = var1.zzMG.zzLU;
                     var0.zziw().zza((zzakf)(new zzat(var12, var16, var0)));
                  }

                  String var20 = var1.zzMG.zzLS;
                  String var13 = var1.zzMG.zzLT;
                  if (var1.zzMG.zzLT != null) {
                     var0.loadDataWithBaseURL(var13, var20, "text/html", "UTF-8", (String)null);
                  } else {
                     var0.loadData(var20, "text/html", "UTF-8");
                  }

                  var10000 = true;
               }
            } else {
               zzafr.zzaT("No template ids present in mediation response");
               var10000 = false;
            }
         }

         var3 = var10000;
      } catch (RemoteException var17) {
         zzafr.zzc("Unable to invoke load assets", var17);
      } catch (RuntimeException var18) {
         var2.countDown();
         throw var18;
      }

      if (!var3) {
         var2.countDown();
      }

      return var3;
   }

   public static boolean zze(@Nullable zzaff var0) {
      return var0 != null && var0.zzTo && var0.zzMG != null && var0.zzMG.zzLS != null;
   }

   static zzrd zza(@Nullable zzvc var0, @Nullable zzvf var1, zzab var2) {
      return new zzaw(var0, var2, var1);
   }

   private static void zzb(zzaka var0) {
      OnClickListener var1;
      if ((var1 = var0.zziL()) != null) {
         var1.onClick(var0.getView());
      }

   }

   private static JSONObject zza(@Nullable Bundle var0, String var1) throws JSONException {
      JSONObject var2 = new JSONObject();
      if (var0 != null && !TextUtils.isEmpty(var1)) {
         JSONObject var3;
         Iterator var4 = (var3 = new JSONObject(var1)).keys();

         while(var4.hasNext()) {
            String var5 = (String)var4.next();
            if (var0.containsKey(var5)) {
               String var6 = var3.getString(var5);
               if ("image".equals(var6)) {
                  Object var7;
                  if ((var7 = var0.get(var5)) instanceof Bitmap) {
                     String var8 = zza((Bitmap)var7);
                     var2.put(var5, var8);
                  } else {
                     zzafr.zzaT("Invalid type. An image type extra should return a bitmap");
                  }
               } else if (var0.get(var5) instanceof Bitmap) {
                  zzafr.zzaT("Invalid asset type. Bitmap should be returned only for image type");
               } else {
                  var2.put(var5, String.valueOf(var0.get(var5)));
               }
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   static String zza(@Nullable zzos var0) {
      if (var0 == null) {
         zzafr.zzaT("Image is null. Returning empty string");
         return "";
      } else {
         try {
            Uri var1;
            if ((var1 = var0.getUri()) != null) {
               return var1.toString();
            }
         } catch (RemoteException var2) {
            zzafr.zzaT("Unable to get image uri. Trying data uri next");
         }

         return zzb(var0);
      }
   }

   private static String zzb(zzos var0) {
      Drawable var1;
      try {
         IObjectWrapper var2;
         if ((var2 = var0.zzeg()) == null) {
            zzafr.zzaT("Drawable is null. Returning empty string");
            return "";
         }

         var1 = (Drawable)com.google.android.gms.dynamic.zzn.zzE(var2);
      } catch (RemoteException var3) {
         zzafr.zzaT("Unable to get drawable. Returning empty string");
         return "";
      }

      if (!(var1 instanceof BitmapDrawable)) {
         zzafr.zzaT("Drawable is not an instance of BitmapDrawable. Returning empty string");
         return "";
      } else {
         return zza(((BitmapDrawable)var1).getBitmap());
      }
   }

   private static String zza(@Nullable Bitmap var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      if (var0 == null) {
         zzafr.zzaT("Bitmap is null. Returning empty string");
         return "";
      } else {
         var0.compress(CompressFormat.PNG, 100, var1);
         String var2 = Base64.encodeToString(var1.toByteArray(), 0);
         String var10000 = String.valueOf("data:image/png;base64,");
         String var10001 = String.valueOf(var2);
         return var10001.length() != 0 ? var10000.concat(var10001) : new String(var10000);
      }
   }

   @Nullable
   private static zzos zzd(Object var0) {
      return var0 instanceof IBinder ? zzot.zzi((IBinder)var0) : null;
   }

   // $FF: synthetic method
   static zzos zze(Object var0) {
      return zzd(var0);
   }

   // $FF: synthetic method
   static JSONObject zzb(Bundle var0, String var1) throws JSONException {
      return zza(var0, var1);
   }

   // $FF: synthetic method
   static void zzc(zzaka var0) {
      zzb(var0);
   }
}
