package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import com.google.android.gms.internal.zzcdr;
import java.util.ArrayList;
import java.util.List;

public class GeofencingEvent {
   private final int mErrorCode;
   private final int zzbhN;
   private final List zzbhO;
   private final Location zzbhP;

   private GeofencingEvent(int var1, int var2, List var3, Location var4) {
      this.mErrorCode = var1;
      this.zzbhN = var2;
      this.zzbhO = var3;
      this.zzbhP = var4;
   }

   public static GeofencingEvent fromIntent(Intent var0) {
      if (var0 == null) {
         return null;
      } else {
         int var1 = var0.getIntExtra("gms_error_code", -1);
         int var5;
         int var2 = (var5 = var0.getIntExtra("com.google.android.location.intent.extra.transition", -1)) == -1 || var5 != 1 && var5 != 2 && var5 != 4 ? -1 : var5;
         ArrayList var11;
         ArrayList var10000;
         if ((var11 = (ArrayList)var0.getSerializableExtra("com.google.android.location.intent.extra.geofence_list")) == null) {
            var10000 = null;
         } else {
            ArrayList var6 = new ArrayList(var11.size());
            ArrayList var8;
            int var9 = (var8 = (ArrayList)var11).size();
            int var10 = 0;

            while(var10 < var9) {
               Object var12 = var8.get(var10);
               ++var10;
               byte[] var7 = (byte[])var12;
               var6.add(zzcdr.zzk(var7));
            }

            var10000 = var6;
         }

         ArrayList var3 = var10000;
         Location var4 = (Location)var0.getParcelableExtra("com.google.android.location.intent.extra.triggering_location");
         return new GeofencingEvent(var1, var2, var3, var4);
      }
   }

   public boolean hasError() {
      return this.mErrorCode != -1;
   }

   public int getErrorCode() {
      return this.mErrorCode;
   }

   public int getGeofenceTransition() {
      return this.zzbhN;
   }

   public List getTriggeringGeofences() {
      return this.zzbhO;
   }

   public Location getTriggeringLocation() {
      return this.zzbhP;
   }
}
