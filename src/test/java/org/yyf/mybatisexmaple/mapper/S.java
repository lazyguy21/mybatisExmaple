package org.yyf.mybatisexmaple.mapper;

import org.apache.ibatis.jdbc.SQL;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

public class S {
  public static void main(String[] args) {
    List<Long> noteIds = Lists.newArrayList(1l,2l,3l);
    String d = new SQL()
        .SELECT("lp_id noteId, story_id storyId, aid investorId")
        .FROM("dr_lpd_storys")
        .WHERE("lp_id in (" + Joiner.on(",").join(noteIds) + ")").toString();
    System.out.println(d);
  }
}
