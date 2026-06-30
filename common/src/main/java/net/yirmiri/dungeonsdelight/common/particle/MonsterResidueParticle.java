package net.yirmiri.dungeonsdelight.common.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;

public class MonsterResidueParticle extends TextureSheetParticle {
    private final SpriteSet sprites;
    private final float xRotSpeed;
    private final float yRotSpeed;
    private final float zRotSpeed;
    private final float groundYaw;

    protected MonsterResidueParticle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd, SpriteSet sprites) {
        super(level, x, y, z);
        this.sprites = sprites;
        this.xd = xd * 1.6;
        this.yd = yd * 1.6;
        this.zd = zd * 1.6;
        this.friction = 0.995F;
        this.gravity = 0.06F;
        this.lifetime = 140;
        this.rCol = 0.75F;
        this.gCol = 0.9F;
        this.bCol = 1.0F;
        this.xRotSpeed = (level.random.nextFloat() - 0.5F) * 0.85F;
        this.yRotSpeed = (level.random.nextFloat() - 0.5F) * 0.85F;
        this.zRotSpeed = (level.random.nextFloat() - 0.5F) * 1.2F;
        this.groundYaw = level.random.nextFloat() * ((float) Math.PI * 2F);
        this.roll = level.random.nextFloat() * ((float) Math.PI * 2F);
        this.oRoll = this.roll;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(sprites);

        if (!this.onGround) {
            this.xd *= 1.005;
            this.yd *= 1.005;
            this.zd *= 1.005;
            this.oRoll = this.roll;
            this.roll += zRotSpeed;
        }
        if (this.age >= 100) {
            this.alpha = 1.0F - (float) (this.age - 100) / 40.0F;
        }
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        Vec3 cameraPosition = camera.getPosition();
        Quaternionf quaternionf = new Quaternionf();

        float px = (float)(Mth.lerp(partialTicks, xo, this.x) - cameraPosition.x());
        float py = (float)(Mth.lerp(partialTicks, yo, this.y) - cameraPosition.y());
        float pz = (float)(Mth.lerp(partialTicks, zo, this.z) - cameraPosition.z());

        if (this.onGround) {
            py += 0.003F;
            quaternionf.rotateY(groundYaw);
            quaternionf.rotateX((float)(-Math.PI * 0.5));
        } else {
            float time = age + partialTicks;
            quaternionf.rotationXYZ(time * xRotSpeed, time * yRotSpeed, time * zRotSpeed);
            quaternionf.rotateX(Mth.sin(time * 0.18F));
            quaternionf.rotateY(Mth.cos(time * 0.15F) * 1.1F);
            quaternionf.rotateZ(Mth.sin(time * 0.22F) * 0.9F);
        }

        Vector3f[] corners = new Vector3f[] {
                new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F),
                new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)
        };

        for (Vector3f corner : corners) {
            corner.rotate(quaternionf);
            corner.mul(getQuadSize(partialTicks));
            corner.add(px, py, pz);
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

        buffer.vertex(corners[3].x(), corners[3].y(), corners[3].z()).uv(u0, v1).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        buffer.vertex(corners[2].x(), corners[2].y(), corners[2].z()).uv(u0, v0).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        buffer.vertex(corners[1].x(), corners[1].y(), corners[1].z()).uv(u1, v0).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        buffer.vertex(corners[0].x(), corners[0].y(), corners[0].z()).uv(u1, v1).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
            return new MonsterResidueParticle(level, x, y, z, xd, yd, zd, sprites);
        }
    }
}