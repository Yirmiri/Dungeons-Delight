package net.yirmiri.dungeonsdelight.common.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class FlyParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;
    private final Vec3 target;
    private final float driftSpeed;
    private final float waveOffset;
    private final boolean clockwise;

    protected FlyParticle(ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet spriteSet) {
        super(world, x, y, z, 0, 0, 0);
        this.spriteSet = spriteSet;
        this.quadSize *= 0.8F + world.random.nextFloat() * 0.5F;
        this.hasPhysics = true;
        this.friction = 0.92F;
        this.lifetime = 50 + world.random.nextInt(20);
        this.target = new Vec3(xSpeed, ySpeed, zSpeed);
        this.driftSpeed = 0.04F + world.random.nextFloat() * 0.03F;
        this.waveOffset = world.random.nextFloat() * ((float) Math.PI * 2F);
        this.clockwise = world.random.nextBoolean();
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        int sprite = (this.age / 3) % 2;
        this.setSprite(this.spriteSet.get(sprite, 1));

        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            Vec3 direction = this.target.subtract(this.x, this.y, this.z);
            double distance = direction.length();

            if (distance > 0.001D) {
                direction = direction.normalize();
            }

            float rotation = (clockwise ? 1F : -1F) * (this.age * 0.25F + this.waveOffset);
            Vec3 swirl = new Vec3(Math.cos(rotation), Math.sin(rotation) * 0.5F, Math.sin(rotation)).scale(0.03F);

            this.xd += direction.x * this.driftSpeed + swirl.x;
            this.yd += direction.y * this.driftSpeed + swirl.y;
            this.zd += direction.z * this.driftSpeed + swirl.z;

            if (distance < 0.25D) {
                this.xd *= 0.8F;
                this.yd *= 0.8F;
                this.zd *= 0.8F;
            }

            this.move(this.xd, this.yd, this.zd);
            this.xd *= this.friction;
            this.yd *= this.friction;
            this.zd *= this.friction;
        }
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        return this.quadSize * Mth.clamp((((float) this.age + scaleFactor) / (float) this.lifetime) * 12.0F, 0.0F, 1.0F);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            FlyParticle particle = new FlyParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
            particle.setSprite(spriteSet.get(0, 1));
            return particle;
        }
    }
}
