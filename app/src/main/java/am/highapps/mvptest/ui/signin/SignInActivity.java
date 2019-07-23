package am.highapps.mvptest.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import am.highapps.mvptest.R;
import am.highapps.mvptest.base.BaseActivity;
import am.highapps.mvptest.ui.main.MainActivity;
import am.highapps.mvptest.util.ActivityUtil;

public class SignInActivity extends BaseActivity implements SignInContract.SignInView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private Button signInBtn;

    @Inject
    SignInContract.SignInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter.isUserAuthDone()) {
            navigateToMain();
        }
        setContentView(R.layout.activity_signin);
        findViews();
        setListeners();
    }

    private void setListeners() {
        signInBtn.setOnClickListener(this);

    }

    private void findViews() {
        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.et_id);
        password = (EditText) findViewById(R.id.et_password);
        signInBtn = (Button) findViewById(R.id.btn_sign_in);
    }

    @Override
    public void setPresenter(SignInContract.SignInPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setIdError() {
        username.setError(getString(R.string.id_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        ActivityUtil.closeKeyboard(this);
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
