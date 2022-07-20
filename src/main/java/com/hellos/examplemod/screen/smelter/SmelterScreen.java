package com.hellos.examplemod.screen.smelter;

import com.google.common.collect.Lists;
import com.hellos.examplemod.TutorialMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;


public class SmelterScreen extends AbstractContainerScreen<SmelterMenu> {
    private static final ResourceLocation TEXTRURE =
            new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/macerator_gui.png");

    public SmelterScreen(SmelterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTRURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.blit(pPoseStack, x, y, 0 ,0, imageWidth, imageHeight);
        blit(pPoseStack, x + 149, y + 70 - menu.getScaledEnergy(), 177, 83- menu.getScaledEnergy(), 13, menu.getScaledEnergy());
        if(menu.isCrafting()){
            blit(pPoseStack, x + 89, y + 32, 176, 0, 8, menu.getScaledProgress());
        }
    }

    public static double mapNumber(double value, double rangeMin, double rangeMax, double resultMin, double resultMax){
        return (value - rangeMin) / (rangeMax - rangeMin) * (resultMax - resultMin) + resultMin;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
        if(isHovering(149, 16, 13, 53, pMouseX, pMouseY)){
            List<Component> list = Lists.newArrayList();
            MutableComponent component = new TranslatableComponent("block.tutorialmod.util.energy_stored").withStyle(ChatFormatting.WHITE);
            list.add(component);
            component = new TranslatableComponent("block.tutorialmod.util.energy_stored_info", (this.menu).getEnergy(),
                    this.menu.getMaxEnergy()).withStyle(ChatFormatting.AQUA);
            list.add(component);
            renderComponentTooltip(pPoseStack, list, pMouseX, pMouseY);
        }

        if(isHovering(91,32,4,25, pMouseX, pMouseY)){
            renderTooltip(pPoseStack, new TranslatableComponent("block.tutorialmod.util.progress", this.menu.getPercentage()), pMouseX, pMouseY);
        }
    }
}
