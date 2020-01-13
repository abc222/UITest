package com.uitest.weeksighin.stepview.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uitest.R;
import com.uitest.weeksighin.stepview.bean.SignListReq;
import com.uitest.weeksighin.stepview.bean.StepBean;
import com.uitest.weeksighin.stepview.ui.widget.StepsView;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 一周签到规则：
 * 1、连续签到7天，即可额外获得15积分奖励
 * 2、连续签到记录在第8天开始时将清零重新计算
 * 3、如果中断签到，连续签到记录也将清零
 *
 * 注：可以显示签到的动画，这里没有使用动画
 * 需要动画可以调用mStepView.startSignAnimation(int position)
 * position表示需要做动画的位置
 */
public class WeekSighinActivity extends AppCompatActivity {

    private StepsView mStepView;
    private RelativeLayout rl_oval;
    private TextView text_sign;
    private TextView text_lianxusign;
    private ArrayList<StepBean> mStepBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_sighin);

        initView();

        initData();

        initListener();
    }

    private void initListener() {

        rl_oval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击签到按钮，请求后台接口数据
                //模拟请求接口数据成功
                requestSuccessData();
            }
        });
    }

    /**
     * 模拟请求接口数据成功后更新数据
     */
    private void requestSuccessData() {
        mStepBeans.clear();//清空初始化数据
        String reponse = "{\n" +
                "    \"datas\": {\n" +
                "        \"day\": 3,\n" +
                "        \"myPoint\": 10890,\n" +
                "        \"signLog\": {\n" +
                "            \"content\": \"每日签到\",\n" +
                "            \"createTime\": \"2019-05-29 09:42:05\",\n" +
                "            \"familyId\": \"0\",\n" +
                "            \"id\": \"951660\",\n" +
                "            \"integral\": \"4\",\n" +
                "            \"logType\": \"3\",\n" +
                "            \"orderId\": \"0\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"userId\": \"43431\"\n" +
                "        },\n" +
                "        \"signState\": true,\n" +
                "        \"userSingninList\": [\n" +
                "            {\n" +
                "                \"createTime\": \"2019-05-27 18:04:15\",\n" +
                "                \"day\": \"1\",\n" +
                "                \"familyId\": \"0\",\n" +
                "                \"id\": \"278904\",\n" +
                "                \"seriesDay\": \"1\",\n" +
                "                \"type\": \"0\",\n" +
                "                \"userId\": \"43431\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"createTime\": \"2019-05-28 09:31:02\",\n" +
                "                \"day\": \"2\",\n" +
                "                \"familyId\": \"0\",\n" +
                "                \"id\": \"278905\",\n" +
                "                \"seriesDay\": \"2\",\n" +
                "                \"type\": \"0\",\n" +
                "                \"userId\": \"43431\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"createTime\": \"2019-05-29 09:42:05\",\n" +
                "                \"day\": \"3\",\n" +
                "                \"familyId\": \"0\",\n" +
                "                \"id\": \"278907\",\n" +
                "                \"seriesDay\": \"3\",\n" +
                "                \"type\": \"0\",\n" +
                "                \"userId\": \"43431\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"msg\": \"success!\",\n" +
                "    \"ret\": 0\n" +
                "}";

        //解析后台请求数据
        SignListReq signListReq = new Gson().fromJson(reponse, SignListReq.class);
        if (signListReq.getRet() == 0) {
            rl_oval.setBackgroundResource(R.drawable.weeksignin_lianxusign_bg);
            text_sign.setText("已签到");
            text_lianxusign.setVisibility(View.VISIBLE);
            text_lianxusign.setText("连续" + signListReq.getDatas().getDay() + "天");

            setSignData(signListReq.getDatas());
        }

    }

    private void initView() {
        mStepView = findViewById(R.id.step_view);
        rl_oval = findViewById(R.id.rl_oval);
        text_sign = findViewById(R.id.text_sign);
        text_lianxusign = findViewById(R.id.text_lianxusign);

    }

    private void initData() {

        //初始化模拟请求后台数据
        String reponse = "{\n" +
                "    \"datas\": {\n" +
                "        \"day\": 2,\n" +
                "        \"myPoint\": 10886,\n" +
                "        \"signLog\": {\n" +
                "            \"content\": \"每日签到\",\n" +
                "            \"createTime\": \"2019-05-28 09:31:02\",\n" +
                "            \"familyId\": \"0\",\n" +
                "            \"id\": \"951656\",\n" +
                "            \"integral\": \"9\",\n" +
                "            \"logType\": \"3\",\n" +
                "            \"orderId\": \"0\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"userId\": \"43431\"\n" +
                "        },\n" +
                "        \"signState\": true,\n" +
                "        \"userSingninList\": [\n" +
                "            {\n" +
                "                \"createTime\": \"2019-05-27 18:04:15\",\n" +
                "                \"day\": \"1\",\n" +
                "                \"familyId\": \"0\",\n" +
                "                \"id\": \"278904\",\n" +
                "                \"seriesDay\": \"1\",\n" +
                "                \"type\": \"0\",\n" +
                "                \"userId\": \"43431\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"createTime\": \"2019-05-28 09:31:02\",\n" +
                "                \"day\": \"2\",\n" +
                "                \"familyId\": \"0\",\n" +
                "                \"id\": \"278905\",\n" +
                "                \"seriesDay\": \"2\",\n" +
                "                \"type\": \"0\",\n" +
                "                \"userId\": \"43431\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"msg\": \"success!\",\n" +
                "    \"ret\": 0\n" +
                "}";

        //解析后台请求数据
        SignListReq signListReq = new Gson().fromJson(reponse, SignListReq.class);
        if (signListReq.getRet() == 0) {
            setSignData(signListReq.getDatas());
        }
    }

    /**
     * 数据处理
     *
     * @param datas
     */
    private void setSignData(SignListReq.DatasBean datas) {

        //处理已签到的数据
        //先添加已签到的日期到集合中
        if (datas.getUserSingninList().size() != 0) {
            for (int i = 0; i < datas.getUserSingninList().size(); i++) {
                //时间格式：2019-05-27 18:04:15
                String createTime = datas.getUserSingninList().get(i).getCreateTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d1 = null;
                try {
                    d1 = df.parse(createTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String timeString = df.format(d1);
                //获取日期的月、日
                String[] timeList = timeString.split(" ");
                String[] split = timeList[0].split("-");
                String month = split[1];//月
                String day = split[2];//日

                //判断是否需要显示积分图标，number表示-- 0为不显示积分，非0为显示积分
                if (datas.getSignLog() != null && datas.getUserSingninList().get(i).getCreateTime().equals(datas.getSignLog().getCreateTime())) {
                    mStepBeans.add(new StepBean(StepBean.STEP_COMPLETED, Integer.parseInt(datas.getSignLog().getIntegral()), month + "." + day));
                } else {
                    mStepBeans.add(new StepBean(StepBean.STEP_COMPLETED, 0, month + "." + day));
                }
            }
        }

        //添加未签到的数据，填充为最近一周数据
        if (mStepBeans.size() < 7) {

            //获取当前时间的月日
            Calendar now = Calendar.getInstance();
            int currentMonth = now.get(Calendar.MONTH) + 1;//当月
            int currentDay = now.get(Calendar.DAY_OF_MONTH);//当天
            String currentTime = setData(currentMonth) + "." + setData(currentDay);

            //后台有签到集合数据
            if (datas.getUserSingninList().size() != 0) {
                String createTime = datas.getUserSingninList().get(datas.getUserSingninList().size() - 1).getCreateTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d1 = null;
                try {
                    d1 = df.parse(createTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String timeString = df.format(d1);
                String[] timeList = timeString.split(" ");
                String[] split = timeList[0].split("-");
                String month = split[1];//月
                String day = split[2];//日

                for (int i = mStepBeans.size(); i < 7; i++) {
                    int parseInt = Integer.parseInt(day) + i - 1;
                    //判断累积的天数是否超过当月的总天数
                    if (parseInt <= getDayOfMonth()) {
                        String time = setData(Integer.parseInt(month)) + "." + setData(parseInt);
                        if (currentTime.equals(time)) {
                            mStepBeans.add(new StepBean(StepBean.STEP_CURRENT, 0, time));
                        } else {
                            mStepBeans.add(new StepBean(StepBean.STEP_UNDO, 0, time));
                        }
                    } else {
                        String time = setData((Integer.parseInt(month) + 1)) + "." + setData(parseInt - getDayOfMonth());
                        if (currentTime.equals(time)) {
                            mStepBeans.add(new StepBean(StepBean.STEP_CURRENT, 0, time));
                        } else {
                            mStepBeans.add(new StepBean(StepBean.STEP_UNDO, 0, time));
                        }
                    }
                }
            } else {//后台没有签到集合数据，没有的话从当天时间开始添加未来一周的日期数据
                for (int i = 0; i < 7; i++) {
                    int parseInt = currentDay + i;
                    //判断累积的天数是否超过当月的总天数
                    if (parseInt <= getDayOfMonth()) {
                        String time = setData(currentMonth) + "." + setData(parseInt);
                        if (currentTime.equals(time)) {
                            mStepBeans.add(new StepBean(StepBean.STEP_CURRENT, 0, time));
                        } else {
                            mStepBeans.add(new StepBean(StepBean.STEP_UNDO, 0, time));
                        }
                    } else {
                        String time = setData((currentMonth + 1)) + "." + setData(parseInt - getDayOfMonth());
                        if (currentTime.equals(time)) {
                            mStepBeans.add(new StepBean(StepBean.STEP_CURRENT, 0, time));
                        } else {
                            mStepBeans.add(new StepBean(StepBean.STEP_UNDO, 0, time));
                        }
                    }
                }
            }
        }

        mStepView.setStepNum(mStepBeans);
    }

    /**
     * 获取最大天数
     *
     * @return
     */
    public int getDayOfMonth() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    /**
     * 日月份处理
     *
     * @param day
     * @return
     */
    public String setData(int day) {
        String time = "";
        if (day < 10) {
            time = "0" + day;
        } else {
            time = "" + day;
        }

        return time;
    }
}
