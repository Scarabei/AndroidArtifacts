package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzbds {
   protected final zzbdt zzaEG;

   protected static zzbdt zzb(zzbdr var0) {
      return (zzbdt)(var0.zzqC() ? zzbeo.zza(var0.zzqE()) : zzbdu.zzo(var0.zzqD()));
   }

   public static zzbdt zzn(Activity var0) {
      return zzb(new zzbdr(var0));
   }

   protected zzbds(zzbdt var1) {
      this.zzaEG = var1;
   }

   public final Activity getActivity() {
      return this.zzaEG.zzqF();
   }

   @MainThread
   public void onCreate(Bundle var1) {
   }

   @MainThread
   public void onStart() {
   }

   @MainThread
   public void onResume() {
   }

   @MainThread
   public void onSaveInstanceState(Bundle var1) {
   }

   @MainThread
   public void onActivityResult(int var1, int var2, Intent var3) {
   }

   @MainThread
   public void onStop() {
   }

   @MainThread
   public void onDestroy() {
   }

   @MainThread
   public void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
   }
}
