package net.me.minecraft_modding_comments.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class tools {

    public static Vec3 ceilVec3(Vec3 vec3) {
        return new Vec3(Math.ceil(vec3.x), Math.ceil(vec3.y), Math.ceil(vec3.z));
    }

    public static int toPositive(int number) {
        if (number < 0) {
            return number * -1;
        }
        return number;
    }
    public static float toPositive(float number) {
        if (number < 0) {
            return number * -1;
        }
        return number;
    }
    public static double toPositive(double number) {
        if (number < 0) {
            return number * -1;
        }
        return number;
    }

    public static int randomInt(int from, int to) {
        return ((int)(Math.random()*(to-from)))+from;
    }

    public static double randomDouble(double from, double to) {
        return ((int)(Math.random()*(to-from)))+from;
    }


    public static int alwaysAbove(int value, int limit) {
        if (value < limit) {
            return limit;
        }
        return value;
    }

    public static float alwaysAbove(float value, float limit) {
        if (value < limit) {
            return limit;
        }
        return value;
    }

    public static double alwaysAbove(double value, double limit) {
        if (value < limit) {
            return limit;
        }
        return value;
    }

    public static BlockPos blockadd(BlockPos blockPos1, BlockPos blockPos2) {
        return new BlockPos(blockPos1.getX() + blockPos2.getX(), blockPos1.getY() + blockPos2.getY(), blockPos1.getZ() + blockPos2.getZ());
    }

    public static BlockPos vectorToBlockPos(Vec3 vector) {
        return new BlockPos(((int) vector.x), ((int) vector.y), ((int) vector.z));
    }
    public static BlockPos vectorToBlockPos(Vector3d vector) {
        return new BlockPos(((int) vector.x), ((int) vector.y), ((int) vector.z));
    }
    public static BlockPos vectorToBlockPos(Vector3f vector) {
        return new BlockPos(((int) vector.x), ((int) vector.y), ((int) vector.z));
    }

    public static BlockPos vectorToBlockPos(Vector3i vector) {
        return new BlockPos(((int) vector.x), ((int) vector.y), ((int) vector.z));
    }

    public static boolean randomChance(double chance) {
        if (chance > 1) {
            return true;
        }
        return chance > Math.random();
    }
}
