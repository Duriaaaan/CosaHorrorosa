package com.example.cosahorrosa;

public interface OnDatabaseOperationComplete {
    void onSuccess();
    void onFailure(String error);
}