package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class EmptyEntityRenderer extends EntityRenderer<Entity> {
    public EmptyEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return ResourceLocation.tryBuild(DungeonsDelight.MOD_ID, "texts/hoss");
    }
}
