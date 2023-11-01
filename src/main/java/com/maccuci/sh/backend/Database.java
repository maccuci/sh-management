package com.maccuci.sh.backend;

public interface Database {

    void connect();
    void disconnect();
    boolean isConnected();
}
