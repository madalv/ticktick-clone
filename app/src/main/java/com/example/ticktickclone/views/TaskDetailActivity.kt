package com.example.ticktickclone.views

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import com.example.ticktickclone.R
import com.example.ticktickclone.TickTickCloneApplication
import com.example.ticktickclone.databinding.ActivityTaskDetailBinding
import com.example.ticktickclone.models.Task
import com.example.ticktickclone.viewmodels.INVALID_TASK_ID
import com.example.ticktickclone.viewmodels.TaskDetailViewModel
import com.example.ticktickclone.viewmodels.TaskDetailViewModelFactory

private const val EXTRA_CRIME_ID = "com.example.ticktickclone.views.EXTRA_CRIME_ID"
private const val EXTRA_LIST_ID = "com.example.ticktickclone.views.EXTRA_LIST_ID"

private const val TAG = "TaskDetailActivity"

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailBinding

    private val vm: TaskDetailViewModel by viewModels {
        TaskDetailViewModelFactory(
            (application as TickTickCloneApplication).taskRepository,
            (application as TickTickCloneApplication).listRepository,
            intent.getLongExtra(EXTRA_CRIME_ID, INVALID_TASK_ID),
            intent.getLongExtra(EXTRA_LIST_ID, INVALID_TASK_ID)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")

        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.task.observe(this) { task ->
            task?.let { updateUI(it) }
        }

        binding.apply {
            titleEditText.doOnTextChanged { text, _, _, _ ->
                vm.updateTask { oldTask -> oldTask.copy(title = text.toString()) }
            }

            descEditText.doOnTextChanged { text, _, _, _ ->
                vm.updateTask { oldTask -> oldTask.copy(description = text.toString()) }
            }

            titleEditText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.titleEditText.backgroundTintList = ColorStateList.valueOf(
                        ResourcesCompat.getColor(resources, R.color.purple_500, null)
                    )
                } else {
                    binding.titleEditText.backgroundTintList = ColorStateList.valueOf(
                        Color.TRANSPARENT
                    )
                }
            }
        }
    }

    private fun updateUI(task: Task) {
        binding.apply {
            if (titleEditText.text.toString() != task.title)
                titleEditText.setText(task.title)

            if (descEditText.text.toString() != task.description)
                descEditText.setText(task.description)
        }
    }

    companion object {
        fun newIntent(pkgContext: Context, crimeID: Long, listId: Long): Intent {
            return Intent(pkgContext, TaskDetailActivity::class.java).apply {
                putExtra(EXTRA_CRIME_ID, crimeID)
                putExtra(EXTRA_LIST_ID, listId)
            }
        }
    }
}
