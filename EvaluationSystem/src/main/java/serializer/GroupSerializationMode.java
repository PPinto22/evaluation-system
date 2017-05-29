package serializer;


public enum GroupSerializationMode {
    /**
     * Group only
     */
    MINIMUM,

    /**
     * Group + Class + Teacher
     */
    DEFAULT,

    /**
     * Group + Class + Teacher + Students + Exams
     */
    FULL,
}
