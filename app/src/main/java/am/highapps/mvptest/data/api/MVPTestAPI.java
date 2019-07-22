package am.highapps.mvptest.data.api;

import am.highapps.mvptest.data.entity.comment.AddCommentResponseEntity;
import am.highapps.mvptest.data.entity.comment.CommentRequestBody;
import am.highapps.mvptest.data.entity.comment.CommentsResponseEntity;
import am.highapps.mvptest.data.entity.comment.MakeCommentHelpfulResponseEntity;
import am.highapps.mvptest.data.entity.comment.MakeReplyHelpfulResponseEntity;
import am.highapps.mvptest.data.entity.reply.AddReplyResponseEntity;
import am.highapps.mvptest.data.entity.reply.AddReplyRequestBody;
import am.highapps.mvptest.data.entity.reply.AddReportResponseEntity;
import am.highapps.mvptest.data.entity.report.AddReportRequestBody;
import am.highapps.mvptest.data.entity.signin.SignInResponseEntity;
import am.highapps.mvptest.data.entity.signin.UserRequestBody;
import am.highapps.mvptest.data.entity.topic.TopicResponseEntity;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface MVPTestAPI {

    @POST(ApiFactory.Url.SIGN_IN)
    Observable<SignInResponseEntity> signIn(@Body UserRequestBody userRequestBody);

    @Headers("LanguageCode: en")
    @GET(ApiFactory.Url.GET_TOPIC_BY_ID)
    Observable<TopicResponseEntity> getTopicById(@Query("id") int id);

    @Headers("LanguageCode: en")
    @GET(ApiFactory.Url.GET_TOPIC_COMENTS)
    Observable<CommentsResponseEntity> getTopicComments(@Query("id") int id, @Query("page") int page, @Query("size") int size);

    @Headers("LanguageCode: en")
    @POST(ApiFactory.Url.ADD_COMMENT)
    Observable<AddCommentResponseEntity> addTopicComment(@Body CommentRequestBody commentRequestBody);


    @Headers("LanguageCode: en")
    @PUT(ApiFactory.Url.MAKE_COMMENT_HELPFUL)
    Observable<MakeCommentHelpfulResponseEntity> makeCommentHelpful(@Query("commentId") int commentId);


    @PUT(ApiFactory.Url.MAKE_REPLY_HELPFUL)
    Observable<MakeReplyHelpfulResponseEntity> makeReplyHelpful(@Query("replyId") int replyId);

    @Headers("LanguageCode: en")
    @POST(ApiFactory.Url.ADD_REPLY_COMMENT)
    Observable<AddReplyResponseEntity> addReply(@Body AddReplyRequestBody addReplyRequestBody);

    @Headers("LanguageCode: en")
    @POST(ApiFactory.Url.ADD_REPORT)
    Observable<AddReportResponseEntity> addReport(@Body AddReportRequestBody addReportRequestBody);
}
