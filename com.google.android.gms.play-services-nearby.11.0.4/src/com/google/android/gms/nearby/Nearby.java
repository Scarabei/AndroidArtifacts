package com.google.android.gms.nearby;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzckc;
import com.google.android.gms.internal.zzcke;
import com.google.android.gms.internal.zzclm;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.messages.Messages;
import com.google.android.gms.nearby.messages.zzd;
import com.google.android.gms.nearby.messages.internal.zzak;
import com.google.android.gms.nearby.messages.internal.zzaw;

public final class Nearby {
   public static final Api CONNECTIONS_API;
   public static final Connections Connections;
   public static final Api MESSAGES_API;
   public static final Messages Messages;
   private static zzd zzbwi;
   private static Api zzbwj;
   private static zzckc zzbwk;

   static {
      CONNECTIONS_API = new Api("Nearby.CONNECTIONS_API", zzclm.zzajS, zzclm.zzajR);
      Connections = new zzclm();
      MESSAGES_API = new Api("Nearby.MESSAGES_API", zzak.zzajS, zzak.zzajR);
      Messages = zzak.zzbzi;
      zzbwi = new zzaw();
      zzbwj = new Api("Nearby.BOOTSTRAP_API", zzcke.zzajS, zzcke.zzajR);
      zzbwk = new zzcke();
   }
}
