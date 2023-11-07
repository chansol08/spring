package org.spring.example.controller;

import org.spring.example.mapper.MemberMapper;
import org.spring.example.model.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private MemberMapper memberMapper;

    @RequestMapping("/memberList.do")
    public String memberList(Model model) {
        List<MemberVO> members = memberMapper.memberList();
        model.addAttribute("members", members);

        return "memberList";
    }

    @RequestMapping("/memberInsert.do")
    public String memberInsert(MemberVO member) {
        memberMapper.memberInsert(member);

        return "redirect:/memberList.do";
    }

    @RequestMapping("/memberRegister.do")
    public String memberRegister() {

        return "memberRegister";
    }

    @RequestMapping("/memberDelete.do")
    public String memberDelete(@RequestParam("number") int number) {
        memberMapper.memberDelete(number);

        return "redirect:/memberList.do";
    }

    @RequestMapping("/memberContent.do")
    public String memberContent(@RequestParam("number") int number, Model model) {
        MemberVO member = memberMapper.memberContent(number);
        model.addAttribute("member", member);

        return "memberContent";
    }

    @RequestMapping("/memberUpdate.do")
    public String memberUpdate(MemberVO member) {
        memberMapper.memberUpdate(member);

        return "redirect:/memberList.do";
    }

    @RequestMapping("/memberAjaxList.do")
    public @ResponseBody List<MemberVO> memberAjaxList() {
        List<MemberVO> members = memberMapper.memberList();

        return members;
    }
}
