package com.google.android.gms.gcm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.util.Iterator;

public class GcmListenerService extends com.google.firebase.iid.zzb {
   public void onMessageReceived(String var1, Bundle var2) {
   }

   public void onDeletedMessages() {
   }

   public void onMessageSent(String var1) {
   }

   public void onSendError(String var1, String var2) {
   }

   public void handleIntent(Intent var1) {
      String var2 = var1.getAction();
      String var10001;
      String var10002;
      String var10003;
      if (!"com.google.android.c2dm.intent.RECEIVE".equals(var2)) {
         var10002 = String.valueOf(var1.getAction());
         if (var10002.length() != 0) {
            var10001 = "Unknown intent action: ".concat(var10002);
         } else {
            var10003 = new String;
            var10001 = var10003;
            var10003.<init>("Unknown intent action: ");
         }

         Log.w("GcmListenerService", var10001);
      } else {
         String var3;
         if ((var3 = var1.getStringExtra("message_type")) == null) {
            var3 = "gcm";
         }

         byte var5 = -1;
         switch(var3.hashCode()) {
         case -2062414158:
            if (var3.equals("deleted_messages")) {
               var5 = 1;
            }
            break;
         case 102161:
            if (var3.equals("gcm")) {
               var5 = 0;
            }
            break;
         case 814694033:
            if (var3.equals("send_error")) {
               var5 = 3;
            }
            break;
         case 814800675:
            if (var3.equals("send_event")) {
               var5 = 2;
            }
         }

         switch(var5) {
         case 0:
            Bundle var8;
            (var8 = var1.getExtras()).remove("message_type");
            var8.remove("android.support.content.wakelockid");
            if ("1".equals(zza.zze(var8, "gcm.n.e")) || zza.zze(var8, "gcm.n.icon") != null) {
               if (!zza.zzaY(this)) {
                  zza.zzaX(this).zzv(var8);
                  return;
               }

               zza.zzu(var8);
            }

            String var9 = var8.getString("from");
            var8.remove("from");
            zzt(var8);
            this.onMessageReceived(var9, var8);
            return;
         case 1:
            this.onDeletedMessages();
            return;
         case 2:
            this.onMessageSent(var1.getStringExtra("google.message_id"));
            return;
         case 3:
            String var7;
            if ((var7 = var1.getStringExtra("google.message_id")) == null) {
               var7 = var1.getStringExtra("message_id");
            }

            this.onSendError(var7, var1.getStringExtra("error"));
            return;
         default:
            var10002 = String.valueOf(var3);
            if (var10002.length() != 0) {
               var10001 = "Received message with unknown type: ".concat(var10002);
            } else {
               var10003 = new String;
               var10001 = var10003;
               var10003.<init>("Received message with unknown type: ");
            }

            Log.w("GcmListenerService", var10001);
         }
      }
   }

   static void zzt(Bundle var0) {
      Iterator var1 = var0.keySet().iterator();

      while(var1.hasNext()) {
         String var2;
         if ((var2 = (String)var1.next()) != null && var2.startsWith("google.c.")) {
            var1.remove();
         }
      }

   }
}
