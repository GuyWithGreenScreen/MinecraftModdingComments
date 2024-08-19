package net.me.minecraft_modding_comments.tools.custom;

import net.minecraft.world.phys.Vec3;

public class TickObject implements TickObjectValue{
    int tick;
    Runnable code;
    public TickObject(int value, Runnable code1) {
        tick = value;
        code = code1;

    }

    @Override
    public int value() {
        return this.tick;
    }

    @Override
    public Runnable runnable2() {
        return this.code;
    }
}
