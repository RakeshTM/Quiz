package com.daqa.data.db;

public final class DBConstants {

    private DBConstants() throws Exception {
        throw new Exception("Not Allowed");
    }

    private static final String CREATE_TABLE = "CREATE TABLE ";
    static final String INTEGER = " INTEGER ";
    private static final String TEXT = " TEXT ";
    private static final String REAL = " REAL ";
    private static final String COMMA = ", ";
    private static final String PRIMARY_KEY = " PRIMARY KEY ";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";
    private static final String SEMI_COLON = ";";
    private static final String AUTO_INCREMENT = " AUTOINCREMENT ";

    final static class QUESTION {

        static final String TABLE_QUESTION = "tblQuestion";
        static final String COLUMN_ID = "id";
        static final String COLUMN_QUESTION = "question";

        public static final String CREATE_TABLE_QUESTION = CREATE_TABLE + TABLE_QUESTION + OPEN_BRACKET
                + COLUMN_ID + INTEGER + COMMA
                + COLUMN_QUESTION + TEXT + COMMA
                + PRIMARY_KEY + OPEN_BRACKET + COLUMN_ID + CLOSE_BRACKET + CLOSE_BRACKET + SEMI_COLON;

    }

    final static class OPTION {

        static final String TABLE_OPTION = "tblOption";
        static final String COLUMN_ID = "id";
        static final String COLUMN_QUESTION_ID = "colQuesitonId";
        static final String COLUMN_OPTION = "colOption";

        public static final String CREATE_TABLE_OPTION = CREATE_TABLE + TABLE_OPTION + OPEN_BRACKET
                + COLUMN_ID + INTEGER + COMMA
                + COLUMN_QUESTION_ID + INTEGER + COMMA
                + COLUMN_OPTION + TEXT + COMMA
                + PRIMARY_KEY + OPEN_BRACKET + COLUMN_ID + CLOSE_BRACKET + CLOSE_BRACKET + SEMI_COLON;

    }

    final static class IMAGE {

        static final String TABLE_IMAGE = "tblImage";
        static final String COLUMN_QUESTION_ID = "colQuestionId";
        static final String COLUMN_IMAGE_URL = "colImageUrl";
        static final String COLUMN_LOCAL_URL = "colLocalUrl";

        public static final String CREATE_TABLE_IMAGE = CREATE_TABLE + TABLE_IMAGE + OPEN_BRACKET
                + COLUMN_QUESTION_ID + INTEGER + COMMA
                + COLUMN_IMAGE_URL + TEXT + COMMA
                + COLUMN_LOCAL_URL + TEXT + COMMA
                + PRIMARY_KEY + OPEN_BRACKET + COLUMN_QUESTION_ID + CLOSE_BRACKET + CLOSE_BRACKET + SEMI_COLON;

    }


}
