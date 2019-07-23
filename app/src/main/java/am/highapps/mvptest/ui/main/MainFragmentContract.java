package am.highapps.mvptest.ui.main;

import am.highapps.mvptest.base.BasePresenter;
import am.highapps.mvptest.base.BaseView;
import am.highapps.mvptest.data.entity.comment.CommentContent;
import am.highapps.mvptest.data.entity.comment.CommentData;
import am.highapps.mvptest.data.entity.reply.Reply;
import am.highapps.mvptest.data.entity.topic.TopicData;
import am.highapps.mvptest.ui.dialog.ActionsBottomSheetFragment;
import am.highapps.mvptest.ui.dialog.ReportBottomSheetFragment;
import am.highapps.mvptest.ui.dialog.TypingDialogFragment;

public interface MainFragmentContract {

    interface MainFragmentPresenter extends BasePresenter {

        void getTopicById(int id);

        void getTopicComments(int id);

        void addComment(String comment);

        void voteComment(int commentId, int pos, boolean currentUserVote);

        void voteReply(int id, int pos, boolean isVote);

        void addReply(String comment, int commentId, int pos);

        void addReport(String reportText, ReportBottomSheetFragment.DialogType dialogType, int typeId);
    }

    interface MainFragmentView extends BaseView<MainFragmentPresenter> {

        void showTypingDialog(TypingDialogFragment.DialogType dialogType, int commentId, int pos);

        void showActionsBottomFragment(ActionsBottomSheetFragment.DialogType dialogType, int typeId, int pos, boolean isCurrentUserVote);

        void showReportBottomFragment(ReportBottomSheetFragment.DialogType dialogType, int typeId, int pos);

        void showMessage(String message);

        void setTopic(TopicData data);

        void setComment(CommentData data);

        void addComment(CommentContent commentContent);

        void changeVoteCount(int typeId, int pos, int data);

        void addCommentReply(Reply reply, int commentId, int pos);
    }
}
