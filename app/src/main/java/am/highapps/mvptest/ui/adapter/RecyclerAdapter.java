package am.highapps.mvptest.ui.adapter;

import android.util.SparseArray;
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
    private SparseArray<CommentContentItem> commentWithReplys;


    public RecyclerAdapter() {
        contentList = new ArrayList<>();
        commentWithReplys = new SparseArray<>();
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
                ((CommentViewHolder) holder).bind((CommentContent) commentWithReplys.get(position).getItem(), position);
            }
            break;
            case TYPE_REPLY: {
                ((ReplyViewHolder) holder).bind((Reply) commentWithReplys.get(position).getItem(), position);
            }
            break;
        }
    }

    @Override
    public int getItemCount() {
        return commentWithReplys.size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOPIC;
        } else {
            if (commentWithReplys.get(position).getItem() instanceof CommentContent) {
                return TYPE_COMMENT;
            } else {
                return TYPE_REPLY;
            }
        }
    }

    public void setTopicData(TopicData topicData) {
        this.topicData = topicData;
        notifyItemChanged(0);
    }

    public void setComments(List<CommentContent> contents) {
        if (contentList.size() == 0) {
            this.contentList.addAll(contents);
        }

        this.commentWithReplys.clear();

        int index = 0;
        for (CommentContent c : contentList) {
            index++;
            commentWithReplys.put(index, new CommentContentItem(index, c));
            int replySize = c.getReplies() == null ? 0 : c.getReplies().size();
            if (replySize > 0) {
                for (Reply r : c.getReplies()) {
                    index++;
                    commentWithReplys.put(index, new CommentContentItem(index, r));
                }
            }

        }

        notifyItemRangeChanged(1, commentWithReplys.size());
    }


    public void addComment(CommentContent commentContent) {
        contentList.add(commentContent);
        setComments(contentList);
    }

    public void addCommentReply(Reply reply, int commentId, int pos) {
        commentWithReplys.put(pos + 1, new CommentContentItem(pos + 1, reply));
    }

    public void changeVotes(int id, int pos, int data) {

        if (commentWithReplys.get(pos).getItem() instanceof CommentContent) {
            CommentContent commentContent = (CommentContent) commentWithReplys.get(pos).getItem();
            commentContent.setHelpfulAnswersCount(commentContent.getHelpfulAnswersCount() + data);
            commentWithReplys.put(pos, new CommentContentItem(pos, commentContent));
        } else {
            Reply reply = (Reply) commentWithReplys.get(pos).getItem();
            reply.setHelpfulAnswersCount(reply.getHelpfulAnswersCount() + data);
            commentWithReplys.put(pos, new CommentContentItem(pos, reply));
        }


        notifyItemChanged(pos);
    }


    public void setOnTypeCommentClickListener(OnTypeCommentClickListener onTypeCommentClickListener) {
        this.onTypeCommentClickListener = onTypeCommentClickListener;
    }

    public void setOnCommentVoteClickListener(OnCommentVoteClickListener onCommentVoteClickListener) {
        this.onCommentVoteClickListener = onCommentVoteClickListener;
    }

    public void setOnReplyVoteClickListener(OnReplyVoteClickListener onReplyVoteClickListener) {
        this.onReplyVoteClickListener = onReplyVoteClickListener;
    }

    public void setOnCommentDotesClickListener(OnCommentDotesClickListener onCommentDotesClickListener) {
        this.onCommentDotesClickListener = onCommentDotesClickListener;
    }

    public void setOnReplyDotesClickListener(OnReplyDotesClickListener onReplyDotesClickListener) {
        this.onReplyDotesClickListener = onReplyDotesClickListener;
    }

    public void setOnCommentReplyClickListener(OnCommentReplyClickListener onCommentReplyClickListener) {
        this.onCommentReplyClickListener = onCommentReplyClickListener;
    }


    public interface OnTypeCommentClickListener {
        void onStartTypeClick(TypingDialogFragment.DialogType dialogType);
    }


    public interface OnCommentVoteClickListener {
        void onCommentVoteClick(int id, int pos);
    }

    public interface OnCommentDotesClickListener {
        void onCommentDotesClick(int id, int pos);
    }

    public interface OnReplyVoteClickListener {
        void onReplyVoteClick(int id, int pos);
    }

    public interface OnReplyDotesClickListener {
        void onReplyDotesClick(int id, int pos);
    }

    public interface OnCommentReplyClickListener {
        void onCommentReplyClick(int commentId, int pos);
    }
}
