package nhl69.luansapp.firebase_sinhvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    public static EditText ten, gmailedt, mkedt, ngsinh;
    private Button dangky, back;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        anhXa();
        mAuth = FirebaseAuth.getInstance();
        dangky.setOnClickListener(view -> {
            createUser();
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void createUser() {
        String gmail = gmailedt.getText().toString();
        String pass = mkedt.getText().toString();

        if (TextUtils.isEmpty(gmail)){
            gmailedt.setError("Không thể để trống email");
            gmailedt.requestFocus();
        } else
            if (TextUtils.isEmpty(pass)){
                mkedt.setError("Không thể để trống mật khẩu");
                mkedt.requestFocus();
            } else {
                mAuth.createUserWithEmailAndPassword(gmail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUp.this, "Tạo người dùng thành công", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SignUp.this, Login.class));
                        } else {
                            Toast.makeText(SignUp.this, "Tạo người dùng không thành công" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
    }

    private void anhXa() {
        ten = (EditText) findViewById(R.id.unamein);
        gmailedt = (EditText) findViewById(R.id.gmail);
        mkedt = (EditText) findViewById(R.id.mk);
        ngsinh = (EditText) findViewById(R.id.ngsinh);
        dangky = (Button) findViewById(R.id.btnsignup);
        back = (Button) findViewById(R.id.btnback);
    }
}