package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzcdr;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeofencingRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int INITIAL_TRIGGER_ENTER = 1;
   public static final int INITIAL_TRIGGER_EXIT = 2;
   public static final int INITIAL_TRIGGER_DWELL = 4;
   public static final Creator CREATOR = new zzi();
   private final List zzbhQ;
   private final int zzbhR;
   private final String mTag;

   GeofencingRequest(List var1, int var2, String var3) {
      this.zzbhQ = var1;
      this.zzbhR = var2;
      this.mTag = var3;
   }

   public List getGeofences() {
      ArrayList var1;
      (var1 = new ArrayList()).addAll(this.zzbhQ);
      return var1;
   }

   public int getInitialTrigger() {
      return this.zzbhR;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzbhQ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getInitialTrigger());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.mTag, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public static final class Builder {
      private final List zzbhQ = new ArrayList();
      private int zzbhR = 5;
      private String mTag = "";

      public final GeofencingRequest.Builder addGeofence(Geofence var1) {
         zzbo.zzb(var1, "geofence can't be null.");
         zzbo.zzb(var1 instanceof zzcdr, "Geofence must be created using Geofence.Builder.");
         this.zzbhQ.add((zzcdr)var1);
         return this;
      }

      public final GeofencingRequest.Builder addGeofences(List var1) {
         if (var1 != null && !var1.isEmpty()) {
            Iterator var2 = var1.iterator();

            while(var2.hasNext()) {
               Geofence var3;
               if ((var3 = (Geofence)var2.next()) != null) {
                  this.addGeofence(var3);
               }
            }

            return this;
         } else {
            return this;
         }
      }

      public final GeofencingRequest.Builder setInitialTrigger(int var1) {
         this.zzbhR = var1 & 7;
         return this;
      }

      public final GeofencingRequest build() {
         zzbo.zzb(!this.zzbhQ.isEmpty(), "No geofence has been added to this request.");
         return new GeofencingRequest(this.zzbhQ, this.zzbhR, this.mTag);
      }
   }
}
