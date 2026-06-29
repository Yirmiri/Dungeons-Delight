package net.yirmiri.dungeonsdelight.common.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

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
        return getBoundingBox().inflate(10.0);
    }

    @Override
    public void tick() {
        super.tick();
        setSpriteFromAge(sprites);
        setAlpha((float) (1 - age < 0.5 ? 8 * age * age * age * age : 1 - Math.pow(-2 * age + 2, 4) / lifetime));
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        Vec3 cameraPosition = camera.getPosition();
        Quaternionf quaternionf = new Quaternionf();
        quaternionf.rotationXYZ((float) (Math.PI * 0.5), 0.0F, (float) Math.PI);

        if (roll != 0.0F) {
            quaternionf.rotateZ(Mth.lerp(partialTicks, oRoll, roll));
        }
        quadSize += ((age + partialTicks) / lifetime) * 0.24F;

        Vector3f[] corners =
                new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F),
                        new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)
                };

        for (Vector3f corner : corners) {
            corner.rotate(quaternionf);
            corner.mul(getQuadSize(partialTicks));
            corner.add(
                    (float) (Mth.lerp(partialTicks, xo, this.x) - cameraPosition.x()),
                    (float) (Mth.lerp(partialTicks, yo, this.y) - cameraPosition.y()),
                    (float) (Mth.lerp(partialTicks, zo, this.z) - cameraPosition.z()));
        }

        float u0 = getU0();
        float u1 = getU1();
        float v0 = getV0();
        float v1 = getV1();
        int light = getLightColor(partialTicks);

        buffer.vertex(corners[0].x(), corners[0].y(), corners[0].z()).uv(u1, v1).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        buffer.vertex(corners[1].x(), corners[1].y(), corners[1].z()).uv(u1, v0).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        buffer.vertex(corners[2].x(), corners[2].y(), corners[2].z()).uv(u0, v0).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        buffer.vertex(corners[3].x(), corners[3].y(), corners[3].z()).uv(u0, v1).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
    }

//    @Override //todo 1.21
//    public FacingCameraMode getFacingCameraMode() {
//        return FacingCameraMode.LOOKAT_XYZ;
//    }
//
//    @Override
//    public void render(VertexConsumer buffer, Camera camera, float pPartialTicks) {
//        Quaternionf quaternionf = new Quaternionf();
//        getFacingCameraMode().setRotation(quaternionf, camera, pPartialTicks);
//
//        if (roll != 0.0F) {
//            quaternionf.rotateZ(Mth.lerp(pPartialTicks, oRoll, roll));
//        }
//
//        quadSize += ((age + pPartialTicks) / lifetime) * 0.24F;
//        quaternionf.rotationXYZ((float) -(Math.PI * .5), 0, 0);
//        renderRotatedQuad(buffer, camera, quaternionf, pPartialTicks);
//    }

//    @Override
//    public ParticleRenderType getRenderType() {
//        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
//    }

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