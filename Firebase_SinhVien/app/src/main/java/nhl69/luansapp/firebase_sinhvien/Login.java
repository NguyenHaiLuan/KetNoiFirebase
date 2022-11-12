package nhl69.luansapp.firebase_sinhvien;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button dangki, dangnhap;
    public static EditText username, password;
    private CheckBox showcb;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();
        mAuth = FirebaseAuth.getInstance();

        dangnhap.setOnClickListener(view -> {
            loginUser();
        });

        //SỰ KIỆN CHO NÚT ĐĂNG KÍ
        dangki.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, SignUp.class));
        });


        // CHECKBOX HIỆN MẬT KHẨU
        showcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showcb.isChecked())
                    password.setTransformationMethod(null);
                else
                    password.setTransformationMethod(new PasswordTransformationMethod());
            }
        });
    }

    private void anhXa() {
        dangki = (Button) findViewById(R.id.btncreate);
        dangnhap = (Button) findViewById(R.id.loginbtn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pass);
        showcb = (CheckBox) findViewById(R.id.cbshowp);
    }

    private void loginUser() {
        String gmail = username.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(gmail)){
            username.setError("Không thể để trống email");
            username.requestFocus();
        } else
        if (TextUtils.isEmpty(pass)){
            password.setError("Không thể để trống mật khẩu");
            password.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(gmail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                    } else {
                        Toast.makeText(Login.this, "Đăng nhập không thành công" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}