package com.backend.db.service.impl;


import com.backend.db.bean.Tache;
import com.backend.db.bean.User;
import com.backend.db.mapper.IssueMapper;
import com.backend.db.service.IssueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gitlab4j.api.utils.DurationUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Tache> implements IssueService {

    public Tache Issuebyid(Integer id){
        Tache i= getById(id);
        return i;
    }

    public Boolean DeleteIssue(Integer id){
        return removeById(id);
    }

    public List<Tache> Issues_non_chiffre(){
        List<Tache> ls = list();
        List<Tache> res = new ArrayList<>();
        for(Tache t: ls){
            if(t.getHumanTimeEstimate() == null){
                res.add(t);
            }
        }
        return res;
    }

    public List<Tache> Issues_byusername(String username){
        List<Tache> ls = list();
        List<Tache> res = new ArrayList<>();
        for(Tache t: ls){
            if(t.getEditor() != null) {
                if (t.getEditor().equals(username)) {
                    res.add(t);
                }
            }
        }
        return res;
    }


    public List<Tache> Issues_non_valide(){
        List<Tache> ls = list();
        List<Tache> res = new ArrayList<>();
        for(Tache t: ls){
            if(t.getUpdated() == 0){
                res.add(t);
            }
        }
        return res;
    }

    public List<Tache> Issues_order(){
        List<Tache> ls = list();
        List<Tache> res = new ArrayList<>();
        for(Tache t: ls){
            if(t.getTime() != null){
                res.add(t);
            }
        }
        Collections.sort(res, new Comparator<Tache>() {

            @Override
            public int compare(Tache o1, Tache o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });

        return res;
    }



    public Boolean setHumainTimeEstimate(Integer id,String time){
        try{
            int seconds = DurationUtils.parse(time);
        }catch (IllegalArgumentException e){
            return false;
        }
        Tache t = getById(id);
        if(t.getUpdated() == 1){
            return false;
        }
        long ti = new Date().getTime();
        Timestamp timestamp = new Timestamp(ti);
        t.setTime(timestamp.toString());
        t.setHumanTimeEstimate(time);
        return saveOrUpdate(t);
    }

    public Boolean setHumainTimeEstimate2(Integer id,String time,String username){
        try{
            int seconds = DurationUtils.parse(time);
        }catch (IllegalArgumentException e){
            return false;
        }
        Tache t = getById(id);
        if(t.getUpdated() == 1){
            return false;
        }
        long ti = new Date().getTime();
        Timestamp timestamp = new Timestamp(ti);
        t.setTime(timestamp.toString());
        t.setHumanTimeEstimate(time);
        t.setEditor(username);
        return saveOrUpdate(t);
    }


    public Boolean saveIssue(Tache issue){
        Boolean b = saveOrUpdate(issue);
        if (b) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public List<Tache> getAll(){
        return list();
    }


}
