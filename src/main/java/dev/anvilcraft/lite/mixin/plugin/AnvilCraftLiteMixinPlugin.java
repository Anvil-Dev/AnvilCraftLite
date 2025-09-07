package dev.anvilcraft.lite.mixin.plugin;

import net.neoforged.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class AnvilCraftLiteMixinPlugin implements IMixinConfigPlugin {
    private static boolean hasJei = false;

    private boolean isLoaded(String clazz) {
        return AnvilCraftLiteMixinPlugin.class.getClassLoader().getResource(clazz) != null;
    }

    @Override
    public void onLoad(String mixinPackage) {
        hasJei = LoadingModList.get().getMods().stream().anyMatch(it -> it.getModId().equals("jei"));
    }

    @Override
    public @Nullable String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.contains("jei")) return hasJei;
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public @Nullable List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
