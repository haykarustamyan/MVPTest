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
import am.highapps.mvptest.data.entity.reply.Reply;
import am.highapps.mvptest.ui.adapter.RecyclerAdapter;

public class ReplyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView userIv;
    private TextView userNameTv;
    private TextView commentTv;
    private TextView createdDateTv;
    private TextView votesCountTv;
    private TextView replyTv;
    private ImageView dotesMenuIv;

    private Reply reply;
    private RecyclerAdapter.OnReplyVoteClickListener onReplyVoteClickListener;
    private RecyclerAdapter.OnReplyDotesClickListener onReplyDotesClickListener;
    private int pos;

    public ReplyViewHolder(View view, RecyclerAdapter.OnReplyVoteClickListener onReplyVoteClickListener,
                           RecyclerAdapter.OnReplyDotesClickListener onReplyDotesClickListener) {
        super(view);

        this.onReplyVoteClickListener = onReplyVoteClickListener;
        this.onReplyDotesClickListener = onReplyDotesClickListener;

        userIv = itemView.findViewById(R.id.iv_user);
        userNameTv = itemView.findViewById(R.id.tv_username);
        commentTv = itemView.findViewById(R.id.tv_comment);
        createdDateTv = itemView.findViewById(R.id.tv_created_date);
        votesCountTv = itemView.findViewById(R.id.tv_votes_count);
        replyTv = itemView.findViewById(R.id.tv_reply);
        dotesMenuIv = itemView.findViewById(R.id.iv_dotes_menu);

        votesCountTv.setOnClickListener(this);
        dotesMenuIv.setOnClickListener(this);
    }

    public void bind(Reply r, int pos) {
        this.pos = pos;
        if (r == null) {
            commentTv.setText("Loading...");
            return;
        }
        this.reply = r;

        Glide.with(itemView.getContext())
//                .load(reply.getPublisher().getImageUrl())
                .load(R.drawable.ic_doc)
                .placeholder(R.drawable.ic_participants)
                .apply(RequestOptions.circleCropTransform())
                .into(userIv);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);


        String reformattedDate = null;
        try {
            reformattedDate = myFormat.format(inputFormat.parse(reply.getCreatedDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        userNameTv.setText(String.format("%s %s", reply.getPublisher().getFirstName(), reply.getPublisher().getLastName()));
        commentTv.setText(reply.getComment());
        createdDateTv.setText(reformattedDate);
        votesCountTv.setText(votesCountTv.getContext().getResources().getString(R.string.votes_count_text, "" + reply.getHelpfulAnswersCount()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_votes_count:
                if (onReplyVoteClickListener != null) {
                    onReplyVoteClickListener.onReplyVoteClick(reply.getId(), pos);
                }
                break;
            case R.id.iv_dotes_menu:
                if (onReplyDotesClickListener != null) {
                    onReplyDotesClickListener.onReplyDotesClick(reply.getId(), pos);
                }
                break;
        }
    }
}
