package am.highapps.mvptest.ui.main;

import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;

import am.highapps.mvptest.data.entity.comment.CommentContent;
import am.highapps.mvptest.data.entity.comment.CommentData;
import am.highapps.mvptest.data.entity.comment.CommentRequestBody;
import am.highapps.mvptest.data.entity.reply.AddReplyRequestBody;
import am.highapps.mvptest.data.entity.reply.AddReportResponseData;
import am.highapps.mvptest.data.entity.reply.Reply;
import am.highapps.mvptest.data.entity.report.AddReportRequestBody;
import am.highapps.mvptest.data.entity.signin.TokenResponseData;
import am.highapps.mvptest.data.entity.topic.TopicData;
import am.highapps.mvptest.data.repository.MVPTestDataSource;
import am.highapps.mvptest.ui.dialog.ReportBottomSheetFragment;
import am.highapps.mvptest.util.Constant;
import am.highapps.mvptest.util.ExcUtil;
import am.highapps.mvptest.util.NetworkUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static am.highapps.mvptest.data.api.ApiFactory.TOPIC_ID;

public class MainFragmentPresenterImpl implements MainFragmentContract.MainFragmentPresenter {

    private static final String TAG = "LoginPresenterImpl";

    private MVPTestDataSource mvpTestDataSource;
    private NetworkUtil networkUtil;
    private CompositeDisposable compositeDisposable;
    private MainFragmentContract.MainFragmentView mainFragmentView;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    public MainFragmentPresenterImpl(MVPTestDataSource mvpTestDataSource,
                                     NetworkUtil networkUtil,
                                     CompositeDisposable compositeDisposable,
                                     MainFragmentContract.MainFragmentView mainFragmentView) {
        this.mvpTestDataSource = mvpTestDataSource;
        this.networkUtil = networkUtil;
        this.compositeDisposable = compositeDisposable;
        this.mainFragmentView = mainFragmentView;

        this.mainFragmentView.setPresenter(this);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        mainFragmentView = null;
    }

    public void addComment(String comment, int topicId) {
        Disposable disposable = mvpTestDataSource.addComment(new CommentRequestBody(comment, topicId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscribe -> {
                    if (!networkUtil.isConnected()) {
                        mainFragmentView.hideProgress();
                        mainFragmentView.showMessage(Constant.Error.NO_NETWORK);
                    }
                })
                .subscribe(commentsResponseEntity -> {
                            CommentContent commentContent = commentsResponseEntity.getData();
                            if (commentContent != null) {
                                mainFragmentView.addComment(commentContent);
                                Log.d(TAG, "data: " + commentContent.getComment());
                            } else {
                                mainFragmentView.hideProgress();
                                mainFragmentView.showMessage(commentsResponseEntity.getMessage());
                            }
                        },
                        error -> {
                            mainFragmentView.hideProgress();
                            mainFragmentView.showMessage(ExcUtil.readError(error));
                        }
                );


        compositeDisposable.add(disposable);
    }


    @Override
    public void addComment(String comment) {
        addComment(comment, TOPIC_ID);
    }

    @Override
    public void voteComment(int commentId, int pos) {
        Disposable disposable = mvpTestDataSource.makeCommentHelpful(commentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscribe -> {
                    if (!networkUtil.isConnected()) {
                        mainFragmentView.hideProgress();
                        mainFragmentView.showMessage(Constant.Error.NO_NETWORK);
                    }
                })
                .subscribe(makeCommentHelpfulResponseEntity -> {
                            if (makeCommentHelpfulResponseEntity.isSuccess()) {
                                mainFragmentView.changeVoteCount(commentId, pos, makeCommentHelpfulResponseEntity.getData());
                            } else {
                                mainFragmentView.hideProgress();
                                mainFragmentView.showMessage(makeCommentHelpfulResponseEntity.getMessage());
                            }
                        },
                        error -> {
                            mainFragmentView.hideProgress();
                            mainFragmentView.showMessage(ExcUtil.readError(error));
                        }
                );


        compositeDisposable.add(disposable);
    }

    @Override
    public void voteReply(int replyId, int pos) {

        Disposable disposable = mvpTestDataSource.makeReplyHelpful(replyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscribe -> {
                    if (!networkUtil.isConnected()) {
                        mainFragmentView.hideProgress();
                        mainFragmentView.showMessage(Constant.Error.NO_NETWORK);
                    }
                })
                .subscribe(makeReplyHelpfulResponseEntity -> {
                            if (makeReplyHelpfulResponseEntity.isSuccess()) {
                                mainFragmentView.changeVoteCount(replyId, pos, makeReplyHelpfulResponseEntity.getData());
                            } else {
                                mainFragmentView.hideProgress();
                                mainFragmentView.showMessage(makeReplyHelpfulResponseEntity.getMessage());
                            }
                        },
                        error -> {
                            mainFragmentView.hideProgress();
                            mainFragmentView.showMessage(ExcUtil.readError(error));
                        }
                );


        compositeDisposable.add(disposable);
    }

    @Override
    public void addReply(String comment, int commentId, int pos) {
        Disposable disposable = mvpTestDataSource.addReply(new AddReplyRequestBody(comment, commentId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscribe -> {
                    if (!networkUtil.isConnected()) {
                        mainFragmentView.hideProgress();
                        mainFragmentView.showMessage(Constant.Error.NO_NETWORK);
                    }
                })
                .subscribe(commentsResponseEntity -> {
                            Reply reply = commentsResponseEntity.getData();
                            if (reply != null) {
                                mainFragmentView.addCommentReply(reply,commentId,pos);
                                Log.d(TAG, "data: " + reply.getComment());
                            } else {
                                mainFragmentView.hideProgress();
                                mainFragmentView.showMessage(commentsResponseEntity.getMessage());
                            }
                        },
                        error -> {
                            mainFragmentView.hideProgress();
                            mainFragmentView.showMessage(ExcUtil.readError(error));
                        }
                );


        compositeDisposable.add(disposable);
    }

    @Override
    public void addReport(String reportText, ReportBottomSheetFragment.DialogType dialogType, int typeId) {
        int reportType=0;
        switch (dialogType){
            case COMMENT:
                reportType=AddReportRequestBody.FORUM_COMMENT;
                break;
            case REPLY:
                reportType=AddReportRequestBody.FORUM_COMMENT_REPLY;
                break;
        }

        Disposable disposable = mvpTestDataSource.addReport(new AddReportRequestBody(reportText,reportType,typeId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscribe -> {
                    if (!networkUtil.isConnected()) {
                        mainFragmentView.hideProgress();
                        mainFragmentView.showMessage(Constant.Error.NO_NETWORK);
                    }
                })
                .subscribe(addReportResponseEntity  -> {
                            AddReportResponseData topic = addReportResponseEntity.getData();
                            mainFragmentView.showMessage(addReportResponseEntity.getMessage());
                            mainFragmentView.showMessage(addReportResponseEntity.getMessage());
                        },
                        error -> {
                            mainFragmentView.hideProgress();
                            mainFragmentView.showMessage(ExcUtil.readError(error));
                        }
                );


        compositeDisposable.add(disposable);

    }

    @Override
    public void getTopicById(int id) {
        getTopic(id);
    }

    @Override
    public void getTopicComments(int id) {
        getComments(id, 1, 4);
    }


    private void getTopic(int id) {
        Disposable disposable = mvpTestDataSource.getTopicById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscribe -> {
                    if (!networkUtil.isConnected()) {
                        mainFragmentView.hideProgress();
                        mainFragmentView.showMessage(Constant.Error.NO_NETWORK);
                    }
                })
                .subscribe(topicData -> {
                            TopicData topic = topicData.getData();
                            if (topic != null) {
                                Log.d(TAG, "topic: " + topic.getId());
                                mainFragmentView.setTopic(topic);
                                getTopicComments(topic.getId());
                            } else {
                                mainFragmentView.hideProgress();
                                mainFragmentView.showMessage(topicData.getMessage());
                            }
                        },
                        error -> {
                            mainFragmentView.hideProgress();
                            mainFragmentView.showMessage(ExcUtil.readError(error));
                        }
                );


        compositeDisposable.add(disposable);
    }

    private void getComments(int id, int page, int size) {

        Disposable disposable = mvpTestDataSource.getTopicComments(id, page, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscribe -> {
                    if (!networkUtil.isConnected()) {
                        mainFragmentView.hideProgress();
                        mainFragmentView.showMessage(Constant.Error.NO_NETWORK);
                    }
                })
                .subscribe(commentsResponseEntity -> {
                            CommentData data = commentsResponseEntity.getData();
                            if (data != null) {
                                mainFragmentView.setComment(data);
                                Log.d(TAG, "data: " + data.getContent());
                            } else {
                                mainFragmentView.hideProgress();
                                mainFragmentView.showMessage(commentsResponseEntity.getMessage());
                            }
                        },
                        error -> {
                            mainFragmentView.hideProgress();
                            mainFragmentView.showMessage(ExcUtil.readError(error));
                        }
                );
        compositeDisposable.add(disposable);
    }

}
