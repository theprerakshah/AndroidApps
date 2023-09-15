package com.example.databindingincludelayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.databindingincludelayout.databinding.MainActivityBinding;
import com.example.databindingincludelayout.dataextraction.Stock;

public class MainActivity extends AppCompatActivity {
    private MainActivityBinding mainActivityBinding;
    Stock stock1 = new Stock("IDFC", "$12");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        mainActivityBinding.setStock(stock1);
//        stock1.setStockName("GreenPower");
//        stock1.setStockLTP("$200");

    }
//        stock1.setStockLTP

    public class MainActivityClickHandler {
        Context context;

        public MainActivityClickHandler(Context context) {
            this.context = context;
        }

        public void clickHandlerButton(View view) {
            stock1.setStockName("geef");
            stock1.setStockLTP("$12");
            mainActivityBinding.setStock(stock1);
        }
    }


}