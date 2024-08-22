package net.me.minecraft_modding_comments.tools.custom;

import net.minecraft.world.phys.Vec3;

public class TickObject implements TickObjectValue{
    int tick;
    Runnable code;
    Runnable code1;
    int identifier2;
    public TickObject(int value, int identifier, Runnable code1) {
        tick = value;
        code = code1;
        identifier = identifier2;

    }

    public TickObject(int value, Runnable code1) {
        tick = value;
        code = code1;
    }

    public TickObject(int value, int identifier1, Runnable code1, Runnable code2) {
        tick = value;
        code = code1;
        code1 = code2;
        identifier2 = identifier1;
    }

    @Override
    public int value() {
        return this.tick;
    }

    @Override
    public int identifier() {
        return this.identifier2;
    }

    @Override
    public Runnable runnable() {
        return this.code;
    }

    @Override
    public Runnable runnable2() {
        return this.code1;
    }
}
