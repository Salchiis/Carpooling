package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carpooling.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;

    public void changeScreen(){
        System.out.println("asd");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.activity:
                    replaceFragment(new ActivityFragment());

                    break;

                case R.id.profile:
                    replaceFragment(new ProfileFragment());

                    break;

            }


                return  true;
        });
    }

    private void replaceFragment(Fragment fragment){
        //buttonN = (Button)findViewById(R.id.buttonN);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    public void Btn_pumpInfo_Clicked(View v){
        Intent myIntent = new Intent(v.getContext(), Questions1.class);
        startActivity(myIntent);
    }


}