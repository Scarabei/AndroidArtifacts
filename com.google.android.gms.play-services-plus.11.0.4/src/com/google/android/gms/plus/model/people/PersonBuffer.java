package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzcri;
import com.google.android.gms.internal.zzcrt;

public final class PersonBuffer extends AbstractDataBuffer {
   private final zzd zzbBE;

   public PersonBuffer(DataHolder var1) {
      super(var1);
      if (var1.zzqN() != null && var1.zzqN().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)) {
         this.zzbBE = new zzd(var1, zzcri.CREATOR);
      } else {
         this.zzbBE = null;
      }
   }

   public final Person get(int var1) {
      return (Person)(this.zzbBE != null ? (Person)((SafeParcelable)this.zzbBE.get(var1)) : new zzcrt(this.zzaCX, var1));
   }
}
