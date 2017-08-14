package com.google.android.gms.location.places;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzar;

public class PlacePhotoMetadataBuffer extends AbstractDataBuffer {
   public PlacePhotoMetadataBuffer(DataHolder var1) {
      super(var1);
   }

   public PlacePhotoMetadata get(int var1) {
      return new zzar(this.zzaCX, var1);
   }
}
