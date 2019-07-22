package am.highapps.mvptest.ui.dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import am.highapps.mvptest.R;
import am.highapps.mvptest.util.ActivityUtil;

import static am.highapps.mvptest.util.Constant.Argument.ARGUMENT_DIALOG_TYPE;
import static am.highapps.mvptest.util.Constant.Argument.ARGUMENT_POS;
import static am.highapps.mvptest.util.Constant.Argument.ARGUMENT_TYPE_ID;

public class ReportBottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private ReportBottomSheetFragmentInteractionListener reportBottomSheetFragmentInteractionListener;

    private TextView unwantedCommercialTv;
    private TextView hateOrRudeTv;
    private TextView otherTv;
    private TextView doReportTv;
    private TextView chooseReasonTv;
    private TextView cancelTv;
    private LinearLayout wrapperLl;
    private EditText reportTextEt;


    private DialogType dialogType;
    private int typeId;
    private int pos;

    private String reportText;

    public static ReportBottomSheetFragment getInstance(DialogType dialogType, int typeId, int pos) {

        ReportBottomSheetFragment frag = new ReportBottomSheetFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_DIALOG_TYPE, String.valueOf(dialogType));
        args.putInt(ARGUMENT_TYPE_ID, typeId);
        args.putInt(ARGUMENT_POS, pos);
        frag.setArguments(args);
        return frag;
    }

    public ReportBottomSheetFragment() {
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
        View view = inflater.inflate(R.layout.fragment_report_bottom_sheet, container, false);
        getData();

        findViews(view);
        initFields();
        setListeners();


        return view;
    }

    private void getData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            dialogType = ReportBottomSheetFragment.DialogType.valueOf(arguments.getString(ARGUMENT_DIALOG_TYPE));
            typeId = arguments.getInt(ARGUMENT_TYPE_ID);
            pos = arguments.getInt(ARGUMENT_POS);
        }

    }

    private void findViews(View view) {
        unwantedCommercialTv = view.findViewById(R.id.tv_unwanted_commercial);
        hateOrRudeTv = view.findViewById(R.id.tv_hate_or_rude_speach);
        otherTv = view.findViewById(R.id.tv_other);
        doReportTv = view.findViewById(R.id.tv_do_report);
        chooseReasonTv = view.findViewById(R.id.tv_choose_reason);
        cancelTv = view.findViewById(R.id.tv_cancel);
        reportTextEt = view.findViewById(R.id.et_report_text);
        wrapperLl = view.findViewById(R.id.ll_wrapper);
    }

    private void initFields() {

    }

    private void setListeners() {
        unwantedCommercialTv.setOnClickListener(this);
        hateOrRudeTv.setOnClickListener(this);
        otherTv.setOnClickListener(this);
        doReportTv.setOnClickListener(this);
        chooseReasonTv.setOnClickListener(this);
        cancelTv.setOnClickListener(this);

    }


    public void setReportBottomSheetFragmentInteractionListener(ReportBottomSheetFragmentInteractionListener reportBottomSheetFragmentInteractionListener) {
        this.reportBottomSheetFragmentInteractionListener = reportBottomSheetFragmentInteractionListener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_unwanted_commercial:
                doReportTv.setVisibility(View.VISIBLE);
                unwantedCommercialTv.setTextColor(getResources().getColorStateList(R.color.color_E5324A));
                hateOrRudeTv.setTextColor(getResources().getColorStateList(R.color.colorBlack));

                reportText = getString(R.string.unwanted_commercial_content_or_spam);

                break;
            case R.id.tv_hate_or_rude_speach:
                doReportTv.setVisibility(View.VISIBLE);
                unwantedCommercialTv.setTextColor(getResources().getColorStateList(R.color.colorBlack));
                hateOrRudeTv.setTextColor(getResources().getColorStateList(R.color.color_E5324A));

                reportText = getString(R.string.hate_or_rude_speach);

                break;
            case R.id.tv_other:
                doReportTv.setVisibility(View.VISIBLE);
                chooseReasonTv.setVisibility(View.GONE);
                cancelTv.setVisibility(View.VISIBLE);
                wrapperLl.setVisibility(View.GONE);
                reportTextEt.setVisibility(View.VISIBLE);
                reportText = "";
                unwantedCommercialTv.setTextColor(getResources().getColorStateList(R.color.colorBlack));
                hateOrRudeTv.setTextColor(getResources().getColorStateList(R.color.colorBlack));

                break;
            case R.id.tv_do_report:
                if (reportBottomSheetFragmentInteractionListener != null) {
                    reportBottomSheetFragmentInteractionListener.onDoReportClick(TextUtils.isEmpty(reportText) ? reportTextEt.getText().toString() : reportText,
                            dialogType,typeId,pos);
                }
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            default:
        }
    }

    public interface ReportBottomSheetFragmentInteractionListener {

        void onDoReportClick(String reportText, DialogType dialogType, int typeId, int pos);
    }

    public enum DialogType {
        COMMENT,
        REPLY
    }
}