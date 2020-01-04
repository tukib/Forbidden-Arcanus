package com.stal111.forbidden_arcanus.util;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.stal111.forbidden_arcanus.Main;
import com.stal111.forbidden_arcanus.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class RenderUtils {

    private static final ClientWorld world = Minecraft.getInstance().world;
    private static final VertexFormat ITEM_FORMAT_WITH_LIGHTMAP = addTex2sToVertexFormat(DefaultVertexFormats.field_227849_i_);

    public static void setRenderLayer(ModBlocks block, RenderType renderType) {
        RenderTypeLookup.setRenderLayer(block.getBlock(), renderType);
    }

    public static boolean isLightMapDisabled() {
         return true;
    }

    public static VertexFormat getFormatWithLightMap(VertexFormat format) {
        if (isLightMapDisabled()) {
            return format;
        }

        if (format == DefaultVertexFormats.BLOCK) {
            return DefaultVertexFormats.BLOCK;
        } else if (format == DefaultVertexFormats.field_227849_i_) {
            return ITEM_FORMAT_WITH_LIGHTMAP;
        }
        return format;
    }

    private static VertexFormat addTex2sToVertexFormat(VertexFormat format) {
        List<VertexFormatElement> elements = new ArrayList<>(format.func_227894_c_());
        elements.add(DefaultVertexFormats.TEX_2S);
        return new VertexFormat(ImmutableList.copyOf(elements));
    }

    public static void renderItemTexture(int x, int y, ItemStack stack) {
        Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(Main.MOD_ID, "textures/item/" + stack.getItem().getRegistryName().getPath() + ".png"));
        AbstractGui.blit(x, y, 0, 0, 16, 16, 16, 16);
    }
}