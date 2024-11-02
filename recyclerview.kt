// activity XML
/*
<androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="16dp"
        tools:listitem="@layout/item_view" />
*/

// item_view.xml
/*
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="8dp">

    <TextView
        android:id="@+id/itemTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Item Name"
        android:textSize="18sp"
        />

</LinearLayout>

*/


// Mainactivity 
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = listOf(
            Item("Imtiaz"),
            Item("Ima"),
            Item("Ifaz"),
            Item("Rima")
        )

        val adapter = ItemAdapter(itemList);
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

    }
}
/*--------Activity Code End Here------------*/

// itemList.kt
data class Item(val name: String)
/*--------Items Domain Code End Here------------*/

// itemAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class itemAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<itemAdapter.ItemViewHolder>() {

    // ViewHolder class for holding the views
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemTextView: TextView = view.findViewById(R.id.itemTextView)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemAdapter.ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: itemAdapter.ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemTextView.text = item.name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = itemList.size
}
/*--------Adapter Code End Here------------*/


