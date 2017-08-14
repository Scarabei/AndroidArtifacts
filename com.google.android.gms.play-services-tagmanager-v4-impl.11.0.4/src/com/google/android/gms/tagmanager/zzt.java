package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.Map;

final class zzt extends zzbr {
   private static final String ID;
   private static final String VALUE;

   public static String zzAG() {
      return ID;
   }

   public zzt() {
      super(ID, VALUE);
   }

   public static String zzAH() {
      return VALUE;
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      return (com.google.android.gms.internal.zzbr)var1.get(VALUE);
   }

   static {
      ID = zzbf.zzds.toString();
      VALUE = zzbg.zzko.toString();
   }
}
