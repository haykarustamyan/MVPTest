package am.highapps.mvptest.ui.dialog;


import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import androidx.fragment.app.DialogFragment;


import am.highapps.mvptest.R;
import am.highapps.mvptest.util.ActivityUtil;

import static am.highapps.mvptest.util.Constant.Argument.ARGUMENT_TYPE_ID;
import static am.highapps.mvptest.util.Constant.Argument.ARGUMENT_DIALOG_TYPE;
import static am.highapps.mvptest.util.Constant.Argument.ARGUMENT_POS;

public class TypingDialogFragment extends DialogFragment implements View.OnClickListener, OnEditorActionListener {

    private OnTypingDialogInteractionListener interactionListener;

    private EditText commentTextEt;
    private Button sendBtn;
    private ImageView closeBtn;

    private DialogType dialogType;
    private int typeId;
    private int pos;

    public TypingDialogFragment() {

    }

    public static TypingDialogFragment getInstance(DialogType dialogType, int typeId, int pos) {
        TypingDialogFragment frag = new TypingDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_DIALOG_TYPE, String.valueOf(dialogType));
        args.putInt(ARGUMENT_TYPE_ID, typeId);
        args.putInt(ARGUMENT_POS, pos);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_typing, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            ActivityUtil.setWhiteNavigationBar(getDialog());
        }

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        getData();

        findViews(view);
        setListeners();
        ActivityUtil.showKeyboard(getActivity());
        return view;
    }

    private void getData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            dialogType = DialogType.valueOf(arguments.getString(ARGUMENT_DIALOG_TYPE));
            typeId = arguments.getInt(ARGUMENT_TYPE_ID);
            pos = arguments.getInt(ARGUMENT_POS);
        }

    }

    private void findViews(View view) {
        commentTextEt = (EditText) view.findViewById(R.id.et_comment);
        sendBtn = (Button) view.findViewById(R.id.btn_send);
        closeBtn = (ImageView) view.findViewById(R.id.btn_close);
    }

    private void setListeners() {
        getDialog().setCanceledOnTouchOutside(false);
        commentTextEt.setOnEditorActionListener(this);
        commentTextEt.requestFocus();
        sendBtn.setOnClickListener(this);
        closeBtn.setOnClickListener(this);
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            send(commentTextEt.getText().toString(), this.dialogType, this.typeId, this.pos);
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                send(commentTextEt.getText().toString(), this.dialogType, this.typeId, this.pos);
                break;
            case R.id.btn_close:
                close();
                break;
            default:
        }
    }

    private void send(String commentText, DialogType dialogType, int typeId, int pos) {
        ActivityUtil.closeKeyboard(getView(), getActivity());
        if (interactionListener != null) {
            interactionListener.onTypingDialogSendClick(commentText, dialogType, typeId, pos);
        }
        dismiss();
    }

    private void close() {
        ActivityUtil.closeKeyboard(getView(), getActivity());
        dismiss();
    }

    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                    ActivityUtil.setWhiteNavigationBar(getDialog());
                }
                dismiss();
            }
        };
    }

    public void setInteractionListener(OnTypingDialogInteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }


    public interface OnTypingDialogInteractionListener {
        void onTypingDialogSendClick(String inputText, DialogType type, int commentId, int pos);
    }


    public enum DialogType {
        TOPIC_COMMENT,
        COMMENT,
        REPLY
    }
}
