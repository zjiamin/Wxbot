package com.tz.newlife.module.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tz.newlife.R;
import com.tz.newlife.adapters.Msg_Recycler_Adapter;
import com.tz.newlife.module.base.BaseActivity;
import com.tz.newlife.module.been.Msg;

import java.util.ArrayList;
import java.util.List;

public class ConversationActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private RecyclerView msg_recycler;
    private EditText msg_editText;
    private Button msg_button;
    private List<Msg> msgList = new ArrayList<>();
    private Msg_Recycler_Adapter msg_recycler_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        initMsg();
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_layout);//直接找toolbar_layout里的id
        setSupportActionBar(toolbar);
        //虽然这个ActionBar是由ToolBar来具体实现的，但是还是要用下面的方法来找到ActionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);//去掉toolbar左侧Title
        }


        msg_recycler = (RecyclerView) findViewById(R.id.msg_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msg_recycler.setLayoutManager(layoutManager);
        msg_recycler_adapter = new Msg_Recycler_Adapter(msgList);
        msg_recycler.setAdapter(msg_recycler_adapter);

        msg_editText = (EditText) findViewById(R.id.msg_et);
        msg_button = (Button) findViewById(R.id.msg_btn);
        msg_button.setOnClickListener(this);
    }

    private void initMsg() {
        msgList.add(new Msg("Hello guy.", Msg.TYPE_RECIEVED));
        msgList.add(new Msg("Hello, who is that?", Msg.TYPE_SENT));
        msgList.add(new Msg("This is Tom, nice talking to u", Msg.TYPE_RECIEVED));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.msg_btn) {
            String content = msg_editText.getText().toString();
            if (!"".equals(content)) {
                Msg msg = new Msg(content, Msg.TYPE_SENT);
                msgList.add(msg);
                //当有新消息时，刷新RecyclerView中的显示
                msg_recycler_adapter.notifyItemInserted(msgList.size() - 1);
                //将RecyclerView定位到最后一行
                msg_recycler.scrollToPosition(msgList.size() - 1);
                msg_editText.setText("");//将输入框清空
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
