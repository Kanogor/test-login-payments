package ru.kanogor.testlogin_payments.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.kanogor.testlogin_payments.databinding.PaymentsItemsBinding
import ru.kanogor.testlogin_payments.domain.entity.ResponseInfo
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class PaymentItemsAdapter :
    ListAdapter<ResponseInfo, PaymentItemsViewHolder>(PaymentItemsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentItemsViewHolder {
        return PaymentItemsViewHolder(
            PaymentsItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PaymentItemsViewHolder, position: Int) {
        val item = getItem(position)
        val titleText = item.title
        val amountText = if (item.amount == null) "" else item.amount.toString()
        val dateInMills = if (item.created != null) item.created!! * 1000 else 0
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val instant = Instant.ofEpochMilli(dateInMills)
        val time = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val dataText = if (dateInMills == 0L) "" else formatter.format(time)
        with(holder.binding) {
            title.text = titleText
            amount.text = amountText
            dateText.text = dataText
        }
    }

}

class PaymentItemsDiffUtil : DiffUtil.ItemCallback<ResponseInfo>() {
    override fun areItemsTheSame(oldItem: ResponseInfo, newItem: ResponseInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResponseInfo, newItem: ResponseInfo): Boolean {
        return oldItem.id == newItem.id
    }

}

class PaymentItemsViewHolder(val binding: PaymentsItemsBinding) : ViewHolder(binding.root)
