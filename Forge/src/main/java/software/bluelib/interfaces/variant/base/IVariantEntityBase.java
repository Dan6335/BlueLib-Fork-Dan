// Copyright (c) BlueLib. Licensed under the MIT License.

package software.bluelib.interfaces.variant.base;

import net.minecraft.resources.ResourceLocation;
import software.bluelib.entity.variant.VariantLoader;
import software.bluelib.entity.variant.VariantParameter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A {@code base Interface} providing fundamental methods for handling entity variants.
 * <p>
 * This interface defines methods for retrieving texture locations and variant names associated with entities.
 * </p>
 * <p>
 * Key Methods:
 * <ul>
 *   <li>{@link #getTextureLocation(String, String)} - Retrieves the {@link ResourceLocation} for the entity texture.</li>
 *   <li>{@link #getEntityVariants(String)} - Retrieves a {@link List<String>} of variant names for a specified entity.</li>
 * </ul>
 * </p>
 * @author MeAlam
 * @Co-author Dan
 * @since 1.0.0
 */
public interface IVariantEntityBase {

    /**
     * A {@link ResourceLocation} that points to the texture of an entity.
     * <p>
     * This method constructs a {@link ResourceLocation} using the provided mod ID and texture path.
     * </p>
     *
     * @param pModId {@link String} - The mod ID used to locate the texture.
     * @param pPath {@link String} - The path to the texture within the mod.
     * @return A {@link ResourceLocation} pointing to the specified texture.
     * @author MeAlam
     * @Co-author Dan
     * @since 1.0.0
     */
    default ResourceLocation getTextureLocation(String pModId, String pPath) {
        return new ResourceLocation(pModId, pPath);
    }

    /**
     * A {@link List<String>} of variant names associated with the specified entity.
     * <p>
     * This method retrieves the names of all variants for a given entity by querying the {@link VariantLoader}.
     * </p>
     *
     * @param pEntityName {@link String} - The name of the entity whose variant names are to be retrieved.
     * @return A {@link List<String>} containing the names of variants associated with the specified entity.
     * @author MeAlam
     * @Co-author Dan
     * @since 1.0.0
     */
    default List<String> getEntityVariants(String pEntityName) {
        List<VariantParameter> variants = VariantLoader.getVariantsFromEntity(pEntityName);
        return variants.stream()
                .map(VariantParameter::getVariantName)
                .collect(Collectors.toList());
    }
}
