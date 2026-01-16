package net.yirmiri.dungeonsdelight.common.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import org.joml.Quaternionf;

public class EchoBlastParticle extends HugeExplosionParticle {
    private final SpriteSet sprites;
    private final float maxSize;
    private final int maxLifetime;

    protected EchoBlastParticle(ClientLevel level, double x, double y, double z, float maxSize, int maxLifetime, SpriteSet sprites) {
        super(level, x, y, z, 0.0F, sprites);
        this.sprites = sprites;
        this.maxSize = maxSize;
        this.maxLifetime = maxLifetime;
        this.lifetime = maxLifetime;
        this.quadSize = 0.0F;
        this.alpha = 1.0F;
        this.setSpriteFromAge(sprites);
    }

    public AABB getRenderBoundingBox(float partialTicks) {
        return this.getBoundingBox().inflate(10.0);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.setAlpha((float) (1 - this.age < 0.5 ? 8 * this.age * this.age * this.age * this.age : 1 - Math.pow(-2 * this.age + 2, 4) / this.lifetime));
    }

    @Override
    public FacingCameraMode getFacingCameraMode() {
        return FacingCameraMode.LOOKAT_XYZ;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float pPartialTicks) {
        Quaternionf quaternionf = new Quaternionf();
        this.getFacingCameraMode().setRotation(quaternionf, camera, pPartialTicks);

        if (this.roll != 0.0F) {
            quaternionf.rotateZ(Mth.lerp(pPartialTicks, this.oRoll, this.roll));
        }

        this.quadSize += ((this.age + pPartialTicks) / this.lifetime) * 0.24F;
        quaternionf.rotationXYZ((float) -(Math.PI * .5), 0, 0);
        this.renderRotatedQuad(buffer, camera, quaternionf, pPartialTicks);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new EchoBlastParticle(level, x, y + 0.5, z, 2.5F, 20, sprites);
        }
    }

    public static class Small extends Provider {
        private final SpriteSet sprites;

        public Small(SpriteSet sprites) {
            super(sprites);
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new EchoBlastParticle(level, x, y + 0.5, z, 2F, 16, sprites);
        }
    }

    public static class Medium extends Provider {
        private final SpriteSet sprites;

        public Medium(SpriteSet sprites) {
            super(sprites);
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new EchoBlastParticle(level, x, y + 0.5, z, 3F, 24, sprites);
        }
    }

    public static class Large extends Provider {
        private final SpriteSet sprites;

        public Large(SpriteSet sprites) {
            super(sprites);
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new EchoBlastParticle(level, x, y + 0.5, z, 4F, 32, sprites);
        }
    }
}
