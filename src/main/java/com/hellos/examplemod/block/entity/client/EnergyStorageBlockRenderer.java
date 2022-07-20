package com.hellos.examplemod.block.entity.client;

import com.hellos.examplemod.block.entity.custom.EnergyStorageBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.checkerframework.checker.nullness.qual.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class EnergyStorageBlockRenderer extends GeoBlockRenderer<EnergyStorageBlockEntity> {
    public EnergyStorageBlockRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new EnergyStorageBlockModel());
    }

    @Override
    public RenderType getRenderType(EnergyStorageBlockEntity animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder,
                                    int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
