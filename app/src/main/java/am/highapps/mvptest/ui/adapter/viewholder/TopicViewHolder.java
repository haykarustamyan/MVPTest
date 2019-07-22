package am.highapps.mvptest.ui.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import am.highapps.mvptest.R;
import am.highapps.mvptest.data.entity.topic.TopicData;
import am.highapps.mvptest.ui.adapter.RecyclerAdapter;
import am.highapps.mvptest.ui.dialog.TypingDialogFragment;

public class TopicViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

    public ConstraintLayout container;
    private TextView titleTv;
    private TextView createdDateTv;
    private TextView categoryTv;
    private ImageView userIv;
    private TextView userNameTv;
    private TextView descriptionTv;
    private TextView viewsCountTv;
    private TextView answersCountTv;
    private TextView participantsCountTv;
    private TextView typeCommentTv;

    private RecyclerAdapter.OnTypeCommentClickListener onTypeCommentClickListener;

    private TopicData topicData;

    public TopicViewHolder(@NonNull View itemView, RecyclerAdapter.OnTypeCommentClickListener onTypeCommentClickListener) {
        super(itemView);

        this.onTypeCommentClickListener = onTypeCommentClickListener;

        container = itemView.findViewById(R.id.cl_root);
        titleTv = itemView.findViewById(R.id.tv_title);
        createdDateTv = itemView.findViewById(R.id.tv_created_date);
        categoryTv = itemView.findViewById(R.id.tv_category);
        userIv = itemView.findViewById(R.id.iv_user);
        userNameTv = itemView.findViewById(R.id.tv_username);
        descriptionTv = itemView.findViewById(R.id.tv_description);
        viewsCountTv = itemView.findViewById(R.id.tv_views_count);
        answersCountTv = itemView.findViewById(R.id.tv_answers_count);
        participantsCountTv = itemView.findViewById(R.id.tv_participants_count);
        typeCommentTv = itemView.findViewById(R.id.tv_type);


        typeCommentTv.setOnClickListener(this);

    }

    public void bind(TopicData topic) {
        if (topic == null) {
            titleTv.setText("Loading...");
            typeCommentTv.setVisibility(View.INVISIBLE);
            return;
        }
        this.topicData = topic;

        Glide.with(itemView.getContext())
//                .load(topicData.getPublisher().getImageUrl())
                .load(R.drawable.ic_doc)
                .placeholder(R.drawable.ic_participants)
                .apply(RequestOptions.circleCropTransform())
                .into(userIv);


        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MMMM yyyy",Locale.ENGLISH);


        String reformattedDate = null;
        try {
            reformattedDate = myFormat.format(inputFormat.parse(topic.getCreatedDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        titleTv.setText(topicData.getTitle());
        createdDateTv.setText(reformattedDate);
        categoryTv.setText(topicData.getCategory());
        userNameTv.setText(String.format("%s %s", topicData.getPublisher().getFirstName(), topicData.getPublisher().getLastName()));
        descriptionTv.setText(topicData.getDescription());
        viewsCountTv.setText(viewsCountTv.getContext().getResources().getString(R.string.views_count_text, "" + topicData.getViewsCount()));
        answersCountTv.setText(answersCountTv.getContext().getResources().getString(R.string.answers_count, "" + topicData.getAnswersCount()));
        participantsCountTv.setText("" + topicData.getParticipantsCount());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_type:
                if(onTypeCommentClickListener!=null){
                    onTypeCommentClickListener.onStartTypeClick(TypingDialogFragment.DialogType.TOPIC_COMMENT);
                }
                break;
        }
    }
}
