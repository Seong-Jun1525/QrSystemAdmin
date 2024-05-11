package kr.ac.yuhan.cs.qradmin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import kr.ac.yuhan.cs.qradmin.adapter.AdminAdapter;
import kr.ac.yuhan.cs.qradmin.data.AdminData;
import kr.ac.yuhan.cs.qradmin.util.ChangeMode;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class AdminActivity extends AppCompatActivity {
    private NeumorphCardView adminListCardView;
    private NeumorphCardView editTextSearchAdminField;
    private NeumorphButton adminSearchBtn;
    private NeumorphImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        LinearLayout adminListPage = (LinearLayout) findViewById(R.id.adminListPage);

        ListView listView = findViewById(R.id.listView);

        // Create Fake Date
        ArrayList<AdminData> fakeDataList = createFakeData();
        // Setting Adapter
        AdminAdapter adapter = new AdminAdapter(this, fakeDataList);
        listView.setAdapter(adapter);

        // Receives current mode value
        int modeValue = getIntent().getIntExtra("mode", 1);

        // Receives background color value passed from MainActivity
        int backgroundColor = getIntent().getIntExtra("background_color", Color.rgb(236, 240, 243));

        // Setting BackgroundColor
        View backgroundView = getWindow().getDecorView().getRootView();
        backgroundView.setBackgroundColor(backgroundColor);

        // Admin Page Id
        backBtn = (NeumorphImageView) findViewById(R.id.backBtn);
        adminListCardView = (NeumorphCardView) findViewById(R.id.adminListCardView);
        editTextSearchAdminField = (NeumorphCardView) findViewById(R.id.editTextSearchAdminField);
        adminSearchBtn = (NeumorphButton) findViewById(R.id.adminSearchBtn);

        // listView onItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get information about the clicked item
                AdminData selectedItem = fakeDataList.get(position);
                showAdminInfoDialog(selectedItem);
            }
        });

        if(modeValue == 1) {
            // DarkMode
            ChangeMode.applySubTheme(adminListPage, modeValue);

            // Admin Page Btn
            ChangeMode.setColorFilterDark(backBtn);
            ChangeMode.setDarkShadowCardView(backBtn);

            // Admin Page CardView content
            ChangeMode.setDarkShadowCardView(adminListCardView);
            ChangeMode.setDarkShadowCardView(editTextSearchAdminField);
            ChangeMode.setDarkShadowCardView(adminSearchBtn);
        }
        else {
            // LightMode
            adminSearchBtn.setBackgroundColor(Color.rgb(0, 174, 142));
            ChangeMode.setLightShadowCardView(adminSearchBtn);
        }

        // BackBtn onClickListener
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change ShapeType to 'pressed' when clicked
                backBtn.setShapeType(1);
                // After clicked, it changes back to 'flat'
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        backBtn.setShapeType(0);
                    }
                }, 200);
                finish();
            }
        });
    }
    // Create Fake Data
    private ArrayList<AdminData> createFakeData() {
        ArrayList<AdminData> dataList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            // Create Fake Data & Add AdminList
            AdminData adminData = new AdminData(i, "Admin" + i, "1234", "position" + i);
            dataList.add(adminData);
        }
        return dataList;
    }
    public static void showErrorDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("오류 발생")
                .setMessage(message)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Pressed "확인" Btn
                        dialog.dismiss(); // Close Dialog
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showAdminInfoDialog(AdminData selectedItem) {
        // Create Dialog & Layout Setting
        Dialog dialog = new Dialog(AdminActivity.this);
        dialog.setContentView(R.layout.dialog_admin_item_info);

        // Get TextView ID in Dialog
        TextView textViewAdminNum = dialog.findViewById(R.id.textViewAdminNum);
        textViewAdminNum.setText("Num: " + selectedItem.getAdminNum());

        TextView textViewAdminId = dialog.findViewById(R.id.textViewAdminId);
        textViewAdminId.setText("Id: " + selectedItem.getAdminId());

        TextView textViewAdminPosition = dialog.findViewById(R.id.textViewAdminPosition);
        textViewAdminPosition.setText("Postion: " + selectedItem.getAdminPosition());

        // Show Dialog
        dialog.show();
    }
}