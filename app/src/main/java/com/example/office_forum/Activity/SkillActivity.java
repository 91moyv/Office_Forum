package com.example.office_forum.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.office_forum.R;
import com.example.office_forum.SkillEntity;
import com.example.office_forum.cardswipelayout.CardItemTouchHelperCallback;
import com.example.office_forum.cardswipelayout.CardLayoutManager;
import com.example.office_forum.cardswipelayout.OnSwipeListener;

import java.util.ArrayList;
import java.util.List;

public class SkillActivity extends AppCompatActivity {
    private List<SkillEntity> list = new ArrayList<>();
  ImageView mImageView;
  Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sill);

       /* RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load(R.drawable.word_skill_01).apply(options).into(mImageView);

*/      mButton=findViewById(R.id.bt_skill_return);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
        initData();
    }


    private void initView() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.skill_recycleview);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new MyAdapter());
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), list);
        cardCallback.setOnSwipedListener(new OnSwipeListener<SkillEntity>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
            /*    MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }*/
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, SkillEntity skillEntity, int direction) {

            }



            @Override
            public void onSwipedClear() {

                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();

                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }, 0L);
            }

        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);

    }


    private void initData() {
        list.add(new SkillEntity("Alt+左键提取出生年月日","  在Word中，我们可以使用快捷键「Alt + 鼠标左键」，效果是选择矩形区域。" +
                "身份证号码6位以后就是出生年月日了，所以利用这一功能，我们可以快速的选择6位以后的8位数，然后「Ctrl + C」复制，" +
                "「Ctrl + V」粘贴，即可快速的将身份证号码中出生年月日提取出来。",R.drawable.word_skill_01));
        list.add(new SkillEntity("  通配符提取出生年月日","就是利用通配符了，我们先将身份证号码复制一份，" +
                "然后选中被复制出来的号码，使用快捷键 「Ctrl + H」打开查找替换对话框，" +
                "查找内容中输入「([0-9]{6})([0-9]{4})([0-9]{2})([0-9]{2}) ([0-9]{4})」，" +
                "在替换为中输入「\\2年\\3月\\4日」，然后点击「更多」，勾选「使用通配符」，" +
                "最后点 击「全部替换」，最后点击「否」即可。这个通配符的含义，在之前我有讲过很多遍，" +
                "查找内容中每个 括弧（）分一组，一共是5组。替换为中「\\2\\3\\4」表示保留2、3、4组，并且后面对应加上年月日。"
                ,R.drawable.word_skill_02));
        list.add(new SkillEntity("F1帮助","  在Word中使用F1功能键，可以获取帮助。",R.drawable.word_skill_03));
        list.add(new SkillEntity("F2移动文字或图形","  F2按键可以移动文字和图形。选中文本，" +
                "按下F2，然后将光标定位到你想移动到的地方，按下回车，即可移动。",R.drawable.word_skill_04));
        list.add(new SkillEntity("F3 自动图文集","「自动图文集」功能操作起来，其实非常简单。" +
                "比如，平常在公司经常会使用“合同”。这里，我们打开一份常用的合同，然后使用快捷键「Ctrl + A」全选，" +
                "进入「插入」-「文本」-「文档部件」-「自动图文集」-「将所选内容保存到自动图文集库」，" +
                "注意在弹出的「新建构建基块」里面，我们填写好名称：“公司合同”，" +
                "其他的默认即可。设置完成好后，我们在文档中输入“公司合同”，然后按下「F3」键，" +
                "就会立刻获得我们的“公司合同”内容了。",R.drawable.word_skill_05));
        list.add(new SkillEntity("F4 重复上一步操作","F4功能键在Word中作用非常大，之前我们也有讲过，它可以重复上一步操作",R.drawable.word_skill_06));
        list.add(new SkillEntity("F5 快速定位","使用F5键，我们可以直接跳转到指定的页、节、行、书签、图形等各个你想要到达的地方。",R.drawable.word_skill_07));

    }

    private class MyAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_skill_item, parent, false);
            return new MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ImageView avatarImageView = ((MyAdapter.MyViewHolder) holder).avatarImageView;


            RequestOptions options = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(SkillActivity.this).load(list.get(position).getImgId()).apply(options).into(avatarImageView);
            ((MyViewHolder) holder).mContent.setText(list.get(position).getContent());

            ((MyViewHolder) holder).mTitle.setText(list.get(position).getTitle());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView avatarImageView;

            TextView mTitle;
            TextView mContent;
            MyViewHolder(View itemView) {
                super(itemView);
                mTitle=itemView.findViewById(R.id.skill_title);
                mContent=itemView.findViewById(R.id.skill_content);
                avatarImageView = (ImageView) itemView.findViewById(R.id.skill_images);

            }

        }
    }

}
