package am.highapps.mvptest.data.api;

public class ApiFactory {

    public static final int TOPIC_ID = 1;

    public static class Url {
        public static final String BASE_URL = "https://mcapi.armlon.co.uk/api/";
        public static final String SIGN_IN = "token/signIn";
        public static final String GET_USER_DETAILS = "patient/getUserDetails";
        public static final String GET_TOPIC_BY_ID = "forumTopic/getTopicById";
        public static final String GET_TOPIC_COMENTS = "forumComment/getTopicComments";
        public static final String ADD_COMMENT = "forumComment/addComment";
        public static final String MAKE_COMMENT_HELPFUL = "forumComment/makeCommentHelpful";
        public static final String REMOVE_COMMENT_HELPFUL = "forumComment/removeHelpfulComment";
        public static final String ADD_REPLY_COMMENT = "forumComment/replyComment";
        public static final String ADD_REPORT = "forumReport/addReport";
        public static final String MAKE_REPLY_HELPFUL = "forumComment/makeReplyHelpful";
        public static final String REMOVE_REPLY_HELPFUL = "forumComment/removeHelpfulReply";
    }

}
