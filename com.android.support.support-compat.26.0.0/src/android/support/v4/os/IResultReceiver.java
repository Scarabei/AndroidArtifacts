package android.support.v4.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IResultReceiver extends IInterface {
   void send(int var1, Bundle var2) throws RemoteException;

   public abstract static class Stub extends Binder implements IResultReceiver {
      private static final String DESCRIPTOR = "android.support.v4.os.IResultReceiver";
      static final int TRANSACTION_send = 1;

      public Stub() {
         this.attachInterface(this, "android.support.v4.os.IResultReceiver");
      }

      public static IResultReceiver asInterface(IBinder obj) {
         if (obj == null) {
            return null;
         } else {
            IInterface iin = obj.queryLocalInterface("android.support.v4.os.IResultReceiver");
            return (IResultReceiver)(iin != null && iin instanceof IResultReceiver ? (IResultReceiver)iin : new IResultReceiver.Stub.Proxy(obj));
         }
      }

      public IBinder asBinder() {
         return this;
      }

      public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
         switch(code) {
         case 1:
            data.enforceInterface("android.support.v4.os.IResultReceiver");
            int _arg0 = data.readInt();
            Bundle _arg1;
            if (0 != data.readInt()) {
               _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg1 = null;
            }

            this.send(_arg0, _arg1);
            return true;
         case 1598968902:
            reply.writeString("android.support.v4.os.IResultReceiver");
            return true;
         default:
            return super.onTransact(code, data, reply, flags);
         }
      }

      private static class Proxy implements IResultReceiver {
         private IBinder mRemote;

         Proxy(IBinder remote) {
            this.mRemote = remote;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "android.support.v4.os.IResultReceiver";
         }

         public void send(int resultCode, Bundle resultData) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.os.IResultReceiver");
               _data.writeInt(resultCode);
               if (resultData != null) {
                  _data.writeInt(1);
                  resultData.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(1, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }
      }
   }
}
