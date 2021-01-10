package com.example.esoftwarica.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.esoftwarica.R
import com.example.esoftwarica.model.Student

class StudentAdapter(
    private val context: Context,
    private val listStudent : ArrayList<Student>,
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView : TextView = view.findViewById(R.id.student_name_text)
        val addrTextView : TextView = view.findViewById(R.id.student_address_text)
        val ageTextView : TextView = view.findViewById(R.id.student_age_text)
        val genderTextView : TextView = view.findViewById(R.id.student_gender_text)
        val profileImageView : ImageView = view.findViewById(R.id.student_image_view)
        val deleteButton : ImageButton = view.findViewById(R.id.delete_student_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_layout, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = listStudent[position]
        holder.nameTextView.text = student.name
        holder.addrTextView.text = student.address
        holder.genderTextView.text = student.gender
        holder.ageTextView.text = student.age

        Glide.with(context).load("https://images.assetsdelivery.com/compings_v2/blankstock/blankstock1904/blankstock190401447.jpg")
            .into(holder.profileImageView)

        holder.deleteButton.setOnClickListener {
            deleteStudentWithAlert(position)
        }
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Clicked : ${student.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteStudentWithAlert(position: Int) {
        val dialog = AlertDialog.Builder(context).apply {
            setIcon(android.R.drawable.ic_menu_delete)
            setTitle("Student Delete")
            setMessage("Are you sure you want to delete?")
            setPositiveButton("Delete") { _, _ ->
                listStudent.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, listStudent.size)
                Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show()
            }
            setNeutralButton("Cancel") { _,_ ->
                //
            }
            setCancelable(false)
        }.create()
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.danger))
        }
        dialog.show()
    }

    override fun getItemCount(): Int {
        return listStudent.size
    }
}