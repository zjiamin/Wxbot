package com.tz.newlife.module.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tz.newlife.R;
import com.tz.newlife.adapters.News_Recycler_Adapter;
import com.tz.newlife.module.been.News;
import com.tz.newlife.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HotTopicsFragment extends Fragment {

    private RecyclerView recyclerView;

    private News_Recycler_Adapter adapter;

    private List<News> news_list = new ArrayList<>();

    private String[] title = {
            "俄罗斯的世界杯之夏，就是你我最宝贵的青春",
            "八关键词盘点世界杯，与俄罗斯难说再见",
            "重回巅峰！法国4-2克罗地亚20年后再夺冠，格子、姆巴佩建功",
            "高素质，中国老球迷决赛后清理垃圾获赠世界杯组委会勋章",
            "官方：桑保利不再执教阿根廷",
            "莫德里奇：我只想赢得世界杯",
            "德布劳内：大家应该给与斯特林更多的支持和爱护",
            "阿斯：C罗莫拉塔J罗马里亚诺一年可进80球，皇马却全部都放走"
            ,"友尽！埃弗顿热身赛22-0取胜"
            };
    private String[] content = {
            "\n 当你看着梅西和C罗在同一天离开，我们知道这个世界存在超级英雄，但有时候超级英雄也无法拯救世界。我们期盼着梅西完成四年前未竟的梦想，我们也期盼C罗以一己之力带着葡萄牙像蝴蝶一样再度飞过沧海，单身并不是所有的一厢情愿都能够走进现实。于是那个夜晚之后，我们对青春进行了一场祭奠，那些我们追逐过的，信仰过的，激昂过的，悲伤过的故事，全部写下了句点。一个时代结束之后，会有下一个时代，只是我们的青春退役之后，又去哪里寻觅下一个青春。",

            "\n 当高卢雄鸡与格子军团联手为世人奉献了一场精彩的决赛，当法国队在漫天大雨中称雄卢日尼基的时候，如俄罗斯芭蕾舞剧一样精彩的世界杯也落下了帷幕。这届关注度极高的世界杯给我们留下了很多美好的回忆，也有着太多惊喜和遗憾。在这曲终人散之时，让我们用八个关键词来诠释本届世界杯的异彩纷呈。那些创造历史的瞬间、战术革新的斗法、英雄无悔的泪水、影响深远的科技……等四年以后的卡塔尔，你是否依然记得?",

            "\n 北京时间7月15日23时，2018俄罗斯世界杯迎来万众瞩目的决赛，高卢雄鸡法国对阵格子军团克罗地亚。上半场格列兹曼造成曼朱基奇乌龙并打入一粒点球，佩里西奇破门；下半场博格巴和姆巴佩先后远射建功，洛里出现超级巨大低级失误，曼朱基奇抓住机会扳回一城。最终，法国4-2战胜克罗地亚，二十年后再度夺得世界杯冠军！",

            "\n 据记者了解，决赛赛后，一名来自中国的球迷在看台带动其他的球迷一起捡垃圾，他的举动引来了赛后正在巡查看台的俄罗斯世界杯组委会官员的注意，该官员和几位组委会同事一同上前对他的举动表示了肯定，特意送给了这位中国老球迷一枚俄罗斯世界杯纪念章并一起合影留念",

            "\n 在刚刚结束的俄罗斯世界杯中，他率队惊险小组出线后止步16强。阿根廷国家队在官方公告中写道：\n" +
                    "\n" +
                    "今天，阿根廷足协与桑保利达成协议，双方同意终止合同。同时，球队体能教练Jorge Desio和录像分析师Matías Manna也一同离职。阿根廷足协感谢这几位专业人士在任期内的工作与贡献",

            "\n 莫德里奇表示，他不在乎能否获得金球奖，他只想赢得世界杯。\n" +
                    "\n在出席赛前新闻发布会时，莫德里奇说道：“我只专注于帮助我的球队取得成功。有可能成为金球奖候选很棒，但我不在乎个人奖项。我想克罗地亚赢得世界杯，其他一切都不在我控制中。”",
            "\n 北京时间今天晚上22点，英格兰将与比利时展开世界杯三四名的决赛。\n" +
                    "\n" +
                    "赛前德布劳内表示：“我不明白为什么有些球迷要批评斯特林的表现，在我看过的比赛中斯特林表现得非常好。当然，没有取得进球可能是遭受批评的原因之一，可是足球并不是一项仅仅关乎于进球的运动。",

            "\n《阿斯报》指出，皇马过去一年放走的莫拉塔、J罗、马里亚诺和C罗，他们四人一个赛季能为球队带来80粒进球。\n" +
                    "在2016-17赛季，莫拉塔、J罗和马里亚诺一共打进36球，而C罗在上赛季打进了44球，皇马就这样放走了打进80球的四名球员。\n" +
                    "\n" +
                    "毫无疑问，失去C罗将会严重削弱皇马的攻击力，在上赛季，当C罗缺阵时，皇马就显得难以进球，本泽马和贝尔似乎都无法填补C罗的空缺。而在转会市场上，想找到另一名平均每个赛季进50球的射手根本就不可能。C罗在皇马一共上阵438次打进了450球，他还为皇马带来了众多冠军。\n" +
                    "\n" +
                    "去年夏天，皇马放走了莫拉塔、J罗和马里亚诺，而他们三人在2016-17赛季一共打进36球。这个赛季，莫拉塔上阵1872分钟打进20球，他是球队的第二射手，但无论是否有伤在身，他都只能担任本泽马的替补，而本泽马的进球数比他还少一球。最终莫拉塔选择了离队，他以8000万欧元加盟了切尔西。皇马还以800万欧元的价格把马里亚诺卖给了里昂，在2016-17赛季，他上阵302分钟就打进了5球，但齐达内和马里亚诺都觉得，让他到像法甲这样的联赛更适合其发展和成长。\n" +
                    "\n" +
                    "皇马去年夏天还把J罗租借给拜仁，在皇马的3个赛季，J罗一共打进36球，其中在2016-17赛季，他打进11球，是队内的第四射手，现在C罗也离开了伯纳乌，皇马今夏必定要引进一名顶级射手来提升球队的进球能力。",
            "\n 北京时间今天凌晨，埃弗顿在与奥地利伊尔德宁镇的球队ATV Irdning的友谊赛中以22-0的疯狂大比分取胜......" +
                    "上半场埃弗顿就打进10球，贝恩斯、霍尔盖特和迈克尔-基恩各入一球，卢克曼戴帽，托松上演大四喜。\n" +
                    "\n" +
                    "没想到下半场埃弗顿并没有停下进球的脚步，他们再次打进12球......弗拉希奇梅开二度，尼亚斯大四喜，米拉拉斯14分钟就打进4球，最终上演五子登科，ATV伊尔德宁队还打进了1粒乌龙球。最终埃弗顿以22-0取胜。"
            };

    private int[] pics = {
            R.mipmap.ronaldo_messi,
            R.mipmap.mbp,
            R.mipmap.france_chunping,
            R.mipmap.lqm,
            R.mipmap.sbl,
            R.mipmap.modric,
            R.mipmap.dbln,
            R.mipmap.ronaldo,
            R.mipmap.everton
    };

    private String[] comments = {
            "共171条评论",
            "共50条评论",
            "共932条评论",
            "共50条评论",
            "共262条评论",
            "共101条评论",
            "共774条评论",
            "共3981条评论",
            "共1132条评论"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_topics, container, false);
        
        initNews();

        initView(view);

        return view;
    }

    private void initNews() {
        //不写clear就会导致每次左右滑动后一直增加list中的内容
        news_list.clear();

        for (int i = 0; i < content.length; i++) {
            news_list.add(new News(title[i], content[i], pics[i], comments[i]));
        }
    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };

        recyclerView.setLayoutManager(layoutManager);

        adapter = new News_Recycler_Adapter(news_list, getActivity());


        recyclerView.setAdapter(adapter);

    }


    /**
     * 产生4位随机数(0000-9999)
     * @return 4位随机数
     */
    public static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom  ;
        }
        return fourRandom;
    }

}
