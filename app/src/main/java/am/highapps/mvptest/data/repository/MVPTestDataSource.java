package am.highapps.mvptest.data.repository;

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
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface MVPTestDataSource {

    Observable<SignInResponseEntity> signIn(UserRequestBody userRequestBody);

    Observable<TopicResponseEntity> getTopicById(int id);

    Observable<CommentsResponseEntity> getTopicComments(int id,int page,int size);

    Observable<AddCommentResponseEntity> addComment(CommentRequestBody commentRequestBody);

    Observable<MakeCommentHelpfulResponseEntity> makeCommentHelpful(int commentId);

    Observable<MakeReplyHelpfulResponseEntity> makeReplyHelpful(int replyId);

    Observable<AddReplyResponseEntity> addReply(AddReplyRequestBody addReplyRequestBody);

    Observable<AddReportResponseEntity> addReport(AddReportRequestBody addReportRequestBody);
}
