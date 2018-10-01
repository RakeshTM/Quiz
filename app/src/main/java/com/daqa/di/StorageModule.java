package com.daqa.di;

import android.content.Context;

import com.daqa.data.DataManager;
import com.daqa.data.IDataManager;
import com.daqa.data.db.DBHelper;
import com.daqa.data.db.IDBHelper;
import com.daqa.data.network.INetworkHelper;
import com.daqa.data.network.NetworkHelper;
import com.daqa.data.sp.ISPHelper;
import com.daqa.data.sp.SPHelper;
import com.daqa.di.annotations.ApplicationContext;

import dagger.Binds;
import dagger.Module;

@Module
public interface StorageModule {

    @Binds
    @ApplicationContext
    Context bindContext(Context context);

    @Binds
    INetworkHelper bindNetworkHelper(NetworkHelper nh);

    @Binds
    IDBHelper bindDBHelper(DBHelper dh);

    @Binds
    ISPHelper bindFileHelper(SPHelper fh);

    @Binds
    IDataManager bindDataManager(DataManager dm);

}
