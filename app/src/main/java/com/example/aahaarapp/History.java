package com.example.aahaarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class History extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookref = db.collection("user data");
    public static final String TAG = "TAG";
    private TextView textViewData;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        fAuth= FirebaseAuth.getInstance();
        textViewData=findViewById(R.id.data);

        loadNotes();
    }

    public void loadNotes() {
        notebookref.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String data="";
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());//
                                if (document.contains("name") && document.contains("description") && document.contains("user type") && document.contains("userid")) {

                                    String name = (String) document.get("name");
                                    String type = (String) document.get("user type");
                                    String description = (String) document.get("description");
                                    String Userid = (String) document.get("userid");
                                    String userID = fAuth.getCurrentUser().getUid();
                                    Timestamp ts = (Timestamp) document.get("timestamp");
                                    //String dateandtime=String.valueOf(ts);
                                    String dateandtime=String.valueOf(ts.toDate());
                                    //String dateandtime = ts.toString();

                                    if(Userid.equals(userID)) {
                                        data += "Name: " + name + "\nUser Type: " + type + "\nDescription: " + description + "\nDate & Time: " + dateandtime + "\n\n";
                                        //data += "Name: " + name + "\nUser Type: " + type + "\nDescription: " + description + "\n";
                                    }
                                    textViewData.setText(data);
                                }
                            }
                            //textViewData.setText(data);
                        } else {
                            Log.d(TAG, "Error fetching data: ", task.getException());
                        }
                    }
                });
    }
}