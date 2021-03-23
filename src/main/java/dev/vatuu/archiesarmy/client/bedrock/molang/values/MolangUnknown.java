package dev.vatuu.archiesarmy.client.bedrock.molang.values;

import dev.vatuu.archiesarmy.client.bedrock.molang.MolangResult;
import dev.vatuu.archiesarmy.client.bedrock.molang.context.MolangContext;
import dev.vatuu.archiesarmy.client.bedrock.molang.elements.MolangValue;

public class MolangUnknown extends MolangValue<String> {

    public MolangUnknown(String value) {
        super(value, false);
    }

    @Override
    public String parse(String value) {
        return value;
    }

    @Override
    public boolean isValidToken(String s) {
        return false;
    }

    @Override
    public MolangResult process(MolangContext ctx) {
        return MolangResult.ZERO;
    }

    @Override
    public String toString() {
        return "Unknown[" + value + "]";
    }
}
