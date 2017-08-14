package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.nearby.connection.Payload;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class zzcoq {
   static Pair zza(Payload var0) throws IOException {
      switch(var0.getType()) {
      case 1:
         return Pair.create(new zzcoo(var0.getId(), var0.getType(), var0.asBytes(), (ParcelFileDescriptor)null, (String)null, -1L, (ParcelFileDescriptor)null), (Object)null);
      case 2:
         String var1 = var0.asFile().asJavaFile() == null ? null : var0.asFile().asJavaFile().getAbsolutePath();
         return Pair.create(new zzcoo(var0.getId(), var0.getType(), (byte[])null, var0.asFile().asParcelFileDescriptor(), var1, var0.asFile().getSize(), (ParcelFileDescriptor)null), (Object)null);
      case 3:
         ParcelFileDescriptor[] var2;
         ParcelFileDescriptor[] var3;
         try {
            var2 = ParcelFileDescriptor.createPipe();
            var3 = ParcelFileDescriptor.createPipe();
         } catch (IOException var5) {
            Log.e("NearbyConnections", String.format("Unable to create PFD pipe for streaming payload %d from client to service.", var0.getId()), var5);
            throw var5;
         }

         return Pair.create(new zzcoo(var0.getId(), var0.getType(), (byte[])null, var2[0], (String)null, -1L, var3[0]), Pair.create(var2[1], var3[1]));
      default:
         IllegalArgumentException var4 = new IllegalArgumentException(String.format("Outgoing Payload %d has unknown type %d", var0.getId(), var0.getType()));
         Log.wtf("NearbyConnections", "Unknown payload type!", var4);
         throw var4;
      }
   }

   static Payload zza(zzcoo var0) {
      long var1 = var0.getId();
      switch(var0.getType()) {
      case 1:
         return Payload.zza(var0.getBytes(), var1);
      case 2:
         String var3;
         if ((var3 = var0.zzzO()) != null) {
            try {
               return Payload.zza(Payload.File.zza(new File(var3), var0.zzzP()), var1);
            } catch (FileNotFoundException var5) {
               String var10002 = String.valueOf(var3);
               String var10001;
               if (var10002.length() != 0) {
                  var10001 = "Failed to create Payload from ParcelablePayload: Java file not found at ".concat(var10002);
               } else {
                  String var10003 = new String;
                  var10001 = var10003;
                  var10003.<init>("Failed to create Payload from ParcelablePayload: Java file not found at ");
               }

               Log.w("NearbyConnections", var10001, var5);
            }
         }

         return Payload.zza(Payload.File.zzb(var0.zzzN()), var1);
      case 3:
         return Payload.zza(Payload.Stream.zzc(var0.zzzN()), var1);
      default:
         Log.w("NearbyConnections", String.format("Incoming ParcelablePayload %d has unknown type %d", var0.getId(), var0.getType()));
         return null;
      }
   }
}
