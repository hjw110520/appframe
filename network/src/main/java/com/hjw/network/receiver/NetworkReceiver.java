package com.hjw.network.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.hjw.base.utils.NetWorkType;
import com.hjw.base.utils.NetworkUtil;
import com.hjw.base.utils.ToastUtils;

public class NetworkReceiver extends BroadcastReceiver {

  private INetworkListener listener;

  @Override
  public void onReceive(Context context, Intent intent) {
    String action = intent.getAction();
    if (action.equalsIgnoreCase(ConnectivityManager.CONNECTIVITY_ACTION)) {
      NetWorkType type = NetworkUtil.getNetworkType(context);
      if (listener != null) {
        listener.onNetChanged(type);
      }
      switch (type) {
        case NONE:
          ToastUtils.showToast("当前无网络!");
          break;
        case WIFI:
          //ToastUtil.showToast(context, "无线网络!");
          break;
        default:
          ToastUtils.showToast("当前是3G/4G流量，请注意使用!");
          break;
      }
    }
  }

  public static IntentFilter makeNetworkIntentFilter() {
    IntentFilter filter = new IntentFilter();
    filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
    return filter;
  }

  public void setNetworkListener(INetworkListener listener) {
    this.listener = listener;
  }

  public interface INetworkListener {
    void onNetChanged(NetWorkType type);
  }

}
