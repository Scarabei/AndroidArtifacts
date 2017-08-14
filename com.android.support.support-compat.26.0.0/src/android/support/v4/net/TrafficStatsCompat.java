package android.support.v4.net;

import android.net.TrafficStats;
import android.os.ParcelFileDescriptor;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public final class TrafficStatsCompat {
   private static final TrafficStatsCompat.TrafficStatsCompatBaseImpl IMPL;

   /** @deprecated */
   @Deprecated
   public static void clearThreadStatsTag() {
      TrafficStats.clearThreadStatsTag();
   }

   /** @deprecated */
   @Deprecated
   public static int getThreadStatsTag() {
      return TrafficStats.getThreadStatsTag();
   }

   /** @deprecated */
   @Deprecated
   public static void incrementOperationCount(int operationCount) {
      TrafficStats.incrementOperationCount(operationCount);
   }

   /** @deprecated */
   @Deprecated
   public static void incrementOperationCount(int tag, int operationCount) {
      TrafficStats.incrementOperationCount(tag, operationCount);
   }

   /** @deprecated */
   @Deprecated
   public static void setThreadStatsTag(int tag) {
      TrafficStats.setThreadStatsTag(tag);
   }

   /** @deprecated */
   @Deprecated
   public static void tagSocket(Socket socket) throws SocketException {
      TrafficStats.tagSocket(socket);
   }

   /** @deprecated */
   @Deprecated
   public static void untagSocket(Socket socket) throws SocketException {
      TrafficStats.untagSocket(socket);
   }

   public static void tagDatagramSocket(DatagramSocket socket) throws SocketException {
      IMPL.tagDatagramSocket(socket);
   }

   public static void untagDatagramSocket(DatagramSocket socket) throws SocketException {
      IMPL.untagDatagramSocket(socket);
   }

   static {
      if (VERSION.SDK_INT >= 24) {
         IMPL = new TrafficStatsCompat.TrafficStatsCompatApi24Impl();
      } else {
         IMPL = new TrafficStatsCompat.TrafficStatsCompatBaseImpl();
      }

   }

   @RequiresApi(24)
   static class TrafficStatsCompatApi24Impl extends TrafficStatsCompat.TrafficStatsCompatBaseImpl {
      public void tagDatagramSocket(DatagramSocket socket) throws SocketException {
         TrafficStats.tagDatagramSocket(socket);
      }

      public void untagDatagramSocket(DatagramSocket socket) throws SocketException {
         TrafficStats.untagDatagramSocket(socket);
      }
   }

   static class TrafficStatsCompatBaseImpl {
      public void tagDatagramSocket(DatagramSocket socket) throws SocketException {
         ParcelFileDescriptor pfd = ParcelFileDescriptor.fromDatagramSocket(socket);
         TrafficStats.tagSocket(new DatagramSocketWrapper(socket, pfd.getFileDescriptor()));
         pfd.detachFd();
      }

      public void untagDatagramSocket(DatagramSocket socket) throws SocketException {
         ParcelFileDescriptor pfd = ParcelFileDescriptor.fromDatagramSocket(socket);
         TrafficStats.untagSocket(new DatagramSocketWrapper(socket, pfd.getFileDescriptor()));
         pfd.detachFd();
      }
   }
}
