package com.penguinmod.client;

import com.penguinmod.PenguinMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class PenguinModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(PenguinModel.LAYER, PenguinModel::createBodyLayer);
        EntityRenderers.register(PenguinMod.PENGUIN, PenguinRenderer::new);
    }
}
