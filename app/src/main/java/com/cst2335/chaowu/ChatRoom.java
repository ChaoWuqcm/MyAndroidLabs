package com.cst2335.chaowu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cst2335.chaowu.databinding.ActivityChatRoomBinding;
import com.cst2335.chaowu.databinding.ReceiveMessageBinding;
import com.cst2335.chaowu.databinding.SentMessageBinding;
import com.cst2335.data.ChatRoomViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    ChatRoomViewModel chatModel;

    ArrayList<ChatMessage> messages;
    private RecyclerView.Adapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);


        messages = chatModel.messages.getValue();
        if(messages == null){
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());
        }
        //send button
        binding.sentButton.setOnClickListener(click -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(binding.sendMessage.getText().toString(),currentDateandTime,true);
            messages.add(chatMessage);
            myAdapter.notifyItemInserted(messages.size()-1);

            binding.sendMessage.setText("");

        });
        //receive button
        binding.button.setOnClickListener(click -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(binding.sendMessage.getText().toString(),currentDateandTime,false);
            messages.add(chatMessage);
            myAdapter.notifyItemInserted(messages.size()-1);

            binding.sendMessage.setText("");

        });
        binding.recycleview.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleview.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                if(viewType == 0){
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater(),parent,false);
                    return new MyRowHolder(binding.getRoot());
                }
                else{
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater(),parent,false);
                    return new MyRowHolder(binding.getRoot());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                holder.messageText.setText("");
                holder.timeText.setText("");

                //holder.receiveMessage.setText("");
                //holder.receiveTime.setText("");

                //set message and time spent
                String obj = messages.get(position).getMessage();
                holder.timeText.setText(messages.get(position).getTimeSpent());
                holder.messageText.setText(obj);


            }

            @Override
            public int getItemViewType(int position){
                ChatMessage obj = messages.get(position);
                if(obj.getIsSentButton() == true){
                    return 0;
                }
                else{
                    return 1;
                }
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }
        });


    }

    class MyRowHolder extends RecyclerView.ViewHolder{
        TextView messageText;
        TextView timeText;


        public MyRowHolder(@NonNull View itemView){
            super(itemView);
            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);

        }
    }
}