package com.example.floatlayer.storage;

import com.example.floatlayer.Config;
import com.example.floatlayer.layer.FloatLayer;

public class FloatLayerItem {
    public FloatLayer floatLayer;
    public Config config;

    public FloatLayerItem(FloatLayer floatLayer, Config config) {
        this.floatLayer = floatLayer;
        this.config = config;
    }
}
