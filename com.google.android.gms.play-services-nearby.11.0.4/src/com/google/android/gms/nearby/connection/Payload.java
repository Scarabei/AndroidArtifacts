package com.google.android.gms.nearby.connection;

import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class Payload {
   private final long id;
   @Payload.Type
   private final int type;
   @Nullable
   private final byte[] zzbws;
   @Nullable
   private final Payload.File zzbwt;
   @Nullable
   private final Payload.Stream zzbwu;

   public static Payload fromBytes(byte[] var0) {
      zzbo.zzb(var0, "Cannot create a Payload from null bytes.");
      return zza(var0, UUID.randomUUID().getLeastSignificantBits());
   }

   public static Payload zza(byte[] var0, long var1) {
      return new Payload(var1, 1, var0, (Payload.File)null, (Payload.Stream)null);
   }

   public static Payload fromFile(java.io.File var0) throws FileNotFoundException {
      return zza(Payload.File.zza(var0, var0.length()), UUID.randomUUID().getLeastSignificantBits());
   }

   public static Payload fromFile(ParcelFileDescriptor var0) {
      return zza(Payload.File.zzb(var0), UUID.randomUUID().getLeastSignificantBits());
   }

   public static Payload zza(Payload.File var0, long var1) {
      return new Payload(var1, 2, (byte[])null, var0, (Payload.Stream)null);
   }

   public static Payload fromStream(InputStream var0) {
      return zza(Payload.Stream.zzi(var0), UUID.randomUUID().getLeastSignificantBits());
   }

   public static Payload fromStream(ParcelFileDescriptor var0) {
      return zza(Payload.Stream.zzc(var0), UUID.randomUUID().getLeastSignificantBits());
   }

   public static Payload zza(Payload.Stream var0, long var1) {
      return new Payload(var1, 3, (byte[])null, (Payload.File)null, var0);
   }

   private Payload(long var1, int var3, @Nullable byte[] var4, @Nullable Payload.File var5, @Nullable Payload.Stream var6) {
      this.id = var1;
      this.type = var3;
      this.zzbws = var4;
      this.zzbwt = var5;
      this.zzbwu = var6;
   }

   public long getId() {
      return this.id;
   }

   @Payload.Type
   public int getType() {
      return this.type;
   }

   @Nullable
   public byte[] asBytes() {
      return this.zzbws;
   }

   @Nullable
   public Payload.File asFile() {
      return this.zzbwt;
   }

   @Nullable
   public Payload.Stream asStream() {
      return this.zzbwu;
   }

   public @interface Type {
      int BYTES = 1;
      int FILE = 2;
      int STREAM = 3;
   }

   public static class Stream {
      @Nullable
      private final ParcelFileDescriptor zzbww;
      @Nullable
      private InputStream zzbwy;

      public static Payload.Stream zzi(InputStream var0) {
         zzbo.zzb(var0, "Cannot create Payload.Stream from null InputStream.");
         return new Payload.Stream((ParcelFileDescriptor)null, var0);
      }

      public static Payload.Stream zzc(ParcelFileDescriptor var0) {
         zzbo.zzb(var0, "Cannot create Payload.Stream from null ParcelFileDescriptor.");
         return new Payload.Stream(var0, (InputStream)null);
      }

      private Stream(@Nullable ParcelFileDescriptor var1, @Nullable InputStream var2) {
         this.zzbww = var1;
         this.zzbwy = var2;
      }

      @NonNull
      public InputStream asInputStream() {
         if (this.zzbwy == null) {
            this.zzbwy = new AutoCloseInputStream(this.zzbww);
         }

         return this.zzbwy;
      }

      @Nullable
      public ParcelFileDescriptor asParcelFileDescriptor() {
         return this.zzbww;
      }
   }

   public static class File {
      @Nullable
      private final java.io.File zzbwv;
      private final ParcelFileDescriptor zzbww;
      private final long zzbwx;

      public static Payload.File zza(java.io.File var0, long var1) throws FileNotFoundException {
         return new Payload.File((java.io.File)zzbo.zzb(var0, "Cannot create Payload.File from null java.io.File."), ParcelFileDescriptor.open(var0, 268435456), var1);
      }

      public static Payload.File zzb(ParcelFileDescriptor var0) {
         return new Payload.File((java.io.File)null, (ParcelFileDescriptor)zzbo.zzb(var0, "Cannot create Payload.File from null ParcelFileDescriptor."), var0.getStatSize());
      }

      private File(@Nullable java.io.File var1, ParcelFileDescriptor var2, long var3) {
         this.zzbwv = var1;
         this.zzbww = var2;
         this.zzbwx = var3;
      }

      @Nullable
      public java.io.File asJavaFile() {
         return this.zzbwv;
      }

      @NonNull
      public ParcelFileDescriptor asParcelFileDescriptor() {
         return this.zzbww;
      }

      public long getSize() {
         return this.zzbwx;
      }
   }
}
