package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import org.json.JSONException;

final class cj implements ch {
   public final cm zzx(byte[] var1) throws ca {
      if (var1 == null) {
         throw new ca("Cannot parse a null byte[]");
      } else if (var1.length == 0) {
         throw new ca("Cannot parse a 0 length byte[]");
      } else {
         db var2;
         try {
            if ((var2 = cb.zzfO(new String(var1))) != null) {
               zzcvk.v("The container was successfully parsed from the resource");
            }
         } catch (JSONException var4) {
            throw new ca("The resource data is corrupted. The container cannot be extracted from the JSON data");
         } catch (ca var5) {
            throw new ca("The resource data is invalid. The container cannot be extracted from the JSON data");
         }

         cm var3 = ci.zzbKE.zzx(var1);
         return new cm(Status.zzaBm, 0, new cn(var2), var3.zzCR());
      }
   }
}
