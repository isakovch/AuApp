package com.example.chyngyz.auapp.ui;

public interface Lifecycle<V> {

    void bind(V view);

    void unbind();
}
