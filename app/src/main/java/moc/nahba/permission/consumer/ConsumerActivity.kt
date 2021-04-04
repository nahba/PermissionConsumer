package moc.nahba.permission.consumer

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat

class ConsumerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer)

        val startExposer = findViewById<Button>(R.id.button)
        startExposer.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, "moc.nahba.permission")
                    == PackageManager.PERMISSION_GRANTED) {
                startExposer()
            } else {
                if(Build.VERSION.SDK_INT > 23) {
                    requestPermissions(arrayOf("moc.nahba.permission"),8181)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray) {
        when(requestCode) {
            8181 -> {
                if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    print("GRANTED")
                    startExposer()
                } else {
                    print("DENIED")
                }
            }
            else -> {
                print("Nothing")
            }
        }
    }

    private fun startExposer() {
        val intent = Intent()
        intent.action = "moc.nahba.permission.CustomAction"
        intent.addCategory("android.intent.category.DEFAULT")
        startActivity(intent)
    }
}