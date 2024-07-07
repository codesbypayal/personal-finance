package com.example.personalfinancetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.personalfinancetracker.databinding.ActivityAddTransactionBinding;
import com.example.personalfinancetracker.models.Transaction;
import com.example.personalfinancetracker.viewmodels.TransactionViewModel;

public class AddTransactionActivity extends AppCompatActivity {

    private ActivityAddTransactionBinding binding;
    private TransactionViewModel transactionViewModel;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        type = getIntent().getStringExtra("type");

        binding.btnSave.setOnClickListener(this::saveTransaction);
    }

    private void saveTransaction(View view) {
        String amountStr = binding.etAmount.getText().toString();
        String category = binding.etCategory.getText().toString();
        String date = binding.etDate.getText().toString();

        if (amountStr.isEmpty() || category.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        Transaction transaction = new Transaction(amount, category, date, type);
        transactionViewModel.insert(transaction);

        Toast.makeText(this, "Transaction saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
