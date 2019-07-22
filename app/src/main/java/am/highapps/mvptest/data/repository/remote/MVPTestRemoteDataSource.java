package am.highapps.mvptest.data.repository.remote;


import javax.inject.Inject;

import am.highapps.mvptest.data.api.MVPTestAPI;
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
import am.highapps.mvptest.data.repository.MVPTestDataSource;
import io.reactivex.Observable;

public class MVPTestRemoteDataSource implements MVPTestDataSource {

    private final MVPTestAPI mvpTestAPI;

    @Inject
    public MVPTestRemoteDataSource(MVPTestAPI gitHubService) {
        mvpTestAPI = gitHubService;
    }

    @Override
    public Observable<SignInResponseEntity> signIn(UserRequestBody signInRequestBody) {
        return mvpTestAPI.signIn(signInRequestBody);
    }

    @Override
    public Observable<TopicResponseEntity> getTopicById(int id) {
        return mvpTestAPI.getTopicById(id);
    }

    @Override
    public Observable<CommentsResponseEntity> getTopicComments(int id, int page, int size) {
        return mvpTestAPI.getTopicComments(id,page,size);
    }

    @Override
    public Observable<AddCommentResponseEntity> addComment(CommentRequestBody commentRequestBody) {
        return mvpTestAPI.addTopicComment(commentRequestBody);
    }

    @Override
    public Observable<MakeCommentHelpfulResponseEntity> makeCommentHelpful(int commentId) {
        return mvpTestAPI.makeCommentHelpful(commentId);
    }

    @Override
    public Observable<MakeReplyHelpfulResponseEntity> makeReplyHelpful(int replyId) {
        return mvpTestAPI.makeReplyHelpful(replyId);
    }

    @Override
    public Observable<AddReplyResponseEntity> addReply(AddReplyRequestBody addReplyRequestBody) {
        return mvpTestAPI.addReply(addReplyRequestBody);
    }

    @Override
    public Observable<AddReportResponseEntity> addReport(AddReportRequestBody addReportRequestBody) {
        return mvpTestAPI.addReport(addReportRequestBody);
    }


}
