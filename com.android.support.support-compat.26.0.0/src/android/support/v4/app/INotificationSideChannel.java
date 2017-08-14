package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface INotificationSideChannel extends IInterface {
   void notify(String var1, int var2, String var3, Notification var4) throws RemoteException;

   void cancel(String var1, int var2, String var3) throws RemoteException;

   void cancelAll(String var1) throws RemoteException;

   public abstract static class Stub extends Binder implements INotificationSideChannel {
      private static final String DESCRIPTOR = "android.support.v4.app.INotificationSideChannel";
      static final int TRANSACTION_notify = 1;
      static final int TRANSACTION_cancel = 2;
      static final int TRANSACTION_cancelAll = 3;

      public Stub() {
         this.attachInterface(this, "android.support.v4.app.INotificationSideChannel");
      }

      public static INotificationSideChannel asInterface(IBinder obj) {
         if (obj == null) {
            return null;
         } else {
            IInterface iin = obj.queryLocalInterface("android.support.v4.app.INotificationSideChannel");
            return (INotificationSideChannel)(iin != null && iin instanceof INotificationSideChannel ? (INotificationSideChannel)iin : new INotificationSideChannel.Stub.Proxy(obj));
         }
      }

      public IBinder asBinder() {
         return this;
      }

      public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
         String _arg0;
         int _arg1;
         String _arg2;
         switch(code) {
         case 1:
            data.enforceInterface("android.support.v4.app.INotificationSideChannel");
            _arg0 = data.readString();
            _arg1 = data.readInt();
            _arg2 = data.readString();
            Notification _arg3;
            if (0 != data.readInt()) {
               _arg3 = (Notification)Notification.CREATOR.createFromParcel(data);
            } else {
               _arg3 = null;
            }

            this.notify(_arg0, _arg1, _arg2, _arg3);
            return true;
         case 2:
            data.enforceInterface("android.support.v4.app.INotificationSideChannel");
            _arg0 = data.readString();
            _arg1 = data.readInt();
            _arg2 = data.readString();
            this.cancel(_arg0, _arg1, _arg2);
            return true;
         case 3:
            data.enforceInterface("android.support.v4.app.INotificationSideChannel");
            _arg0 = data.readString();
            this.cancelAll(_arg0);
            return true;
         case 1598968902:
            reply.writeString("android.support.v4.app.INotificationSideChannel");
            return true;
         default:
            return super.onTransact(code, data, reply, flags);
         }
      }

      private static class Proxy implements INotificationSideChannel {
         private IBinder mRemote;

         Proxy(IBinder remote) {
            this.mRemote = remote;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "android.support.v4.app.INotificationSideChannel";
         }

         public void notify(String packageName, int id, String tag, Notification notification) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
               _data.writeString(packageName);
               _data.writeInt(id);
               _data.writeString(tag);
               if (notification != null) {
                  _data.writeInt(1);
                  notification.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(1, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void cancel(String packageName, int id, String tag) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
               _data.writeString(packageName);
               _data.writeInt(id);
               _data.writeString(tag);
               this.mRemote.transact(2, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void cancelAll(String packageName) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
               _data.writeString(packageName);
               this.mRemote.transact(3, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }
      }
   }
}
