package com.dev.wedrive;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.wedrive.entity.ApiCar;
import com.dev.wedrive.service.CarService;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import lombok.Getter;

public class CreateNewCarActivity extends AppCompatActivity implements Validator.ValidationListener {

    private Validator validator;

    protected CarService carService;

    private ApiCar car;

    @Getter
    @NotEmpty
    @Length(max = 100)
    private EditText brand;

    @Getter
    @NotEmpty
    @Length(max = 100)
    private EditText model;

    @Getter
    @NotEmpty
    @Max(value = 2030, message = "Year invalid")
    @Min(value = 1980, message = "Year invalid")
    private EditText year;

    @Getter
    @NotEmpty
    @Length(max = 100)
    private EditText color;

    @Getter
    @NotEmpty
    @Length(max = 100)
    private EditText number;

    public CreateNewCarActivity() {
        super();
        carService = new CarService();
        validator = new Validator(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_car);

        validator.setValidationListener(this);

        brand = findViewById(R.id.car_brand_input);
        model = findViewById(R.id.car_model_input);
        year = findViewById(R.id.car_year_input);
        color = findViewById(R.id.car_color_input);
        number = findViewById(R.id.car_number_input);

        Button okBtn = findViewById(R.id.car_save_btn);
        okBtn.setOnClickListener((v) -> validator.validate());


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            load(bundle.getString("uuid"));
        }
    }

    @Override
    public void onValidationSucceeded() {

        if (car == null)
            carService.createCar(new ApiCar(this), (car) -> {
                finish();
                return null;
            });
        else
            carService.updateCar(car.load(this), (car) -> {
                finish();
                return null;
            });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();

            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void load(String uuid) {
        carService.getCar(uuid, (car) -> {
            this.car = car;
            brand.setText(car.brand);
            model.setText(car.model);
            year.setText(Integer.toString(car.year));
            color.setText(car.color);
            number.setText(car.number);
            return null;
        });
    }
}
