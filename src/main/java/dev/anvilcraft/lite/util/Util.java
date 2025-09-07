package dev.anvilcraft.lite.util;

import net.minecraft.world.InteractionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Util {
    public static <T> T cast(@NotNull Object o) {
        //noinspection unchecked
        return (T) o;
    }

    /**
     * 若传入的值可被强转为{@code T}类型，则返回包含传入的值的{@link Optional}
     *
     * @param <T> 想要转为的类型
     * @param o   一个值，可为null
     *
     * @return 一个可能包含传入的值的{@link Optional}
     */
    public static <T> Optional<T> castSafely(@Nullable Object o, Class<T> clazz) {
        return Optional.ofNullable(o)
            .filter(clazz::isInstance)
            .map(Util::cast);
    }

    /**
     * 若传入的值可被强转为传入的任意类型，则返回true
     *
     * @param o 一个值，可为null
     *
     * @return 传入的值，但是类型为{@code T}
     */
    @SuppressWarnings("TypeParameterExplicitlyExtendsObject")
    @SafeVarargs
    public static boolean instanceOfAny(@Nullable Object o, Class<? extends Object>... classes) {
        Optional<Object> optional = Optional.empty();
        for (Class<?> clazz : classes) {
            optional = optional.or(() -> Util.castSafely(o, clazz));
        }
        return optional.isPresent();
    }

    public static InteractionResult sidedSuccess(boolean isClientSide) {
        return isClientSide ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }
}
