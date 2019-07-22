package am.highapps.mvptest.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import javax.inject.Inject;

import am.highapps.mvptest.R;
import am.highapps.mvptest.base.BaseFragment;
import am.highapps.mvptest.data.entity.comment.CommentContent;
import am.highapps.mvptest.data.entity.comment.CommentData;
import am.highapps.mvptest.data.entity.reply.Reply;
import am.highapps.mvptest.data.entity.topic.TopicData;
import am.highapps.mvptest.ui.adapter.RecyclerAdapter;
import am.highapps.mvptest.ui.dialog.ActionsBottomSheetFragment;
import am.highapps.mvptest.ui.dialog.ReportBottomSheetFragment;
import am.highapps.mvptest.ui.dialog.TypingDialogFragment;

import static am.highapps.mvptest.data.api.ApiFactory.TOPIC_ID;

public class MainFragmentActions extends BaseFragment implements MainFragmentContract.MainFragmentView,
        RecyclerAdapter.OnTypeCommentClickListener, SwipeRefreshLayout.OnRefreshListener, RecyclerAdapter.OnCommentVoteClickListener, ActionsBottomSheetFragment.ActionsBottomSheetFragmentInteractionListener, TypingDialogFragment.OnTypingDialogInteractionListener, RecyclerAdapter.OnReplyVoteClickListener, RecyclerAdapter.OnCommentDotesClickListener, RecyclerAdapter.OnReplyDotesClickListener,
        RecyclerAdapter.OnCommentReplyClickListener, ReportBottomSheetFragment.ReportBottomSheetFragmentInteractionListener {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;


    @Inject
    MainFragmentContract.MainFragmentPresenter mMainFragmentPresenter;


    public static MainFragmentActions newInstance() {
        return new MainFragmentActions();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        findViews(view);
        initRecycler();

        mMainFragmentPresenter.getTopicById(TOPIC_ID);

        return view;
    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.rv_main_fragment);
    }

    private void initRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter();
        recyclerAdapter.setOnTypeCommentClickListener(this);
        recyclerAdapter.setOnCommentVoteClickListener(this);
        recyclerAdapter.setOnReplyVoteClickListener(this);
        recyclerAdapter.setOnCommentDotesClickListener(this);
        recyclerAdapter.setOnReplyDotesClickListener(this);
        recyclerAdapter.setOnCommentReplyClickListener(this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMainFragmentPresenter.onDestroy();
    }

    @Override
    public void onRefresh() {
        mMainFragmentPresenter.getTopicById(1);
    }

//    @Override
//    public void showRepos(List<Repo> repoList) {
//        swipeRefreshLayout.setRefreshing(false);
//        recyclerAdapter1.updateDataSet(repoList);
//    }

    @Override
    public void showTypingDialog(TypingDialogFragment.DialogType dialogType, int typeId, int pos) {
        TypingDialogFragment typingDialogFragment = TypingDialogFragment.getInstance(dialogType, typeId, pos);
        typingDialogFragment.setInteractionListener(this);
        typingDialogFragment.show(getChildFragmentManager(), "fragment_typing");
    }

    @Override
    public void showActionsBottomFragment(ActionsBottomSheetFragment.DialogType dialogType, int typeId, int pos) {
        ActionsBottomSheetFragment bottomSheet = ActionsBottomSheetFragment.getInstance(dialogType, typeId, pos);
        bottomSheet.setActionsBottomSheetFragmentInteractionListener(this);
        bottomSheet.show(getChildFragmentManager(), "fragment_bottom_menu");
    }

    @Override
    public void showReportBottomFragment(ReportBottomSheetFragment.DialogType dialogType, int typeId, int pos) {
        ReportBottomSheetFragment bottomSheet = ReportBottomSheetFragment.getInstance(dialogType, typeId, pos);
        bottomSheet.setReportBottomSheetFragmentInteractionListener(this);
        bottomSheet.show(getChildFragmentManager(), "fragment_bottom_menu");
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTopic(TopicData data) {
        recyclerAdapter.setTopicData(data);
    }

    @Override
    public void setComment(CommentData data) {
        recyclerAdapter.setComments(data.getContent());
    }

    @Override
    public void addComment(CommentContent commentContent) {
        recyclerAdapter.addComment(commentContent);
    }

    @Override
    public void changeVoteCount(int id, int pos, int data) {
        recyclerAdapter.changeVotes(id, pos, data);
    }

    @Override
    public void addCommentReply(Reply reply, int commentId, int pos) {
        recyclerAdapter.addCommentReply(reply,commentId,pos);
    }

    @Override
    public void onStartTypeClick(TypingDialogFragment.DialogType dialogType) {
        showTypingDialog(dialogType, -1, -1);
    }

    @Override
    public void setPresenter(MainFragmentContract.MainFragmentPresenter presenter) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onCommentVoteClick(int id, int pos) {
        mMainFragmentPresenter.voteComment(id, pos);
    }

    @Override
    public void onHelpfulAnswerClick(ActionsBottomSheetFragment.DialogType dialogType, int typeId, int pos) {
        switch (dialogType) {
            case COMMENT:
                mMainFragmentPresenter.voteComment(typeId, pos);
                break;
            case REPLY:
                mMainFragmentPresenter.voteReply(typeId, pos);
                break;
        }
    }

    @Override
    public void onReplyClick(int typeId, int pos) {
        showTypingDialog(TypingDialogFragment.DialogType.REPLY, typeId, pos);
    }

    @Override
    public void onReportClick(ActionsBottomSheetFragment.DialogType dialogType, int typeId, int pos) {
        switch (dialogType) {
            case COMMENT:
                showReportBottomFragment(ReportBottomSheetFragment.DialogType.COMMENT, typeId, pos);
                break;
            case REPLY:
                showReportBottomFragment(ReportBottomSheetFragment.DialogType.REPLY, typeId, pos);
                break;
        }
    }

    @Override
    public void onTypingDialogSendClick(String comment, TypingDialogFragment.DialogType type, int commentId, int pos) {
        switch (type) {
            case TOPIC_COMMENT:
                mMainFragmentPresenter.addComment(comment);
                break;
            case COMMENT:
            case REPLY:
                mMainFragmentPresenter.addReply(comment, commentId, pos);
                break;
        }
    }

    @Override
    public void onReplyVoteClick(int id, int pos) {
        mMainFragmentPresenter.voteReply(id, pos);
    }

    @Override
    public void onCommentDotesClick(int id, int pos) {
        showActionsBottomFragment(ActionsBottomSheetFragment.DialogType.COMMENT, id, pos);
    }

    @Override
    public void onReplyDotesClick(int id, int pos) {
        showActionsBottomFragment(ActionsBottomSheetFragment.DialogType.REPLY, id, pos);
    }


    @Override
    public void onCommentReplyClick(int commentId, int pos) {
        showTypingDialog(TypingDialogFragment.DialogType.COMMENT, commentId, pos);
    }

    @Override
    public void onDoReportClick(String reportText, ReportBottomSheetFragment.DialogType dialogType, int typeId, int pos) {
        mMainFragmentPresenter.addReport(reportText, dialogType, typeId);
    }
}
