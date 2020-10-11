package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.*;
import com.example.demo.mapper.MallUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xiong.utils.ExcelUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RefundService {

    @Autowired
    private MallUserMapper userMapper;

    public void refund(MultipartFile file) {

    }


//    public void sendMsg() {
//        List<MallUser> mallUsers = userMapper.selectList(new QueryWrapper<MallUser>().eq("role_id", "4").eq("is_del", "0"));
//        System.out.println(mallUsers.size());
//        String baseUrl = "https://####mall.meifute.com";
//
//        MallPropelNews mallPropelNews = new MallPropelNews();
//
//        mallPropelNews.setTitle("系统提醒");
//        mallPropelNews.setSubTitle("尊敬的总代，目前平台暂不支持查询自己的相关数据，如需查看自己和伙伴的进出货数据、人脉组成信息数据等，" +
//                "可通过以下方式获取：\n" +
//                "方式1：微信搜索小程序“美Mall业务报表”\n" +
//                "方式2：扫描以下图片的二维码");
//        mallPropelNews.setType("0");
//        mallPropelNews.setIsImg("3");//表示二维码
//        mallPropelNews.setImgUrl("https://prod-mall-bucket.oss-cn-shanghai.aliyuncs.com/imgupload/1569754173512-pro.jpg"); //二维码
//        mallPropelNews.setStatus("0");
//        mallPropelNews.setNewsType("4");
//        int i = 0;
//        for (MallUser p: mallUsers) {
//            mallPropelNews.setMallUserId(p.getId());
//            String s = HttpRequest.post(baseUrl + "/notify/api/implement/queue/create/new/user/news", JsonUtils.objectToJson(mallPropelNews), "Bearer 48b7e999-9efc-4a37-b0a7-5d9b7a48ba5b");
//            System.out.println(s);
//            System.out.println(++i);
//        }
//    }

    public void sendMsg() {
        List<MallUser> mallUsers = userMapper.selectList(new QueryWrapper<MallUser>().eq("status","0").eq("is_del", "0"));
        System.out.println(mallUsers.size());
        String baseUrl = "https://mall.meifute.com";

        MallPropelNews mallPropelNews = new MallPropelNews();

        mallPropelNews.setTitle("系统提醒");
        mallPropelNews.setSubTitle("尊敬的代理，您好：\n" +
                "\n" +
                "为了给各位代理提供更好的服务，我们将在今天下午5点（2019年10月24日17:00）对App进行功能和服务升级，本次升级大约将持续2小时，服务升级期间App将无法使用，由此带来不便，敬请谅解。\n");
        mallPropelNews.setType("0");
        mallPropelNews.setIsImg("1");//表示二维码
//        mallPropelNews.setImgUrl("https://prod-mall-bucket.oss-cn-shanghai.aliyuncs.com/imgupload/1569754173512-pro.jpg"); //二维码
        mallPropelNews.setStatus("0");
        mallPropelNews.setNewsType("4");
        int i = 0;
        for (MallUser p: mallUsers) {
            mallPropelNews.setMallUserId(p.getId());
            String s = HttpRequest.post(baseUrl + "/notify/api/implement/queue/create/new/user/news", JsonUtils.objectToJson(mallPropelNews), "Bearer 5a2135e8-c4ca-475a-b11c-2a0ebdc49843");
            System.out.println(s);
            System.out.println(++i);
        }
    }

    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(new Date().before(sd.parse("2019-09-27")));
//        int i=0;
//        System.out.println(++i);
//        String s = screenExpress();
//        System.out.println(s);

        String a = "[\"1220518736768045056\",\"1220594662943920128\",\"1222125247046754304\",\"1222020153363324928\",\"1222085578570784768\",\"1220976307681267712\",\"1221855963788275712\",\"1221983684686962688\"]";;
        List<String> list = JsonUtils.jsonToList(a, String.class);
        System.out.println(list);
    }

    public static String screenExpress() {
        List<String> express = new ArrayList<>();
        for (int i=0;i<3;i++) {
            express.add(i+"");
        }
        List<String> collect = express.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
//        String explain = LogisticsModeEnum.explain(collect.get(0));
        System.out.println(collect);
        return collect.get(0);
    }

}
