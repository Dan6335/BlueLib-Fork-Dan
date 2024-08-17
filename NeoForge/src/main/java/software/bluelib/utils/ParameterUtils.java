// Copyright (c) BlueLib. Licensed under the MIT License.

package software.bluelib.utils;

import software.bluelib.entity.variant.VariantParameter;
import software.bluelib.entity.variant.VariantLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class to connect custom parameters to the variants.
 */
public class ParameterUtils {

    /**
     * A static map that holds the custom parameters associated with each variant. <br>
     * The outer map's key represents the variant's name, <br>
     * and the inner map stores key-value pairs of custom parameters for that variant.
     */
    private static final Map<String, Map<String, String>> variantParametersMap = new HashMap<>();

    /**
     * Retrieves the value of a custom parameter for a specific variant.
     *
     * @param pVariantName The variant name you want to see the custom parameter of.
     * @param pParameterKey The parameter you want to see.
     * @return The value of the custom parameter identified by {@code pParameterKey} for the variant specified by {@code pVariantName}.
     */
    public static String getParameter(String pVariantName, String pParameterKey) {
        return variantParametersMap.getOrDefault(pVariantName, new HashMap<>()).getOrDefault(pParameterKey, "unknown");
    }

    /**
     * Builder class for creating and connecting custom parameters to a specific variant.
     */
    public static class ParameterBuilder {
        /**
         * The name of the variant for which the parameters are being built.
          */
        private final String variantName;

        /**
         * A map to store parameters associated with the variant, <br>
         * where each key-value pair represents a parameter name and its corresponding value.
         */
        private final Map<String, String> parameters = new HashMap<>();

        /**
         * Private constructor that initializes the builder with a specific variant name.
          */
        private ParameterBuilder(String pVariantName) {
            this.variantName = pVariantName;
        }

        /**
         * Static factory method to create a {@link ParameterBuilder} for a specific variant.
         *
         * @param pVariantName The name of the variant for which parameters are being built.
         * @return A new instance of {@link ParameterBuilder}.
         */
        public static ParameterBuilder forVariant(String pVariantName) {
            return new ParameterBuilder(pVariantName);
        }

        /**
         * Adds a parameter with the given key to the builder.
         *
         * @param pParameter The key of the parameter to add.
         * @return The current instance of {@link ParameterBuilder} to allow method chaining.
         */
        public ParameterBuilder withParameter(String pParameter) {
            parameters.put(pParameter, "");
            return this;
        }

        /**
         * Connects the parameters built with this builder to the variant specified during creation.<br>
         * This method updates the static {@link VariantParameter} with the parameters for the specified variant.
         *
         * @return The current instance of {@link ParameterBuilder} to allow method chaining.
         */
        public ParameterBuilder connect() {
            VariantParameter variant = VariantLoader.getVariantByName(variantName);
            if (variant != null) {
                Map<String, String> updatedParameters = new HashMap<>();
                for (String key : parameters.keySet()) {
                    updatedParameters.put(key, variant.getParameter(key));
                }
                variantParametersMap.put(variantName, updatedParameters);
            } else {
                System.out.println("Variant not found: " + variantName);
            }
            return this;
        }

        /**
         * Retrieves the {@link VariantLoader} instance to load variants.
         * @return A new instance of {@link VariantLoader}.
         */
        private VariantLoader getVariantLoader() {
            return new VariantLoader();
        }
    }
}
