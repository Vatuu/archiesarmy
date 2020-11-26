package dev.vatuu.archiesarmy.client.models;

import com.google.common.collect.ImmutableMap;
import dev.vatuu.archiesarmy.entities.EnchanterEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;

import java.util.HashMap;
import java.util.Map;

public class EnchanterEntityModel extends BetterCompositeEntityModel<EnchanterEntity> implements ModelWithHead, ModelWithArms {

    private final ModelPart root;
    private final ModelPart head, nose;
    private final ModelPart body, cape, legRight, legLeft;
    private final ModelPart armRight, armLeft, armVillager;

    public EnchanterEntityModel() {
        this.textureHeight = 64;
        this.textureWidth = 128;

        this.root = new ModelPart(this);
        root.setPivot(0, 24, 0);

        this.head = new ModelPart(this);
        root.addChild(head);
        head.addCuboid("head", -4, -10, -4, 8, 10, 8, 0, 0, 0);
        head.addCuboid("turban", -5, -14, -5, 10, 8, 10, 0, 0, 18);
        head.setPivot(0, -24, 0);

        this.nose = new ModelPart(this);
        head.addChild(nose);
        nose.addCuboid("nose", -6, -3, 3, 2, 4, 2, 0, 24, 0);
        nose.setPivot(5, 0, -9);

        this.body = new ModelPart(this);
        root.addChild(body);
        body.addCuboid("body", -4, -4, -3, 8, 12, 6, 0, 32, 0);
        body.addCuboid("coat", -4, -4, -3, 8, 19, 6, .5f, 0, 36);
        body.setPivot(0, -20, 0);

        this.cape = new ModelPart(this);
        body.addChild(cape);
        cape.addCuboid("cape", -6, 0, 0, 12, 23, 1, 0, 28, 36);
        cape.setPivot(0, -4, 3);
        cape.pitch = 0.1745f;

        this.armRight = new ModelPart(this);
        body.addChild(armRight);
        armRight.setTextureOffset(56, 18);
        armRight.addCuboid(-4, 0, -2, 4, 12, 4, 0, true);
        armRight.setPivot(-4, -4, 0);
        armRight.visible = false;

        this.armLeft = new ModelPart(this);
        body.addChild(armLeft);
        armLeft.setTextureOffset(56, 18);
        armLeft.addCuboid(0, 0, -2, 4, 12, 4, 0, false);
        armLeft.setPivot(4, -4, 0);
        armLeft.visible = false;

        this.armVillager = new ModelPart(this);
        body.addChild(armVillager);
        armVillager.addCuboid("hands", -4, 2, -2, 8, 4, 4, 0, 54, 46);
        armVillager.setTextureOffset(54, 34);
        armVillager.addCuboid(4, -2, -2, 4, 8, 4, 0, true);
        armVillager.addCuboid(-8, -2, -2, 4, 8, 4, 0, false);
        armVillager.pitch = -0.7854f;

        this.legRight = new ModelPart(this);
        body.addChild(legRight);
        legRight.setTextureOffset(40, 18);
        legRight.addCuboid(-2, 0, -2, 4, 12, 4, 0, false);
        legRight.setPivot(-2, 8, 0);

        this.legLeft = new ModelPart(this);
        body.addChild(legLeft);
        legLeft.setTextureOffset(40, 18);
        legLeft.addCuboid(-2, 0, -2, 4, 12, 4, 0, true);
        legLeft.setPivot(2, 8, 0);
    }

    @Override
    public ModelPart getHead() {
        return head;
    }

    public ImmutableMap<String, ModelPart> getAllParts() {
        Map<String, ModelPart> map = new HashMap<>();
        map.put("root", root);
        map.put("head", head); map.put("nose", nose);
        map.put("body", body); map.put("cape", cape); map.put("legLeft", legLeft); map.put("legRight", legRight);
        map.put("armVillager", armVillager); map.put("armLeft", armLeft); map.put("armRight", armRight);
        return ImmutableMap.copyOf(map);
    }

    @Override
    public void setArmAngle(Arm arm, MatrixStack matrices) { }

    @Override
    public void setAngles(EnchanterEntity e, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch) {
        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;

        this.legRight.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance * 0.5F;
        this.legRight.yaw = 0.0F;
        this.legRight.roll = 0.0F;
        this.legLeft.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance * 0.5F;
        this.legLeft.yaw = 0.0F;
        this.legLeft.roll = 0.0F;
    }

}
