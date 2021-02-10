package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ItemStatementBinding

class StatementAdapter(
        private val statements: List<Statement>,
        private val listener: OnItemClickListener
) :
        RecyclerView.Adapter<StatementAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{

        val binding = ItemStatementBinding.bind(itemView)

        fun databind(statement: Statement) {
            binding.tvStatement.text = statement.statement
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            // Prevent the app from crashing when an object is clicked during an animation.
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_statement, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return statements.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(statements[position])
    }
}