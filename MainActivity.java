package com.example.personalfinancetracker;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.personalfinancetracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAddIncome.setOnClickListener(v -> openAddTransactionActivity("income"));
        binding.btnAddExpense.setOnClickListener(v -> openAddTransactionActivity("expense"));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, TransactionListFragment.newInstance())
                    .commitNow();
        }
    }

    private void openAddTransactionActivity(String type) {
        Intent intent = new Intent(this, AddTransactionActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
