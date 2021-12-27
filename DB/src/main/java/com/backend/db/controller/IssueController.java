package com.backend.db.controller;



import com.backend.db.bean.Tache;
import com.backend.db.bean.User;
import com.backend.db.service.impl.IssueServiceImpl;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.*;
import org.gitlab4j.api.utils.DurationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
public class IssueController {

    private GitLabApi gitLabApi;
    private String label = "STR";
    private String projectID = "30171739";
    private String accessToken = "Ty9GGY459aiQQ-s92qBb";

    @Autowired
    IssueServiceImpl issueService;


    @ResponseBody
    @PostMapping("/issue/refresh")
    public Boolean refresh(){
       this.gitLabApi = new GitLabApi("https://gitlab.com",accessToken);
       IssueFilter filter = new IssueFilter();
       List<String> ls = new ArrayList<>();
       ls.add(this.label);
       filter.setLabels(ls);
       List<Issue> issues = new ArrayList<>();
       try {
           issues = gitLabApi.getIssuesApi().getIssues(this.projectID, filter);
       }catch (GitLabApiException e){
           System.out.println(e.getMessage());
           return false;
       }
       if(issues.isEmpty()){
           return false;
       }

       for(Issue i: issues){
            Tache t = new Tache(i.getIid(),i.getTitle());
            t.setDescription(i.getDescription());
            TimeStats ts = i.getTimeStats();

           Assignee a = i.getAssignee();
           if(a != null) {
               t.setAssignee(a.getName());
           }
           if(ts.getHumanTimeEstimate() != null){
               t.setHumanTimeEstimate(""+ts.getHumanTimeEstimate());
               t.setUpdated(1);
           }

           if(issueService.getById(i.getIid()) != null){
               Tache t2 = issueService.getById(i.getIid());
               t.setHumanTimeEstimate(t2.getHumanTimeEstimate());
               t.setEditor(t2.getEditor());
               t.setTime(t2.getTime());
           }
            issueService.saveIssue(t);

       }
       return true;
    }

    @ResponseBody
    @PostMapping("/issue/valide")
    public Boolean valide(@RequestParam("id") Integer id){
        Tache t = issueService.getById(id);
        if(t.getHumanTimeEstimate() != null){
            if (t.getUpdated() == 0){
                this.gitLabApi = new GitLabApi("https://gitlab.com",accessToken);
                try {
                    gitLabApi.getIssuesApi().estimateTime(this.projectID,id,t.getHumanTimeEstimate());
                    t.setUpdated(1);
                    issueService.saveIssue(t);
                }catch (GitLabApiException e){
                    System.out.println("error2");
                    return false;
                }
                return true;
            }else{
                this.gitLabApi = new GitLabApi("https://gitlab.com",accessToken);
                try {
                    gitLabApi.getIssuesApi().estimateTime(this.projectID,id,t.getHumanTimeEstimate());
                    t.setUpdated(0);
                    issueService.saveIssue(t);
                }catch (GitLabApiException e){
                    System.out.println("error2");
                    return false;
                }
                return true;
            }
        }
        System.out.println("error2");
        return false;
    }

    @ResponseBody
    @PostMapping("/issue/setEstimateTime")
    public Boolean setHumainTimeEstimate(@RequestParam("id") Integer id,@NotEmpty @RequestParam("time") String time) throws Exception{
        return  issueService.setHumainTimeEstimate(id,time);
    }

    @ResponseBody
    @PostMapping("/issue/setEstimateTimeFirst")
    public Boolean setHumainTimeEstimate(@RequestParam("id") Integer id,@NotEmpty @RequestParam("time") String time,@NotEmpty @RequestParam("username") String username) throws Exception{
        return  issueService.setHumainTimeEstimate2(id,time,username);
    }


    @ResponseBody
    @GetMapping("/issue/all")
    public List<Tache> getAll(){
        return issueService.getAll();
    }

    @ResponseBody
    @GetMapping("/issue/bytimeorder")
    public List<Tache> bytimeorder(){
        return issueService.Issues_order();
    }

    @ResponseBody
    @GetMapping("/issue/editor")
    public List<Tache> bytimeorder(@NotEmpty @RequestParam("username") String username){
        return issueService.Issues_byusername(username);
    }

    @ResponseBody
    @GetMapping("/issue/issuelist")
    public List<Tache> getIssue(){ return issueService.Issues_non_chiffre(); }

    @ResponseBody
    @GetMapping("/issue/issueNonValid")
    public List<Tache> getIssueNonValid(){ return issueService.Issues_non_valide(); }

    @ResponseBody
    @GetMapping("/issue")
    public Tache Tachebyid(@RequestParam("id") Integer id) {
        return issueService.Issuebyid(id);
    }


    @ResponseBody
    @PostMapping("/issue/reset")
    public Boolean resetById(@RequestParam("id") Integer id){
        this.gitLabApi = new GitLabApi("https://gitlab.com",accessToken);
        try {
            gitLabApi.getIssuesApi().resetEstimatedTime(this.projectID,id);
        }catch (Exception e){
            return false;
        }

        refresh();
        return true;

    }



}
