package net.yirmiri.dungeonsdelight.common.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class FlameEffectParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected FlameEffectParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.friction = 0.96F;
        this.sprites = sprites;
        this.quadSize *= 0.75F;
        this.hasPhysics = false;
        this.setSprite(sprites.get(level.random.nextIntBetweenInclusive(0, 99), 99));
    }

    protected FlameEffectParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites) {
        super(level, x, y, z);
        this.friction = 0.96F;
        this.sprites = sprites;
        this.quadSize *= 0.75F;
        this.hasPhysics = false;
        this.setSprite(sprites.get(level.random.nextIntBetweenInclusive(0, 99), 99));
    }


    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    public void tick() {
        super.tick();
        this.quadSize *= Mth.clamp((((this.lifetime * 1.2f) - this.age) /(this.lifetime * 1.2f)) * 2, 0, 1);
    }

    @Override
    protected int getLightColor(float partialTick) {
        int i = super.getLightColor(partialTick);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet SPRITES;
        public Provider(SpriteSet spriteProvider) {
            this.SPRITES = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientLevel world, double x, double y, double z, double power, double i, double j) {
            return new FlameEffectParticle(world, x, y, z, this.SPRITES);
        }
    }
}