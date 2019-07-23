package am.highapps.mvptest.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import am.highapps.mvptest.R;
import am.highapps.mvptest.data.entity.comment.CommentContent;
import am.highapps.mvptest.data.entity.reply.Reply;
import am.highapps.mvptest.data.entity.topic.TopicData;
import am.highapps.mvptest.ui.adapter.viewholder.CommentViewHolder;
import am.highapps.mvptest.ui.adapter.viewholder.EmptyViewHolder;
import am.highapps.mvptest.ui.adapter.viewholder.ReplyViewHolder;
import am.highapps.mvptest.ui.adapter.viewholder.TopicViewHolder;
import am.highapps.mvptest.ui.dialog.TypingDialogFragment;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_EMPTY = 0;
    public static final int TYPE_TOPIC = 1;
    public static final int TYPE_COMMENT = 2;
    public static final int TYPE_REPLY = 3;
    private float totalHeightTopic = 0;

    private OnTypeCommentClickListener onTypeCommentClickListener;
    private OnCommentVoteClickListener onCommentVoteClickListener;
    private OnReplyVoteClickListener onReplyVoteClickListener;
    private OnCommentReplyClickListener onCommentReplyClickListener;
    private OnCommentDotesClickListener onCommentDotesClickListener;
    private OnReplyDotesClickListener onReplyDotesClickListener;


    private TopicData topicData;
    private List<CommentContent> contentList;
    private ArrayList<Item> allList;


    public RecyclerAdapter() {
        contentList = new ArrayList<>();
        allList = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_EMPTY: {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.empty_data_view, parent, false);
                return new EmptyViewHolder(view);
            }
            case TYPE_TOPIC: {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_topic_view, parent, false);
                return new TopicViewHolder(view, onTypeCommentClickListener);
            }
            case TYPE_COMMENT: {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_comment_view, parent, false);
                return new CommentViewHolder(view, onCommentVoteClickListener, onCommentReplyClickListener, onCommentDotesClickListener);
            }
            case TYPE_REPLY: {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_reply_view, parent, false);
                return new ReplyViewHolder(view, onReplyVoteClickListener, onReplyDotesClickListener);
            }
            default:
                throw new IllegalStateException("Unknown view type");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_EMPTY: {
            }
            break;
            case TYPE_TOPIC: {
                TopicViewHolder viewHolder = ((TopicViewHolder) holder);
                viewHolder.bind(topicData);
                viewHolder.container.post(new Runnable() {
                    @Override
                    public void run() {
                        if (totalHeightTopic == 0) {
                            totalHeightTopic = viewHolder.container.getHeight() + 100;
                        }
                        RecyclerView.LayoutParams listParams = (RecyclerView.LayoutParams) viewHolder.container.getLayoutParams();
                        listParams.height = (int) totalHeightTopic;
                        viewHolder.container.setLayoutParams(listParams);

                    }
                });

            }
            break;
            case TYPE_COMMENT: {
                ((CommentViewHolder) holder).bind((CommentContent) contentList.get(allList.get(position).getCommentPos()), position);
            }
            break;
            case TYPE_REPLY: {
                ((ReplyViewHolder) holder).bind((Reply) contentList.get(allList.get(position).getCommentPos())
                        .getReplies().get(allList.get(position).getReplyPos()), position);
            }
            break;
        }
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return allList.get(position).getViewType();
    }

    public void setTopicData(TopicData topicData) {
        this.topicData = topicData;
        allList.add(new Item(TYPE_TOPIC, -1, -1));
        notifyItemChanged(0);
    }

    public void setComments(List<CommentContent> contents) {
        if (contentList.size() == 0) {

            this.contentList.addAll(contents);
        }
        this.allList.clear();

//        int index = 0;
        allList.add(new Item(TYPE_TOPIC, -1, -1));
        for (CommentContent c : contentList) {
//            index++;
//            commentWithReplys.add(new CommentContentItem(index, c));
            allList.add(new Item(TYPE_COMMENT, contentList.indexOf(c), -1));
            List<Reply> replies = c.getReplies();
            int replySize = replies == null ? 0 : c.getReplies().size();
            if (replySize > 0) {
                for (Reply r : replies) {
//                    index++;
//                    commentWithReplys.add(index, new CommentContentItem(index, r));
                    allList.add(new Item(TYPE_REPLY, contentList.indexOf(c), replies.indexOf(r)));
                }
            }

        }

        notifyDataSetChanged();
    }


    public void addComment(CommentContent commentContent) {
        contentList.add(0, commentContent);
//        allList.add(0, new Item(TYPE_COMMENT, contentList.indexOf(commentContent), -1));
//        notifyItemInserted(0);
        setComments(contentList);
    }

    public void addCommentReply(Reply reply, int commentId, int pos) {
        CommentContent commentContent = contentList.get(allList.get(pos).getCommentPos());
        commentContent.getReplies().add(reply);
        int index = allList.indexOf(allList.get(pos));
        allList.add(index + 1, new Item(TYPE_REPLY, contentList.indexOf(commentContent), commentContent.getReplies().indexOf(reply)));
        notifyItemInserted(index);
    }


    public void changeVotes(int id, int pos, int data) {

        switch (allList.get(pos).getViewType()) {
            case TYPE_COMMENT:
                CommentContent commentContent = (CommentContent) contentList.get(allList.get(pos).getCommentPos());
                commentContent.setCurrentUserVote(!commentContent.isCurrentUserVote());
                commentContent.setHelpfulAnswersCount(commentContent.getHelpfulAnswersCount() + ((data == 0) ? -1 : 1));
                break;
            case TYPE_REPLY:
                Reply reply = (Reply) contentList.get(allList.get(pos).getCommentPos()).getReplies().get(allList.get(pos).getReplyPos());
                reply.setCurrentUserVote(!reply.isCurrentUserVote());
                reply.setHelpfulAnswersCount(reply.getHelpfulAnswersCount() + ((data == 0) ? -1 : 1));
                break;
        }

        notifyItemChanged(pos);
    }


    public void setOnTypeCommentClickListener(OnTypeCommentClickListener
                                                      onTypeCommentClickListener) {
        this.onTypeCommentClickListener = onTypeCommentClickListener;
    }

    public void setOnCommentVoteClickListener(OnCommentVoteClickListener
                                                      onCommentVoteClickListener) {
        this.onCommentVoteClickListener = onCommentVoteClickListener;
    }

    public void setOnReplyVoteClickListener(OnReplyVoteClickListener onReplyVoteClickListener) {
        this.onReplyVoteClickListener = onReplyVoteClickListener;
    }

    public void setOnCommentDotesClickListener(OnCommentDotesClickListener
                                                       onCommentDotesClickListener) {
        this.onCommentDotesClickListener = onCommentDotesClickListener;
    }

    public void setOnReplyDotesClickListener(OnReplyDotesClickListener
                                                     onReplyDotesClickListener) {
        this.onReplyDotesClickListener = onReplyDotesClickListener;
    }

    public void setOnCommentReplyClickListener(OnCommentReplyClickListener
                                                       onCommentReplyClickListener) {
        this.onCommentReplyClickListener = onCommentReplyClickListener;
    }


    public interface OnTypeCommentClickListener {
        void onStartTypeClick(TypingDialogFragment.DialogType dialogType);
    }


    public interface OnCommentVoteClickListener {
        void onCommentVoteClick(int id, int pos, boolean currentUserVote);
    }

    public interface OnCommentDotesClickListener {
        void onCommentDotesClick(int id, int pos, boolean isCurrentUserVote);
    }

    public interface OnReplyVoteClickListener {
        void onReplyVoteClick(int id, int pos, boolean currentUserVote);
    }

    public interface OnReplyDotesClickListener {
        void onReplyDotesClick(int id, int pos, boolean isCurrentUserVote);
    }

    public interface OnCommentReplyClickListener {
        void onCommentReplyClick(int commentId, int pos);
    }
}