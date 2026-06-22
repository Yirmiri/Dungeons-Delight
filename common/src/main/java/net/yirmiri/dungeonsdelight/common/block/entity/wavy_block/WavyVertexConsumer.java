package net.yirmiri.dungeonsdelight.common.block.entity.wavy_block;

import com.mojang.blaze3d.vertex.VertexConsumer;

public record WavyVertexConsumer(VertexConsumer delegate, float time, float strength) implements VertexConsumer {
    @Override
    public VertexConsumer vertex(double x, double y, double z) {
        float angle = this.time * 0.01F;
        float dirX = (float) Math.cos(angle);
        float dirZ = (float) Math.sin(angle);
        float wave = (float) Math.sin((x * dirX + z * dirZ) + this.time * 0.05F) * strength;
        return delegate.vertex(x + wave, y, z + wave);
    }

    @Override
    public VertexConsumer color(int r, int g, int b, int a) {
        return delegate.color(r, g, b, a);
    }

    @Override
    public VertexConsumer uv(float u, float v) {
        return delegate.uv(u, v);
    }

    @Override
    public VertexConsumer overlayCoords(int u, int v) {
        return delegate.overlayCoords(u, v);
    }

    @Override
    public VertexConsumer uv2(int u, int v) {
        return delegate.uv2(u, v);
    }

    @Override
    public VertexConsumer uv2(int light) {
        return delegate.uv2(light);
    }

    @Override
    public VertexConsumer normal(float x, float y, float z) {
        return delegate.normal(x, y, z);
    }

    @Override
    public void endVertex() {
        delegate.endVertex();
    }

    @Override
    public void defaultColor(int r, int g, int b, int a) {
        delegate.defaultColor(r, g, b, a);
    }

    @Override
    public void unsetDefaultColor() {
        delegate.unsetDefaultColor();
    }
}