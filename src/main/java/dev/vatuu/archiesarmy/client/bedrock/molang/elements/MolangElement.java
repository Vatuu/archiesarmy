package dev.vatuu.archiesarmy.client.bedrock.molang.elements;

import dev.vatuu.archiesarmy.client.bedrock.molang.MolangResult;
import dev.vatuu.archiesarmy.client.bedrock.molang.context.MolangContext;

public interface MolangElement {

    boolean isValidToken(String s);

    MolangResult process(MolangContext ctx);

    interface Factory<T extends MolangElement> {
        T create(String value);
    }
}
