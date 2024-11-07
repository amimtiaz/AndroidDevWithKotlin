// XML Code
/*
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/accelerometer_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Accelerometer Data"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/gyroscope_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Gyroscope Data"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/magnetometer_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Magnetometer Data"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/proximity_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Proximity Data"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/light_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Light Sensor Data"
        android:textSize="18sp" />

</LinearLayout>
*/


// Activity Code 

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SensorActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var gyroscope: Sensor? = null
    private var magnetometer: Sensor? = null
    private var proximity: Sensor? = null
    private var lightSensor: Sensor? = null

    private lateinit var accelerometerTextView: TextView
    private lateinit var gyroscopeTextView: TextView
    private lateinit var magnetometerTextView: TextView
    private lateinit var proximityTextView: TextView
    private lateinit var lightTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        // Initialize TextViews for displaying sensor data
        accelerometerTextView = findViewById(R.id.accelerometer_text)
        gyroscopeTextView = findViewById(R.id.gyroscope_text)
        magnetometerTextView = findViewById(R.id.magnetometer_text)
        proximityTextView = findViewById(R.id.proximity_text)
        lightTextView = findViewById(R.id.light_text)

        // Initialize SensorManager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Initialize sensors
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()
        // Register listeners for each sensor
        accelerometer?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        gyroscope?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        magnetometer?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        proximity?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        lightSensor?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            when (event.sensor.type) {
                Sensor.TYPE_ACCELEROMETER -> {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]
                    accelerometerTextView.text = "Accelerometer\nx: $x, y: $y, z: $z"
                }
                Sensor.TYPE_GYROSCOPE -> {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]
                    gyroscopeTextView.text = "Gyroscope\nx: $x, y: $y, z: $z"
                }
                Sensor.TYPE_MAGNETIC_FIELD -> {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]
                    magnetometerTextView.text = "Magnetometer\nx: $x, y: $y, z: $z"
                }
                Sensor.TYPE_PROXIMITY -> {
                    val distance = event.values[0]
                    proximityTextView.text = "Proximity\nDistance: $distance cm"
                }
                Sensor.TYPE_LIGHT -> {
                    val lux = event.values[0]
                    lightTextView.text = "Light\nIlluminance: $lux lx"
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // If you want to use it, you can. But it's optional
    }
}

// Thank me letter :)
