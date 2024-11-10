// add this dependencies
// Room dependencies
    implementation("androidx.room:room-runtime:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")

    
// ViewBinding and DataBinding in Gradle File
     buildFeatures {
        viewBinding = true // Enable ViewBinding
        dataBinding = false // Disable DataBinding (if you're not using it)
    }

// Add this plugins in Gradle File
    id("kotlin-kapt")

// layout
// shape
<selector xmlns:android="http://schemas.android.com/apk/res/android">
<item>
    <shape android:shape="rectangle">
        <solid android:color="#E2E2E2"/>
        <corners android:radius="18sp"/>
    </shape>
</item>
</selector>

// Activity Layout
  <?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:orientation="vertical"
    android:padding="24dp"
    >

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:orientation="vertical"
        app:layout_constraintBottom_toTopOf="@+id/linkTv"
        app:layout_constraintTop_toTopOf="parent"
        tools:layout_editor_absoluteX="24dp">

        <EditText
            android:id="@+id/taskEditText"
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_marginBottom="16dp"
            android:background="@drawable/bg_codervive"
            android:hint="Enter task name"
            android:inputType="textMultiLine"
            android:padding="16dp"
            android:textColor="@color/black" />

        <androidx.appcompat.widget.AppCompatButton
            android:id="@+id/buttonAddTask"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            android:background="@drawable/bg_codervive"
            android:backgroundTint="@color/black"
            android:text="Add Task"
            android:textColor="@color/white" />

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </LinearLayout>

    <TextView
        android:id="@+id/linkTv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Made by Imtiaz || CoderVive"
        android:textSize="16sp"
        android:background="#B5B5B5"
        android:padding="12dp"
        android:textColor="#000000"
        android:clickable="true"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>



// item_task.xml
  <?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="45dp"
    android:backgroundTint="@color/black"
    android:background="@drawable/bg_codervive"
    android:padding="8dp"
    android:layout_margin="4dp"
    >

    <TextView
        android:id="@+id/tvDisplayCoderVive"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:text="tvDisplay"
        android:layout_marginRight="8dp"
        android:textColor="@color/white"
        android:textSize="18sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/deleteBtnCoderVive"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <ImageView
        android:id="@+id/deleteBtnCoderVive"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="8dp"
        app:layout_constraintBottom_toBottomOf="@+id/tvDisplayCoderVive"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="@+id/tvDisplayCoderVive"
        app:srcCompat="@drawable/delete_codervive"
        app:tint="@color/white"
        />
</androidx.constraintlayout.widget.ConstraintLayout>


// Code || Class
//Task Class
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "task_name") val taskName: String
)


// TaskAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolistapp.databinding.ItemTaskBinding

class TaskAdapter(private val taskList: MutableList<Task>, private val deleteTask: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = taskList.size

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvDisplayCoderVive.text = task.taskName
            binding.deleteBtnCoderVive.setOnClickListener { deleteTask(task) }
        }
    }
}


// TaskDatabase.kt
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task_table")
    suspend fun getAllTasks(): List<Task>
}


// MainActivity.class

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.to_dolistapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskDatabase: TaskDatabase
    private lateinit var taskDao: TaskDao
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addTaskButton: Button
    private lateinit var taskEditText: EditText
    private var taskList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database and DAO
        taskDatabase = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java, "imtiaz_database"
        ).fallbackToDestructiveMigration() // Optional: Useful for when you update the schema
            .build()
        taskDao = taskDatabase.taskDao()

        // Initialize the views
        recyclerView = binding.recyclerView
        addTaskButton = binding.buttonAddTask
        taskEditText = binding.taskEditText

        // Setup RecyclerView
        taskAdapter = TaskAdapter(taskList) { task -> deleteTask(task) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        // Button listener for adding task
        addTaskButton.setOnClickListener { addTask() }

        loadTasks()
        linkUp()
    }


    private fun linkUp(){
        val spannableString = SpannableString("Visit Codervive")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Intent to open the URL
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.codervive.com"))
                startActivity(intent)
            }
        }

        // Set the span to make text clickable
        spannableString.setSpan(clickableSpan, 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.linkTv.text = spannableString
        binding.linkTv.movementMethod = android.text.method.LinkMovementMethod.getInstance()
    }


    private fun addTask() {
        val taskName = taskEditText.text.toString()
        if (taskName.isNotEmpty()) {
            val task = Task(taskName = taskName)
            CoroutineScope(Dispatchers.IO).launch {
                taskDao.insert(task)
                loadTasks() // Reload tasks after insertion
            }
            taskEditText.text.clear() // Clear the input field
        }
    }

    private fun deleteTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.delete(task)
            loadTasks() // Reload tasks after deletion
        }
    }

    private fun loadTasks() {
        CoroutineScope(Dispatchers.IO).launch {
            val tasks = taskDao.getAllTasks()
            withContext(Dispatchers.Main) {
                taskList.clear()
                taskList.addAll(tasks)
                taskAdapter.notifyDataSetChanged()
            }
        }
    }
}

  
