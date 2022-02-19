package dev.vatuu.archiesarmy.extensions.mixins;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.bedrock.animation.AnimationContext;
import dev.vatuu.archiesarmy.extensions.EntityExt;
import dev.vatuu.archiesarmy.network.PacketS2CAnimationControl;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.server.PlayerStream;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.Identifier;
import net.minecraft.util.Nameable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityExt, Nameable, CommandOutput {

    @Shadow
    @Nullable
    public abstract MinecraftServer getServer();

    @Shadow
    public int age;

    @Shadow
    private EntityDimensions dimensions;
    @Shadow
    private float standingEyeHeight;

    @Shadow
    protected abstract float getEyeHeight(EntityPose pose, EntityDimensions dimensions);

    @Shadow
    public abstract EntityType<?> getType();

    private final Object2IntMap<Identifier> animationData = new Object2IntArrayMap<>();

    @Environment(EnvType.CLIENT)
    private final Object2IntMap<AnimationContext> animations = new Object2IntArrayMap<>();

    public Object2IntMap<Identifier> getServerAnimationData() {
        return animationData;
    }

    @Environment(EnvType.CLIENT)
    public Object2IntMap<AnimationContext> getClientAnimations() {
        return animations;
    }

    public void addAnimationNbt(Identifier id, int startAge) {
        if (this.getServer() == null)
            return;

        animationData.put(id, this.age - startAge);
        PacketS2CAnimationControl packet = new PacketS2CAnimationControl(
                PacketS2CAnimationControl.AnimationCommand.ADD,
                (Entity) ((Object) this),
                id,
                false);
        PlayerStream.all(this.getServer()).forEach(p -> ArchiesArmy.INSTANCE.networkHandler.sendToClient(p, packet));
    }

    public void addAnimation(Identifier id, boolean override) {
        if (this.getServer() == null)
            return;

        if (override)
            this.animationData.clear();

        animationData.put(id, this.age);
        PacketS2CAnimationControl packet = new PacketS2CAnimationControl(
                PacketS2CAnimationControl.AnimationCommand.ADD,
                (Entity) ((Object) this),
                id,
                override);
        PlayerStream.all(this.getServer()).forEach(p -> ArchiesArmy.INSTANCE.networkHandler.sendToClient(p, packet));
    }

    public void updateTime(Identifier id, int newAge) {
        if (this.getServer() == null)
            return;

        animationData.replace(id, newAge);
    }

    public void removeAnimation(Identifier id, boolean all) {
        if (this.getServer() == null)
            return;

        if (all)
            this.animationData.clear();
        else
            this.animationData.remove(id);

        PacketS2CAnimationControl packet = new PacketS2CAnimationControl(
                PacketS2CAnimationControl.AnimationCommand.REMOVE,
                (Entity) ((Object) this),
                id,
                all);
        PlayerLookup.all(this.getServer()).forEach(p -> ArchiesArmy.INSTANCE.networkHandler.sendToClient(p, packet));

    }

    @Inject(method = "writeNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;putUuid(Ljava/lang/String;Ljava/util/UUID;)V", shift = At.Shift.AFTER))
    public void toTagAnim(NbtCompound tag, CallbackInfoReturnable<NbtCompound> info) {
        if (!getServerAnimationData().isEmpty()) {
            NbtList list = new NbtList();
            getServerAnimationData().forEach((id, startAge) -> {
                int animAge = this.age - startAge;
                NbtCompound anim = new NbtCompound();
                anim.putString("animation", id.toString());
                anim.putInt("animAge", animAge);
                list.add(anim);
            });
            if(info.getReturnValue() == null)
                info.setReturnValue(new NbtCompound());
            info.getReturnValue().put("animations", list);
        }
    }

    @Inject(method = "readNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setAir(I)V", shift = At.Shift.AFTER))
    public void fromTagAnim(NbtCompound tag, CallbackInfo info) {
        NbtList anim = tag.getList("animations", NbtType.COMPOUND);
        anim.forEach(t -> {
            NbtCompound data = (NbtCompound) t;
            Identifier id = new Identifier(data.getString("animation"));
            int startAge = data.getInt("animAge");
            this.addAnimationNbt(id, startAge);
        });
    }
}
