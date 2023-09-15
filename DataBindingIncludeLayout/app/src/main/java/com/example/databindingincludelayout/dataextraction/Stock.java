package com.example.databindingincludelayout.dataextraction;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.databindingincludelayout.BR;

public class Stock extends BaseObservable {
    String stockName;
    String stockLTP;

    @Bindable
    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
        notifyPropertyChanged(BR.stockName);
    }

    @Bindable
    public String getStockLTP() {
        return stockLTP;
    }

    public void setStockLTP(String stockLTP) {
        this.stockLTP = stockLTP;
        notifyPropertyChanged(BR.stockLTP);
    }

    public Stock(String stockName, String stockLTP) {
        this.stockName = stockName;
        this.stockLTP = stockLTP;
    }
}
