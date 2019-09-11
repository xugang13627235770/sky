package com.example.springboottest1.service;

import com.example.springboottest1.entity.MyPage;
import com.example.springboottest1.entity.ZyPeople;
import com.example.springboottest1.entity.ZyczPeople;
import com.example.springboottest1.mapper.ZyMapper;
import com.example.springboottest1.mapper.ZyczMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * 类Userservice的实现描述：TODO 类实现描述 
 * @author Administrator 2019/3/25 15:20
 */
@Service
public class ZyPeopleService {

    @Autowired
    private ZyczMapper zyczMapper;

    /**
     * 注入dao
     */
    @Autowired
    private ZyMapper zyMapper;

    //用户登录
    public ZyPeople getZyPeople(String sfzh){
        ZyPeople zyPeople = zyMapper.getZyPeople(sfzh);
        return zyPeople;
    }

    public void processPhoto() throws IOException {
        int count = zyMapper.getZyPeopleCount();
        int pageSize = 10;
        int num = count/pageSize+1;
        for(int i=0;i<num;i++){
            MyPage myPage = new MyPage();
            myPage.setPageSize(pageSize);
            myPage.setCurrentPage(i+1);
            List<ZyPeople>  zyPeopleList =  zyMapper.getAllZyPeople(myPage);
            if(CollectionUtils.isEmpty(zyPeopleList)){
                continue;
            }

            Map<String,ZyPeople> zyPeopleMap = new HashMap<>();
            List<String> sfzhList = new ArrayList<>();
            try {
                zyPeopleMap = zyPeopleList.stream().collect(toMap(zyPeople->zyPeople.getSfzh(), zyPeople->zyPeople));
                sfzhList = zyPeopleList.stream().map(ZyPeople::getSfzh).collect(toList());
            }catch (Exception e){
                getRepeat(zyPeopleList);
            }

            if(CollectionUtils.isEmpty(sfzhList) || CollectionUtils.isEmpty(zyPeopleMap)){
                continue;
            }
            List<ZyczPeople> zyczPeopleList = zyczMapper.getZyczPeopleBysfzh(sfzhList);
            getDiffrent(zyPeopleList,zyczPeopleList);
            if(CollectionUtils.isEmpty(zyczPeopleList)){
                continue;
            }

            for(int j=0;j<zyczPeopleList.size();j++){
                ZyczPeople zyczPeople = zyczPeopleList.get(j);
                if(StringUtils.isEmpty(zyczPeople.getPid()) || zyczPeople.getImage() == null){
                    System.out.println("图片为空："+zyczPeopleList.get(j).getPid()+";");
                    continue;
                }
                ZyPeople tempZyPeople = zyPeopleMap.get(zyczPeople.getPid());
                String name = tempZyPeople.getXm()+"_"+zyczPeople.getPid()+"_"+tempZyPeople.getLb();

                String path = "D:/download/";
                File dir = new File(path);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                String filepath = path + name + ".jpg";

                try {
                    File ff = new File(filepath);
                    FileOutputStream fops = new FileOutputStream(ff);
                    fops.write(zyczPeople.getImage());
                    fops.close();
                } catch (Exception e) {
                    System.out.println("IO导出失败："+zyczPeopleList.get(j).getPid()+";");
                }
            }
        }
    }

    public void processSinglePhoto() throws IOException {
        int count = zyMapper.getZyPeopleCount();
        int pageSize = 10;
        int num = count/pageSize+1;
        for(int i=0;i<num;i++){
            MyPage myPage = new MyPage();
            myPage.setPageSize(pageSize);
            myPage.setCurrentPage(i+1);
            List<ZyPeople>  zyPeopleList =  zyMapper.getAllZyPeople(myPage);
            if(CollectionUtils.isEmpty(zyPeopleList)){
                continue;
            }

            for(int j=0;j<zyPeopleList.size();j++){
                ZyPeople zyPeople = zyPeopleList.get(j);
                if(StringUtils.isEmpty(zyPeople.getSfzh())){
                    System.out.println("zyPeople身份证号码为空："+zyPeople.getId());
                    continue;
                }

                ZyczPeople zyczPeople = zyczMapper.getZyczPeople(zyPeople.getSfzh());
                if(zyczPeople == null){
                    System.out.println("未找到数据："+ zyPeople.getSfzh());
                    continue;
                }

                if(zyczPeople.getImage() == null){
                    System.out.println("图片为空："+ zyPeople.getSfzh());
                    continue;
                }

                String name = zyPeople.getXm()+"_"+zyczPeople.getPid()+"_"+zyPeople.getLb();

                String path = "D:/download/";
                File dir = new File(path);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                String filepath = path + name + ".jpg";

                try {
                    File ff = new File(filepath);
                    FileOutputStream fops = new FileOutputStream(ff);
                    fops.write(zyczPeople.getImage());
                    fops.close();
                } catch (Exception e) {
                    System.out.println("IO导出失败："+zyczPeople.getPid()+";");
                }
            }
        }
    }

    private void getDiffrent(List<ZyPeople> zyPeopleList, List<ZyczPeople> zyczPeopleList) {
        List<String> list1 = zyPeopleList.stream().map(ZyPeople::getSfzh).collect(toList());
        if(CollectionUtils.isEmpty(zyczPeopleList)){
            System.out.println("未找到人员："+list1);
            return;
        }

        List<String> list2 = zyczPeopleList.stream().map(ZyczPeople::getPid).collect(toList());
        List<String> diff = new ArrayList<String>();
        for(String str:list1)
        {
            if(!list2.contains(str))
            {
                diff.add(str);
            }
        }
        if(!CollectionUtils.isEmpty(diff)){
            System.out.println("未找到人员："+diff);
        }
    }

    private void getRepeat( List<ZyPeople> zyPeopleList){
       List<String> list = zyPeopleList.stream().map(ZyPeople::getSfzh).collect(toList());
        Map<String,Integer> map = new HashMap<>();
        for(String str:list){
            Integer i = 1; //定义一个计数器，用来记录重复数据的个数
            if(map.get(str) != null){
                i=map.get(str)+1;
            }
            map.put(str,i);
        }

        System.out.print("重复的数据为：");
        for(String s:map.keySet()){
            if(map.get(s) > 1){
                System.out.print(s+" ");
            }
        }
    }

    public int insertZyczPeople() throws Exception {
        ZyczPeople zyczPeople = new ZyczPeople();
        zyczPeople.setId("6");
        zyczPeople.setPid("785554");
        zyczPeople.setImage(getBytes("D:\\a.jpg"));
        return zyczMapper.insertZyczPeople(zyczPeople);
    }

    public byte[] getBytes(String filePath){
        File f = new File(filePath);
        BufferedImage bi;
        ByteArrayOutputStream baos;
        try {
            bi = ImageIO.read(f);
            baos= new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
