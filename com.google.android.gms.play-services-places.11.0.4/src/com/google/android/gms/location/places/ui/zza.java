package com.google.android.gms.location.places.ui;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.internal.PlaceEntity;

class zza {
   public static final int RESULT_ERROR = 2;

   public static Place getPlace(Context var0, Intent var1) {
      zzbo.zzb(var1, "intent must not be null");
      zzbo.zzb(var0, "context must not be null");
      return (Place)com.google.android.gms.common.internal.safeparcel.zze.zza(var1, "selected_place", PlaceEntity.CREATOR);
   }

   public static Status getStatus(Context var0, Intent var1) {
      zzbo.zzb(var1, "intent must not be null");
      zzbo.zzb(var0, "context must not be null");
      return (Status)com.google.android.gms.common.internal.safeparcel.zze.zza(var1, "status", Status.CREATOR);
   }
}
