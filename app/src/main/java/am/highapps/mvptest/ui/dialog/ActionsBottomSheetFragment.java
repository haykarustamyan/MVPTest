package am.highapps.mvptest.ui.dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import am.highapps.mvptest.R;
import am.highapps.mvptest.util.ActivityUtil;

import static am.highapps.mvptest.util.Constant.Argument.ARGUMENT_IS_VOTE;
import static am.highapps.mvptest.util.Constant.Argument.ARGUMENT_TYPE_ID;
import static am.highapps.mvptest.util.Constant.Argument.ARGUMENT_DIALOG_TYPE;
import static am.highapps.mvptest.util.Constant.Argument.ARGUMENT_POS;

public class ActionsBottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private ActionsBottomSheetFragmentInteractionListener actionsBottomSheetFragmentInteractionListener;

    private TextView helpfulAnswerTv;
    private TextView replyTv;
    private TextView reportTv;
    private View divider;

    private DialogType dialogType;
    private int typeId;
    private int pos;
    private boolean isVote;

    public static ActionsBottomSheetFragment getInstance(DialogType dialogType, int typeId, int pos, boolean isCurrentUserVote) {

        ActionsBottomSheetFragment frag = new ActionsBottomSheetFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_DIALOG_TYPE, String.valueOf(dialogType));
        args.putInt(ARGUMENT_TYPE_ID, typeId);
        args.putInt(ARGUMENT_POS, pos);
        args.putBoolean(ARGUMENT_IS_VOTE, isCurrentUserVote);
        frag.setArguments(args);
        return frag;
    }

    public ActionsBottomSheetFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            ActivityUtil.setWhiteNavigationBar(dialog);
        }

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actions_bottom_sheet, container, false);
        getData();

        findViews(view);
        initFields();
        setListeners();

        return view;
    }

    private void getData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            dialogType = ActionsBottomSheetFragment.DialogType.valueOf(arguments.getString(ARGUMENT_DIALOG_TYPE));
            typeId = arguments.getInt(ARGUMENT_TYPE_ID);
            pos = arguments.getInt(ARGUMENT_POS);
            isVote = arguments.getBoolean(ARGUMENT_IS_VOTE);
        }

    }

    private void findViews(View view) {
        helpfulAnswerTv = view.findViewById(R.id.tv_helpful_answer);
        replyTv = view.findViewById(R.id.tv_reply);
        reportTv = view.findViewById(R.id.tv_report);
        divider = view.findViewById(R.id.view_divider);
    }

    private void initFields() {
        switch (dialogType) {
            case COMMENT:
                replyTv.setVisibility(View.VISIBLE);
                divider.setVisibility(View.VISIBLE);
                break;
            case REPLY:
                replyTv.setVisibility(View.GONE);
                divider.setVisibility(View.GONE);
                break;
        }
    }

    private void setListeners() {
        helpfulAnswerTv.setOnClickListener(this);
        replyTv.setOnClickListener(this);
        reportTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_helpful_answer:
                if (actionsBottomSheetFragmentInteractionListener != null) {
                    actionsBottomSheetFragmentInteractionListener.onHelpfulAnswerClick(dialogType, typeId, pos, isVote);
                }
                dismiss();
                break;
            case R.id.tv_reply:
                if (actionsBottomSheetFragmentInteractionListener != null) {
                    actionsBottomSheetFragmentInteractionListener.onReplyClick(typeId, pos);
                }
                dismiss();
                break;
            case R.id.tv_report:
                if (actionsBottomSheetFragmentInteractionListener != null) {
                    actionsBottomSheetFragmentInteractionListener.onReportClick(ActionsBottomSheetFragment.DialogType.COMMENT, typeId, pos);
                }
                dismiss();
                break;
            default:
        }
    }

    public void setActionsBottomSheetFragmentInteractionListener(ActionsBottomSheetFragmentInteractionListener actionsBottomSheetFragmentInteractionListener) {
        this.actionsBottomSheetFragmentInteractionListener = actionsBottomSheetFragmentInteractionListener;
    }

    public interface ActionsBottomSheetFragmentInteractionListener {
        void onHelpfulAnswerClick(DialogType dialogType, int typeId, int pos, boolean isVote);

        void onReplyClick(int typeId, int pos);

        void onReportClick(ActionsBottomSheetFragment.DialogType dialogType, int typeId, int pos);
    }

    public enum DialogType {
        COMMENT,
        REPLY
    }
}