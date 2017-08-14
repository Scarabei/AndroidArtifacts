package com.google.android.gms.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;

final class zzaxy implements Cast.ApplicationConnectionResult {
   private final Status mStatus;
   private final ApplicationMetadata zzaye;
   private final String zzayf;
   private final String mSessionId;
   private final boolean zzayg;

   public zzaxy(Status var1, ApplicationMetadata var2, String var3, String var4, boolean var5) {
      this.mStatus = var1;
      this.zzaye = var2;
      this.zzayf = var3;
      this.mSessionId = var4;
      this.zzayg = var5;
   }

   public zzaxy(Status var1) {
      this(var1, (ApplicationMetadata)null, (String)null, (String)null, false);
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final ApplicationMetadata getApplicationMetadata() {
      return this.zzaye;
   }

   public final String getApplicationStatus() {
      return this.zzayf;
   }

   public final String getSessionId() {
      return this.mSessionId;
   }

   public final boolean getWasLaunched() {
      return this.zzayg;
   }
}
