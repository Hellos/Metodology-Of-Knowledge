package com.hellos.examplemod.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.hellos.examplemod.world.feature.ModOrePlacement.commonOrePlacement;

public class ModPlacedFeatures {

    public static final Holder<PlacedFeature> TIN_ORE_PLACED = PlacementUtils.register("tin_ore_placed",
            ModConfiguredFeatures.TIN_ORE, commonOrePlacement(7, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-10), VerticalAnchor.belowTop(240))));

    public static final Holder<PlacedFeature> COBALT_ORE_PLACED = PlacementUtils.register("cobalt_ore_placed",
            ModConfiguredFeatures.COBALT_ORE, commonOrePlacement(100, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480))));

    public static final Holder<PlacedFeature> ALUMINUM_ORE_PLACED = PlacementUtils.register("aluminum_ore_placed",
            ModConfiguredFeatures.ALUMINUM_ORE, commonOrePlacement(9, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-10), VerticalAnchor.belowTop(240))));
}
