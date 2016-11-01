package net.nosorog.project_777;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FullInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.full_information);

        String about = getIntent().getExtras().getString("about");
        String address = getIntent().getExtras().getString("address");
        String company = getIntent().getExtras().getString("company");
        final String email = getIntent().getExtras().getString("email");
        String firstName = getIntent().getExtras().getString("first_name");
        long id = getIntent().getExtras().getLong("id");
        String lastName = getIntent().getExtras().getString("last_name");
        final String phone = getIntent().getExtras().getString("phone");
        String photo = getIntent().getExtras().getString("photo");

        TextView aboutTextView = (TextView) findViewById(R.id.robot_about_text_view);
        TextView addressTextView = (TextView) findViewById(R.id.robot_address_text_view);
        TextView companyTextView = (TextView) findViewById(R.id.robot_company_text_view);
        TextView mailTextView = (TextView) findViewById(R.id.robot_email_text_view);
        TextView firstNameTextView = (TextView) findViewById(R.id.robot_first_name_text_view);
        TextView idTextView = (TextView) findViewById(R.id.robot_id_text_view);
        TextView phoneTextView = (TextView) findViewById(R.id.robot_phone_text_view);
        ImageView photoImageView = (ImageView) findViewById(R.id.face_of_robot_text_view);

        aboutTextView.setText(about);
        addressTextView.setText("address: " + address);
        companyTextView.setText("company: " + company);
        mailTextView.setText("e-mail: " + email);
        firstNameTextView.setText("name: " + firstName + " " + lastName);
        idTextView.setText("id: " + id);
        phoneTextView.setText(phone);
        Picasso.with(getApplicationContext()).load(photo).into(photoImageView);

        phoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });

        mailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                startActivity(intent);
            }
        });
    }
}
