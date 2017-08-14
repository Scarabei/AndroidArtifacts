package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import org.json.JSONException;

final class ck implements ch {
   public final cm zzx(byte[] var1) throws ca {
      if (var1 == null) {
         throw new ca("Cannot parse a null byte[]");
      } else if (var1.length == 0) {
         throw new ca("Cannot parse a 0 length byte[]");
      } else {
         dj var2;
         try {
            if ((var2 = cb.zzfP(new String(var1))) != null) {
               zzcvk.v("The runtime configuration was successfully parsed from the resource");
            }
         } catch (JSONException var3) {
            throw new ca("The resource data is corrupted. The runtime configuration cannot be extracted from the JSON data");
         } catch (ca var4) {
            throw new ca("The resource data is invalid. The runtime  configuration cannot be extracted from the JSON data");
         }

         return new cm(Status.zzaBm, 0, (cn)null, var2);
      }
   }
}
