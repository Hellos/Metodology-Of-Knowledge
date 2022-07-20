package com.hellos.examplemod.screen.energy_storage;

import com.google.common.collect.Lists;
import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.block.ModBlocks;
import com.hellos.examplemod.screen.macerator.MaceratorMenu;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.ColorHandlerEvent;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class EnergyStorageScreen extends AbstractContainerScreen<EnergyStorageMenu> {
    private static final ResourceLocation TEXTRURE =
            new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/energy_storage_gui.png");

    public EnergyStorageScreen(EnergyStorageMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        this.inventoryLabelY = 83;
        this.imageHeight = 177;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTRURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0 ,0, imageWidth, imageHeight);
        blit(pPoseStack, x + 81, y + 60 - menu.getScaledEnergy(), 177, 81 - menu.getScaledEnergy(), 14, menu.getScaledEnergy());
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        if(isHovering(81, 6, 13, 53, pMouseX, pMouseY)){
            List<Component> list = Lists.newArrayList();
            MutableComponent component = new TranslatableComponent("block.tutorialmod.util.energy_stored").withStyle(ChatFormatting.WHITE);
            list.add(component);
            component = new TranslatableComponent("block.tutorialmod.util.energy_stored_info", (this.menu).getEnergy(),
                    this.menu.getMaxEnergy()).withStyle(ChatFormatting.AQUA);
            list.add(component);
            renderComponentTooltip(pPoseStack, list, pMouseX, pMouseY);
        }
    }
}
