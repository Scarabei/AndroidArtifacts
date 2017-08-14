package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.model.people.PersonBuffer;

final class zzcrh implements People.LoadPeopleResult {
   // $FF: synthetic field
   private Status zzakB;

   zzcrh(zzcrg var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final PersonBuffer getPersonBuffer() {
      return null;
   }

   public final String getNextPageToken() {
      return null;
   }
}
