package dev.vatuu.archiesarmy.animation;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.animation.client.Animation;
import dev.vatuu.archiesarmy.animation.client.AnimationData;
import dev.vatuu.archiesarmy.animation.client.AnimationRegistry;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class TesseractComponent implements EntitySyncedComponent {

    private final Entity target;
    private Identifier animation;
    private long startTime;
    private int animLength;

    private boolean shouldLoop, freeze;

    @Environment(EnvType.CLIENT) private AnimationData data;

    public TesseractComponent(Entity e) {
        this.target = e;
        this.shouldLoop = true;
        this.freeze = false;
    }

    public static TesseractComponent get(Entity e) {
        return ArchiesArmy.COMPONENT_ANIMATION.get(e);
    }

    @Override
    public Entity getEntity() {
        return target;
    }

    public void startAnimation(Identifier id, int animLength, boolean shouldLoop) {
        this.animation = id;
        if(id != null) {
            this.animLength = animLength;
            this.startTime = getEntity().world.getTime();
            this.shouldLoop = shouldLoop;
        } else {
            this.startTime = this.animLength = -1;
            this.shouldLoop = true;
        }
        this.sync();
    }

    @Override @Environment(EnvType.CLIENT)
    public void readFromPacket(PacketByteBuf buf) {
        CompoundTag tag = buf.readCompoundTag();
        if (tag != null) {
            this.fromTag(tag);
            AnimationRegistry.INSTANCE.removeActiveData(data);
            Animation anim = AnimationRegistry.INSTANCE.getAnimation(this.animation);
            if(anim != null) {
                data = new AnimationData(anim, tag.getInt("animProgress"), shouldLoop, freeze);
                AnimationRegistry.INSTANCE.addActiveData(data);
            } else
                data = null;
        }
    }

    @Environment(EnvType.CLIENT)
    public AnimationData getData() {
        return data;
    }

    public Identifier getCurrentAnimation() {
        if(getEntity().world.getTime() - startTime >= animLength)
            animation = null;
        return animation;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        if(tag.getBoolean("hasAnimation")) {
            this.startTime = tag.getLong("startTime");
            this.animLength = tag.getInt("animLength");
            this.animation = new Identifier(tag.getString("animation"));
        } else {
            this.startTime = this.animLength = -1;
            this.animation = null;
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("hasAnimation", animation != null);
        if(animation != null) {
            tag.putLong("startTime", this.startTime);
            tag.putInt("animLength", this.animLength);
            tag.putInt("animProgress", (int)(getEntity().world.getTime() - this.startTime));
            tag.putString("animation", animation.toString());
        }
        return tag;
    }

    public void setFreeze(boolean b) {
        this.freeze = b;
        this.sync();
    }
}
