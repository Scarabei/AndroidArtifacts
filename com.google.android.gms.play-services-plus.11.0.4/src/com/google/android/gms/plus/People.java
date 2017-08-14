package com.google.android.gms.plus;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.Collection;

/** @deprecated */
@Deprecated
public interface People {
   /** @deprecated */
   @Deprecated
   Person getCurrentPerson(GoogleApiClient var1);

   /** @deprecated */
   @Deprecated
   PendingResult loadVisible(GoogleApiClient var1, int var2, String var3);

   /** @deprecated */
   @Deprecated
   PendingResult loadVisible(GoogleApiClient var1, String var2);

   /** @deprecated */
   @Deprecated
   PendingResult loadConnected(GoogleApiClient var1);

   /** @deprecated */
   @Deprecated
   PendingResult load(GoogleApiClient var1, Collection var2);

   /** @deprecated */
   @Deprecated
   PendingResult load(GoogleApiClient var1, String... var2);

   public interface LoadPeopleResult extends Releasable, Result {
      PersonBuffer getPersonBuffer();

      String getNextPageToken();
   }

   public interface OrderBy {
      int ALPHABETICAL = 0;
      int BEST = 1;
   }
}
