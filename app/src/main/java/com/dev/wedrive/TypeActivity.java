package com.dev.wedrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.wedrive.entity.ApiProfile;
import com.dev.wedrive.entity.ApiToken;
import com.dev.wedrive.service.ApiService;
import com.dev.wedrive.service.ProfileService;

import java.util.Observable;

public class TypeActivity extends AbstractAuthActivity implements View.OnClickListener {


    private ProfileService profileService;

    public TypeActivity() {
        super();
        this.profileService = new ProfileService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        Button driverBtn = findViewById(R.id.type_driver_btn);
        driverBtn.setOnClickListener(this);

        Button passengerBtn = findViewById(R.id.type_passenger_btn);
        passengerBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        ApiProfile profile = new ApiProfile();

        if (v.getId() == R.id.type_driver_btn)
            profile.type = ApiProfile.TYPE_DRIVER;

        if (v.getId() == R.id.type_passenger_btn)
            profile.type = ApiProfile.TYPE_PASSENGER;

        profileService.updateProfile(profile, (apiProfile) -> goToAndFinish(MapActivity.class), (error) -> {
        });
    }

}
