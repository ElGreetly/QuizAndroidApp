package com.example.android.recyclerviewquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.Random;

/**
 * Created by ElGreetly on 2/8/18.
 */
public class Recycler extends RecyclerView.Adapter<Recycler.Holder> {
    private String[] question;
    private RecyclerView recyclerView1;
    private LinearLayoutManager linearLayoutManager;
    private View view;
    private int[][] arrange = new int[][] {{0,1,2,3},{0,1,3,2},{0,3,1,2},{3,0,1,2}};
    private String[][] ans;
    static int[] t =  {0,0,0,0,0,0,0,0,0,0};
    private int pos;
     Recycler(String[] questions,String[] ans1,String[] ans2,String[] ans3,String[] ans4,LinearLayoutManager layoutManager,RecyclerView recyclerView) {
        question = questions;
        recyclerView1 = recyclerView;
        linearLayoutManager = layoutManager;
        ans = new String[][]{ans1,ans2,ans3,ans4};
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.recycler;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent,false);
        Holder holder = new Holder(view);
        return holder;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        Random random = new Random();
        int n = 0;
        holder.textView.setText(question[position]);
        n = random.nextInt(3);
            for (int i = 0; i < 4; i++) {
                view = holder.radioGroup.getChildAt(arrange[n][i]);
                if (view instanceof RadioButton) if (ans[i][position] != "1")
                    ((RadioButton) view).setText(ans[i][position]);
                else ((RadioButton) view).setVisibility(View.GONE);
            }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomGridLayoutManager.isScrollEnabled = true;
                pos=linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if ((pos <= question.length) && (holder.radioGroup.getCheckedRadioButtonId() > -1 )){
                linearLayoutManager.smoothScrollToPosition(recyclerView1, null, pos+1);}
                else{Toast.makeText(holder.context,"Select an answer", Toast.LENGTH_SHORT).show();}
                if(pos >7) {
                    holder.button.setVisibility(View.GONE);
                }
            }
        });
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radiobutton = (RadioButton) radioGroup.findViewById(i);
                if(toString().valueOf(radiobutton.getText()) == ans[3][position]){
                    Log.v("y", toString().valueOf(i));
                    t[position] = 1;} else {t[position] = 0;}
            }
        });
    }
    @Override
    public int getItemCount() {
        return question.length;
    }
    class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        Button button;
        RadioGroup radioGroup;
        private final Context context;
         Holder(View itemView) {
            super(itemView);
             context = itemView.getContext();
            textView = (TextView) itemView.findViewById(R.id.recycle_text);
            button = (Button) itemView.findViewById(R.id.recycle_button);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.radio_group);
        }
    }
}
