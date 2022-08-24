package com.example.appkart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appkart.R;
import com.example.appkart.adapter.SliderAdapter;

public class On_Boarding_Activity extends AppCompatActivity {


    ViewPager viewPager;
    SliderAdapter sliderAdapter;
    LinearLayout dotsLayout;
    TextView[] dots;

    Button btn;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        viewPager=findViewById(R.id.slider);
        dotsLayout=findViewById(R.id.dots);
        addDots(0);
        btn=findViewById(R.id.get_started_btn);

       viewPager.addOnPageChangeListener(changeListener);

        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(On_Boarding_Activity.this,MainActivity.class));
            }
        });
    }

    private void addDots (int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i=0; i < dots.length;i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }
            if (dots.length > 0) {
                dots[position].setTextColor(getResources().getColor(R.color.pink));
            }
    }
    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            if(position==0)
            {
                btn.setVisibility(View.INVISIBLE);
            }
            else if(position==1)
            {
                btn.setVisibility(View.INVISIBLE);
            }
            else{
                animation= AnimationUtils.loadAnimation(On_Boarding_Activity.this,R.anim.slide_animation);
                btn.setAnimation(animation);
                btn.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}