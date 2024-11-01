/* XML Start */
/*
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/editTextName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter Name"/>

    <EditText
        android:id="@+id/editTextAge"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter Age"
        android:inputType="number"/>

    <!-- Buttons to Save and Retrieve Data -->
    <Button
        android:id="@+id/buttonSave"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Save Data"/>

    <Button
        android:id="@+id/buttonRetrieve"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Retrieve Data"/>

    <!-- TextView to Display Retrieved Data -->
    <TextView
        android:id="@+id/textViewDisplay"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Data will be displayed here"
        android:textSize="16sp"
        android:paddingTop="16dp"/>

  </LinearLayout>
*/

/* Code Start */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Define keys for SharedPreferences
    private val PREFS_NAME = "MyPrefs"
    private val NAME_KEY = "name"
    private val AGE_KEY = "age"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get SharedPreferences instance
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Save Data to SharedPreferences
        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val age = binding.editTextAge.text.toString().toInt()

            // Save Data
            val editor = sharedPreferences.edit()
            editor.putString(NAME_KEY, name)
            editor.putInt(AGE_KEY, age)
            editor.apply() // Or editor.commit()

            binding.textViewDisplay.text = "Data saved!"
        }

        // Retrieve Data from SharedPreferences
        binding.buttonRetrieve.setOnClickListener {
            val name = sharedPreferences.getString(NAME_KEY, "No Name Found")
            val age = sharedPreferences.getInt(AGE_KEY, 0)

            binding.textViewDisplay.text = "Name: $name, Age: $age"
        }

    }
}
