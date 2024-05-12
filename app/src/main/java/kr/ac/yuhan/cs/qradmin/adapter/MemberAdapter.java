package kr.ac.yuhan.cs.qradmin.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.yuhan.cs.qradmin.R;
import kr.ac.yuhan.cs.qradmin.data.MemberData;


public class MemberAdapter extends BaseAdapter {
    private ArrayList<MemberData> memberList;
    private LayoutInflater inflater;
    private Context context;

    // MemberAdapter Constructor
    public MemberAdapter(Context context, ArrayList<MemberData> memberList) {
        this.context = context;
        this.memberList = memberList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return memberList.size();
    }

    @Override
    public Object getItem(int position) {
        return memberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // ViewHolder Init
            convertView = inflater.inflate(R.layout.member_list_item, parent, false);
            viewHolder = new ViewHolder();
            // Id of member_list.xml
            viewHolder.numberTextView = convertView.findViewById(R.id.number);
            viewHolder.memberIdTextView = convertView.findViewById(R.id.memberId);
            viewHolder.memberPointTextView = convertView.findViewById(R.id.memberPoint);
            viewHolder.outBtn = convertView.findViewById(R.id.outBtn); // outBtn 초기화
            convertView.setTag(viewHolder);
        } else {
            // ViewHolder Recycle
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MemberData memberData = memberList.get(position);
        viewHolder.numberTextView.setText(String.valueOf(memberData.getNumber()));
        viewHolder.memberIdTextView.setText(memberData.getMemberId());
        viewHolder.memberPointTextView.setText(String.valueOf(memberData.getPoint()));

        // outBtn onClickListener
        viewHolder.outBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("해당 회원을 삭제 합니다.");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // selected "삭제"
                        removeMember(position); // Delete data
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show(); // Show AlertDialog
            }
        });

        return convertView;
    }

    // // Delete MemberData => Need to Update MemberData
    public void removeMember(int position) {
        memberList.remove(position);
        notifyDataSetChanged(); // 변경된 데이터셋을 알려 ListView를 갱신
    }

    static class ViewHolder {
        TextView numberTextView;
        TextView memberIdTextView;
        TextView memberPointTextView;
        ImageView outBtn; // outBtn 추가
    }
}