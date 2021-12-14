package com.moyasar.moysdkdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.moyasar.android.sdk.PaymentConfig
import com.moyasar.android.sdk.PaymentResult
import com.moyasar.android.sdk.PaymentSheet
import com.moyasar.android.sdk.payment.models.Payment

class MainActivity : AppCompatActivity() {
    var paymentSheet: PaymentSheet? = null
    val config = PaymentConfig(
        amount = 100,
        currency = "SAR",
        description = "Sample Android SDK Payment",
        apiKey = "pk_test_your_key_here",
        baseUrl = "https://api.moyasar.com"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paymentSheet = PaymentSheet(this, { handlePaymentResult(it) }, config)

        val donateBtn = findViewById<Button>(R.id.button)
        donateBtn.setOnClickListener {
            paymentSheet!!.present()
        }
    }

    fun handlePaymentResult(result: PaymentResult) {
        when (result) {
            is PaymentResult.Completed -> {
                handleCompletedPayment(result.payment);
            }
            is PaymentResult.Failed -> {
                // Handle error
                var error = result.error;
            }
            PaymentResult.Canceled -> {
                // User has canceled the payment
            }
        }
    }

    fun handleCompletedPayment(payment: Payment) {
        when (payment.status) {
            "paid" -> { /* Handle successful payment */ }
            "failed" -> {
                var errorMessage = payment.source["message"]
                /* Handle failed payment */
            }
            else -> { /* Handle other statuses */ }
        }
    }
}