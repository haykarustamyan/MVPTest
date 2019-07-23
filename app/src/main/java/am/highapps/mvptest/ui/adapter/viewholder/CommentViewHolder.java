package am.highapps.mvptest.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import am.highapps.mvptest.R;
import am.highapps.mvptest.data.entity.comment.CommentContent;
import am.highapps.mvptest.ui.adapter.RecyclerAdapter;

public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView userIv;
    private TextView userNameTv;
    private TextView commentTv;
    private TextView createdDateTv;
    private TextView votesCountTv;
    private TextView replyTv;
    private ImageView dotesMenuIv;

    private CommentContent commentContent;
    private RecyclerAdapter.OnCommentVoteClickListener onCommentVoteClickListener;
    private RecyclerAdapter.OnCommentReplyClickListener onCommentReplyClickListener;
    private RecyclerAdapter.OnCommentDotesClickListener onCommentDotesClickListener;
    private int pos;

    public CommentViewHolder(View view, RecyclerAdapter.OnCommentVoteClickListener onCommentVoteClickListener,
                             RecyclerAdapter.OnCommentReplyClickListener onCommentReplyClickListener,
                             RecyclerAdapter.OnCommentDotesClickListener onCommentDotesClickListener) {
        super(view);
        this.onCommentVoteClickListener = onCommentVoteClickListener;
        this.onCommentDotesClickListener = onCommentDotesClickListener;
        this.onCommentReplyClickListener = onCommentReplyClickListener;

        userIv = itemView.findViewById(R.id.iv_user);
        userNameTv = itemView.findViewById(R.id.tv_username);
        commentTv = itemView.findViewById(R.id.tv_comment);
        createdDateTv = itemView.findViewById(R.id.tv_created_date);
        votesCountTv = itemView.findViewById(R.id.tv_votes_count);
        replyTv = itemView.findViewById(R.id.tv_reply);
        dotesMenuIv = itemView.findViewById(R.id.iv_dotes_menu);

        replyTv.setOnClickListener(this);
        votesCountTv.setOnClickListener(this);
        dotesMenuIv.setOnClickListener(this);
    }

    public void bind(CommentContent comment, int pos) {
        this.pos = pos;
        if (comment == null) {
            commentTv.setText("Loading...");
            return;
        }
        this.commentContent = comment;

        Glide.with(itemView.getContext())
//                .load(commentContent.getPublisher().getImageUrl())
                .load(R.drawable.ic_doc_2)
                .placeholder(R.drawable.ic_participants)
                .apply(RequestOptions.circleCropTransform())
                .into(userIv);


        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);


        String reformattedDate = null;
        try {
            reformattedDate = myFormat.format(inputFormat.parse(commentContent.getCreatedDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        userNameTv.setText(String.format("%s %s", commentContent.getPublisher().getFirstName(), commentContent.getPublisher().getLastName()));
        commentTv.setText(commentContent.getComment());
        createdDateTv.setText(reformattedDate);
        votesCountTv.setText(votesCountTv.getContext().getResources().getString(R.string.votes_count_text, "" + commentContent.getHelpfulAnswersCount()));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_votes_count:
                if (onCommentVoteClickListener != null) {
                    onCommentVoteClickListener.onCommentVoteClick(commentContent.getId(), pos, commentContent.isCurrentUserVote());
                }
                break;

            case R.id.tv_reply:
                if (onCommentReplyClickListener != null) {
                    onCommentReplyClickListener.onCommentReplyClick(commentContent.getId(), pos);
                }
                break;
            case R.id.iv_dotes_menu:
                if (onCommentDotesClickListener != null) {
                    onCommentDotesClickListener.onCommentDotesClick(commentContent.getId(), pos, commentContent.isCurrentUserVote());
                }
                break;
        }
    }
}
