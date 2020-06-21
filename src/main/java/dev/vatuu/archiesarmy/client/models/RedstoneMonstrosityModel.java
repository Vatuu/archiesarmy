package dev.vatuu.archiesarmy.client.models;

import com.google.common.collect.ImmutableMap;
import dev.vatuu.archiesarmy.entities.RedstoneMonstrosityEntity;
import net.minecraft.client.model.ModelPart;

import java.util.HashMap;
import java.util.Map;

public class RedstoneMonstrosityModel extends BetterCompositeEntityModel<RedstoneMonstrosityEntity> {

    final ModelPart root;
    final ModelPart hips, leftLeg, rightLeg;
    final ModelPart body;
    final ModelPart head, jaw, eyes;
    final ModelPart rightShoulder, rightArm, rightHand, rightFinger1, rightFinger2, rightFinger3;
    final ModelPart leftShoulder, leftArm, leftHand, leftFinger1, leftFinger2, leftFinger3;

    public RedstoneMonstrosityModel() {
        this.textureHeight = this.textureWidth = 512;

        root = createBone(this, 0F, 24.0F, 0F);

        hips = createBone(this, 0F, -36F, 0F, root);
        hips.setTextureOffset(208, 139).addCuboid(-14F, 0F, -10F, 28F, 11F, 21F);

        leftLeg = createBone(this, 9F, -29F, 0F, root);
        leftLeg.setTextureOffset(208, 0).addCuboid(0F, 0F, -9F, 24F, 29F, 19F);
        rightLeg = createBone(this, -9F, -29F, 0F, root);
        rightLeg.setTextureOffset(98, 214).addCuboid(-24F, 0F, -9F, 24F, 29F, 19F);

        body = createBone(this, 0F, 36F, 0F, hips);
        body.setTextureOffset(0, 0).addCuboid(-38F, -93F, -15F, 74F, 57F, 30F);
        body.setTextureOffset(248, 48).addCuboid(-15F, -87F, 15F, 28F, 16F, 11F);

        head = createBone(this, 2F, -67F, 4F, body);
        head.setTextureOffset(0, 189).addCuboid(-14F, -22F, -40F, 28F, 31F, 21F);
        head.setTextureOffset(171, 257).addCuboid(14F, -20F, -36F, 20F, 13F, 13F);
        head.setTextureOffset(76, 262).addCuboid(25F, -36F, -36F, 9F, 16F, 13F);
        head.setTextureOffset(250, 171).addCuboid(-34F, -20F, -36F, 20F, 13F, 13F);
        head.setTextureOffset(237, 257).addCuboid(-34F, -36F, -36F, 9F, 16F, 13F);
        jaw = createBone(this, 0.5F, 9F, -17F, head);
        jaw.setTextureOffset(229, 110).addCuboid(-14F, -6.8F, -23F, 27F, 7F, 21F, -0.1F);
        jaw.setTextureOffset(184, 233).addCuboid(-14F, 0F, -23F, 27F, 3F, 21F, -0.1F);
        eyes = createBone(this, -2F, 67F, -4F, head);
        eyes.setTextureOffset(0, 15).addCuboid(-12F, -70F, -36.03F, 4F, 4F, 0F);
        eyes.setTextureOffset(0, 15).addCuboid(12F, -70F, -36.03F, 4F, 4F, 0F);
        eyes.setTextureOffset(11, 0).addCuboid(0F, -73F, -36.03F, 6F, 3F, 0F);

        rightShoulder = createBone(this, -38, -73, -1, body);
        rightShoulder.setTextureOffset(183, 183).addCuboid(-20, -33, -14, 20, 23, 27);
        rightShoulder.setTextureOffset(101, 110).addCuboid(-37, -10, -14, 37, 23, 27);
        rightArm = createBone(this, -19, 13, 0, rightShoulder);
        rightArm.setTextureOffset(0, 241).addCuboid(-11, -8, -8, 22, 25, 16);
        rightHand = createBone(this, 0, 16, 0, rightArm);
        rightHand.setTextureOffset(90, 160).addCuboid(-15, 0, -15, 30, 20, 30);
        rightFinger1 = createBone(this, -8, 20, 4, rightHand);
        rightFinger1.setTextureOffset(0,0).addCuboid(-2, 0, -2, 3, 10, 5, true);
        rightFinger2 = createBone(this, -8, 20, -6, rightHand);
        rightFinger2.setTextureOffset(0,0).addCuboid(-2, 0, -3, 3, 10, 5, true);
        rightFinger3 = createBone(this, 8, 20, 0, rightHand);
        rightFinger3.setTextureOffset(0, 0).addCuboid(-1, 0, -3, 3, 10, 5);

        leftShoulder = createBone(this, 38, -73, -1, body);
        leftShoulder.setTextureOffset(181, 60).addCuboid(-2, -33, -14, 20, 23, 27);
        leftShoulder.setTextureOffset(0, 87).addCuboid(-2, -10, -14, 37, 23, 27);
        leftArm = createBone(this, 19, 13, 0, leftShoulder);
        leftArm.setTextureOffset(0, 241).addCuboid(-14, -8, -8, 22, 25, 16, true);
        leftHand = createBone(this, 0, 16, 0, leftArm);
        leftHand.setTextureOffset(0, 137).addCuboid(-18, 0, -15, 30, 20, 30);
        leftFinger1 = createBone(this, 8, 20, 4, leftHand);
        leftFinger1.setTextureOffset(0, 0).addCuboid(-4, 0, -2, 3, 10, 5);
        leftFinger2 = createBone(this, 8, 20, -6, leftHand);
        leftFinger2.setTextureOffset(0, 0).addCuboid(-4, 0, -3, 3, 10, 5);
        leftFinger3 = createBone(this, -11, 20, 0, leftHand);
        leftFinger3.setTextureOffset(0, 0).addCuboid(-2, 0, -3, 3, 10, 5, true);
    }

    @Override
    public void setAngles(RedstoneMonstrosityEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) { }

    @Override
    public ImmutableMap<String, ModelPart> getAllParts() {
        Map<String, ModelPart> map = new HashMap<>();
        map.put("root", root);
        map.put("hips", hips); map.put("leftLeg", leftLeg); map.put("rightLeg", rightLeg); map.put("body", body);
        map.put("head", head); map.put("jaw", jaw); map.put("eyes", eyes);
        map.put("leftShoulder", leftShoulder); map.put("leftArm", leftArm); map.put("leftHand", leftHand);
        map.put("leftFinger1", leftFinger1); map.put("leftFinger2", leftFinger2); map.put("leftFinger3", leftFinger3);
        map.put("rightShoulder", rightShoulder); map.put("rightArm", rightArm); map.put("rightHand", rightHand);
        map.put("rightFinger1", rightFinger1); map.put("rightFinger2", rightFinger2); map.put("rightFinger3", rightFinger3);
        return ImmutableMap.copyOf(map);
    }
}
