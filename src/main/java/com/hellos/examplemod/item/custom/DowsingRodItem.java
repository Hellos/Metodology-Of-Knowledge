package com.hellos.examplemod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.WorldData;

public class DowsingRodItem extends Item {

    public DowsingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide()){
            BlockPos posClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <=  posClicked.getY() + 64; i++){
                Block blockBelow = pContext.getLevel().getBlockState(posClicked.below(i)).getBlock();

                if(isValuableBlock(blockBelow)){
                    outputValuableCoordinates(posClicked.below(i), player, blockBelow);
                    foundBlock = true;
                    if(player.isOnGround())
                        player.jumpFromGround();
                    break;
                }
            }

            if(!foundBlock){
                player.sendMessage(new TranslatableComponent("item.tutorialmod.dowsing_rod.no_valuables"), player.getUUID());
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow){
        player.sendMessage(new TextComponent("§bFound§r " + blockBelow.asItem().getRegistryName().toString() + " at " +
                "(" + blockPos.getX() + "," + blockPos.getY() +"," + blockPos.getZ() + ")"), player.getUUID());
    }

    private boolean isValuableBlock(Block block){
        return block == Blocks.COAL_ORE || block == Blocks.COPPER_ORE
                || block == Blocks.DIAMOND_ORE || block == Blocks.IRON_ORE;
    }
}

