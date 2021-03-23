package dev.vatuu.archiesarmy.client.bedrock.molang.namespaces;

import dev.vatuu.archiesarmy.client.bedrock.molang.MolangResult;
import dev.vatuu.archiesarmy.client.bedrock.molang.values.MolangNumber;

public class MolangMath extends MolangNamespace {

    public MolangMath() {
        super("math");
        registerFunction("abs", 1, (l) -> l.get(0) instanceof MolangNumber, (l, ctx) -> MolangResult.ofFloat(Math.abs(l.get(0).process(ctx).getFloat())));
    }
}
