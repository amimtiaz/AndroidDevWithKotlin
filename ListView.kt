/*ListView in Activity Class*/
/*<ListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/> */


/*list_item.xml*/
/*
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="10dp">

    <!-- ImageView for Item Image -->
    <ImageView
        android:id="@+id/itemImage"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:layout_marginEnd="10dp"
        android:contentDescription="Item Image" />

    <!-- Vertical LinearLayout for Title and Description -->
    <LinearLayout
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:orientation="vertical">

        <TextView
            android:id="@+id/itemTitle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textStyle="bold"
            android:textSize="18sp"
            android:textColor="#000" />

        <TextView
            android:id="@+id/itemDescription"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="14sp"
            android:textColor="#666" />
    </LinearLayout>
</LinearLayout>
*/


/* List Item */
data class ListItem (
    val title: String,
    val description: String,
    val imageResId: Int
)
//------------- ListItem End here -------------

/* ListAdapter */
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapter(private val context: Context, private val dataSource: List<ListItem>) :
    BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    // Get the number of items in the list
    override fun getCount(): Int = dataSource.size

    // Get the list item at the given position
    override fun getItem(position: Int): ListItem = dataSource[position]

    // Get the item ID for the list item at the given position
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // Inflate the list item layout or use the existing view
        val view = convertView ?: inflater.inflate(R.layout.list_item, parent, false)

        // Get the list item data
        val listItem = getItem(position)

        // Find the views in the list item layout
        val titleTextView = view.findViewById<TextView>(R.id.itemTitle)
        val descriptionTextView = view.findViewById<TextView>(R.id.itemDescription)
        val imageView = view.findViewById<ImageView>(R.id.itemImage)

        // Set the text and image for the list item
        titleTextView.text = listItem.title
        descriptionTextView.text = listItem.description
        imageView.setImageResource(listItem.imageResId)

        return view
    }

}

//------------- Adapter End here -------------

/* MainActivity Class */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create sample data
        val items = listOf(
            ListItem("Apple", "A red fruit that's delicious and nutritious.", R.drawable.img),
            ListItem("Banana", "A yellow fruit rich in potassium.", R.drawable.img),
            ListItem("Cherry", "A small, round, and red fruit.", R.drawable.img),
            ListItem("Date", "A sweet brown fruit often eaten dried.", R.drawable.img),
            ListItem("Elderberry", "A small, dark berry used in medicine.", R.drawable.img)
        )

        // Get ListView and set the custom adapter
        val adapter = ListAdapter(this, items)
        binding.listView.adapter = adapter

        // Set an Item Click Listener
        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val clickedItem = items[position]
            Toast.makeText(this, "Clicked on ${clickedItem.title}", Toast.LENGTH_SHORT).show()
        }
    }
}
//------------- MainActivity End here -------------
