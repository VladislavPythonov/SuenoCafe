package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView textViewName, textViewEmail;
    private EditText editTextDateBirth, editTextPhone, editTextAddress;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        editTextDateBirth = findViewById(R.id.editTextDateBirth);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        buttonSave = findViewById(R.id.buttonSave);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                    if (userProfile != null) {
                        Log.d("MyProfileActivity", "Name from database: " + userProfile.name); // Debug message
                        textViewName.setText(userProfile.name);
                        textViewEmail.setText(userProfile.email);
                        editTextDateBirth.setText(userProfile.dateBirth);
                        editTextPhone.setText(userProfile.phone);
                        editTextAddress.setText(userProfile.address);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle possible errors.
                }
            });

            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dateBirth = editTextDateBirth.getText().toString();
                    String phone = editTextPhone.getText().toString();
                    String address = editTextAddress.getText().toString();

                    UserProfile updatedProfile = new UserProfile(
                            textViewName.getText().toString(),
                            textViewEmail.getText().toString(),
                            dateBirth, phone, address
                    );

                    mDatabase.child("users").child(userId).setValue(updatedProfile)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MyProfileActivity.this, "Данные сохранены!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MyProfileActivity.this, "Ошибка при сохранении данных: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
        }

        ImageView imageView_arrow = findViewById(R.id.imageView_arrow);
        imageView_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ConstraintLayout constraintLayout5 = findViewById(R.id.constraintLayout5);
        constraintLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
