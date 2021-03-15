package com.pranjal.quizappwithfirebase.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pranjal.quizappwithfirebase.R
import com.pranjal.quizappwithfirebase.activities.QuestionsActivity
import com.pranjal.quizappwithfirebase.models.Quiz
import com.pranjal.quizappwithfirebase.utils.ColorPicker
import com.pranjal.quizappwithfirebase.utils.IconPicker


class QuizAdapter(val context: Context, val quizzes: List<Quiz>) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {

        val view : View = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false)

        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {

        val current =quizzes[position]

        holder.titleText.text = current.title
        holder.cardView.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.icon.setImageResource(IconPicker.getIcon())

        holder.itemView.setOnClickListener {

            val intent=Intent(context, QuestionsActivity::class.java)
            intent.putExtra("DATE", quizzes[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    //TODO 1
    class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleText: TextView = itemView.findViewById(R.id.quizTitle)
        var icon: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardView: CardView = itemView.findViewById(R.id.cardContainer)

    }
}

