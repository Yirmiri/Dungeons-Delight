package net.yirmiri.dungeonsdelight.common.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class BoneParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    BoneParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = sprites;
        setSpriteFromAge(sprites);
        this.hasPhysics = true;
        this.friction = 0.8F;
        this.gravity = 0.75F;
    }

    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);

        if (!this.onGround) {
          this.oRoll = this.roll;
          this.roll += 0.1F;
        } else if (this.hasPhysics) {
            this.gravity = 0.0F;
            this.friction = 0.0F;
            this.hasPhysics = false;
        }
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public float getQuadSize(float scaleFactor) {
        float f = ((float) age + scaleFactor) / (float) lifetime;
        return quadSize * (1.0F - f * f * 0.5F);
    }

    public int getLightColor(float partialTick) {
        float f = ((float) age + partialTick) / (float) lifetime;
        f = Mth.clamp(f, 0.0F, 1.0F);
        int i = super.getLightColor(partialTick);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int) (f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }
        return j | k << 16;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprites) {
            sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new BoneParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
        }
    }
}
