package com.google.android.gms.plus.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.model.people.PersonBuffer;

final class zzi implements People.LoadPeopleResult {
   private final Status mStatus;
   private final String zzbAC;
   private final PersonBuffer zzbAD;

   public zzi(Status var1, DataHolder var2, String var3) {
      this.mStatus = var1;
      this.zzbAC = var3;
      this.zzbAD = var2 != null ? new PersonBuffer(var2) : null;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final PersonBuffer getPersonBuffer() {
      return this.zzbAD;
   }

   public final String getNextPageToken() {
      return this.zzbAC;
   }

   public final void release() {
      if (this.zzbAD != null) {
         this.zzbAD.release();
      }

   }
}
