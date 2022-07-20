package com.hellos.examplemod.item.client;

import com.hellos.examplemod.item.custom.EnergyStorageBlockItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class EnergyStorageBlockItemRenderer extends GeoItemRenderer<EnergyStorageBlockItem> {
    public EnergyStorageBlockItemRenderer() {
        super(new EnergyStorageBlockItemModel());
    }
}
