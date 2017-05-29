package serializer;

public enum SerializationMode {
    /**
     * Includes entity reference and its attributes
     */
    INCLUDE,

    /**
     * Excludes entity
     */
    EXCLUDE,

    /**
     * Includes entity reference only
     */
    REFERENCE_ONLY
}
