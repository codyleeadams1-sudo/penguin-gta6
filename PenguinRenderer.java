package com.penguinmod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.penguinmod.PenguinMod;
import com.penguinmod.entity.PenguinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class PenguinRenderer extends MobRenderer<PenguinEntity, PenguinRenderState, PenguinModel> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(PenguinMod.MOD_ID, "textures/entity/penguin.png");

    /** The model is authored ~27 pixels tall; scale it down to roughly one block. */
    private static final float MODEL_SCALE = 0.62F;

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel(context.bakeLayer(PenguinModel.LAYER)), 0.4F);
    }

    @Override
    public PenguinRenderState createRenderState() {
        return new PenguinRenderState();
    }

    @Override
    public Identifier getTextureLocation(PenguinRenderState state) {
        return TEXTURE;
    }

    @Override
    protected void scale(PenguinRenderState state, PoseStack poseStack) {
        poseStack.scale(MODEL_SCALE, MODEL_SCALE, MODEL_SCALE);
    }
}
