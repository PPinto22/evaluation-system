package serializer;


public enum GroupSerializationMode {
    /**
     * Group only
     */
    GROUP,

    /**
     * Group + Class + Teacher
     */
    CLASS,

    /**
     * Group + Class + Teacher + Students + Exams
     */
    CLASS_STUDENTS_EXAMS,
}
